import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> listFautes = new ArrayList<>();

        File file = new File("C:\\Users\\talha\\Documents\\TP 2 Algo\\src\\fautes.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String lines;
        while ((lines = buffer.readLine()) != null) listFautes.add(lines);

        Dictionary dictionary = new Dictionary("C:\\Users\\talha\\Documents\\TP 2 Algo\\src\\dico.txt");
        //Exemple avec une liste de mots stockés dans un fichier
        Correction.printResults(dictionary, listFautes);

        //Exemple avec un seul mot
        Correction.printResults(dictionary, List.of("maisno"));
    }
}
