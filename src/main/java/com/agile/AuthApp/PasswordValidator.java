package com.agile.authapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements IValidator {

    private static final String ALLOWEDCHARS = ".*[a-zA-Z]{2}.*";
    private static final String ALLOWEDNUMBERS = ".*[0-9]{2}.*";
    public static final  int MINLENGTH = 8;
    public static final  int MAXLENGTH = 16;
    private String password;

    public boolean isValid(String password) {
        this.password = password;
        return notNull() && 
        hasMinLength() && 
        withinMaxLength() && 
        hasAllowedChars() && 
        hasAllowedNumbers();
    }

    private boolean notNull(){
        return password != null;
    }
    
    private boolean hasAllowedChars(){
        Pattern pattern = Pattern.compile(ALLOWEDCHARS);
        Matcher match = pattern.matcher(password);
        return match.matches();
    }

    private boolean hasAllowedNumbers(){
        Pattern pattern = Pattern.compile(ALLOWEDNUMBERS);
        Matcher match = pattern.matcher(password);
        return match.matches();
    }

    private boolean hasMinLength(){
        return password.length() >= MINLENGTH;
    }

    private boolean withinMaxLength(){
        return password.length() <= MAXLENGTH;
    }
}
