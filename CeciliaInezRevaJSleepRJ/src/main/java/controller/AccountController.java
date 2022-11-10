package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.dbjson.*;
//import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.*;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



@RestController
@RequestMapping("/account")

public class AccountController implements BasicGetController <Account> {
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z.]+[^.]$";
    public static final String REGEX_PASSWORD =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(value = Account.class, filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\CeciliaInezRevaJSleepRJ\\src\\account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index(){
        return "account page";
    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @PostMapping("/login")
    Account login(@RequestParam String email, @RequestParam String password) {
        for (Account acc : getJsonTable()) {
            if (acc.email.equals(email) && acc.password.equals(password)) return acc;
        }
        return null;
    }

    @PostMapping("/register")
    Account register (@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        for (Account account : accountTable){
            if(account.email.equals(email) || (name.isBlank()) || account.validate()){
                return null;
            }
        }

        String generatedPassword = null;

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        accountTable.add(new Account(name, email, generatedPassword));
        return new Account(name, email, generatedPassword);
    }

    @PostMapping("/{id}/registerRenter")
    Renter registerRenter(@PathVariable int id, @RequestParam String username, @RequestParam String address, @RequestParam String phoneNumber) {
        Predicate<Account> predicate = obj -> obj.id == id;
        Account findAcc = Algorithm.find(getJsonTable(), predicate);
        if (findAcc.renter == null) {
            Renter newRenter = new Renter(username, address, phoneNumber);
            findAcc.renter = newRenter;
            return newRenter;
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp (@PathVariable int id, @RequestParam double balance) {
        Predicate<Account> predicate = obj -> obj.id == id;
        Account findAcc = Algorithm.find(getJsonTable(), predicate);
        if (findAcc != null) {
            findAcc.balance += balance;
            return true;
        }
        return false;
    }
}