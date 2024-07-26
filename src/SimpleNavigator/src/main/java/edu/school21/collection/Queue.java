package edu.school21.collection;

import java.util.EmptyStackException;

public class Queue<T> {

  private Node head;
  private Node tail;

  public Queue() {
    head = null;
    tail = null;
  }

  public void push(T value) {
    Node newNode = new Node(value);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
  }

  public T pop() {
    if (head == null) {
      throw new EmptyStackException();
    } else {
      T value = head.value;
      head = head.next;
      return value;
    }
  }

  public T front() {
    if (head == null) {
      throw new EmptyStackException();
    }
    return head.value;
  }

  public T back() {
    if (head == null) {
      throw new EmptyStackException();
    }
    return tail.value;
  }

  public boolean isEmpty() {
    return head == null;
  }

  class Node {

    private final T value;
    private Node prev;
    private Node next;

    public Node(T value) {
      this.value = value;
      this.prev = null;
      this.next = null;
    }
  }
}
