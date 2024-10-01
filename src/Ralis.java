
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Ralis {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        failoNuskaitymas();
        System.out.println("Sveiki atvyke y \"Ralis\" programa!");
        System.out.println("Programos funkciju sarasas yra sis:");
        System.out.println("1: Masinu saraso nuskaitymas ir parodymas");
        System.out.println("2: Vidutinis visu automobiliu varikliu turio skaiciavimas");

        System.out.println("Prasome pasirinkti norima funkcija ivedus numeriuka nuo 1 iki 5");
        int pasirinkimas = Integer.valueOf(scanner.nextLine());

        switch (pasirinkimas) {
            case 1:
                sarasoSpausdinimas();
                break;
            case 2:
                vidutinioTurioSkaiciavimas();
                break;
            case 3:
                paieskaMarkeModelis("");
                break;
            case 4:
                paieskaMarkeModelisGreitis();
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

    static ArrayList<Masina> masinuSarasas = Masina.gautiMasinuSarasa();

    // ##########################
    // 1. Saraso spausdinimas
    // ##########################
    public static void sarasoSpausdinimas() {
        for (Masina masina : masinuSarasas) {
            System.out.println(masina);
        }

    }

    // ##########################
    // 2. Vidutinio turio skaiciavimas
    // ##########################
    public static void vidutinioTurioSkaiciavimas() {
        int kiekis = masinuSarasas.size();
        double bendrasTuris = 0;

        for (Masina masina : masinuSarasas) {
            bendrasTuris += masina.turis;
        }

        double vidutinisTuris = (double) bendrasTuris / kiekis;
        DecimalFormat skaiciausFormatas = new DecimalFormat("#.00");

        System.out.println("Bendras visu masinu vidutinis turis: " + skaiciausFormatas.format(vidutinisTuris) + "L");
    }

    // ##########################
    // 3. Paieska pagal marke ir modeli
    // ##########################
    public static ArrayList<Masina> paieskaMarkeModelis(String returnList) {
        System.out.println("Iveskite masinos marke:");
        String masinosMarke = scanner.nextLine();
        System.out.println("Iveskite masinos modeli:");
        String masinosModelis = scanner.nextLine();

        ArrayList<Masina> atrinktosMasinos = new ArrayList<>();

        for (Masina masina : masinuSarasas) {
            if (masina.marke.contains(masinosMarke)) {
                if (masina.modelis.contains(masinosModelis)) {
                    atrinktosMasinos.add(masina);
                }
            }
        }

        if (!returnList.equals("returnList")) {
            for (Masina masina : atrinktosMasinos) {
                System.out.println(masina);
            }
        }

        return atrinktosMasinos;
    }

}

class Masina {
    public String marke;
    public String modelis;
    public double turis;
    private int maxGreitis;

    private static ArrayList<Masina> masinuSarasas = new ArrayList<>();

    // Declare fields for the Masinos class
    public Masina(String marke, String modelis, double turis, int maxGreitis) {
        this.marke = marke;
        this.modelis = modelis;
        this.turis = turis;
        this.maxGreitis = maxGreitis;
        masinuSarasas.add(this);
    }

    public static ArrayList<Masina> gautiMasinuSarasa() {
        return masinuSarasas;
    }

    // Pakeiciamas toString metodas, kad butu gaunama informacija apie automobilius
    // is objektu
    @Override
    public String toString() {
        return String.format("Marke: %s, Modelis: %s, Turis: %.1fL, Max Greitis: %d km/h",
                marke, modelis, turis, maxGreitis);
    }
}
