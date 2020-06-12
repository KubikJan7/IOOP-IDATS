package gui;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public final class VytvareniKomponent {

//Komponenty pro vytváření dialogových oken
    private VytvareniKomponent() {

    }

    public static Button newBtn(
            String text, ImageView im,
            EventHandler<ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setGraphic(im);
        btn.setPrefWidth(175);
        btn.setOnAction(handler);
        btn.setAlignment(Pos.CENTER_LEFT);
        return btn;
    }

    public static ListView newListView(Double topAnchor, Double leftAnchor,
            Double rightAnchor, Double bottomAnchor) {
        ListView lv = new ListView();
        AnchorPane.setTopAnchor(lv, topAnchor);
        AnchorPane.setLeftAnchor(lv, leftAnchor);
        AnchorPane.setRightAnchor(lv, rightAnchor);
        AnchorPane.setBottomAnchor(lv, bottomAnchor);
        return lv;
    }
    public static TreeView newTreeView(Double topAnchor, Double leftAnchor,
            Double rightAnchor, Double bottomAnchor) {
        TreeView tv = new TreeView();
        AnchorPane.setTopAnchor(tv, topAnchor);
        AnchorPane.setLeftAnchor(tv, leftAnchor);
        AnchorPane.setRightAnchor(tv, rightAnchor);
        AnchorPane.setBottomAnchor(tv, bottomAnchor);
        return tv;
    }

    public static ComboBox newComboBox(String text,
            Enum[] seznamVyctu, EventHandler<ActionEvent> handler) {
        ComboBox comBox = new ComboBox(
                FXCollections.observableList(Arrays.asList(seznamVyctu)));
        comBox.setPromptText(text);
        comBox.setPrefWidth(175);
        comBox.setOnAction(handler);
        return comBox;
    }

    //Komponenty pro vytváření dialogových oken
    public static TextField addRow(GridPane grid, String nazev, int radek) {
        grid.add(new Label(nazev), 0, radek);
        TextField textField = new TextField();
        grid.add(textField, 1, radek);
        return textField;
    }

    public static Spinner addRowSpinner(GridPane grid, String nazev, int radek, int min, int max) {
        grid.add(new Label(nazev), 0, radek);
        Spinner spinner = new Spinner();
        SpinnerValueFactory svc = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        spinner.setValueFactory(svc);
        grid.add(spinner, 1, radek);
        return spinner;
    }
}
