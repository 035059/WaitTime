package com.company;

public class Node<T>{
    Node<T> prev;
    Node<T> next;
    T element;
    public Node() {}
    public Node(T element) {
        this.element = element;
    }
}