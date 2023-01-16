package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
        String dirPath = "data2";
        String fileName = "";

        // instantiate a file/directory object
        File newDirectory = new File(dirPath);
        
        // if firectory exist, print to console 'directory exist' message
        // else create the directory
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
                    listCart(cartItems);
                    break;
                case "users":
                    listUsers(dirPath);
                    break;
                default:
            }

            String strValue = "";
            if (input.startsWith("add")) {
                input = input.replace(',', ' ');  // replace comma with space
                
                Scanner scanner = new Scanner(input.substring(4));  // starts from the 4th character

                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);
                }
            }

            if (input.startsWith("delete")) {
                cartItems = deleteCartItem(cartItems, input);
            }

            if (input.startsWith("login")) {
                createLoginFile(input, dirPath, fileName);
            }
        }
    }
    public static void listCart(List<String> cartItems) {
        if (cartItems.size() > 0) {
            for (String item : cartItems) {
                System.out.println(cartItems.indexOf(item) + 1 + ". " + item);
            }
        }
        else {
            System.out.println("Your cart is empty");
        }
    }

    public static List<String> deleteCartItem(List<String> cartItems, String input) {
        String strValue = "";
        Scanner scanner = new Scanner(input.substring(7));

        while (scanner.hasNext()) {
            strValue = scanner.next();
            int listItemIndex = Integer.parseInt(strValue) - 1;

            if (listItemIndex < cartItems.size()) {
                cartItems.remove(listItemIndex);
            }
            else {
                System.out.println("Incorrect item index");
            }  
        }
        return cartItems;
    }

    public static void createLoginFile(String input, String dirPath, String fileName) throws IOException {
        input = input.replace(',', ' ');

        Scanner sc = new Scanner(input.substring(6));

        while (sc.hasNext()) {
            fileName = sc.next();
        }

        // define the filepath + filename
        // File.separator = "\"
        File loginFile = new File(dirPath + File.separator + fileName);

        // try to create a file
        // isCreated set to true if it is a new file to create
        // isCreated set to false if file already exist
        boolean isCreated = loginFile.createNewFile();

        if (isCreated) {
            System.out.println("New file created successfully" + loginFile.getCanonicalPath());
        }
        else {
            System.out.println("File already created.");
        }
    }

    public static void listUsers(String dirPath) {
        File directoryPath = new File(dirPath);
        String contents[] = directoryPath.list();
        
        for (String file : contents) {
            System.out.println(file);
        }
    }
}
