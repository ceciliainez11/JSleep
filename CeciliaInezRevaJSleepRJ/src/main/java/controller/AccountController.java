package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.Account;
import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.Predicate;
import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.Renter;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+\\\\.[A-Za-z.]+[^.]$";
    public static final String REGEX_PASSWORD = "((?=\\S+$)(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,})";
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);

    public static @JsonAutowired(value = Account.class , filepath = "src\\json\\account.json") JsonTable<Account> accountTable;


    public JsonTable getJsonTable() {
        return accountTable;
    }
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        Account tempAccount = new Account(name, email, password);
        if(tempAccount.name.isBlank() || !tempAccount.validate()){
            return null;
        }

        for (Account account : accountTable){
            if(account.email.equals(tempAccount.email)){
                System.out.println("GAGALLLLL");
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
            tempAccount.password = generatedPassword;
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        accountTable.add(tempAccount);
//        accountTable.add(new Account(name, email, generatedPassword));
//        return new Account(name, email, generatedPassword);
        return tempAccount;
    }



    @PostMapping("/login")
    Account login(
            @RequestParam String email,
            @RequestParam String password
    ){
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
        String hashedPassword = generatedPassword;
        return Algorithm.<Account>find(accountTable, temp -> (temp.email.equals(email)) && temp.password.equals(hashedPassword));

    }

    @PostMapping("/{id}/registerStore")
    Renter registerRenter(
            @PathVariable int id,
            @RequestParam String username,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        //return null;
        for (Account account : accountTable){
            if((account.id == id) && (account.renter == null)){
                account.renter = new Renter(username, phoneNumber, address);
                return(account.renter);
            }

        }
        return null;
    }


    @PostMapping("/{id}/topUp")
    boolean topUp(
            @PathVariable int id,
            @RequestParam double balance
    ) {

        for(Account singleAccount : accountTable) {
            if(singleAccount.id == id) {
                singleAccount.balance += balance;
                return true;
            }
        }
        return false;

    }


//    @GetMapping("/{id}")
//    public T getById(@PathVariable int id) { return "account id " + id + " not found!"; }

    @Override
    public List getPage(int page, int pageSize) {
        return null;
    }
}
