package procesy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import kolekce.AbstrDoubleList;
import kolekce.AbstrLifo;
import kolekce.IAbstrDoubleList;
import kolekce.IAbstrLifo;

public class VyrobniProces implements IVyrobniProces {

    private IAbstrDoubleList seznam;
    private static IVyrobniProces vyrobniProcesy = new VyrobniProces();
    int pocetZaznamu = 0;

    public static IVyrobniProces getInstance() {
        return vyrobniProcesy;
    }

    private VyrobniProces() {
        seznam = new AbstrDoubleList<>();
    }

    @Override
    public int importDat(String soubor) throws IOException {
        ArrayList<String> seznamCisel = new ArrayList<>();
        BufferedReader reader = null;
        String radek;
        char charakterIn;
        String[] slova;
        try {
            reader = new BufferedReader(new FileReader(soubor));
            String id;
            int cas;
            int pocetOsob;
            reader.readLine();
            while ((radek = reader.readLine()) != null) {
                slova = radek.split(";");
                id = slova[0];
                pocetOsob = Integer.valueOf(slova[1]);
                cas = Integer.valueOf(slova[2]);
                if (slova[0].charAt(0) == 'R') {
                    seznam.vlozPosledni(new RoboProces(id, cas));
                } else {
                    seznam.vlozPosledni(new ManualProces(id, pocetOsob, cas));
                }
                pocetZaznamu++;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return pocetZaznamu;
    }

    @Override
    public void vlozProces(Proces proces, VlozPoziceEnum pozice) {
        switch (pozice) {
            case PRVNI:
                seznam.vlozPrvni(proces);
                break;
            case POSLEDNI:
                seznam.vlozPosledni(proces);

                break;
            case NASLEDNIK: {
                try {
                    seznam.vlozNaslednika(proces);
                } catch (NullPointerException | IAbstrDoubleList.ListException ex) {
                    Logger.getLogger(VyrobniProces.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case PREDCHUDCE: {
                try {
                    seznam.vlozPredchudce(proces);
                } catch (IAbstrDoubleList.ListException | NoSuchElementException ex) {
                    Logger.getLogger(VyrobniProces.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    public Proces zpristupniProces(PoziceEnum pozice) throws IAbstrDoubleList.ListException {
        switch (pozice) {
            case PRVNI:
                return (Proces) seznam.zpristupniPrvni();
            case POSLEDNI:
                return (Proces) seznam.zpristupniPosledni();
            case NASLEDNIK: {
                return (Proces) seznam.zpristupniNaslednika();
            }
            case PREDCHUDCE: {
                return (Proces) seznam.zpristupniPredchudce();
            }
            case AKTUALNI: {
                return (Proces) seznam.zpristupniAktualni();
            }
            default:
                return null;
        }
    }

    @Override
    public Proces odeberProces(PoziceEnum pozice) throws IAbstrDoubleList.ListException {
        switch (pozice) {
            case PRVNI:
                return (Proces) seznam.odeberPrvni();
            case POSLEDNI:
                return (Proces) seznam.odeberPosledni();
            case NASLEDNIK: {
                return (Proces) seznam.odeberNaslednika();
            }
            case PREDCHUDCE: {
                return (Proces) seznam.odeberPredchudce();
            }
            case AKTUALNI: {
                return (Proces) seznam.odeberAktualni();
            }
            default:
                return null;
        }
    }

    @Override
    public Iterator iterator() {
        return seznam.iterator();
    }

    @Override
    public IAbstrLifo vytipujKandidatiReorg(int kriterium, ReorgEnum reorganizace) {
        IAbstrLifo zasobnik = AbstrLifo.getInstance();
        Iterator itr = seznam.iterator();
        Proces p, pom = null;
        int index = 0;
        seznam.zpristupniPrvni();
        try {
            while (itr.hasNext()) {
                p = (Proces) itr.next();
                if (p.getTyp() == TypProcesuEnum.MANUAL) {
                    switch (reorganizace) {
                        case DEKOMPOZICE:
                            if (p.casProcesu > kriterium) {
                                zasobnik.vloz(p);
                                index++;
                            }
                            break;

                        case AGREGACE:
                            if (p.casProcesu < kriterium) {
                                if ((index % 2) == 0) {
                                    pom = p;
                                    zasobnik.vloz(p);
                                    index++;
                                } else {
                                    if (pom == seznam.zpristupniPredchudce()) {
                                        zasobnik.vloz(p);
                                        index++;
                                    } else {
                                        zasobnik.odeber();
                                        index--;
                                    }
                                    pom = null;
                                    seznam.zpristupniNaslednika();
                                }

                            }
                            break;
                    }
                }
                seznam.zpristupniNaslednika();
            }
        } catch (NoSuchElementException
                | IAbstrDoubleList.ListException ex) {
        }
        if (index % 2 == 1 && reorganizace ==
                ReorgEnum.AGREGACE) {
            zasobnik.odeber();
        }
        return zasobnik;
    }

    @Override
    public void reorganizace(ReorgEnum reorganizace, IAbstrLifo zasobnik) {
        ManualProces p, p1, p2;
        int cas, pocetOs;
        String id;
        switch (reorganizace) {
            case DEKOMPOZICE:
                while (zasobnik.jePrazdny() != true) {
                    seznam.zpristupniPrvni();
                    Iterator itr = seznam.iterator();
                    p = (ManualProces) zasobnik.odeber();
                    while (itr.hasNext()) {
                        try {
                            if (itr.next() == p) {
                                while (seznam.zpristupniAktualni() != p) {
                                    seznam.zpristupniNaslednika();
                                }
                                p1 = (ManualProces) p.clone();
                                p2 = (ManualProces) p1.clone();
                                id = p1.id;
                                cas = p1.casProcesu;
                                pocetOs = p1.pocetOsob;
                                p1.setId(id + ".a");
                                p1.setCasProcesu((cas / 2) + (cas % 2));
                                p1.setPocetOsob((pocetOs / 2) + (pocetOs % 2));
                                p2.setId(id + ".b");
                                p2.setCasProcesu((cas / 2));
                                p2.setPocetOsob((pocetOs / 2));
                                seznam.vlozNaslednika(p2);
                                seznam.vlozNaslednika(p1);
                                seznam.odeberAktualni();
                                break;
                            }
                        } catch (NoSuchElementException | IAbstrDoubleList.ListException ex) {
                        }
                    }
                }
                break;

            case AGREGACE:
                while (zasobnik.jePrazdny() != true) {
                    Iterator itr = seznam.iterator();
                    p2 = (ManualProces) zasobnik.odeber();
                    p1 = (ManualProces) zasobnik.odeber();
                    seznam.zpristupniPrvni();
                    while (itr.hasNext()) {
                        try {
                            if (itr.next() == p1) {
                                p = (ManualProces) p1.clone();
                                cas = p1.casProcesu + p2.casProcesu;
                                pocetOs = p1.pocetOsob + p2.pocetOsob;
                                if (p.getId().contains(".a")) {
                                    p.setId(p.getId().replace(".a", ""));
                                } else if (p.getId().contains(".b")) {
                                    p.setId(p.getId().replace(".b", ""));
                                }
                                p.setCasProcesu(cas);
                                p.setPocetOsob(pocetOs);
                                seznam.odeberNaslednika();
                                seznam.vlozNaslednika(p);
                                seznam.odeberAktualni();
                            }
                            seznam.zpristupniNaslednika();
                        } catch (NoSuchElementException | IAbstrDoubleList.ListException ex) {
                        }
                    }
                }
                break;
        }

    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public Stream<Proces> stream() {
        return seznam.stream();
    }

    @Override
    public boolean jePrazdny() {
        return seznam.jePrazdny();
    }

}
