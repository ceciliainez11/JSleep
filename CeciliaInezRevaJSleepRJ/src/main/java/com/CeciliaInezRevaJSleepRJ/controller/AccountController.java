package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;
import java.util.regex.Matcher;
import com.CeciliaInezRevaJSleepRJ.Renter;
import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.Account;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * The `AccountController` class provides methods for handling requests for account-related actions, such as
 * registration, login, and adding funds to an account.
 *
 * @RestController indicates that this class is a controller that handles HTTP requests.
 * @RequestMapping("/account") specifies the base URL for requests handled by this controller.
 */
@RestController
@RequestMapping("/account")

/** This class implements the `BasicGetController` interface, which requires the implementation of the `getPage`
 * method. This method is not used in this class.
 */
public class AccountController implements BasicGetController<Account> {
    /** A regular expression for a valid email. */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9 ][a-zA-Z0-9]+@[a-zA-Z.]+?\\\\.[a-zA-Z]+?$";
    /** A regular expression for a valid password. */
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{8,}$";

    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);

    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public static @JsonAutowired(value = Account.class, filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\JSleep\\JSleep\\src\\json\\account.json")
    JsonTable<Account> accountTable;

    /**
     * Registers a new account with the specified name, email, and password.
     * The email and password must match the regular expressions for a valid email address
     * and password, respectively. The name cannot be blank.
     *
     * @param name the name of the new account
     * @param email the email address of the new account
     * @param password the password of the new account
     *
     * @return the newly registered account if the registration is successful, or null if the registration fails
     */
    @PostMapping("/register")
    Account register (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        for (Account acc : accountTable) {
            if (acc.validate() || acc.email.equals(email) || (name.isBlank())){
                return null;
            }
        }
        String genPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            genPass = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        accountTable.add(new Account(name, email, genPass));
        return new Account(name, email, genPass);
    }

    /**
     * Logs in an account with the specified email address and password.
     *
     * @param email the email address of the account
     * @param password the password of the account
     *
     * @return the account if the login is successful, or null if the login fails
     */
    @PostMapping("/login")
    Account login(@RequestParam String email, @RequestParam String password) {
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

    /**
     * Adds the specified amount to the balance of the account with the specified ID.
     *
     * @param id the ID of the account
     * @param balance the amount to add to the account balance
     *
     * @return true if the balance was successfully added, or false if the account with the specified ID was not found
     */
    @PostMapping("/{id}/topUp")
    boolean topUp(@PathVariable int id, @RequestParam double balance)    {
        for (Account singleAccount : accountTable){
            if (singleAccount.id == id){
                singleAccount.balance += balance;
                return true;
            }
        } return false;
    }

    /**
     * Registers a new renter for the account with the specified ID.
     * The renter must not already be registered for the account.
     *
     * @param id the ID of the account for which the renter is being registered
     * @param username the username of the new renter
     * @param address the address of the new renter
     * @param phoneNumber the phone number of the new renter
     *
     * @return the newly registered renter if the registration is successful, or null if the registration fails
     */
    @PostMapping("/{id}/registerRenter")
    Renter registerRenter(@PathVariable int id, @RequestParam String username,
                          @RequestParam String address, @RequestParam String phoneNumber) {
        for (Account account : accountTable){
            if ((account.id == id) && (account.renter == null)){
                account.renter = new Renter(username,phoneNumber, address);
                return (account.renter);
            }
        } return null;
    }


    @GetMapping
    String index() {
        return "account page";
    }

    @Override
    public List getPage(int page, int pageSize) {
        return null;
    }

    /**
     * Returns the table of accounts.
     *
     * @return the table of accounts
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }
}