import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The linked list driver class provides the scanner and user inupt portions of
 * the project and makes calls to DoublyLinkedList
 */
public class BinaryTreeDriver {

    /**
     * main method is what prompts the user and provides the desired output
     */
    public static void main(String[] args) {



        BinarySearchTree<?> tree;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter list type (i - int, d - double, s - string): ");

        String dataType = scan.nextLine();

        if (dataType.equals("i")) {
            tree = new BinarySearchTree<Integer>();
        } else if (dataType.equals("d")) {
            tree = new BinarySearchTree<String>();
        } else {
            tree = new BinarySearchTree<Double>();
        }

        String fileName = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                try {

                    String[] content = line.split("\\s+");
                    for (String item : content) {
                        insertToList(tree, item, dataType);
                    } // for
                    } catch (NumberFormatException e) {
                    } // catch
                } // while
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }  // Print the sorted list
            System.out.println("Binary Search Tree:");
            tree.inOrder();
            System.out.println("");
            System.out.println("Commands:\n");
            System.out.println("(i)  - Insert Item");
            System.out.println("(d)  - Delete Item");
            System.out.println("(p)  - Print Tree");
            System.out.println("(l)  - Count Leaf Nodes");
            System.out.println("(sp) - Find Single Parents");
            System.out.println("(c)  - Find Cousins");
            System.out.println("(q)  - Quit program");

            String str = "";
            while (str != "q") {
                System.out.print("Enter a command: ");
                str = scan.nextLine();

                if (str.equals("i")) { // insert

                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");

                    System.out.print("Enter a number to insert: ");
                    String item = scan.nextLine();

                    insertToList(tree,item,dataType);

                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");

                } else if (str.equals("d")) { // delete
                    System.out.print("Enter a number to delete: ");
                    String item = scan.nextLine();
                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");
                    deleteFromList(tree, item, dataType);
                    if(tree != null) {
                        System.out.println("In-order: ");
                        tree.inOrder();
                        System.out.println("");
                    } // if

                } else if (str.equals("p")) { // print

                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");


                } else if (str.equals("s")) { //search

                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");
                    System.out.print("Enter a number to search");
                    String item = scan.nextLine();
                    System.out.println("");
                    genericSearch(tree, item, dataType);

                } else if (str.equals("l")) { // leaf

                    int num = tree.getNumLeafNodes();
                    System.out.print("The number of leaf nodes are ");
                    System.out.println(num);

                } else if (str.equals("sp")) { // single parent

                    System.out.print("Single Parents: ");
                    tree.getSingleParent();
                    System.out.println("");

                } else if (str.equals("c")) { // cousin

                    System.out.println("In-order: ");
                    tree.inOrder();
                    System.out.println("");

                    System.out.print("Enter a number: ");
                    String item = scan.nextLine();
                    System.out.println("");

                    System.out.print(item);
                    System.out.print(" cousins: ");
                    genericCousin(tree,item,dataType );

                } else if (str.equals("q")) { //quit

                    System.out.println("Exiting the program...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid command");
                } // if-else
            } // while
    } // main

    // Generic method to insert items safely
    private static <T extends Comparable<T>> void insertToList(BinarySearchTree<T> tree,
    String value, String type) {
        T itemParsed = parseGeneric(value, type);
        tree.insert(itemParsed);
    }

    // Generic method to delete items safely
    private static <T extends Comparable<T>> void deleteFromList(BinarySearchTree<T> tree,
    String value, String type) {
        T itemParsed = parseGeneric(value, type);
        tree.delete(itemParsed);
    }

    private static <T extends Comparable<T>> void genericCousin(BinarySearchTree<T> tree,
    String value, String type) {

        T itemParsed = parseGeneric(value, type);
        tree.getCousin(itemParsed);

    } // genericCousin

    private static <T extends Comparable<T>> void genericSearch(BinarySearchTree<T> tree,
      String value, String type) {

          T itemParsed = parseGeneric(value, type);
          tree.search(itemParsed);

      }

    private static <T extends Comparable<T>> T parseGeneric(String item, String type) {
        if (type.equals("i")) {
            return (T) (Comparable<?>) Integer.valueOf(item);
        } else if (type.equals("d")) {
            return (T) (Comparable<?>) Double.valueOf(item);
        } else {
            return (T) item;
        }

    }

} // class
