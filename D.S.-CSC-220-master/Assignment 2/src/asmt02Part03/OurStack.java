package asmt02Part03;

import java.util.Stack;

/**
 * A class of stacks.
 *
 * @param <T>
 * @author Frank M. Carrano
 * @version 4.0
 */
public class OurStack<T> implements StackInterface<T> {
    Stack<T> theStack = new Stack<>();

    public OurStack() {
    } // end default constructor

    public void push(T newEntry) {
        try {
            theStack.push(newEntry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end push

    public T peek() {
        return theStack.peek();
    } // end peek

    public T pop() {
        return theStack.pop();
    } // end pop

    public boolean isEmpty() {
        return theStack.empty();
    } // end isEmpty

    public void clear() {
        while (!isEmpty()) {
            theStack.pop();
        }
    } // end clear
} // end OurStack
