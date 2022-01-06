package com.agile.authapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends Validator {

    private String allowedChars = "((?=.*?[a-zA-Z].*?[a-zA-Z])(?=.*?[0-9].*?[0-9])).{2,}";
    private String password;
    public int minLength = 8;

    @Override
    public boolean isValid(String password) {
        this.password = password;
        return notNull() && hasMinLength() && hasAllowedChars();
    }

    private boolean notNull(){
        return password != null;
    }
    
    private boolean hasAllowedChars(){
        Pattern pattern = Pattern.compile(allowedChars);
        Matcher match = pattern.matcher(password);
        return match.matches();
    }

    private boolean hasMinLength(){
        return password.length() >= minLength;
    }
}
