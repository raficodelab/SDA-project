
package simpleloginapp;
        import java.util.Scanner;

public class SimpleLoginApp {
    public static void main(String[] args) {
        String correctUsername = "rafi";
        String correctPassword = "rafi033";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        if (inputUsername.equals(correctUsername) && inputPassword.equals(correctPassword)) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid username or password.");
        }

        scanner.close();
    }
}

    
    

