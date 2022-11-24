package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.Renter;
import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.Account;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])*[a-zA-Z\\d]{8,}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]@[A-Za-z.-]\\.[a-z]$";

    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);

    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public static @JsonAutowired(value = Account.class, filepath = "src\\json\\account.json")
    JsonTable<Account> accountTable;

    //valid
    @PostMapping("/login")
    Account login(
            @RequestParam String email, @RequestParam String password
    ) {
        String generatedPassword = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));}

            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();}

        String hashedPassword = generatedPassword;

        return Algorithm.<Account>find(accountTable, temp -> (temp.email.equals(email))
                && temp.password.equals(hashedPassword));

    }
    @GetMapping
    String index() {
        return "account page";
    }

    //valid
    @PostMapping("/register")
    Account register
    (
            @RequestParam String name, @RequestParam String email,
            @RequestParam String password
    ) {
        for (Account acc : accountTable) {
            if (acc.validate() || acc.email.equals(email) || (name.isBlank()))
            {
                return null;
            }
        }
        String genPass = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            genPass = sb.toString();

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        accountTable.add(new Account(name, email, genPass));
        return new Account(name, email, genPass);
    }
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    //unvalid
    @PostMapping("/{id}/topUp")
    boolean topUp(@PathVariable int id, @RequestParam double balance)
    {
        for (Account singleAccount : accountTable)
        {

            if (singleAccount.id == id)
            {
                singleAccount.balance += balance;
                return true;
            }
        }
        return false;
    }

    //valid
    @PostMapping("/{id}/registerStore")
    Renter registerRenter(
            @PathVariable int id, @RequestParam String username, @RequestParam String address, @RequestParam String phoneNumber
    ) {

        for (Account account : accountTable)
        {
            if ((account.id == id) && (account.renter == null))
            {
                account.renter = new Renter(username,
                        phoneNumber, address);

                return (account.renter);
            }

        }
        return null;
    }
    @Override
    public List getPage(int page,
                        int pageSize)
    {
        return null;
    }
}