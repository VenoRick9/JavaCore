package by.baraznov.customLinkedList;

import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E el) {

        Node<E> newNode = new Node<>(el, head, null);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E el) {
        Node<E> newNode = new Node<>(el, null, tail);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E el) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(el);
        } else if (index == size) {
            addLast(el);
        } else {
            Node<E> indexNode = findIndex(index);
            Node<E> newNode = new Node<>(el, indexNode, indexNode.prev);
            indexNode.prev.next = newNode;
            indexNode.prev = newNode;
            size++;
        }
    }

    @Override
    public E getFirst() {
        return head.value;
    }

    @Override
    public E getLast() {
        return tail.value;
    }

    @Override
    public E get(int index) {
        return findIndex(index).value;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<E> remove = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return remove.value;

    }

    @Override
    public E removeLast() {
        Node<E> remove = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return remove.value;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
       if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> remove = findIndex(index);
            remove.prev.next = remove.next;
            remove.next.prev = remove.prev;
            size--;
            remove.prev = null;
            remove.next = null;
            return remove.value;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<E> findIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

}
