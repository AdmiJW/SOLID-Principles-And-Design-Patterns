package nullObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        IUser u = new NullUser();

        while (true) {
            System.out.println("Username: " + u.getUsername());
            System.out.println("Description: " + u.getDescription());

            if (u instanceof NullUser) {
                System.out.print("Enter a username: ");
                String username = scan.nextLine();
                System.out.print("Enter a description: ");
                String description = scan.nextLine();
                u = new User(username, description);
            } else {
                System.out.println("Press enter to log out");
                scan.nextLine();
                u = new NullUser();
            }

            System.out.println();
        }
    }
}
