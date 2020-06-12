package korporatniFirma;

import loremIpsum.thedeanda.lorem.Lorem;
import loremIpsum.thedeanda.lorem.LoremIpsum;

public final class GeneratorDat {

    private static final Lorem LOREM = new LoremIpsum();
    private static int indexZamestnanec = 1;
    
    private GeneratorDat() {
    }

    public static IZamestnanci generujZamestnance() {
        indexZamestnanec = 1;
        IZamestnanci zamestnanci = new Zamestnanci();
        for (int i = 1; i < 600; i++) {
            zamestnanci.vloz(i,
                    new Zamestnanec(i, LOREM.getFirstName(), LOREM.getLastName(), LOREM.getEmail()));
        }
        return zamestnanci;
    }

    public static IPobocka generujPobocku(IZamestnanci zamestnanci)
            throws IllegalAccessException {
        Pobocka pobocka = new Pobocka(LOREM.getWords(1), LOREM.getCity());
        Pozice pozice = new ReditelPobocky(zamestnanci.najdi(indexZamestnanec++));
        pobocka.vlozPozici(pozice);
        pobocka.zpristupniReditele();
        int pocetUseku = nahodneCislo(1, 2);
        int pom = 1;
        for (int i = 0; i < pocetUseku; i++) {
            pozice = new VedouciUseku(zamestnanci.najdi(indexZamestnanec++));
            pobocka.vlozPozici(pozice);
            pobocka.zpristupniPodrizenouPozici(i + 1);
            int pocetOddeleni = nahodneCislo(1, 2);
            pom++;
            for (int j = 0; j < pocetOddeleni; j++) {
                pozice = new VedouciOddeleni(zamestnanci.najdi(indexZamestnanec++));
                pobocka.vlozPozici(pozice);
                pobocka.zpristupniPodrizenouPozici(j + 1);
                int pocetPracovniku = nahodneCislo(1, 2);
                pom++;
                for (int k = 0; k < pocetPracovniku; k++) {
                    pozice = new PracovnikOddeleni(zamestnanci.najdi(indexZamestnanec++));
                    pobocka.vlozPozici(pozice);
                    pom++;
                }
                pobocka.zpristupniNadrizenouPozici();
            }
            pobocka.zpristupniNadrizenouPozici();
        }
        pobocka.setPocetPozic(pom);
        return pobocka;
    }

    public static IFirma generujFirmu(IZamestnanci zamestnanci)
            throws IllegalAccessException {
        IFirma firma = new Firma();
        IPobocka pobocka;
        for (int i = 0; i < 4; i++) {
            pobocka = generujPobocku(zamestnanci);
            firma.vloz(pobocka.getNazev(), pobocka);
        }
        return firma;
    }

    private static int nahodneCislo(int min, int max) {
        return (int) (Math.round(Math.random()) * (max - min) + min);
    }
}
