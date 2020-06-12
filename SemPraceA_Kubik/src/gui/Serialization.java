package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import procesy.Proces;
import procesy.VlozPoziceEnum;

public final class Serialization {

    private Serialization() {

    }

    static void obnov(String jmenoSouboru, ProgVyrobniProces progVyrobniProces) throws IOException {
        FileInputStream file = new FileInputStream(jmenoSouboru);
        ObjectInputStream in = new ObjectInputStream(file);
        int pocet = in.readInt();;
        try {
            progVyrobniProces.seznam.zrus();
            for (int i = 0; i < pocet; i++) {
                progVyrobniProces.seznam.vlozProces((Proces) in.readObject(), VlozPoziceEnum.POSLEDNI);
            }
        } catch (ClassNotFoundException ex) {
        }
        in.close();
        file.close();
    }

    static void uloz(String jmenoSouboru, ProgVyrobniProces progVyrobniProces) throws FileNotFoundException, IOException {
        int pocet = 0;
        FileOutputStream file = new FileOutputStream(jmenoSouboru);
        ObjectOutputStream out = new ObjectOutputStream(file);
        Iterator itr = progVyrobniProces.seznam.iterator();
        while(itr.hasNext()){
            itr.next();
            pocet++;
        }
        out.writeInt(pocet);
        itr = progVyrobniProces.seznam.iterator();
        while(itr.hasNext()){
            out.writeObject(itr.next());
        }
        out.close();
        file.close();
    }
}
