/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: PublicTester file
 * 
 * This file contains all the custom tester files and 
 * test MyDeque, test MyQueue, and test MyStack. 
 */

import org.junit.*;
import static org.junit.Assert.*;

import javax.management.Query;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size, 
            int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when the initial capacity is an
     * Illegal Argument Exception. 
     */
    @Test
    public void testMyDequeConstructor() {
        try {
            MyDeque<Integer> deque = new MyDeque<>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			// pass
		}
    }

    /**
     * Test the expandCapacity method when the elements needs to 
     * be rearranged in object. 
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 3, 4, 5, 1, 2 };
        Integer[] finalOrdering = { 1, 2, 3, 4, 5,
            null, null, null, null, null };
        initDeque(deque, orig, 5, 3, 2);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 
            10, deque.data.length);
        assertEquals("Size should not have changed", 5, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 4, deque.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained", 
            finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test the addFirst method when expandCapacity is called 
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 3, 4, 5, 1, 2 };
        initDeque(deque, orig, 5, 3, 2);

        deque.addFirst(0);

        assertEquals("Capacity should change if deque full", 10,
                deque.data.length);
        assertEquals("Should increment size", 6, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 9, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 4,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                Integer.valueOf(0), deque.data[9]);
    }

    /**
     * Test the addLast method when a null pointer is added
     */
    @Test
    public void testAddLast() {
        try {
            MyDeque<Integer> deque = new MyDeque<>(10);
            deque.addLast(null);
        } catch(NullPointerException e){
            // pass
        }
    }

    /**
     * Test the removeFirst method when there is one element
     * in the object.  
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, 100, null, null };
        initDeque(deque, orig, 1, 2, 2);

        assertEquals("Element removed should be returned", 100,
                deque.removeFirst().intValue());
        assertEquals("Array length shouldn't be changed", 5,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 2, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 2,
                deque.rear);
        assertEquals("Index 2 should remain unchanged", null,
                deque.data[2]);
    }

    /**
     * Test the removeLast method when there is no elements in the 
     * object.
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, null, null, null };
        initDeque(deque, orig, 0, 0, 0);

        assertEquals("Element removed should be returned", null,
        deque.removeLast());
        assertEquals("Array length shouldn't be changed", 5,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);

    }

    /**
     * Test the peekFirst method when there is no elements in the 
     * object. 
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, null, null, null };
        initDeque(deque, orig, 0, 2, 2);

        assertEquals("Value at front should be returned",
                null, deque.peekFirst());
        assertEquals("peekFirst should not change capacity", 5,
                deque.data.length);
        assertEquals("peekFirst should not change size", 0, deque.size);
        assertEquals("peekFirst should not change front", 2, deque.front);
        assertEquals("peekFirst should not change rear", 2, deque.rear);
    }

    /**
     * Test the peekLast method when there is no elements in the 
     * object.
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, null, null, null };
        initDeque(deque, orig, 0, 4, 4);

        assertEquals("Value at front should be returned",
                null, deque.peekLast());
        assertEquals("peekFirst should not change capacity", 5,
                deque.data.length);
        assertEquals("peekFirst should not change size", 0, deque.size);
        assertEquals("peekFirst should not change front", 4, deque.front);
        assertEquals("peekFirst should not change rear", 4, deque.rear);
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when we push and pop elements at full capacity
     */
    @Test
    public void testMyStack(){
        // You can test any method from MyStack or a combination of methods
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = { 5, 10, 15, 20, 25 };
        initDeque(stack.theStack, orig, 5, 0, 4);

        stack.push(10);
        assertEquals("Capacity should change if deque full", 10,
                stack.theStack.data.length);
        assertEquals("Should increment size", 6, stack.size());
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 9, stack.theStack.front);
        
        stack.pop();
        assertEquals("Capacity should change if deque full", 10,
                stack.theStack.data.length);
        assertEquals("Should increment size", 5, stack.size());
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 9, stack.theStack.front);    
        

    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when we push and pop elements at full capacity
     */
    @Test
    public void testMyQueue(){
        // You can test any method from MyQueue or a combination of methods
        MyQueue<Integer> queue = new MyQueue<>(5);
        Integer[] orig = { 10, 0, 1, 2, 5 };
        initDeque(queue.theQueue, orig, 5, 0, 4);

        queue.enqueue(10);
        assertEquals("Capacity should change if deque full", 10,
            queue.theQueue.data.length);
        assertEquals("Should increment size", 6, queue.size());
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 0, queue.theQueue.front);
        
        queue.dequeue();
        assertEquals("Capacity should change if deque full", 10,
            queue.theQueue.data.length);
        assertEquals("Should increment size", 5, queue.size());
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 1, queue.theQueue.front);    
    }
}