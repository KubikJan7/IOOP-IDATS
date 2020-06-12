package gui;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public final class VytvareniKomponent {
    
//Komponenty pro vytváření dialogových oken
    private VytvareniKomponent(){
        
    }
    public static Button newBtn(
            String text,
            double rightAnchor, double bottomAnchor,
            EventHandler<ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setOnAction(handler);
        AnchorPane.setLeftAnchor(btn, rightAnchor);
        AnchorPane.setBottomAnchor(btn, bottomAnchor);
        return btn;
    }

    public static ListView newListView(double topAnchor, double leftAnchor,
            double rightAnchor, double bottomAnchor) {
        ListView lv = new ListView();
        AnchorPane.setTopAnchor(lv, topAnchor);
        AnchorPane.setLeftAnchor(lv, leftAnchor);
        AnchorPane.setRightAnchor(lv, rightAnchor);
        AnchorPane.setBottomAnchor(lv, bottomAnchor);
        return lv;
    }

    public static ComboBox newComboBox(String text,
            double rightAnchor, double bottomAnchor,
            Enum[] seznamVyctu, EventHandler<ActionEvent> handler) {
        ComboBox comBox = new ComboBox(
                FXCollections.observableList(Arrays.asList(seznamVyctu)));
        comBox.setPromptText(text);
        comBox.setOnAction(handler);
        AnchorPane.setLeftAnchor(comBox, rightAnchor);
        AnchorPane.setBottomAnchor(comBox, bottomAnchor);
        return comBox;
    }

    //Komponenty pro vytváření dialogových oken
    public static TextField addRow(GridPane grid, String nazev, int radek) {
        grid.add(new Label(nazev), 0, radek);
        TextField textField = new TextField();
        grid.add(textField, 1, radek);
        return textField;
    }

    public static <T> ComboBox<T> addRowComboBox(GridPane grid, String nazev,
            int radek, Enum[] enumList) {
        grid.add(new Label(nazev), 0, radek);
        ComboBox<T> comboBox = new ComboBox(
                FXCollections.observableList(Arrays.asList(enumList)));
        grid.add(comboBox, 1, radek);
        return comboBox;
    }

    public static CheckBox addRowCheckBox(GridPane grid, String nazev, int radek) {
        grid.add(new Label(nazev), 0, radek);
        CheckBox checkBox = new CheckBox();
        grid.add(checkBox, 1, radek);
        return checkBox;
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
