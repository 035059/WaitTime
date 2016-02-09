package com.company;

public class LinkedQueue<T> implements Cloneable{
    private Node<T> head;
    private Node<T> tail;
    private Node<T> temp;
    private Node<T> temp2;
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

    public LinkedQueue<T> copy() {
        LinkedQueue<T> toRet = new LinkedQueue<>();
        for (int i = 0; i < size; i++) {
            T tempElement = this.pop();
            this.add(tempElement);
            toRet.add(tempElement);
        }
        return toRet;
    }

    public void print(){
        temp = head;
        while(temp  != null){
            System.out.println(temp.element);
            temp = temp.next;
        }
    }
}