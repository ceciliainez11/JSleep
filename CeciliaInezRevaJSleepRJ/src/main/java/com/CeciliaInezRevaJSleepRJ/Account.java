package com.CeciliaInezRevaJSleepRJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;


/**
 * A class representing an account, with a name, email, password, and balance.
 * The email and password must match the provided regular expressions for
 * validation to succeed.
 *
 * @author Cecilia Inez Reva
 *
 * @extends Serializable
 * The Account class implements the Serializable interface, allowing
 * instances of the class to be written to and read from streams.
 */
public class Account extends Serializable
{
    // instance variables
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z.]+[^.]$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public String name;
    public String email;
    /**
     * The balance of the account.
     */
    public double balance;
    public String password;
    public Renter renter;
    /**
     * Constructor for objects of class Account
     */
    public Account(String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean validate()
    {
        Pattern patternEmail = Pattern.compile(this.REGEX_EMAIL);
        Matcher emailMatcher = patternEmail.matcher(this.email);
        boolean matchFoundEmail = emailMatcher.find();
        Pattern patternPassword = Pattern.compile(this.REGEX_PASSWORD);
        Matcher passwordMatcher = patternPassword.matcher(this.password);
        boolean matchFoundPassword = passwordMatcher.find();

        if (matchFoundEmail && matchFoundPassword)
            return true;
        else
            return false;
    }

    public String toString()
    {
        return "\n\nID: " + this.id + "\nName: " + name + "\nEmail: " + email + "\nPassword: " + password;
    }

    public boolean read(String Content){
        return false;
    }

    public Object write(){
        return null;
    }
}