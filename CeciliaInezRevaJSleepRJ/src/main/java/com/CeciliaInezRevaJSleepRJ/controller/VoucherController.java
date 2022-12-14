package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This class represents a controller for a voucher.
 * It provides methods create and update a voucher,
 *
 * @author Cecilia Inez Reva
 *
 * @implements BasicGetController<Voucher>
 */
@RestController
@RequestMapping("/voucher")

public class VoucherController implements BasicGetController<Voucher> {
    public static @JsonAutowired(value = Voucher.class , filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\JSleep\\JSleep\\src\\json\\voucher.json") JsonTable<Voucher> voucherTable;

    /**
     * Returns the table of voucher.
     *
     * @return the table of voucher
     */
    @Override
    public JsonTable<Voucher> getJsonTable() {
        return voucherTable;
    }

    /**
     * Determines whether the voucher with the specified ID has been used.
     *
     * @param id the ID of the voucher
     *
     * @return true if the voucher has been used, false otherwise
     */
    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        Voucher tempVoucher = Algorithm.<Voucher>find(voucherTable, temp -> temp.id == id);
        if(tempVoucher == null){
            return false;
        } return tempVoucher.isUsed();
    }

    /**
     * Determines whether the voucher with the specified ID can be applied to the specified price.
     *
     * @param id the ID of the voucher
     * @param price the price to which the voucher should be applied
     *
     * @return true if the voucher can be applied, false otherwise
     */
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

    /**
     * Returns a paginated list of all available vouchers.
     *
     * @param page the page number to return
     * @param pageSize the number of items per page
     *
     * @return a paginated list of all available vouchers
     */
    @GetMapping("/getAvailable")
    List<Voucher> getAvailable(@PathVariable int page, @RequestParam int pageSize) {
        return Algorithm.<Voucher>paginate(voucherTable, page, pageSize, temp -> !temp.isUsed());
    }
}
