import java.util.Iterator;
import java.util.NoSuchElementException;

// Create abstract list

public class DoublyLinkedList<E> implements Iterable<E>{

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }


    // Doubly linkedList gives the advantage of removeLast with a constant time o(1)
    protected static class Node<E>{
        private E data ;
        private Node<E> next;
        private Node<E> pre;
        public Node(E data) {
            this.data = data;
            next = null;
            pre = null;
        }
    }
    private class IteratorHelper implements Iterator<E> {

        Node<E> index;
        IteratorHelper() { index = head; }
        @Override
        public boolean hasNext() {
            return (index != null);   // if head not null means are list contains value so has next
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E val = index.data;
            index = index.next;  // Moving the pointer to the next node
            return val;

        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int nodeNumbers;
    DoublyLinkedList() {
        head = null ;
        tail = null;
        nodeNumbers = 0;
    }

    public void addFirst(E obj) {

        Node<E> newNode = new Node<E>(obj);
        if (head == null) {  // it 's empty
            head = tail = newNode;
            nodeNumbers++;
            return;
        }
        newNode.next = head;
        head.pre = newNode;
        head = newNode;
        nodeNumbers++;
    }

    // O(1)
    public E removeFirst() {
        if (head == null) return null;
        E temp = head.data;
        if (head == tail)
            head = tail = null;
        else {
            head.next.pre = null;
            head = head.next;
        }
        nodeNumbers--;
        return temp;
    }

    // o(1)
    public E removeLast() {
        if (head == null) return null;
        E temp = tail.data;
        if (head == tail)
            head = tail = null;
        else {
            tail.pre.next = null;
            tail = tail.pre;
        }
        nodeNumbers --;
        return temp;
    }

    public void addLast(E obj) {
        Node<E> newNode = new Node<>(obj);
        if (head == null)
        {
            head = tail = newNode;
            nodeNumbers ++;
            return;
        }
        newNode.pre = tail;
        tail.next = newNode;
        tail = newNode;
        nodeNumbers ++;

    }

    public E getLastPreValue() {
        return tail.pre.data;
    }

    public int getLast() {
        return nodeNumbers;
    }


    public void reverse() {
        if (head == null) return ;
        Node<E> current = head, saver = null;
        while (current != null)
        {
            current.pre = current.next;
            current.next = saver;
            saver = current;
            current = current.pre;
        }
        tail = head;
        head = saver;
    }


}
