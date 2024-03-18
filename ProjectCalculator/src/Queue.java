import java.util.ArrayList;
import java.util.EmptyStackException;

public class Queue<T> {
    private ArrayList<T> elements;
    public Queue() {
        elements = new ArrayList<T>();
    }
    public void enqueue(T item) {
        elements.add(item);
    }
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = elements.get(0);
        elements.remove(0);
        return item;
    }
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(0);
    }
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    public int size() {
        return elements.size();
    }
    public void clear() {
        elements.clear();
    }
    public void printQueue() {
        for (T item : elements) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}