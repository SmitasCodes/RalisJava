
import java.util.ArrayList;

class Masina {
    private String marke;
    private String modelis;
    private double turis;
    private int maxGreitis;

    private static ArrayList<Masina> masinuSarasas = new ArrayList<>();

    public Masina() {
    }

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

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public void setModelis(String modelis) {
        this.modelis = modelis;
    }

    public void setTuris(double turis) {
        this.turis = turis;
    }

    public void setMaxGreitis(int maxGreitis) {
        this.maxGreitis = maxGreitis;
    }

    public static ArrayList<Masina> gautiMasinuSarasa() {
        return masinuSarasas;
    }

    // Pakeiciamas toString metodas, kad butu rodoma marke, modelis, turis ir max greitis
    @Override
    public String toString() {
        return String.format("Marke: %s, Modelis: %s, Turis: %.1fL, Max Greitis: %d km/h",
                marke, modelis, turis, maxGreitis);
    }
}
