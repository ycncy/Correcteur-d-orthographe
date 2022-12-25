import java.io.*;
import java.util.*;

public class Dictionary {

    private Set<String> words = new TreeSet<>();
    private Map<String, List<String>> trigrams;

    public Dictionary(String path) throws IOException {
        File file = new File(path);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String lines;
        while ((lines = buffer.readLine()) != null) words.add(lines);
        trigrams = Trigram.buildTrigrams(words.stream().toList());
    }

    public boolean contains(String word) {
        for (String dictionaryWord : words) {
            if (dictionaryWord.equals(word)) return true;
        }
        return false;
    }

    public Map<String, List<String>> getTrigrams() {
        return trigrams;
    }
}
