package ru.academits.esaulov.myarraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private int modCount = 0;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("не верный параметр");
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
            items = Arrays.copyOf(items, length);
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
            private int savedModCount = modCount;
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("следующего нет");
                }
                if (savedModCount != modCount) {
                    throw new ConcurrentModificationException("список изменен во время прохода итератора");
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
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
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
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("не верный параметр");
        }
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
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
        ensureCapacity(length + c.size());
        for (T e : c) {
            items[length] = e;
            length++;
            modCount++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("не верный параметр");
        }
        if (c.size() == 0) {
            return false;
        }
        ensureCapacity(length + c.size());
        System.arraycopy(items, index, items, index + c.size(), length - index);
        modCount++;
        int i = index;
        for (T e : c) {
            items[i++] = e;
            modCount++;
            length++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean isModified = false;
        for (int i = 0; i < length; i++) {
            if (c.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            clear();
            return true;
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
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        items[index] = element;
        modCount++;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("не верный индекс");
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
            throw new IndexOutOfBoundsException("не верный индекс");
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
