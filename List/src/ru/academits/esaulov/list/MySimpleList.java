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
        if (head != null) {
            ListItem<T> p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(addItem);
            length++;
        } else {
            head = addItem;
            length++;
        }
    }

    public T getValueFistItem() {
        if (length != 0) {
            return head.getData();
        } else {
            return null;
        }
    }

    public void insertFirstItem(T data) {
        ListItem<T> firstItem = new ListItem<>(data);
        firstItem.setNext(head.getNext());
        head = firstItem;
        length++;
    }

    public T removeFistItem() {
        if (length > 1) {
            T savedOldData = head.getData();
            head = head.getNext();
            length--;
            return savedOldData;
        } else if (length == 1) {
            T savedOldData = head.getData();
            head = null;
            length--;
            return savedOldData;
        } else {
            throw new ArrayIndexOutOfBoundsException("коллекция пуста");
        }
    }

    public T getValueByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        } else {
            int i = 0;
            ListItem<T> p = head;
            while (i < index) {
                p = p.getNext();
                i++;
            }
            return p.getData();
        }
    }

    public T setValueByIndex(int index, T data) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        } else {
            int i = 0;
            ListItem<T> p = head;
            while (i < index) {
                p = p.getNext();
                i++;
            }
            T savedOldValue = p.getData();
            p.setData(data);
            return savedOldValue;
        }
    }

    public T removeItemByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("индекс неверный");
        } else {
            if (index == 0) {
                length --;
                return removeFistItem();
            } else {
                ListItem<T> prevItem = head;
                ListItem<T> p = head.getNext();
                int i = 1;
                while (i < index) {
                    prevItem = prevItem.getNext();
                    p = p.getNext();
                    i++;
                }
                prevItem.setNext(p.getNext());
                length--;
                return p.getData();
            }
        }
    }

    public boolean removeItemByValue(T data) {
        ListItem<T> p = head;
        int i = 0;
        while (i < length) {
            if (p.getData().equals(data)) {
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
        } else {
            if (index == length) {
                add(data);
                length++;
            } else if (index == 0) {
                insertFirstItem(data);
                length++;
            } else {
                ListItem<T> prevItem = head;
                ListItem<T> p = head.getNext();
                int i = 1;
                while (i < index) {
                    prevItem = prevItem.getNext();
                    p = p.getNext();
                    i++;
                }
                ListItem<T> insertedByIndex = new ListItem<>(data);
                prevItem.setNext(insertedByIndex);
                insertedByIndex.setNext(p);
                length++;
            }
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

    public void copy(MySimpleList<T> list) {
        for (ListItem<T> p = list.head; p != null; p = p.getNext()) {
            this.add(p.getData());
        }
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
