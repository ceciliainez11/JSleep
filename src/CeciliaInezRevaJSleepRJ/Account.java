package CeciliaInezRevaJSleepRJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    // instance variables
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+(?:\\\\.[\\w!#$%&’*+/=?`{|}~^]+)*@(?:[a-zA-Z]+\\.)+[a-zA-Z]{2,6}$";
    public static final String REGEX_PASSWORD = "((?=\\S+$)(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,})";
    public String name;
    public String email;
    public String password;

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