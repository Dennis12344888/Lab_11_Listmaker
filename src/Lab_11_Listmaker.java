import java.util.ArrayList;
import java.util.Scanner;

public class Lab_11_Listmaker {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = getRegExString("[AaDdPpQq]");

            switch (Character.toUpperCase(choice)) {
                case 'A':
                    addItemToList();
                    break;
                case 'D':
                    deleteItemFromList();
                    break;
                case 'P':
                    printList();
                    break;
                case 'Q':
                    if (getYNConfirm("Are you sure you want to quit? (Y/N): ")) {
                        System.out.println("Exiting program.");
                        System.exit(0);
                    }
                    break;
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItemToList() {
        System.out.print("Enter item to add: ");
        String item = scanner.nextLine();
        itemList.add(item);
        System.out.println("Item added to the list.");
    }

    private static void deleteItemFromList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. No items to delete.");
            return;
        }

        printNumberedItemList();

        int itemNumber = getRangedInt("Enter the number of the item to delete: ", 1, itemList.size());

        String removedItem = itemList.remove(itemNumber - 1);
        System.out.println("Item '" + removedItem + "' deleted from the list.");
    }

    private static void printList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("\nList:");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    private static void printNumberedItemList() {
        System.out.println("\nNumbered List:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static char getRegExString(String regex) {
        char choice;
        do {
            String input = scanner.nextLine();
            if (input.length() == 1 && input.matches(regex)) {
                choice = input.charAt(0);
                break;
            } else {
                System.out.print("Invalid input. Please enter a valid choice: ");
            }
        } while (true);
        return choice;
    }

    private static int getRangedInt(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            if (value < min || value > max) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            } else {
                break;
            }
        } while (true);
        return value;
    }

    private static boolean getYNConfirm(String prompt) {
        System.out.print(prompt);
        char choice = getRegExString("[YyNn]");
        return Character.toUpperCase(choice) == 'Y';
    }
}
