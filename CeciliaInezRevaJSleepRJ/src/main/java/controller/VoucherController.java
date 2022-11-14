package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController implements BasicGetController<Voucher> {

    public static @JsonAutowired(value = Voucher.class , filepath = "src\\json\\voucher.json") JsonTable<Voucher> voucherTable;

    @Override
    public JsonTable<Voucher> getJsonTable() {
        return voucherTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        //return null;
//        for (Voucher voucher : voucherTable){
//            if((voucher.id == id)){
//                return voucher.isUsed();
//            }
//        }
        Voucher tempVoucher = Algorithm.<Voucher>find(voucherTable, temp -> temp.id == id);
        if(tempVoucher == null){
            return false;
        }
        return tempVoucher.isUsed();
    }


    @GetMapping("/{id}/canApply")
    boolean canApply(
            @PathVariable int id,
            @RequestParam double price
    ){
        //return null;
        Price priceCheck = new Price(price);
        for (Voucher voucher : voucherTable){
            if((voucher.id == id)){
                return voucher.canApply(priceCheck);
            }
        }
        return false;
    }

    @GetMapping("/getAvailable")
    List<Voucher> getAvailable(
            @PathVariable int page,
            @RequestParam int pageSize
    )
    {
        return Algorithm.<Voucher>paginate(voucherTable, page, pageSize, temp -> !temp.isUsed());
    }
}
