
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import procesy.ManualProces;
import procesy.RoboProces;
import procesy.VlozPoziceEnum;

public final class TestData {

    private TestData(){
        
    }
    public static EventHandler<ActionEvent> runTestData(ProgVyrobniProces progVyrobniProces) {
        return (ActionEvent event) -> {
            progVyrobniProces.seznam.zrus();
            progVyrobniProces.seznam.vlozProces(new RoboProces("R012", 12),VlozPoziceEnum.PRVNI);
            progVyrobniProces.seznam.vlozProces(new ManualProces("O101", 6,5),VlozPoziceEnum.POSLEDNI);
            progVyrobniProces.seznam.vlozProces(new ManualProces("O109", 4,4),VlozPoziceEnum.POSLEDNI);
            progVyrobniProces.seznam.vlozProces(new RoboProces("R0009", 12),VlozPoziceEnum.POSLEDNI);
            progVyrobniProces.obnovZobrazeniSeznamu();
        };
    }
}
