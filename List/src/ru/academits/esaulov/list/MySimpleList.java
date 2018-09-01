package ru.academits.esaulov.list;

import ru.academits.esaulov.list.listitem.ListItem;

import java.util.Objects;

public class MySimpleList<T> {
    private int length;
    private ListItem<T> head;

    public int getLength() {
        return length;
    }

    public T getValueFistItem() {
        if (length == 0) {
            throw new NullPointerException("сптсок пуст");
        }
        return head.getData();
    }

    public void insertFirstItem(T data) {
        head = new ListItem<>(data, head);
        length++;
    }

    public T removeFistItem() {
        if (length == 0) {
            throw new ArrayIndexOutOfBoundsException("коллекция пуста");
        }
        if (length == 1) {
            T savedOldData = head.getData();
            head = null;
            length--;
            return savedOldData;
        }
        T savedOldData = head.getData();
        head = head.getNext();
        length--;
        return savedOldData;
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> p = head;
        int i = 0;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        return p;
    }

    public T getValueByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        }
        return getItemByIndex(index).getData();
    }

    public T setValueByIndex(int index, T data) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        }

        ListItem<T> foundItemByIndex = getItemByIndex(index);
        T savedOldValue = foundItemByIndex.getData();
        foundItemByIndex.setData(data);
        return savedOldValue;
    }

    public T removeItemByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        }
        if (index == 0) {
            return removeFistItem();
        }

        ListItem<T> prevItem = getItemByIndex(index - 1);
        ListItem<T> p = prevItem.getNext();
        prevItem.setNext(p.getNext());
        length--;
        return p.getData();
    }

    public boolean removeItemByValue(T data) {
        ListItem<T> prev = null;
        ListItem<T> p = head;
        int i = 0;
        while (i < length) {
            if (!Objects.equals(p.getData(), data)) {
                prev = p;
                p = p.getNext();
                i++;
            } else {
                if (i == 0) {
                    head = p.getNext();
                    length--;
                    return true;
                } else {
                    prev.setNext(p.getNext());
                    length--;
                    return true;
                }
            }
        }
        return false;
    }

    public void add(T data) {
        insertItemByIndex(length, data);
    }

    public void insertItemByIndex(int index, T data) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException("неверный индекс");
        }
        if (index == 0) {
            insertFirstItem(data);
        } else if (index == length) {
            ListItem<T> lastItem = getItemByIndex(length - 1);
            lastItem.setNext(new ListItem<>(data));
            length++;
        } else {
            ListItem<T> prevItem = getItemByIndex(index - 1);
            ListItem<T> p = prevItem.getNext();
            ListItem<T> insertedByIndex = new ListItem<>(data);
            prevItem.setNext(insertedByIndex);
            insertedByIndex.setNext(p);
            length++;
        }
    }

    public void reverseList() {
        if (length > 1) {
            ListItem<T> prev = null;
            ListItem<T> current = head;
            while (current != null) {
                ListItem<T> temp = current.getNext();
                current.setNext(prev);
                prev = current;
                head = current;
                current = temp;
            }
        }
    }

    public MySimpleList<T> copy() {
        MySimpleList<T> copyMySimpleList = new MySimpleList<>();
        copyMySimpleList.length = length;
        if (length == 0) {
            return copyMySimpleList;
        }

        copyMySimpleList.head = new ListItem<>(head.getData());
        ListItem<T> cp = copyMySimpleList.head;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            cp.setNext(new ListItem<>(p.getData()));
            cp = cp.getNext();
        }
        return copyMySimpleList;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[список пуст]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            sb.append(p.getData());
            if (p.getNext() != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
