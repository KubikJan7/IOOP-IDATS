package automobily;

import java.io.Serializable;

public abstract class Vozidlo implements Serializable{

    private  VozidloEnum typ;
    private  String vyrobce;
    private  String SPZ;
    private  int maxRychlost;
    private  boolean maVyhrivanaSedadla;

    public Vozidlo(VozidloEnum typ,String vyrobce, String SPZ, int maxRychlost, boolean vyhrivanaSedadla) {
        this.typ = typ;
        this.vyrobce = vyrobce;
        this.SPZ = SPZ;
        this.maxRychlost = maxRychlost;
        this.maVyhrivanaSedadla = vyhrivanaSedadla;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Vozidlo a = (Vozidlo) obj;
        if (this.SPZ == a.SPZ) {
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = hash * 29 + SPZ.hashCode();
        return hash;
    }

    public String getVyrobce() {
        return vyrobce;
    }

    public String getSPZ() {
        return SPZ;
    }

    public int getMaxRychlost() {
        return maxRychlost;
    }

    public String isVyhrivanaSedadla() {
        return maVyhrivanaSedadla ? "ano" :"ne";
    }

    public boolean isMaVyhrivanaSedadla() {
        return maVyhrivanaSedadla;
    }

    public VozidloEnum getTyp() {
        return typ;
    }

    public void setTyp(VozidloEnum typ) {
        this.typ = typ;
    }

    public void setVyrobce(String vyrobce) {
        this.vyrobce = vyrobce;
    }

    public void setSPZ(String SPZ) {
        this.SPZ = SPZ;
    }

    public void setMaxRychlost(int maxRychlost) {
        this.maxRychlost = maxRychlost;
    }

    public void setMaVyhrivanaSedadla(boolean maVyhrivanaSedadla) {
        this.maVyhrivanaSedadla = maVyhrivanaSedadla;
    }

    @Override
    public String toString() {
        return typ + ", Výrobce: " + vyrobce + ", SPZ: " + SPZ + ", Maximální rychlost: " + maxRychlost + " km/h" + ", Vyhřívaná sedadla: " + isVyhrivanaSedadla();
    }

}
