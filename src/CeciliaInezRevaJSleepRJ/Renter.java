package CeciliaInezRevaJSleepRJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable {
    public static final String REGEX_NAME = "^[A-Z]{1}\\w{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    // instance variables - replace the example below with your own
    public String phoneNumber;
    public String address;
    public String username;

    /**
     * Constructor for objects of class Renter
     */
    public Renter(String username, String phoneNumber, String address)
    {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
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

