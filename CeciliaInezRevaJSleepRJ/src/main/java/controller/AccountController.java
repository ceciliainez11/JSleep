package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.Account;
import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.Renter;
import org.springframework.web.bind.annotation.*;

import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.Renter;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")

public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z.]+[^.]$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @JsonAutowired(filepath = "a/b/account.json", value = Account.class)
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/login")
    Account login (@RequestParam String email, @RequestParam String password) {
        Account temp = Algorithm.<Account>find(accountTable,pred -> email.equals(pred.email) && password.equals(pred.password));
        return temp;
    }

    @PostMapping("/register")
    Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password)
    {
        Matcher matcheremail = REGEX_PATTERN_EMAIL.matcher(email);
        boolean emailstatus = matcheremail.find();
        Matcher matcherpassword = REGEX_PATTERN_PASSWORD.matcher(password);
        boolean passwordstatus = matcherpassword.find();

        if(passwordstatus && emailstatus && !name.isBlank()){
            return new Account(name, email, password);
        }
        else{
            return null;
        }
    }

    @PostMapping("/{id}/registerRenter")
    public Renter registerRenter(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber)
    {
        for (Account account : accountTable){
            if((account.id == id) && (account.renter == null)){
                return(new Renter(name, phoneNumber, address));
            }
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    public boolean topUp (@PathVariable int id, @RequestParam double balance) {
        balance = 0;
        for(Account account : accountTable){
        if (account.id == id) {
            balance += balance;
            return true;
        }
        } return false;
    }
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }
}
