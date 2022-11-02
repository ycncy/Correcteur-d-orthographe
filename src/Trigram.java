import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trigram {

    public static Map<String, List<String>> buildTrigrams(List<String> words) {
        Map<String, List<String>> trigrams = new HashMap<>();
        for (String word : words) {
            String newWord = "<" + word + ">";
            for (int i = 0; i < newWord.length() - 2; i++) {
                if (trigrams.containsKey(newWord.substring(i, i + 3))) trigrams.get(newWord.substring(i, i + 3)).add(word);
                else {
                    trigrams.put(newWord.substring(i, i + 3), new ArrayList<>());
                    trigrams.get(newWord.substring(i, i + 3)).add(word);
                }
            }
        }
        return trigrams;
    }

    /*public static int dynamicLevenshteinDistance(String word1, String word2) {
        int[][] dynamicTab = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) dynamicTab[i][0] = i;
        for (int j = 0; j <= word2.length(); j++) dynamicTab[0][j] = j;

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == (word2.charAt(j - 1))) dynamicTab[i][j] = dynamicTab[i - 1][j - 1];
                else {
                    dynamicTab[i][j] = 1 + Math.min(Math.min(dynamicTab[i][j - 1], dynamicTab[i - 1][j]), dynamicTab[i - 1][j - 1]);
                }
            }
        }
        return dynamicTab[word1.length()][word2.length()];
    }*/

    public static int levenshteinDistance(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        if (word1.charAt(0) == word2.charAt(0)) return levenshteinDistance(word1.substring(1), word2.substring(1));
        return Math.min(Math.min(levenshteinDistance(word1.substring(1), word2) + 1, levenshteinDistance(word1, word2.substring(1)) + 1), levenshteinDistance(word1.substring(1), word2.substring(1)) + 1);
    }
}
