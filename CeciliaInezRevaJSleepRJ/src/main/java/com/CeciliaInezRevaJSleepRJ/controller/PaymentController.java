package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.Payment;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/payment")

public class PaymentController implements BasicGetController<Payment> {
    public static @JsonAutowired(value = Payment.class , filepath = "src\\json\\payment.json")
    JsonTable<Payment> paymentTable;

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int roomId,
            @RequestParam String from, @RequestParam String to)throws ParseException {

        Account buyer = Algorithm.<Account>find(AccountController.accountTable, temp -> temp.id == buyerId);
        Room room = Algorithm.<Room>find(RoomController.roomTable, temp -> temp.id == roomId);
        System.out.println(buyer.id);
        System.out.println(room.id);
        double price;
        price = room.price.price;

        System.out.println("Harga kamar : " + price);
        System.out.println("Saldo : " + buyer.balance);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf.parse(from);
        Date toDate = sdf.parse(to);

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

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment paymentCheck = Algorithm.<Payment>find(paymentTable, paymentTemp -> paymentTemp.id == id);
        //Cek Prasyarat
        if(paymentCheck == null || paymentCheck.status != Invoice.PaymentStatus.WAITING){
            return false;
        } else{
            Room room = Algorithm.<Room>find(RoomController.roomTable, roomTemp -> roomTemp.id == paymentCheck.getRoomId());
            Account buyerCancel = Algorithm.<Account>find(AccountController.accountTable, accountTemp -> accountTemp.id == paymentCheck.buyerId);

            paymentCheck.status = Invoice.PaymentStatus.FAILED;
            buyerCancel.balance += room.price.price;
            return true;
        }
    }

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id) {
        Payment paymentCheck = Algorithm.<Payment>find(paymentTable, paymentTemp -> paymentTemp.id == id);
        System.out.println(paymentCheck.id);
        System.out.println(paymentCheck.status);
        if(paymentCheck == null || paymentCheck.status != Invoice.PaymentStatus.WAITING){
            return false;
        } else{
            paymentCheck.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
    }

    @PostMapping("/submit")
    public boolean submit(@RequestParam int id) {
        return false;
    }
}
