package procesy;

public class ManualProces extends Proces implements Cloneable {

    public int pocetOsob;

    public ManualProces(String id, int pocetOsob, int casProcesu) {
        super(TypProcesuEnum.MANUAL, id, casProcesu);
        this.pocetOsob = pocetOsob;
    }

    public int getPocetOsob() {
        return pocetOsob;
    }

    public void setPocetOsob(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    @Override
    public Object clone(){
        try {
            return (ManualProces) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ", Počet osob: " + pocetOsob;
    }

}
