package korporatniFirma;

public class PracovnikOddeleni extends Pozice{

    public PracovnikOddeleni(Zamestnanec zamestnanec) {
        super(TypPoziceEnum.PRACOVNIK_ODDELENI, zamestnanec);
    }
    
}
