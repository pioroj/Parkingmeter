package exercisethree;


import java.util.Set;
import java.util.TreeSet;

public class CheckString {

    public static void main(String[] args) {

        String text = "Ala ma kota la la la";
        String phrase = "la";

        String text1 = "Papa papa";
        String phrase1 = "pa";

        String text2 = "Papa papa";
        String phrase2 = "PP";

        checkString(text, phrase);
        checkString(text1, phrase1);
        checkString(text2, phrase2);
        System.out.println(findPhrase("Alala", "la"));

    }

    private static void checkString(String text, String phrase) {
        int phraseLength = phrase.length();
        int textLength = text.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= textLength - phraseLength; i++) {
            if (phrase.equals(text.substring(i, i + phraseLength))) {
                sb.append(i).append(", ");
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        System.out.println("Result = [" + sb.toString() + "]");
    }

    public static Set<Integer> findPhrase(String text, String phrase) {
        Set<Integer> result = new TreeSet<>();
        int textLength = text.length();
        int phraseLength = phrase.length();

        for (int i = 0; i <= textLength - phraseLength; i++) {
            boolean mismatch = false;
            for (int j = 0; j < phraseLength && mismatch; j++) {
                if (text.charAt(i + j) != phrase.charAt(j)) {
                    mismatch = true;
                }
            }
            if (!mismatch)
                result.add(i);
        }
        return result;
    }

}
