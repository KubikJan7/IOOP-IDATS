package automobily;

public class NakladniAutomobil extends Vozidlo {

    private int pocetPaletovychMist;
    private int vykon;

    public NakladniAutomobil(int pocetPaletovychMist, int vykon, String vyrobce, String SPZ, int maxRychlost, boolean vyhrivanaSedadla) {
        super(VozidloEnum.NAKLADNI_AUTOMOBIL, vyrobce, SPZ, maxRychlost, vyhrivanaSedadla);
        this.pocetPaletovychMist = pocetPaletovychMist;
        this.vykon = vykon;
    }

    public int getPocetPaletovychMist() {
        return pocetPaletovychMist;
    }

    public int getVykon() {
        return vykon;
    }

    public void setPocetPaletovychMist(int pocetPaletovychMist) {
        this.pocetPaletovychMist = pocetPaletovychMist;
    }

    public void setVykon(int vykon) {
        this.vykon = vykon;
    }

    @Override
    public String toString() {
        return super.toString() + ", Ložný prostor: " + pocetPaletovychMist + ", Výkon: " + vykon + " kW";
    }

}
