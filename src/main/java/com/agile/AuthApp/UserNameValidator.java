package com.agile.authapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator extends Validator {

    private int minLength = 1;
    private int maxLength = 11;
    private String allowedAlphabets = "^[a-zA-Z]*$";
    private String userName;

    @Override
    public boolean isValid(String userName) {
        this.userName = userName;
        return notNull() && hasMinLength() && withinMaxLength() && hasOnlyAlphabets();
    }

    private boolean notNull(){
        return userName != null;
    }

    private boolean hasMinLength(){
        return userName.length() >= minLength;
    }

    private boolean withinMaxLength(){
        return userName.length() <= maxLength;
    }

    private boolean hasOnlyAlphabets(){
        Pattern pattern = Pattern.compile(allowedAlphabets);
        Matcher match = pattern.matcher(userName);
        return match.matches();
    }    
}
