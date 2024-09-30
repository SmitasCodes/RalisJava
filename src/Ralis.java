
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ralis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        failoNuskaitymas();
        System.out.println("Sveiki atvyke y \"Ralis\" programa!");
        System.out.println("Programos funkciju sarasas yra sis:");
        System.out.println("1: Masinu saraso nuskaitymas ir parodymas");

        System.out.println("Prasome pasirinkti norima funkcija ivedus numeriuka nuo 1 iki 5");
        int pasirinkimas = Integer.valueOf(scanner.nextLine());

        switch (pasirinkimas) {
            case 1:
                sarasoSpausdinimas();
                break;
        }
    }

    // ##########################
    // Failo nuskaitymo funkcija
    // ##########################
    protected static void failoNuskaitymas() {
        InputStream inputStream = Ralis.class.getResourceAsStream("/resources/test.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String eilute;
            while ((eilute = br.readLine()) != null) {
                String[] suformatuotaEilute = eilute.split(", ");
                Masina naujaMasina = new Masina(suformatuotaEilute[0], suformatuotaEilute[1],
                        Double.parseDouble(suformatuotaEilute[2]), Integer.parseInt(suformatuotaEilute[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ##########################
    // 1. Saraso spausdinimas
    // ##########################
    public static void sarasoSpausdinimas() {
        System.out.println("Works");
    }

}

class Masina {
    private String marke;
    private String modelis;
    private double turis;
    private int maxGreitis;

    // Declare fields for the Masinos class
    public Masina(String marke, String modelis, double turis, int maxGreitis) {
        this.marke = marke;
        this.modelis = modelis;
        this.turis = turis;
        this.maxGreitis = maxGreitis;
    }
}
