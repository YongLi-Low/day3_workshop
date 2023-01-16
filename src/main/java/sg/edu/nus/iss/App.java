package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */

    public static void main(String[] args) {
        String dirPath = "/data2";
        String fileName = "";

        File newDirectory = new File(dirPath);
        
        if (newDirectory.exists()) {
            System.out.println("Directory already exist");
        }
        else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to My Shopping Cart");

        List<String> cartItems = new ArrayList<>();

        Console con = System.console();
        String input = "";

        while (!input.equals("quit")) {
            input = con.readLine("What do you want to perform? (Type 'quit' to exit program)");

            switch(input) {
                case "help":
                    System.out.println("'list' to show a list of items in shopping cart");
                    System.out.println("'login <name>' to to access your shopping cart");
                    System.out.println("'add <item>' to to add item into your shopping cart");
                    System.out.println("'delete <item>' to delete item in shopping cart");
                    System.out.println("'quit' to eixt the program");
                    break;
                case "list":
                    if (cartItems.size() > 0) {
                        for (String item : cartItems) {
                            System.out.println(item);
                        }
                    }
                    else {
                        System.out.println("Your cart is empty");
                    }
                    break;
                case "users":
                    break;
                default:
            }

            if (input.startsWith("add"))
                input = input.replace(',', ' ');  // replace comma with space
                
                String strValue = "";
                Scanner scanner = new Scanner(input.substring(4));  // starts from the 4th character

                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);
            }
        }
    }

   
}
