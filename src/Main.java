import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(Arrays.asList("Antarctique", "Coutellerie", "Organes", "Publication", "Russie", "Balise", "Mercenaire", "Approche", "Fraise", "Framboise"));
        System.out.println("La distance la plus basse est de : " + dictionary.levenshteinDistance("Orgabbs", "Organes"));
    }
}
