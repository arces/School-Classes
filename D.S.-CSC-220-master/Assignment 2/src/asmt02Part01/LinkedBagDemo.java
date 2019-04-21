package asmt02Part01;

/**
 * A demonstration of the class LinkedBag.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Duc Ta
 * @version 4.0
 *
 */
public class LinkedBagDemo {

    public static void main(String[] args) {
        // CSC220 ASMT02 PART01 Tester
        System.out.println("- Creating an empty bag.");
        BagInterface<String> aBag = new LinkedBag<>();
        displayBag(aBag);
        // Adding strings
        String[] contentsOfBag = {"A", " ", " ", "G", "B", "A", " ", "d", "A", "o", "o", "B", "A", "A"};
        testAdd(aBag, contentsOfBag);
        // Removing all occurence of the given entries from a bag
        String[] testString = { "A", "B", " " };
        aBag.removeAllOccurences(testString);
        displayBag(aBag);

    } // end main

    // Tests the method add.
    private static void testAdd(BagInterface<String> aBag, String[] content) {
        System.out.print("- Adding to the bag: \t\t\t\t");
        for (String content1 : content) {
            aBag.add(content1);
            System.out.print(content1 + " ");
        } // end for
        System.out.println();

        displayBag(aBag);
    } // end testAdd

    // Tests the method toArray while displaying the bag.
    private static void displayBag(BagInterface<String> aBag) {
        System.out.print("- The bag contains " + aBag.getCurrentSize()
                + " string(s), as follows: \t");
        Object[] bagArray = aBag.toArray();
        for (Object bagArray1 : bagArray) {
            System.out.print(bagArray1 + " ");
        } // end for

        System.out.println();
    } // end displayBag
} // end LinkedBagDemo
