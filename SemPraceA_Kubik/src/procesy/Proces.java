package procesy;

import java.io.Serializable;

public abstract class Proces implements Serializable {

    private TypProcesuEnum typ;
    public String id;
    public int casProcesu;

    public Proces(TypProcesuEnum typ, String id, int casProcesu) {
        this.typ = typ;
        this.id = id;
        this.casProcesu = casProcesu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCasProcesu() {
        return casProcesu;
    }

    public void setCasProcesu(int casProcesu) {
        this.casProcesu = casProcesu;
    }

    public TypProcesuEnum getTyp() {
        return typ;
    }

    @Override
    public String toString() {
        return typ + " proces" + ", Id: " + id + ", Čas procesu: " + casProcesu;
    }

}
