package ru.academits.esaulov.myarraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;

    public MyArrayList(int capacity) {
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    public void trimToSize() {
        //noinspection unchecked
        T[] newItems = (T[]) new Object[length];
        System.arraycopy(items, 0, newItems, 0, length);
        items = newItems;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > 0;
    }

    @Override
    public Iterator<T> iterator() {

        //TODO
        return null;
    }

    @Override
    public T[] toArray() {
        //noinspection unchecked
        T[] array = (T[]) new Object[length];
        System.arraycopy(items, 0, array, 0, array.length);
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //TODO
        return null;
    }

    @Override
    public boolean add(T t) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = t;
        length++;
        return true;
    }

    private void increaseCapacity() {
        T[] old = items;
        //noinspection unchecked
        items = (T[]) new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    public void ensureCapacity(int capacity) {
        //noinspection unchecked
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, capacity);
        items = newItems;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                if (i == length - 1) {
                    length--;
                    return true;
                }
                System.arraycopy(items, i + 1, items, i, length - 1 - i);
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        while (items.length < length + c.size()) {
            increaseCapacity();
        }
        for (T e : c) {
            add(e);
            length++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        while (items.length < length + c.size()) {
            increaseCapacity();
        }
        //noinspection unchecked
        T[] tailArray = (T[]) new Object[length - index];
        System.arraycopy(items, index, tailArray, 0, length - index);
        int i = index;
        for (T e : c) {
            add(i, e);
            i++;
            length++;
        }
        System.arraycopy(tailArray, 0, items, length, tailArray.length);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean flag = false;
        for (Object e : c) {
            if (contains(e)) {
                remove(e);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("неверный индекс");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        items[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        T saveElement = items[index];
        set(index, element);
        for (int i = index; index < length; i++) {
            T saveNext = items[i + 1];
            items[i + 1] = saveElement;
            saveElement = saveNext;

            if (i == length - 1) {
                items[length] = saveElement;
            }
        }
        length++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > length - 1) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        T saveElement = items[index];
        if (index == length - 1) {
            length--;
            return saveElement;
        }
        System.arraycopy(items, index + 1, items, index, length - 1 - index);
        length--;
        return saveElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        if (length == 0) {
            return "{список пуст}";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            sb.append(items[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
