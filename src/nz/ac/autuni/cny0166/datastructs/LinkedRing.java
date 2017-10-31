package nz.ac.autuni.cny0166.datastructs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This structure uses a singly linked list that connects the final node to the starting node. This allows for an infinite call of nextPlayer() which aids
 * the multilayer component of this game.
 */
public class LinkedRing<E> {

    private int size;
    private Node last;
    private Node first;
    private Node current;
    private ArrayList<E> linearStore;

    public LinkedRing() {

        this.size = 0;
        current = null;
        this.last = null;
        this.first = null;
        this.linearStore = new ArrayList<>();

    }

    public void add(E element) {

        if (size == 0) {

            this.last = new Node(element);
            this.current = last;
            this.first = last;

        } else {

            this.last.next = (last = new Node(element));
            this.last.next = this.first;

        }

        this.linearStore.add(element);
        this.size++;

    }

    /**
     * Will progress to the next Object in the structure. The final element will loopp back to the start.
     * @return The next object in the structure.
     */
    public E next() {

        this.current = this.current.next;
        return this.current.element;

    }


    /**
     * Node encapsulates an element object and uses a single next variable to keep the ring structure.
     */
    private class Node {

        private Node next;
        E element;

        private Node(E element) {

            this.element = element;

        }

    }

    public E getCurrent() {
        return this.current.element;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<E> getLinearStructure() {

        return this.linearStore;

    }
}
