package ru.academits.esaulov.myarraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private int modCount = 0;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    public void trimToSize() {
        if (items.length > length) {
            //noinspection unchecked
            T[] newItems = (T[]) new Object[length];
            System.arraycopy(items, 0, newItems, 0, length);
            items = newItems;
        }
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int modCount1 = modCount;
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount1 != modCount) {
                    throw new ConcurrentModificationException();
                }
                return items[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
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
        if (a.length < length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = t;
        length++;
        modCount++;
        return true;
    }

    private void increaseCapacity() {
        T[] old = items;
        //noinspection unchecked
        items = (T[]) new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        if (items.length < capacity) {
            //noinspection unchecked
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, capacity);
            items = newItems;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                remove(i);
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
        //noinspection unchecked
        T[] collection = (T[]) c.toArray();
        if (items.length < length + c.size()) {
            ensureCapacity(length + c.size());
        }
        System.arraycopy(collection, 0, items, length, collection.length);
        modCount++;
        length = length + collection.length;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (c.size() == 0) {
            return false;
        }
        if (items.length < length + c.size()) {
            ensureCapacity(length + c.size());
        }
        //noinspection unchecked
        T[] collection = (T[]) c.toArray();
        System.arraycopy(items, index, items, index + c.size(), length - index);
        modCount++;
        System.arraycopy(collection, 0, items, index, collection.length);
        length = length + collection.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean isModify = false;
        for (Object e : c) {
            if (remove(e)) {
                isModify = true;
            }
        }
        return isModify;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean isModify = false;
        for (int i = 0; i < length; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                isModify = true;
            }
        }
        return isModify;
    }

    @Override
    public void clear() {
        length = 0;
        modCount++;
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
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        items[index] = element;
        modCount++;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        if (items.length == length) {
            increaseCapacity();
        }
        System.arraycopy(items, index, items, index + 1, length - index);
        set(index, element);
        length++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        T saveElement = items[index];
        if (index == length - 1) {
            length--;
            modCount++;
            return saveElement;
        }
        System.arraycopy(items, index + 1, items, index, length - 1 - index);
        length--;
        modCount++;
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
