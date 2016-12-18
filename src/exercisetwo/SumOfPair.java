package exercisetwo;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Dopisać FUNKCJĘ statyczną!!! Można dodać klasę PAIR z własy
public class SumOfPair {

    public static void main(String[] args) {

        int[] table = {1, 4, 5, 3, 6, 3, 6, 1};
        int x = 7;

        Iterator<String> itr = pairs(table, x).iterator();

        while (itr.hasNext()) {
            System.out.println("Pairs: " + itr.next().toString());
        }

    }

    public static Set<String> pairs(int[] table, int x) {
        Set<String> setOfPairs = new HashSet<String>();

        for (int i = 0; i < table.length - 1; i++) {
            int current = table[i]; ///dodanie current optymalizuje kod, pobieranie danych z pamięci kosztuje
            for (int j = i + 1; j < table.length; j++) {
                if (current + table[j] == x) {
                    if (setOfPairs.contains(table[j] + " and " + current))
                        continue;
                    setOfPairs.add(current + " and " + table[j]);
                }
            }
        }
        return setOfPairs;
    }

}
