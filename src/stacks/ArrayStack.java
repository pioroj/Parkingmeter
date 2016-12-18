package stacks;


public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_SIZE = 100;
    private E[] stack;
    private int index = -1;

    public ArrayStack(int size) {
        stack = (E[]) new Object[size];
    }

    public ArrayStack() {
        stack = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        E poppedElement = stack[index];
        stack[index] = null;
        index--;
        return poppedElement;
    }

    @Override
    public void push(E e) {
        if (index >= stack.length - 1) {
            throw new IllegalStateException("Stack can not hold more objects.");
        }
        index++;
        stack[index] = e;
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    public String toString() {
        if (isEmpty()) {
            return "Stack is empty";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            if (i == 0)
                sb.append(stack[i]);
            else
                sb.append(" ").append(stack[i]);
        }

        return sb.toString();
    }
}
