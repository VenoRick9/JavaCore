package by.baraznov.customLinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    @Test
    void size() {
        MyList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        list.addFirst("Job");
        assertEquals(1, list.size());
    }

    @Test
    void addFirst() {
        MyList<String> list = new MyLinkedList<>();
        list.addFirst("Job");
        assertEquals("Job", list.get(0));
        list.addFirst("Dog");
        assertEquals("Dog", list.getFirst());
    }

    @Test
    void addLast() {
        MyList<String> list = new MyLinkedList<>();
        list.addLast("Job");
        assertEquals("Job", list.getLast());
        list.addLast("Dog");
        assertEquals("Dog", list.get(list.size() - 1));
    }

    @Test
    void add() {
        MyList<String> list = new MyLinkedList<>();
        list.add(0, "Job");
        list.add(1, "Dog");
        assertEquals("Job", list.get(0));
        assertEquals("Dog", list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(3, "Cat");
        });
    }

    @Test
    void getFirst() {
        MyList<String> list = new MyLinkedList<>();
        list.addFirst("Job");
        list.addFirst("Dog");
        assertEquals("Dog", list.getFirst());
    }

    @Test
    void getLast() {
        MyList<String> list = new MyLinkedList<>();
        list.addLast("Job");
        list.addLast("Dog");
        assertEquals("Dog", list.getLast());

    }

    @Test
    void get() {
        MyList<String> list = new MyLinkedList<>();
        list.add(0, "Job");
        list.add(1, "Dog");
        list.add(2, "Cat");
        assertEquals("Dog", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    void removeFirst() {
        MyList<String> list = new MyLinkedList<>();
        list.addFirst("Job");
        list.addFirst("Dog");
        list.addFirst("Cat");
        String firstRemove = list.removeFirst();
        assertEquals("Cat", firstRemove);
        assertEquals("Dog", list.get(0));
        assertEquals(2, list.size());
    }

    @Test
    void removeLast() {
        MyList<String> list = new MyLinkedList<>();
        list.addFirst("Job");
        list.addFirst("Dog");
        list.addFirst("Cat");
        String lastRemove = list.removeLast();
        assertEquals("Job", lastRemove);
        assertEquals("Cat", list.get(0));
        assertEquals(2, list.size());
    }

    @Test
    void remove() {
        MyList<String> list = new MyLinkedList<>();
        list.add(0, "Job");
        list.add(1, "Dog");
        list.add(2, "Cat");
        String remove = list.remove(1);
        assertEquals("Dog", remove);
        assertEquals("Cat", list.get(1));
        assertEquals(2, list.size());
    }

}