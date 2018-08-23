package ru.academits.esaulov.list;

import ru.academits.esaulov.list.listitem.ListItem;

public class MySimpleList<T> {
    private int length;
    private ListItem<T> head;

    public int getLength() {
        return length;
    }

    public void add(T data) {
        ListItem<T> addItem = new ListItem<>(data);
        if (head == null) {
            head = addItem;
            length++;
        } else {
            ListItem<T> p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(addItem);
            length++;
        }
    }

    public T getValueFistItem() {
        if (length != 0) {
            return head.getData();
        }
        throw new NullPointerException("сптсок пуст");
    }

    public void insertFirstItem(T data) {
        ListItem<T> firstItem = new ListItem<>(data);
        firstItem.setNext(head);
        head = firstItem;
        length++;
    }

    public T removeFistItem() {
        if (length > 1) {
            T savedOldData = head.getData();
            head = head.getNext();
            length--;
            return savedOldData;
        }
        if (length == 1) {
            T savedOldData = head.getData();
            head = null;
            length--;
            return savedOldData;
        }
        throw new ArrayIndexOutOfBoundsException("коллекция пуста");
    }

    public ListItem<T> getItemByIndex(int index) {
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
            length--;
            return removeFistItem();
        }
        ListItem<T> prevItem = getItemByIndex(index - 1);
        ListItem<T> p = getItemByIndex(index);
        prevItem.setNext(p.getNext());

        length--;
        return p.getData();

    }

    public boolean removeItemByValue(T data) {
        ListItem<T> p = head;
        int i = 0;
        while (i < length) {
            if (p.getData() == data || p.getData().equals(data)) {
                removeItemByIndex(i);
                length--;
                return true;
            }
            p = p.getNext();
            i++;
        }
        return false;
    }

    public void insertItemByIndex(int index, T data) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException("неверный индекс");
        }
        if (index == length) {
            add(data);
        } else if (index == 0) {
            insertFirstItem(data);
        } else {
            ListItem<T> prevItem = getItemByIndex(index - 1);
            ListItem<T> p = getItemByIndex(index);
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
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            copyMySimpleList.add(p.getData());
        }
        return copyMySimpleList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            sb.append("{").append(p.getData()).append("}");
        }
        return sb.toString();
    }
}
