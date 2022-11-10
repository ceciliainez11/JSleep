package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.Voucher;
import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.Price;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/voucher")

public class VoucherController implements BasicGetController<Voucher>{

    @JsonAutowired(value = Voucher.class, filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\CeciliaInezRevaJSleepRJ\\src\\account.json")
    public static JsonTable<Voucher> voucherTable;

    @Override
    public JsonTable<Voucher> getJsonTable() {
        return voucherTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        Voucher newVoucher = Algorithm.<Voucher>find(voucherTable, temp -> temp.id == id);
        if(newVoucher == null){
            return false;
        } return newVoucher.isUsed();
    }

    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price){
        Price newPrice = new Price(price);
        for (Voucher newVoucher : voucherTable){
            if((newVoucher.id == id)){
                return newVoucher.canApply(newPrice);
            }
        } return false;
    }

    @GetMapping("/getAvailable")
    List<Voucher> getAvailable(@PathVariable int page, @RequestParam int pageSize){
        return Algorithm.<Voucher>paginate(voucherTable, page, pageSize, temp -> !temp.isUsed());
    }
}
