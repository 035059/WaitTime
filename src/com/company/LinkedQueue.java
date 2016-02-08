package com.company;

public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private Node<T> temp;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T elem) {
        if (head == null) {
            head = new Node<>();
            head.element = elem;
            tail = head;
            size++;
        } else {
            temp = new Node<>();
            temp.element = elem;
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            size++;
        }

    }

    public T pop(){
        temp = tail;
        tail = tail.prev;
        size--;
        return temp.element;
    }

    public T peek(){
        return tail.element;
    }

    public int getSize() {
        return size;
    }

    void print(){
        temp = head;
        while(temp  != null){
            System.out.println(temp.element);
            temp = temp.next;
        }
    }
}