import java.util.*;

public class Correction {

    private static Map<String, Integer> sortedMap = new LinkedHashMap<>();
    private static TreeSet<String> possibleAnswers = new TreeSet<>();


    public static List<String> correction (Dictionary dictionary, String word) {

        if (dictionary.contains(word)) {
            System.out.println("Le mot est correctement orthographié");
            return null;
        }

        Map<String, List<String>> wordTrigrams = new HashMap<>(Trigram.buildTrigrams(Arrays.asList(word)));

        List<String> commonWords = new ArrayList<>();
        for (String wordTrigram : wordTrigrams.keySet()) {
            if (dictionary.getTrigrams().get(wordTrigram) != null) {
                commonWords.addAll(dictionary.getTrigrams().get(wordTrigram));
            }
        }

        Map<String, Integer> occurenceCount = new HashMap<>();
        for (String string : commonWords) {
            if (!occurenceCount.containsKey(string)) {
                occurenceCount.put(string, 1);
                continue;
            }
            occurenceCount.put(string, occurenceCount.get(string) + 1);
        }

        if (occurenceCount.size() <= 100) {
            possibleAnswers.addAll(occurenceCount.keySet());
        }
        else {
            List<Map.Entry<String, Integer>> list = new LinkedList<>(occurenceCount.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            sortedMap.clear();
            for (Map.Entry<String, Integer> entry : list) {
                possibleAnswers.add(entry.getKey());
            }
            System.out.println(sortedMap);
        }

        //Calculer les mots les plus proches au sens de la distance
        //TODO
        return null;
    }

    public static void printSolutions (Dictionary dictionary, String word) {
        List<String> words = correction(dictionary, word);
        if (words == null) {
            System.out.println("Déjà correct");
        }
        else {
            System.out.println("Voici les meilleures corrections possibles pour votre mot : \n");
            for (int i = 0; i < words.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + words.get(i));
            }
            System.out.println("Choisissez un mot");
            Scanner sc = new Scanner(System.in);
            int selectedWord = sc.nextInt();
            System.out.println(words.get(selectedWord));
        }
    }
}