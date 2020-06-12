package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import korporatniFirma.IPobocka;
import korporatniFirma.Pobocka;

public final class Serialization {

    private Serialization() {

    }

    static void obnov(String jmenoSouboru, Kubik_KorporatniFirma korpFirma) throws IOException {
        try {
            FileInputStream file = new FileInputStream(jmenoSouboru);
            ObjectInputStream in = new ObjectInputStream(file);

            int pocetPobocek = in.readInt();
            korpFirma.firma.zrus();
            for (int i = 0; i < pocetPobocek; i++) {
                IPobocka pobocka = (Pobocka) in.readObject();
                korpFirma.firma.vloz(pobocka.getNazev(), pobocka);
            }
            in.close();
            file.close();
        } catch (ClassNotFoundException ex) {
        }
    }

    static void uloz(String jmenoSouboru, Kubik_KorporatniFirma korpFirma) throws IOException, FileNotFoundException {

        FileOutputStream file = new FileOutputStream(jmenoSouboru);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeInt((int) korpFirma.firma.stream().count());

        korpFirma.firma.stream().forEach(t -> {
            try {
                out.writeObject(t);
            } catch (IOException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        out.close();
        file.close();
    }
}
