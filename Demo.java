import java.util.Scanner;

/**
 * Main class for interacting with the Container.
 * It allows insertion and removal of integers through the command line.
 */
public class Demo {
    /**
     * The main entry point for a demo run of the container.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Container container = new Container();
        Scanner scanner = new Scanner(System.in);

        System.out.println(); // added for quick padding

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Insert integer");
            System.out.println("2. Remove integer");
            System.out.println("3. Lookup integer");
            System.out.println("4. View contents");
            System.out.println("5. Quit\n");
            System.out.print("Enter your choice (1/2/3/4/5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Insertion
                    System.out.print("Enter an integer to insert: ");
                    String inputInsert = scanner.nextLine();
                    try {
                        int numInsert = Integer.parseInt(inputInsert);
                        container.insert(numInsert);
                        System.out.println("\nContents after insertion: \n" + container.getContents());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid integer.\n");
                    }
                    break;

                case "2":
                    // Removal
                    System.out.print("Enter an integer to remove: ");
                    String inputRemove = scanner.nextLine();
                    try {
                        int numRemove = Integer.parseInt(inputRemove);
                        if (container.contains(numRemove)) {
                            container.remove(numRemove);
                            System.out.println("\nContents after removal: \n" + container.getContents());
                        } else {
                            System.out.println("\nNumber not found.\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\nPlease enter a valid integer. \n");
                    }
                    break;
                case "3":
                    // Removal
                    System.out.print("Enter an integer to lookup: ");
                    String inputLookup = scanner.nextLine();
                    try {
                        int numLookup = Integer.parseInt(inputLookup);
                        if (container.contains(numLookup)) {
                            System.out.println("\nContainer Contains: " + numLookup + "\n");
                        } else {
                            System.out.println("\nNumber not found.\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\nPlease enter a valid integer. \n");
                    }
                    break;
                case "4":
                    // View contents
                    System.out.println("\nContainer contents: \n" + container.getContents());
                    break;
                case "5":
                    // Quit
                    System.out.println("\nGoodbye!\n");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please choose 1, 2, 3, or 4.\n");
            }
        }
    }
}
