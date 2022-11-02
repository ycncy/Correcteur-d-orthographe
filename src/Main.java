import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary("C:\\Users\\talha\\IdeaProjects\\TP 2 Algo\\src\\dico.txt");
        //System.out.println("La distance la plus basse est de : " + Trigram.dynamicLevenshteinDistance("Orgabbs", "Organes"));
        Correction.printSolutions(dictionary, "joure");
    }
}
