package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.controller.AccountController;
import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.controller.RoomController;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a controller for a payment.
 * It provides methods create and update a payment,
 *
 * @author Cecilia Inez Reva
 *
 * @implements BasicGetController<Payment>
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    public static @JsonAutowired(value = Payment.class , filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\JSleep\\JSleep\\src\\json\\payment.json") JsonTable<Payment> paymentTable;

    /**
     * Returns the table of payments.
     *
     * @return the table of payments
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Creates a new payment with the specified details.
     * The payment's status is initially set to WAITING.
     *
     * @param buyerId the ID of the buyer
     * @param renterId the ID of the renter
     * @param roomId the ID of the room
     * @param from the start date of the reservation
     * @param to the end date of the reservation
     *
     * @return the newly created payment
     *
     * @throws ParseException if the dates are in an invalid format
     */
    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int roomId,
                          @RequestParam String from, @RequestParam String to)throws ParseException {


        Account buyer = Algorithm.<Account>find(AccountController.accountTable, temp -> temp.id == buyerId);
        Room room = Algorithm.<Room>find(RoomController.roomTable, temp -> temp.id == roomId);
        System.out.println(buyer.id);
        System.out.println(room.id);
        double price, priceTest;
        price = room.price.price;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf.parse(from);
        Date toDate = sdf.parse(to);

        long timeDiff = Math.abs(toDate.getTime() - fromDate.getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        price = room.price.price * daysDiff;

        System.out.println("Days Diff = " + daysDiff);
        System.out.println("Harga Kamar Asli : " + price);
        System.out.println("Saldo : " + buyer.balance);
        System.out.println(fromDate);
        System.out.println(toDate);
        System.out.println(Payment.availability(fromDate, toDate, room));

        if (room == null || buyer == null || buyer.balance <= price || !Payment.availability(fromDate, toDate, room)) {
            System.out.println("GAGAGALLALALALL");
            return null;
        }
        Payment paymentCheck = new Payment(buyerId, renterId, roomId, fromDate, toDate);


        buyer.balance -= price;

        paymentCheck.status = Invoice.PaymentStatus.WAITING;
        Payment.makeBooking(fromDate, toDate, room);

        paymentTable.add(paymentCheck);
        return paymentCheck;
    }

    /**
     * Cancels the payment with the specified ID.
     * The payment must have a status of WAITING.
     *
     * @param id the ID of the payment to cancel
     *
     * @return true if the payment was successfully cancelled, or false if the payment was not found or had an invalid status
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel(
            @PathVariable int id
    ) {
        Payment paymentCheck = Algorithm.<Payment>find(paymentTable, paymentTemp -> paymentTemp.id == id);
        //Cek Prasyarat
        if(paymentCheck == null || paymentCheck.status != Invoice.PaymentStatus.WAITING){
            return false;
        }
        else{
            Room room = Algorithm.<Room>find(RoomController.roomTable, roomTemp -> roomTemp.id == paymentCheck.getRoomId());
            Account buyerCancel = Algorithm.<Account>find(AccountController.accountTable, accountTemp -> accountTemp.id == paymentCheck.buyerId);

            long timeDiff = Math.abs(paymentCheck.to.getTime() - paymentCheck.from.getTime());
            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

            paymentCheck.status = Invoice.PaymentStatus.FAILED;
            buyerCancel.balance += room.price.price * daysDiff;
            System.out.println(buyerCancel.balance);
            return true;
        }
    }

    /**
     * Accepts the payment with the specified ID.
     * The payment must have a status of WAITING.
     *
     * @param id the ID of the payment to accept
     *
     * @return true if the payment was successfully accepted, or false if the payment was not found or had an invalid status
     */
    @PostMapping("/{id}/accept")
    public boolean accept(
            @PathVariable int id
    ) {
        Payment paymentCheck = Algorithm.<Payment>find(paymentTable, paymentTemp -> paymentTemp.id == id);
        System.out.println(paymentCheck.id);
        System.out.println(paymentCheck.status);
        if(paymentCheck == null || paymentCheck.status != Invoice.PaymentStatus.WAITING){
            return false;
        }
        else{
            paymentCheck.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
    }

    /**
     * Returns the payment with the given buyer and renter IDs and room ID.
     * If the payment has failed, the method will attempt to return the next payment in the sequence.
     * If no payment is found, the method returns null.
     *
     * @param buyerId the ID of the buyer
     * @param renterId the ID of the renter
     * @param roomId the ID of the room
     * @return the payment with the given IDs, or null if no such payment exists
     */
    @GetMapping("/getPayment")
    public Payment getPayment (@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int roomId) {
        Payment result, nextResult;
        int index;
        result = Algorithm.<Payment>find(paymentTable, pred -> pred.buyerId == buyerId && pred.renterId == renterId && pred.getRoomId() == roomId);
        if(result == null){
            return result;
        }
        index = result.id;
        while(result.status == Invoice.PaymentStatus.FAILED){
            index++;
            int finalIndex = index;
            nextResult = Algorithm.<Payment>find(paymentTable, pred -> pred.id == finalIndex && pred.buyerId == buyerId && pred.renterId == renterId && pred.getRoomId() == roomId);
            if (nextResult == null){
                break;
            }
            result = nextResult;
        }
        return result;
    }

}
