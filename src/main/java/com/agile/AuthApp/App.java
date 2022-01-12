package com.agile.authapp;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    static Scanner scanner = new Scanner(System.in);
    static java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();
    public static void main( String[] args ) throws JsonIOException, JsonSyntaxException, IOException
    {
        logger.log(Level.INFO, "Welcome to Auth App!");
        logger.log(Level.INFO, "Already have an account, press 1 to login. Press 2 to create an account." );
        int option = scanner.nextInt();
        if(option == 1){
            login();
        }
        else if(option == 2){
            createAccount();
            login();
        }
        else{
            logger.log(Level.INFO, "Invalid option. Closing the App" );
            System.exit(0);
        }
        scanner.close();
    }

    public static void createAccount() throws JsonIOException, JsonSyntaxException, IOException{
        logger.log(Level.INFO, "Create Account" );
        logger.log(Level.INFO, "UserName restrictions: Only Alphabets, Min Length = 1 and Max Length = 11" );
        logger.log(Level.INFO, "Password restrictions: Only number" );

        logger.log(Level.INFO, "Enter your User Name:" );
        String userName = scanner.next();
        logger.log(Level.INFO, "Enter your Password:" );
        String password = scanner.next();

        UserAccountService userAccountService = new UserAccountService(new UserNameValidator(), new PasswordValidator(), new UserAccountsProvider());
        UserAccountCreationStatus userAccount = userAccountService.createAccount(userName, password);

        if(userAccount.isAccountCreationSuccess){
            logger.log(Level.INFO, "Successfuly created the account" );
        }
        else{
            logger.log(Level.INFO, "Your account was not created, please correct the issue/s" );
           
           if(userAccount.userNameValidationMessage != null) {
            logger.log(Level.INFO, userAccount.userNameValidationMessage );
           }
           if(userAccount.passwordValidationMessage != null) {
            logger.log(Level.INFO, userAccount.passwordValidationMessage );
           }
        }
    }

    public static void login() throws IOException{
        logger.log(Level.INFO, "Enter UserName and Password for Login:" );
        logger.log(Level.INFO, "UserName:" );
        String userName = scanner.next();
        logger.log(Level.INFO, "Password:" );
        String password = scanner.next();
        AuthService authService = new AuthService();
        User user = authService.authenticateUser(userName, password);
        if(user.isAuthenticated()){
            logger.log(Level.INFO, "Login Successfull" );
        }
        else{
            String errorMessage = user.errorMessage();
            logger.log(Level.INFO, errorMessage);
        }
    }

    //#region To be deleted
    //Just for test purpose // To be deleted...
    public static void showMessage(){
        logger.log(Level.INFO, "test");
    }

    public boolean showMsg(){
        return anotherMessage();
    }

    public boolean anotherMessage(){
        return false;
    }
    //#endregion
}
