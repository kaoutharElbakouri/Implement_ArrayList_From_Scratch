package io.github.kaoutharelbakouri;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Paquet<T> implements Iterable<T> {
    private int size;
    private T[] tab;

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return tab[currentIndex++];
            }

            @Override
            public void remove() {
                Paquet.this.remove(--currentIndex);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public Paquet() {
        size = 0;
        tab = (T[]) new Object[10];
    }

    public void add(T e) {
        if (size == tab.length) {
            tab = Arrays.copyOf(tab, size * 2 + 1);
        }
        tab[size++] = e;
    }

    public void add(int index, T item) {
        checkArrayIndexOutOfBounds(index);
        if (size == tab.length) {
            tab = Arrays.copyOf(tab, size * 2 + 1);
        }
        for (int i = size; i > index; i--) {
            tab[i] = tab[i - 1];
        }
        tab[index] = item;
        size++;
    }

    public void remove(int index) {
        checkArrayIndexOutOfBounds(index);
        for (int i = index; i < size - 1; i++) {
            tab[i] = tab[i + 1];
        }
        size--;
    }

    public int getSize() {
        return size;
    }

    public T get(int index) {
        checkArrayIndexOutOfBounds(index);
        return tab[index];
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Les elements de notre Collection sont :");
        for (T t : this) {
            s.append(" ").append(t);
        }
        return s.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, T newVal) {
        checkArrayIndexOutOfBounds(index);
        tab[index] = newVal;
    }

    private void checkArrayIndexOutOfBounds(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + " is out of Bounds");
        }
    }
}
