package projecteuler;


public class Multiples {
    public static void main(String[] args) {
        int sum = getSum(1000);
        System.out.println("The sum is: " + sum);
    }

    private static int getSum(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }


}
