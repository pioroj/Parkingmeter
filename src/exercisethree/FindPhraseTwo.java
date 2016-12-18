package exercisethree;


import java.util.Set;
import java.util.TreeSet;

public class FindPhraseTwo {

    public static void main(String[] args) {

        String text = "Ala ma kota la la la";
        String phrase = "la";

        String text1 = "aaaaaaaa";
        String phrase1 = "aa";

        String text2 = "Papa papa";
        String phrase2 = "PP";


        System.out.println(findPhrase(text, phrase));
        System.out.println(findPhrase(text1, phrase1));
        System.out.println(findPhrase(text2, phrase2));


    }

    private static Set<Integer> findPhrase(String text, String phrase) {
        Set<Integer> result = new TreeSet<>();
        int textLength = text.length();
        int phraseLength = phrase.length();


        for (int i = text.indexOf(phrase); i <= textLength - phraseLength; i++) {
            result.add(text.indexOf(phrase, i));
        }

        if (result.contains(-1)) {
            result.remove(-1);
        }
        return result;
    }

    private static Set<Integer> findphraseSecond(String text, String phrase) {
        Set<Integer> result = new TreeSet<>();
        int lastFound;
        int nextIndexToLook = 0;
        while ((lastFound = text.indexOf(phrase, nextIndexToLook)) != -1) {
            result.add(lastFound);
            nextIndexToLook = lastFound + 1;
        }
        return result;
    }

}
