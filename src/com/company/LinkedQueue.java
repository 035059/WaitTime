package com.company;

public class LinkedQueue<T> {
    /**
     * Basic Linked Queue, plus a clone function
     * Also uses generics, cause they are cool/really useful
     * */

    // Attribute declaration
    private Node<T> head;
    private Node<T> tail;
    private Node<T> temp;
    private int size;

    /*
     * Constructor - sets the default values of the head, tail and size
     */
    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
     * Adds the passed element to the end of the queue
     */
    public void add(T elem) {
        if (head == null) { // If there are no other Nodes in the queue
            head = new Node<>(); // Set the head to a new node
            head.element = elem; // Set the element of the new node to the passed element
            tail = head; // Because there are no other values, the head is also the tail
            size++; // Add one to the size
        } else { // If there are other elements in the queue
            temp = new Node<>(); // Make a new temporary queue
            temp.element = elem; // Set the element of the temp node to the passed element
            tail.next = temp; // Set the node after the tail of the queue to the temp node
            temp.prev = tail; // Set the value before the end of the queue to the tail
            tail = temp; // Make the temp node the end of the queue
            size++; // Add one to the size
        }

    }

    /*
     * returns the element of the first item in the queue, the removes it from the queue
     */
    public T pop(){
        temp = head; // Make a temp node equal to the head
        head = head.next; // The replace the head with the next element in the queue
        size--; // Reduce the size of the queue by one
        return temp.element; // Return the element of the temp node
    }

    /*
     * Returns the element of the first item in the queue, does not remove it from the queue
     */
    public T peek(){
        return tail.element;
    }

    /*
         * Returns the size of the queue
         */
    public int getSize() {
        return size;
    }

    /*
     * Returns a copy of the queue that the method is called on
     */
    public LinkedQueue<T> copy() {
        LinkedQueue<T> toRet = new LinkedQueue<>(); // Make a new Queue
        for (int i = 0; i < size; i++) { // Iterate for the size of the queue
            T tempElement = this.pop(); // Pop the front of the queue
            this.add(tempElement); // Add that element to this queue again
            toRet.add(tempElement); // Add the element to the temp queue
        }
        return toRet; // Return the new, identical, queue
    }
}