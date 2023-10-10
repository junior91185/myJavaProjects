import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {
    private ArrayList<T> elements;

    public Stack() {
        elements = new ArrayList<T>();
    }
    public void push(T item) {
        elements.add(item);
    }
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int lastIndex = elements.size() - 1;
        T item = elements.get(lastIndex);
        elements.remove(lastIndex);
        return item;
    }
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
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
    public void printStack() {
        for (T item : elements) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}