package automobily;

public class OsobniAutomobil extends Vozidlo {

    private Barva barva;
    private int pocetDveri;

    public OsobniAutomobil(Barva barva, int pocetDveri, String vyrobce, String SPZ, int maxRychlost, boolean vyhrivanaSedadla) {
        super(VozidloEnum.OSOBNI_AUTOMOBIL, vyrobce, SPZ, maxRychlost, vyhrivanaSedadla);
        this.barva = barva;
        this.pocetDveri = pocetDveri;
    }

    public Barva getBarva() {
        return barva;
    }

    public int getPocetDveri() {
        return pocetDveri;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public void setPocetDveri(int pocetDveri) {
        this.pocetDveri = pocetDveri;
    }

    @Override
    public String toString() {
        return super.toString() + ", Barva: " + barva + ", Počet dveří: " + pocetDveri;
    }

}
