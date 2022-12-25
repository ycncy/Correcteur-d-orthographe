import java.util.*;

public class Correction {

    public static Map<String, List<String>> correction (Dictionary dictionary, List<String> words) {

        Map<String, List<String>> correctWords = new HashMap<>();

        for (String word : words) {

            if (dictionary.contains(word)) {
                System.out.println("Le mot est correctement orthographié");
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

            List<String> possibleAnswers = new ArrayList<>();

            if (occurenceCount.size() <= 100) {
                possibleAnswers.addAll(occurenceCount.keySet());
            } else {
                List<Map.Entry<String, Integer>> list = new LinkedList<>(occurenceCount.entrySet());
                list.sort(new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                int count = 0;
                for (Map.Entry<String, Integer> entry : list) {
                    if (count == 100) break;
                    possibleAnswers.add(entry.getKey());
                    count++;
                }
            }

            Map<String, Integer> distanceMap = new HashMap<>();
            int count = 0;
            for (String string : possibleAnswers) {
                if (count == 100) break;
                distanceMap.put(string, Trigram.dynamicLevenshteinDistance(word, string));
                count++;
            }

            List<Map.Entry<String, Integer>> list = new LinkedList<>(distanceMap.entrySet());
            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            List<String> correctWordsValue = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : list) {
                correctWordsValue.add(entry.getKey());
            }
            correctWords.put(word, correctWordsValue.subList(0, 5));
        }
        return correctWords;
    }

    public static void printResults(Dictionary dictionary, List<String> words) {
        for (String word : words) {
            System.out.println("Votre mot : " + word);
            int i = 1;
            System.out.println("Voici les 5 corrections possibles pour ce mot : \n");
            for (String correctWords : correction(dictionary, words).get(word)) {
                System.out.println("Mot n°" + i + " : " + correctWords);
                i++;
            }
        }
    }
}