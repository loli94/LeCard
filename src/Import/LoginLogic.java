package Import;

import java.util.Scanner;

public class LoginLogic {

public static void main(String[] args) {

    String benutzername;
    String passwort;

    passwort = "123";
    benutzername = "Lars";

    Scanner input1 = new Scanner(System.in);
    System.out.println("Enter Benutzername : ");
    String username = input1.next();

    Scanner input2 = new Scanner(System.in);
    System.out.println("Enter Passwort : ");
    String password = input2.next();

    if (username.equals(benutzername) && password.equals(passwort)) {

        System.out.println("Erfolgreich angemeldet");
    }

    else if (username.equals(benutzername)) {
        System.out.println("Falsches Passwort!");
    } else if (password.equals(passwort)) {
        System.out.println("Falscher Benutzername!");
    } else {
        System.out.println("Falscher Benutzername & Falsches Passwort!");
    }

}

}