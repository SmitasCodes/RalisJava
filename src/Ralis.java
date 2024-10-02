
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
        System.out.println("Sveiki atvyke y \"Ralis\" programa!");
        failoNuskaitymas();
        menu();
    }

    // Menu funkcija
    public static void menu() {
        System.out.println("\nProgramos funkciju meniu yra sis:");
        System.out.println("1: Masinu saraso nuskaitymas ir parodymas.");
        System.out.println("2: Vidutinis visu automobiliu varikliu turio skaiciavimas.");
        System.out.println("3: Paieska pagal marke ir modeli.");
        System.out.println("4: Paieska pagal marke, modeli bei maksimalu greiti.");
        System.out.println("5: Saraso rikiavimas pagal marke ir maksimalu greiti.");

        System.out.println("\nPrasome pasirinkti norima funkcija ivedus numeriuka nuo 1 iki 5");
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
            case 5:
                sarasoRikiavimas();
        }
    }

    // Funkcija skirta pasirinkti tolimesni veiksma po ralio funkciju ivykdymo
    public static void veiksmoPasirinkimas( ) {
        System.out.println("Norint sugryzti i menu spauskite - y");
        System.out.println("Programos sustabdymui spauskite - n");
        String ivestis = scanner.nextLine();
        if (ivestis.equals("y")) {
            menu();
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
        System.out.println("");
        for (Masina masina : masinuSarasas) {
            System.out.println(masina);
        }
        System.out.println("");
        veiksmoPasirinkimas();
    }

    // ##########################
    // 2. Vidutinio turio skaiciavimas
    // ##########################
    public static void vidutinioTurioSkaiciavimas() {
        int kiekis = masinuSarasas.size();
        double bendrasTuris = 0;

        for (Masina masina : masinuSarasas) {
            bendrasTuris += masina.getTuris();
        }

        double vidutinisTuris = (double) bendrasTuris / kiekis;
        DecimalFormat skaiciausFormatas = new DecimalFormat("#.00");

        System.out
                .println("\nBendras visu masinu vidutinis turis yra: " + skaiciausFormatas.format(vidutinisTuris)
                        + "l\n");

        veiksmoPasirinkimas();
    }

    // ##########################
    // 3. Paieska pagal marke ir modeli
    // ##########################
    public static ArrayList<Masina> paieskaMarkeModelis(String returnList) {
        System.out.println("");
        System.out.println("Iveskite masinos marke:");
        String masinosMarke = scanner.nextLine();
        System.out.println("Iveskite masinos modeli:");
        String masinosModelis = scanner.nextLine();

        ArrayList<Masina> atrinktosMasinos = new ArrayList<>();

        for (Masina masina : masinuSarasas) {
            if (masina.getMarke().contains(masinosMarke) &&
                    masina.getModelis().contains(masinosModelis)) {
                atrinktosMasinos.add(masina);
            }
        }

        if (!returnList.equals("returnList")) {
            if (!atrinktosMasinos.isEmpty()) {
                for (Masina masina : atrinktosMasinos) {
                    System.out.println("\nPaieskos rezultai:");
                    System.out.println(masina + "\n");
                }
            } else {
                System.out.println("\nNera tokios masinos pagal ivesta marke ir modeli!\n");
            }

            System.out.println("Jeigu norite atlikti paieska is naujo spauskite - r");
            System.out.println("Norint sugryzti i menu spauskite - y");
            System.out.println("Programos sustabdymui spauskite - n");
            String ivestis = scanner.nextLine();
            if (ivestis.equals("r")) {
                paieskaMarkeModelis("");
            } else if (ivestis.equals("y")) {
                menu();
            }
        }

        return atrinktosMasinos;
    }

    // ##########################
    // 4. Paieska pagal marke, modeli ir max greiti
    // ##########################
    public static void paieskaMarkeModelisGreitis() {
        ArrayList<Masina> atrinktosMasinos = paieskaMarkeModelis("returnList");
        ArrayList<Masina> atrinktosMasinosSuGreiciu = new ArrayList<>();
        System.out.println("Iveskite masinos max greiti:");
        int maxGreitis = Integer.valueOf(scanner.nextLine());

        for (Masina masina : atrinktosMasinos) {
            if (masina.getMaxGreitis() == maxGreitis) {
                atrinktosMasinosSuGreiciu.add(masina);
            }
        }

        if (!atrinktosMasinosSuGreiciu.isEmpty()) {
            for (Masina masina : atrinktosMasinosSuGreiciu) {
                System.out.println("\nPaieskos rezultatai:");
                System.out.println(masina + "\n");
            }
        } else {
            System.out.println("Nera tokios masinos pagal ivesta marke, modeli bei max greiti!");
        }

        System.out.println("Jeigu norite atlikti paieska is naujo spauskite - r");
        System.out.println("Norint sugryzti i menu spauskite - y");
        System.out.println("Programos sustabdymui spauskite - n");
        String ivestis = scanner.nextLine();
        if (ivestis.equals("r")) {
            paieskaMarkeModelisGreitis();
        } else if (ivestis.equals("y")) {
            menu();
        }
    }

    // ##########################
    // 5. Saraso rikiavimas pagal marke ir maksimalu greiti
    // ##########################
    public static void sarasoRikiavimas() {
        masinuSarasas.sort((m1, m2) -> {
            int markeCompare = m2.getMarke().compareTo(m1.getMarke());
            if (markeCompare != 0) {
                return markeCompare;
            } else {
                return Integer.compare(m2.getMaxGreitis(), m1.getMaxGreitis());
            }
        });

        System.out.println("Sarasas isrikiuotas pagal marke ir maksimalu greiti:");
        for (Masina masina : masinuSarasas) {
            System.out.println(masina);
        }

        System.out.println("Norint sugryzti i menu spauskite - y");
        System.out.println("Programos sustabdymui spauskite - n");
        String ivestis = scanner.nextLine();
        if (ivestis.equals("y")) {
            menu();
        }
    }

}
