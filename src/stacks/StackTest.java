package stacks;


public class StackTest {
    public static void main(String[] args) {
        ArrayStack<Integer> table = new ArrayStack<>(3);

        table.push(1);
        table.push(2);
        table.push(3);
        table.pop();
        table.pop();

        System.out.println(table);
    }
}
