package com.company;

public class Node<T>{
    /**
     * Minimalist doubly linked Node, aimed at efficiency
     * */

    Node<T> prev; // Reference to previous node
    Node<T> next; // Reference to next node
    T element; // The data stored in the array
    public Node() {} // Empty constructor as other attributes are public
}