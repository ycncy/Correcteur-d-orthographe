import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary {

    private Set<String> words = new TreeSet<>();
    private int[] distance;

    public Dictionary(List<String> words) {
        this.words.addAll(words);
    }

    public int levenshteinDistance(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        if (word1.charAt(0) == word2.charAt(0)) return levenshteinDistance(word1.substring(1), word2.substring(1));
        return Math.min(Math.min(levenshteinDistance(word1.substring(1), word2) + 1, levenshteinDistance(word1, word2.substring(1)) + 1), levenshteinDistance(word1.substring(1), word2.substring(1)) + 1);
    }
}
