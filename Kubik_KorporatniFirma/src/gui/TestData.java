package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static korporatniFirma.GeneratorDat.generujFirmu;
import static korporatniFirma.GeneratorDat.generujZamestnance;

public final class TestData {

    private TestData() {

    }

    static EventHandler<ActionEvent> runTestData(Kubik_KorporatniFirma korporatniFirma) {
        return (ActionEvent event) -> {
            try {
                korporatniFirma.firma.zrus();
                generujFirmu(generujZamestnance())
                        .stream()
                        .forEach(t -> {
                            korporatniFirma.firma.vloz(t.getNazev(), t);
                        }
                        );
                
            } catch (IllegalAccessException ex) {
            }
            korporatniFirma.obnovZobrazeniSeznamu();
        };
    }

}
