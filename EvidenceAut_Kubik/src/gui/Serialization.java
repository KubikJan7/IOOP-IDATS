package gui;

import automobily.Vozidlo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Serialization {

    private Serialization() {

    }

    static void obnov(String jmenoSouboru, EvidenceAutomobilu evidenceAutomobilu) throws IOException {
        FileInputStream file = new FileInputStream(jmenoSouboru);
        ObjectInputStream in = new ObjectInputStream(file);
        int velikost = in.readInt();
        int pocet = in.readInt();
        try {
            evidenceAutomobilu.seznam.zrus();
            for (int i = 0; i < pocet; i++) {
                evidenceAutomobilu.seznam.pridej((Vozidlo) in.readObject());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EvidenceAutomobilu.class.getName()).log(Level.SEVERE, null, ex);
        }
        in.close();
        file.close();
    }

    static void uloz(String jmenoSouboru, EvidenceAutomobilu evidenceAutomobilu) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream(jmenoSouboru);
        ObjectOutputStream out = new ObjectOutputStream(file);
        Object[] pole = evidenceAutomobilu.seznam.toArray();
        out.writeInt(evidenceAutomobilu.seznam.getVelikost());
        out.writeInt(evidenceAutomobilu.seznam.getPocet());
        for (Object vozidlo : pole) {
            out.writeObject(vozidlo);
        }
        out.close();
        file.close();
    }
}
