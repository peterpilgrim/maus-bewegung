package org.example.maus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


class MyLinkedList {


    public String getHead() {
        if ( head != null ) {
            return head.value;
        }
        else {
            return null;
        }
    }

    public String getTail() {
        if ( tail != null ) {
            return tail.value;
        }
        else {
            return null;
        }
    }

    static class Node {
        public Node prev;
        public Node next;
        public String value;

        public Node(String value) {
            this(null,null,value);
        }

        public Node(Node prev, Node next, String value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

    }

    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public void add(String value) {
        var newNode = new Node(value);
        if ( tail == null ) {
            tail = newNode;
            if ( head == null ) {
                head = newNode;
            }
        }
        else {
            newNode.prev = tail;  /*1*/
            tail.next = newNode;  /*2*/
            tail = newNode;       /*3*/
        }
    }
}

public class LinkedListNodeTest {

    @DisplayName("A new Linked List should initially be empty")
    @Test
    public void new_linked_list_should_be_empty() {
        assertThat( new MyLinkedList().isEmpty(), is(true));
    }

    @DisplayName("Add an element to Linked List")
    @Test
    public void add_a_node_to_linked_list() {
        var linkedList = new MyLinkedList();
        linkedList.add("hello");
        assertThat(linkedList.isEmpty(), is(false));
    }

    @DisplayName("Linked List has a head and tail")
    @Test
    public void linked_list_has_a_head_and_tail() {
        var linkedList = new MyLinkedList();
        linkedList.add("Malaga");
        assertThat(linkedList.getHead(), is("Malaga"));
        assertThat(linkedList.getTail(), is("Malaga"));
    }

    @DisplayName("Linked List has a head and tail after adding 2 nodes")
    @Test
    public void linked_list_has_a_head_and_tail_2() {
        var linkedList = new MyLinkedList();
        linkedList.add("London");
        linkedList.add("Glasgow");
        assertThat(linkedList.getHead(), is("London"));
        assertThat(linkedList.getTail(), is("Glasgow"));
    }
}
