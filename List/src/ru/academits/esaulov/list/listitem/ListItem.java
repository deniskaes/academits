package ru.academits.esaulov.list.listitem;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    public ListItem(T data) {
        this.data = data;
    }

    public ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    public ListItem<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        if (this.getData() == null) {
            return "null";
        }
        return data.toString();
    }
}
