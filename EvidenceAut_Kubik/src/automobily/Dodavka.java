package automobily;

public class Dodavka extends Vozidlo{
private int uzitnaHmotnost;
private int objemUloznehoProstoru;

    public Dodavka(int uzitnaHmotnost, int objemUloznehoProstoru, String vyrobce, String SPZ, int maxRychlost, boolean vyhrivanaSedadla) {
        super(VozidloEnum.DODAVKA, vyrobce, SPZ, maxRychlost, vyhrivanaSedadla);
        this.uzitnaHmotnost = uzitnaHmotnost;
        this.objemUloznehoProstoru = objemUloznehoProstoru;
    }

    public int getUzitnaHmotnost() {
        return uzitnaHmotnost;
    }

    public int getObjemUloznehoProstoru() {
        return objemUloznehoProstoru;
    }

    public void setUzitnaHmotnost(int uzitnaHmotnost) {
        this.uzitnaHmotnost = uzitnaHmotnost;
    }

    public void setObjemUloznehoProstoru(int objemUloznehoProstoru) {
        this.objemUloznehoProstoru = objemUloznehoProstoru;
    }

    @Override
    public String toString() {
        return super.toString() + ", Užitná hmotnost: " + uzitnaHmotnost + ", Objem úložného prostoru: " + objemUloznehoProstoru + " m^3";
    }


}
