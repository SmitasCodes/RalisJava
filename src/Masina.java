
import java.util.ArrayList;

class Masina {
    private String marke;
    private String modelis;
    private double turis;
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

    public String getMarke() {
        return marke;
    }

    public String getModelis() {
        return modelis;
    }

    public int getMaxGreitis() {
        return maxGreitis;
    }

    public double getTuris() {
        return turis;
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
