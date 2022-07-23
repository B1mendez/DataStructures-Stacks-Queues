import junit.framework.ComparisonCompactor;

/**
 * Name: Brian Mendez
 * Email: B1mendez@ucsd.edu
 * Source: None 
 * 
 * This file contains a MyDeque class that implements 
 * DequeInterface. It creates methods that can add/remove
 * elements from both ends of the object
 */


 /**
  * This class implements the DequeInterface and has four
  * instance variables that record the elements in the 
  * object, the size, and the front and rear side of object
  */
public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;

    private int DEFAULT_SIZE = 10; 

    /**
     * Constructor that initializes the instance variables
     * and sets data with an initialCapacity. 
     * 
     * @param initialCapacity - the length of data
     */
    public MyDeque (int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException(); 
        }
        
        this.data = new Object[initialCapacity];
        this.size = 0; 
        this.rear = 0;
        this.front = 0;
    }

    /**
     * Returns the number of valid elements in 
     * the array data. 
     * 
     * @return size of data (valid elements)
     */
    public int size(){
        return size;
    }

    /**
     * Expands the capacity of the data when necessary 
     * if the capacity is zero set the data length to 
     * the default capacity. 
     * 
     */
    public void expandCapacity(){ 
        int temp = data.length; 
        if (temp == 0){
            temp = DEFAULT_SIZE;
        }
        else {
            temp = temp * 2;
        }

        Object[] newArr = new Object[temp]; 
        for (int i = 0; i < data.length; i++){
            newArr[i] = data[(i + front) % data.length]; 
        }   

        front = 0;
        if (size == 0){
            rear = 0; 
        }
        else {
            rear = size - 1;
        }
        
        data = newArr;
    }
    
    /**
     * Add the element to the beginning of the circular array
     * 
     * @param Element - the data entry being added
     */
    public void addFirst(E element){
        if (element == null){
            throw new NullPointerException(); 
        }

        if (size == data.length){
            expandCapacity();
        }
        
        int temp = front; 
        if (front == 0 && size != 0){
            front = data.length - 1;
        }
        else if (size == 0 ){
            front = temp;
        }
        else {
            front = temp - 1;
        } 
        data[front] = element; 
        size++;
    }

    /**
     * Add the element at the end of the circular array
     * 
     * @param Element - the data entry being added
     */
    public void addLast (E element){
        if (element == null){
            throw new NullPointerException(); 
        }

        if (size == data.length){
            expandCapacity();
        }
        
        int temp = rear; 
        if (size == 0){
            rear = temp; 
        }
        else if (rear == data.length){
            rear = 0; 
        }
        else {
            rear = temp + 1; 
        }

        data[rear] = element; 
        size++;
    }

    /**
     * Remove the element in the front of the circular array
     * 
     * @return the element being removed from the array 
     */
    @SuppressWarnings("unchecked")
    public E removeFirst(){
        if (size == 0) {
            return null; 
        }

        E removed = (E)data[front];
        data[front] = null;  
        int temp = front; 

        if (size == 1){
            front = rear; 
        }
        else if (front == data.length){
            front = 0; 
        }
        else {
            front = temp + 1;
        }
    
        size--;
        return removed; 
    }

    /**
     * Remove the element at the end of the circular array
     * 
     * @return the element being removed
     */
    @SuppressWarnings("unchecked")
    public E removeLast(){
        if (size == 0){
            return null; 
        }

        E removed = (E)data[rear];
        data[rear] = null; 
        int temp = rear; 

        if (size == 1){
            rear = front; 
        }
        else if (rear == 0){
            rear = data.length; 
        }
        else {
            rear = temp - 1; 
        }
        
        size--;
        return removed;
    }

    /**
     * Returns the element at the beginning of the array, or null 
     * if there was no such element.
     * 
     * @return the element in front of the array
     */
    @SuppressWarnings("unchecked")
    public E peekFirst(){
        if (size == 0){
            return null; 
        }

        return (E)data[front]; 
    }

    /**
     * Returns the element at the end of the array, or null 
     * if there was no such element.
     * 
     * @return the element at the end of the array
     */
    @SuppressWarnings("unchecked")
    public E peekLast(){
        if (size == 0){
            return null; 
        }

        return (E)data[rear]; 
    }
}
