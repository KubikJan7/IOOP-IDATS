package gui;

import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import procesy.RoboProces;

public class DialogRoboP extends Stage {

    private final Consumer<RoboProces> vystupniOperace;
    private TextField txIdProcesu;
    private TextField txCasProcesu;
    private final boolean edit;
    private final RoboProces robotickyProces;

    public static DialogRoboP factoryDialogRobotickyProces(
            Supplier<RoboProces> vstup, Consumer<RoboProces> vystup) {
        return new DialogRoboP(vstup, vystup);
    }

    private DialogRoboP(Supplier<RoboProces> vstup,
            Consumer<RoboProces> vystup) {
        this.vystupniOperace = vystup;
        edit = vstup != null;

        robotickyProces = (edit) ? vstup.get() : new RoboProces(null, 0);
        setTitle("Dialog Robotický Proces");
        setWidth(320);
        setHeight(180);
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
        Button buttonUloz = new Button("Uloz");
        buttonUloz.setOnAction(e -> {
            update();
            vystupniOperace.accept(robotickyProces);
            hide();
        });
        grid.add(buttonUloz, 0, ++radek);
        return new Scene(box);
    }

    private void update() {
        try {
            robotickyProces.setCasProcesu(Integer.valueOf(txCasProcesu.getText()));
            robotickyProces.setId(txIdProcesu.getText());
        } catch (NumberFormatException ex) {
            AlertHandler.informationAlert("Nesprávně zadaná hodnota",
                    "Nově vytvořená položka získá nulové parametry,"
                    + "editované položce zůstanou původní parametry.");
        }
    }

    private void set() {
        try {
            txCasProcesu.setText(Integer.toString(robotickyProces.getCasProcesu()));
            txIdProcesu.setText(robotickyProces.getId());
        } catch (NumberFormatException ex) {
            AlertHandler.warningAlert(null,
                    "Hodnota nebyla zadáná ve validním tvaru. ");
        }
    }
}
