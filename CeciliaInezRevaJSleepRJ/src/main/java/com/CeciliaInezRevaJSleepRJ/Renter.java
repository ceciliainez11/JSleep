package com.CeciliaInezRevaJSleepRJ;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 The Renter class represents a renter of a property.

 @author Cecilia Inez Reva

 @version 1.0
 */
public class Renter extends Serializable {
    public static final String REGEX_NAME = "^[A-Z]{1}\\w{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    // instance variables - replace the example below with your own
    public String phoneNumber;
    public String address;
    public String username;

    /**

     Constructs a new Renter with the specified username, phone number and address.
     @param username the username of the renter
     @param phoneNumber the phone number of the renter
     @param address the address of the renter
     */
    public Renter(String username, String phoneNumber, String address)
    {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**

     Validates the renter's username and phone number.

     @return true if the username and phone number are valid, false otherwise
     */
    public boolean validate()
    {
        Pattern patternName = Pattern.compile(this.REGEX_NAME);
        Matcher nameMatcher = patternName.matcher(this.username);
        boolean matchFoundName = nameMatcher.find();
        Pattern patternPhone = Pattern.compile(this.REGEX_PHONE);
        Matcher phoneMatcher = patternPhone.matcher(this.phoneNumber);
        boolean matchFoundNum = phoneMatcher.find();

        if (matchFoundNum && matchFoundName)
            return true;
        else
            return false;
    }
}

