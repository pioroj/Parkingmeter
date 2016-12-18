package exerciseOne;


public class BinaryOnes {

    public static void main(String[] args) {
        int number = 1;
        for (int i = 1; i <= 15; i++) {
            System.out.println(number);
            number = number * 2 + 1;
        }

        System.out.println("Drugi sposób");
        long pow = 1;
        long num = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(number);
            pow = pow << 1; //bitowe przesunięcie w lewo czyli mnożenie
            num = num | pow; // bitowe dodawanie
        }

    }
}
