package korporatniFirma;

import java.io.Serializable;

public abstract class Pozice implements Serializable{
    TypPoziceEnum typ;
    Zamestnanec zamestnanec;

    public Pozice(TypPoziceEnum typ, Zamestnanec zamestnanec) {
        this.typ = typ;
        this.zamestnanec = zamestnanec;
    }

    public TypPoziceEnum getTyp() {
        return typ;
    }

    public void setTyp(TypPoziceEnum typ) {
        this.typ = typ;
    }

    public Zamestnanec getZam() {
        return zamestnanec;
    }

    public void setZam(Zamestnanec zam) {
        this.zamestnanec = zam;
    }

    @Override
    public String toString() {
        return typ + ": "+ zamestnanec.getId();
    }
    
}
