package gui;

import java.util.function.Consumer;
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
import kolekce.IAbstrLifo;
import procesy.IVyrobniProces;
import procesy.ReorgEnum;

public class DialogReorg extends Stage {

    private final Consumer<IVyrobniProces> vystupniOperace;
    private TextField txKriterium;
    private ReorgEnum reorganizace;
    private IVyrobniProces seznam;

    public static DialogReorg factoryDialogReorganizace(
            IVyrobniProces seznam, ReorgEnum reorganizace, Consumer<IVyrobniProces> vystup) {
        return new DialogReorg(seznam, reorganizace, vystup);
    }

    private DialogReorg(IVyrobniProces seznam, ReorgEnum reorganizace,
            Consumer<IVyrobniProces> vystup) {
        this.seznam = seznam;
        this.reorganizace = reorganizace;
        this.vystupniOperace = vystup;

        setTitle("Dialog Reorganizace Manuálních Procesů");
        setWidth(320);
        setHeight(135);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setScene(getScena());
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
        txKriterium = VytvareniKomponent.addRow(grid, "Kritérium:", radek++);
        Button buttonUloz = new Button("Uloz");
        buttonUloz.setOnAction(e -> {
            update();
            vystupniOperace.accept(seznam);
            hide();
        });
        grid.add(buttonUloz, 0, ++radek);
        return new Scene(box);
    }
    private void update() {
        try {
            IAbstrLifo zasobnik = seznam.vytipujKandidatiReorg(Integer.
                    valueOf(txKriterium.getText()), reorganizace);
            if(zasobnik.jePrazdny()){
                AlertHandler.informationAlert(null,"K reorganizaci nebyly nalezeny vhodné procesy.");
            }
            seznam.reorganizace(reorganizace, zasobnik);
            } catch (NumberFormatException ex) {
            AlertHandler.informationAlert(null,"Nesprávně zadaná hodnota.");
        }
    }
}
