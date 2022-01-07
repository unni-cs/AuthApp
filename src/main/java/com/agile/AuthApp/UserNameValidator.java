package com.agile.authapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator implements IValidator {

    private static final int MINLENGTH = 1;
    private static final  int MAXLENGTH = 11;
    private static final  String ALLOWEDALPHABETS = "^[a-zA-Z]*$";
    private String userName;

    public boolean isValid(String userName) {
        this.userName = userName;
        return notNull() && hasMinLength() && withinMaxLength() && hasOnlyAlphabets();
    }

    private boolean notNull(){
        return userName != null;
    }

    private boolean hasMinLength(){
        return userName.length() >= MINLENGTH;
    }

    private boolean withinMaxLength(){
        return userName.length() <= MAXLENGTH;
    }

    private boolean hasOnlyAlphabets(){
        Pattern pattern = Pattern.compile(ALLOWEDALPHABETS);
        Matcher match = pattern.matcher(userName);
        return match.matches();
    }    
}
