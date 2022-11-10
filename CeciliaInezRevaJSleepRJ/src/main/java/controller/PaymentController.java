package com.CeciliaInezRevaJSleepRJ.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.*;
import com.CeciliaInezRevaJSleepRJ.Account;
import com.CeciliaInezRevaJSleepRJ.Payment;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.controller.AccountController;
import com.CeciliaInezRevaJSleepRJ.controller.RoomController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController <Payment> {
    @JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Payment> paymentTable;

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int roomId,
                          @RequestParam String from, @RequestParam String to) throws ParseException {
        double price;
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, pred -> pred.id == buyerId);
        Room room = Algorithm.<Room>find(RoomController.roomTable, pred -> pred.id == roomId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf.parse(from);
        Date toDate = sdf.parse(to);

        price = room.price.price;

        if (buyer == null||room == null||buyer.balance <= price||!Payment.availability (fromDate, toDate, room)) {
            return null;
        }
        return null;
//        Payment newPayment = new Payment(Date buyerId, Date renterId, Date roomId, Date fromDate, Date toDate);
//        buyer.balance -= price;
//        newPayment.status = Invoice.PaymentStatus.WAITING;
//        Payment.makeBooking(fromDate, toDate, room);
//        paymentTable.add(newPayment);
//        return newPayment;
    }

    @PostMapping("/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment payment = Algorithm.<Payment> find (paymentTable, payment1 -> payment1.id == id);

        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return false;
        } else {
            Account buyer = Algorithm.<Account>find (AccountController.accountTable, account -> account.id == payment.buyerId);
            Room room = Algorithm.<Room>find(RoomController.roomTable, room1 -> room1.id == payment.getRoomId());
            payment.status = Invoice.PaymentStatus.FAILED;
            buyer.balance += room.price.price;
            return true;
        }
    }

    @PostMapping("/accept")
    public boolean accept (@PathVariable int id) {
        Payment payment = Algorithm.<Payment> find (paymentTable, payment1 -> payment1.id == id);

        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return false;
        } else {
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
    }

    @PostMapping("/submit")
    public boolean submit (@RequestParam int id) {
        return false;
    }
}