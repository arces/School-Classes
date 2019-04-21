package asmt02Part01;

/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag
 * is never full.
 *
 * @param <T>
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Duc Ta
 * @version 4.1
 */
public final class LinkedBag<T> implements BagInterface<T> {

    private Node firstNode;       // Reference to first node
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    /**
     * Sees whether this bag is empty.
     *
     * @return True if this bag is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    /**
     * Gets the number of entries currently in this bag.
     *
     * @return The integer number of entries currently in this bag.
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry
     * @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
        // (firstNode is null if chain is empty)        
        firstNode = newNode;      // New node is at beginning of chain
        numberOfEntries++;

        return true;
    } // end add

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in this bag.
     */
    @Override
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while

        return result;
    } // end toArray

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in this bag.
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;

        int counter = 0;
        Node currentNode = firstNode;
        while ((counter < numberOfEntries) && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            } // end if

            counter++;
            currentNode = currentNode.next;
        } // end while

        return frequency;
    } // end getFrequencyOf

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the bag contains anEntry, or false otherwise.
     */
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        } // end while	

        return found;
    } // end contains

    //My method
    public void removeAllOccurences(T[] str) {
        //Sets the current node to the first node, in order to start from the beginning
        Node currentNode = firstNode;
        //Will loop threw each link/node in the link list
        for (int i = 0; i < numberOfEntries; i++) {

            //Debug prints for more info
            //System.out.println(i);
            //System.out.println(numberOfEntries);
            //System.out.println("Current node is "+currentNode.data+"\n");

            //Will loop threw all the given strings in a T[] to check if any of them equals the data in the current node.
            for (T s : str) {
                if (currentNode.data.equals(s)) {

                    currentNode.data = firstNode.data; // Replace located entry with entry in first node

                    //Debug print
                    //System.out.println("Replaceing data with " +firstNode.data+"\n-----------");

                    firstNode = firstNode.next;  // Remove first node
                    //Reduces i which means it can do one more action on the linked list.
                    i--;

                    //Since we removed a link in our list it reduces the num of entries
                    numberOfEntries--;
                }
            }

            //The current node moves to the next node
            currentNode = currentNode.next;
        }
    }

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the entry, if located,
    // or null otherwise.
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        } // end while

        return currentNode;
    } // end getReferenceTo

    /**
     * Removes all entries from this bag.
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    } // end clear

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    @Override
    public T remove() {
        T result = null;
        if (firstNode != null) {
            result = firstNode.data;
            firstNode = firstNode.next; // Remove first node from chain
            numberOfEntries--;
        } // end if

        return result;
    } // end remove

    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false otherwise.
     */
    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if (nodeN != null) {
            System.out.println(nodeN.data);
            nodeN.data = firstNode.data; // Replace located entry with entry in first node
            System.out.println(firstNode.data + "\n-----------");
            firstNode = firstNode.next;  // Remove first node

            numberOfEntries--;

            result = true;
        } // end if

        return result;
    } // end remove

    private class Node {

        private T data; // Entry in bag
        private Node next; // Link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node
} // end LinkedBag

