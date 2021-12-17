package com.agile.AuthApp;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) throws JsonIOException, JsonSyntaxException, IOException
    {
        System.out.println( "Welcome to Auth App!" );
        System.out.println( "Already have an account, press 1 to login. Press 2 to create an account." );
        int option = scanner.nextInt();
        if(option == 1){
            login();
        }
        else if(option == 2){
            CreateAccount();
            login();
        }
        else{
            System.out.println( "Invalid option. Closing the App" );
            System.exit(0);
        }
        scanner.close();
    }

    public static void CreateAccount() throws JsonIOException, JsonSyntaxException, IOException{
        System.out.println( "Create Account" );
        System.out.println( "UserName restrictions: Only Alphabets, Min Length = 1 and Max Length = 11" );
        System.out.println( "Password restrictions: Only number" );

        System.out.println( "Enter your User Name:" );
        String userName = scanner.next();
        System.out.println( "Enter your Password:" );
        String password = scanner.next();

        UserAccountService userAccountService = new UserAccountService();
        UserAccountCreationStatus userAccount = userAccountService.createAccount(userName, password);

        if(userAccount.IsAccountCreationSuccess){
            System.out.println( "Successfuly created the account" );
        }
        else{
            System.out.println( "Your account was not created, please correct the issue/s" );
           
           if(userAccount.UserNameValidationMessage != null) {
                System.out.println( userAccount.UserNameValidationMessage );
           }
           if(userAccount.PasswordValidationMessage != null) {
                System.out.println( userAccount.PasswordValidationMessage );
           }
        }
    }

    public static void login() throws IOException{
        System.out.println( "Enter UserName and Password for Login:" );
        System.out.println( "UserName:" );
        String userName = scanner.next();
        System.out.println( "Password:" );
        String password = scanner.next();
        AuthService authService = new AuthService();
        User user = authService.authenticateUser(userName, password);
        if(user.IsAuthenticated){
            System.out.println( "Login Successfull" );
        }
        else{
            System.out.println( user.ErrorMessage );
        }
    }

    //#region To be deleted
    //Just for test purpose // To be deleted...
    public static void ShowMessage(){
        System.out.println("test");
    }

    public boolean ShowMsg(){
        boolean result =  anotherMessage();
        return result;
    }

    public boolean anotherMessage(){
        return false;
    }
    //#endregion
}
