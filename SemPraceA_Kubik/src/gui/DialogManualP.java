package gui;

import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import procesy.ManualProces;

public class DialogManualP extends Stage {

    private final Consumer<ManualProces> vystupniOperace;
    private TextField txIdProcesu;
    private Spinner<Integer> spPocetOsob;
    private TextField txCasProcesu;
    private final boolean edit;
    private final ManualProces manualniProces;

    public static DialogManualP factoryDialogManualniProces(
            Supplier<ManualProces> vstup, Consumer<ManualProces> vystup) {
        return new DialogManualP(vstup, vystup);
    }

    private DialogManualP(Supplier<ManualProces> vstup,
            Consumer<ManualProces> vystup) {
        this.vystupniOperace = vystup;
        edit = vstup != null;

        manualniProces = (edit) ? vstup.get() : new ManualProces(null, 0, 0);
        setTitle("Dialog Manualni Proces");
        setWidth(320);
        setHeight(220);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setScene(getScena());
        if (edit) {
            set();
        }
    }

    private Scene getScena() throws IndexOutOfBoundsException {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        box.getChildren().addAll(grid);
        int radek = 0;
        txIdProcesu = VytvareniKomponent.addRow(grid, "Id procesu:", radek++);
        txCasProcesu = VytvareniKomponent.addRow(grid, "Čas procesu:", radek++);
        spPocetOsob = VytvareniKomponent.addRowSpinner(grid, "Počet osob", radek++, 1, 25);
        Button buttonUloz = new Button("Uloz");
        buttonUloz.setOnAction(e -> {
            update();
            vystupniOperace.accept(manualniProces);
            hide();
        });
        grid.add(buttonUloz, 0, ++radek);
        return new Scene(box);
    }

    private void update() {
        try {
            manualniProces.setId(txIdProcesu.getText());
            manualniProces.setPocetOsob(spPocetOsob.getValue());
            manualniProces.setCasProcesu(Integer.valueOf(txCasProcesu.getText()));
        } catch (NumberFormatException ex) {
            AlertHandler.informationAlert("Nesprávně zadaná hodnota",
                    "Nově vytvořená položka získá nulové parametry,"
                    + "editované položce zůstanou původní parametry.");
        }
    }

    private void set() {
        try {
            spPocetOsob.getValueFactory().setValue(manualniProces.getPocetOsob());
            txCasProcesu.setText(Integer.toString(manualniProces.getCasProcesu()));
            txIdProcesu.setText(manualniProces.getId());
        } catch (NumberFormatException ex) {
            AlertHandler.warningAlert(null,
                    "Hodnota nebyla zadáná ve validním tvaru. ");
        }
    }
}
