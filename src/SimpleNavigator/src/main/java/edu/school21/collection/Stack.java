package edu.school21.collection;

import java.util.EmptyStackException;

public class Stack<T> {

  private Node head;

  public Stack() {
    head = null;
  }

  public void push(T value) {
    Node newNode = new Node(value);
    if (head != null) {
      newNode.prev = head;
    }
    head = newNode;
  }

  public T pop() {
    if (head == null) {
      throw new EmptyStackException();
    } else {
      T value = head.value;
      head = head.prev;
      return value;
    }
  }

  public T top() {
    if (head == null) {
      throw new EmptyStackException();
    }
    return null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  class Node {

    private final T value;
    private Node prev;

    public Node(T value) {
      this.value = value;
      this.prev = null;
    }
  }
}
