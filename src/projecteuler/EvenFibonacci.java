package projecteuler;


public class EvenFibonacci {

    public static void main(String[] args) {

        String name = "adxa";
        System.out.println(name.indexOf("x"));
        //sumEvenFibo(4000000);

    }

    private static void sumEvenFibo(int max) {
        int first;
        int second = 0;
        int sum = 1;
        int totalSum = 0;

        do {
            first = second;
            second = sum;
            sum = first + second;
            if (sum % 2 == 0) {
                totalSum += sum;
            }
            System.out.println(sum);
        } while (sum + second <= max);
        System.out.println("Total sum: " + totalSum);
    }

}
