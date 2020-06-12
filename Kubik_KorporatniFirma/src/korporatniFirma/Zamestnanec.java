package korporatniFirma;

import java.io.Serializable;

public class Zamestnanec implements Serializable {

    private final int id;
    private final String jmeno;
    private final String prijmeni;
    private final String email;

    public Zamestnanec(int id, String jmeno, String prijmeni, String email) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Jméno: " + jmeno + ", Příjmení " + prijmeni + ", Email: " + email;
    }

}
