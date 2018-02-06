package Import;

import java.util.Scanner;

public class LoginMain {

public static void main(String[] args) {

    String Username;
    String Password;

    Password = "123";
    Username = "Lars";

    Scanner input1 = new Scanner(System.in);
    System.out.println("Enter Username : ");
    String username = input1.next();

    Scanner input2 = new Scanner(System.in);
    System.out.println("Enter Password : ");
    String password = input2.next();

    if (username.equals(Username) && password.equals(Password)) {

        System.out.println("Erfolgreich angemeldet");
    }

    else if (username.equals(Username)) {
        System.out.println("Falsches Passwort!");
    } else if (password.equals(Password)) {
        System.out.println("Falscher Username!");
    } else {
        System.out.println("Falscher Username & Falsches Passwort!");
    }

}

}