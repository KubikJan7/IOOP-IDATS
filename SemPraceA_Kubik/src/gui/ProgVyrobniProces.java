package gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import kolekce.IAbstrDoubleList;
import procesy.IVyrobniProces;
import procesy.ManualProces;
import procesy.Proces;
import procesy.VyrobniProces;
import procesy.PoziceEnum;
import procesy.ReorgEnum;
import procesy.RoboProces;
import procesy.TypProcesuEnum;
import procesy.VlozPoziceEnum;

/**
 *
 * @author Legol
 */
public class ProgVyrobniProces extends Application {

    private ComboBox<TypProcesuEnum> comBoxNovy;
    private ComboBox<ReorgEnum> comBoxReorg;
    private ComboBox<VlozPoziceEnum> comBoxPoz;
    ListView<Proces> list = new ListView<>();
    public IVyrobniProces seznam = VyrobniProces.getInstance();
    String jmenoSouboru = "prog_vyrobni_proces.ser";
    String importSoubor = "import.csv";
    ObservableList<Proces> observableList = FXCollections.observableArrayList();

    private Predicate<Proces> filtr = (t) -> t != null;
    private final Predicate<Proces> filterOff = (t) -> t != null;
    Stage dialog = null;

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        ObservableList<Node> children = root.getChildren();
        Scene scene = new Scene(root, 620, 438);
        primaryStage.setTitle("Semestrální práce A - Kubík");
        primaryStage.setScene(scene);

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.VERTICAL);
        pane.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(pane, 5.0);
        AnchorPane.setRightAnchor(pane, 5.0);
        AnchorPane.setBottomAnchor(pane, 5.0);
        pane.setVgap(5);

        Image upIm = new Image("file:arrow-up.png");
        ImageView imV1 = new ImageView(upIm);
        //imV1.setScaleX(0.25);imV1.setScaleY(1.5);
        imV1.setFitHeight(10);
        Image downIm = new Image("file:arrow-down.png");
        ImageView imV2 = new ImageView(downIm);
        //imV2.setScaleX(0.25);imV2.setScaleY(1.5);
        imV2.setFitHeight(10);
        pane.getChildren().addAll(VytvareniKomponent.newBtn(null, imV1, posunNahoru()),
                VytvareniKomponent.newBtn(null, imV2, posunDolu()),
                VytvareniKomponent.newBtn("Zruš", null, zrusProcesZeSeznamu()),
                comBoxNovy = VytvareniKomponent.
                        newComboBox("Vložit nový proces", TypProcesuEnum.values(), pridejNovyProces()),
                comBoxPoz = VytvareniKomponent.
                        newComboBox("Vkládat na pozici", VlozPoziceEnum.values(), null),
                VytvareniKomponent.newBtn("Upravit proces", null, upravProces()),
                comBoxReorg = VytvareniKomponent.newComboBox("Reorganizovat", ReorgEnum.values(), reorganizujProces()),
                VytvareniKomponent.newBtn("Import ze souboru", null, nactiZeSouboru()),
                VytvareniKomponent.newBtn("Zálohovat do souboru", null, handlerUloz),
                VytvareniKomponent.newBtn("Obnovit ze souboru", null, handlerObnov),
                VytvareniKomponent.newBtn("Zruš procesy", null, zrusVsechnyProcesy()),
                VytvareniKomponent.newBtn("Test", null, TestData.runTestData(this)));
        imV1.setPreserveRatio(true);
        imV2.setPreserveRatio(true);
        children.add(pane);
        list = VytvareniKomponent.newListView(0.0, 5, 185, 5);
        list.setOnMouseClicked(vyberMysi());
        children.add(list);
        primaryStage.getIcons().add(new Image("file:assemblyRobot.jpg"));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Optional<ButtonType> result = AlertHandler.confirmationAlert("Ukončení aplikace", null, "Opravdu si přejete ukončit tuto aplikaci?")
                    .showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            } else {
                e.consume();
            }
        });
    }

    protected void obnovZobrazeniSeznamu() {
        observableList.clear();
        seznam.stream()
                .filter(filtr)
                .forEach(t -> observableList.add(t));
        list.setItems(observableList);
    }
    private final EventHandler<ActionEvent> handlerUloz = event -> {
        try {
            Serialization.uloz(jmenoSouboru, this);
        } catch (IOException ex) {
            AlertHandler.errorAlert(null, "Chyba v ukládání seznamu do souboru");
        }

    };
    private final EventHandler<ActionEvent> handlerObnov = event -> {
        try {
            Serialization.obnov(jmenoSouboru, this);
            obnovZobrazeniSeznamu();
        } catch (IOException ex) {
            AlertHandler.errorAlert(null, "Chyba v obnově seznamu ze souboru");
        }
    };

    private EventHandler<ActionEvent> zrusProcesZeSeznamu() {
        return event -> {
            try {
                seznam.zpristupniProces(PoziceEnum.AKTUALNI);
            } catch (NoSuchElementException e) {
                AlertHandler.informationAlert(null, "V seznamu se nenachází žádný prvek");
                return;
            } catch (IAbstrDoubleList.ListException ex) {
                AlertHandler.informationAlert(null, "Nebyl vybrán proces ke zrušení");
                return;
            }
            Iterator itr = seznam.iterator();
            itr.remove();
            obnovZobrazeniSeznamu();
            list.getSelectionModel().selectFirst();
        };
    }

    private EventHandler<ActionEvent> zrusVsechnyProcesy() {
        return event -> {
            seznam.zrus();
            obnovZobrazeniSeznamu();
        };
    }

    private EventHandler<ActionEvent> nactiZeSouboru() {
        return event -> {
            try {
                seznam.zrus();
                seznam.importDat(importSoubor);
                obnovZobrazeniSeznamu();
            } catch (IOException ex) {
                AlertHandler.informationAlert(null,
                        "Soubor je poškozen");
            }
        };
    }

    private EventHandler<MouseEvent> vyberMysi() {
        return event -> {
            try {
                seznam.zpristupniProces(PoziceEnum.PRVNI);

                Iterator itr = seznam.iterator();
                while (itr.next() != list.getSelectionModel().getSelectedItem()) {
                    seznam.zpristupniProces(PoziceEnum.NASLEDNIK);
                }
            } catch (NoSuchElementException | IAbstrDoubleList.ListException e) {

            }
        };
    }

    private EventHandler<ActionEvent> posunNahoru() {
        return event -> {
            if (list.getSelectionModel().getSelectedItem() == null) {
                try {
                    list.getSelectionModel().select((Proces) seznam.zpristupniProces(PoziceEnum.PRVNI));
                } catch (NoSuchElementException | IAbstrDoubleList.ListException ex) {
                }
            } else {
                try {
                    list.getSelectionModel().select((Proces) seznam.zpristupniProces(PoziceEnum.PREDCHUDCE));
                } catch (IAbstrDoubleList.ListException ex) {
                }
            }
        };
    }

    private EventHandler<ActionEvent> posunDolu() {
        return event -> {
            if (list.getSelectionModel().getSelectedItem() == null) {
                try {
                    list.getSelectionModel().select((Proces) seznam.zpristupniProces(PoziceEnum.PRVNI));
                } catch (IAbstrDoubleList.ListException|NoSuchElementException ex) {
                }
            } else {
                try {
                    list.getSelectionModel().select((Proces) seznam.zpristupniProces(PoziceEnum.NASLEDNIK));
                } catch (IAbstrDoubleList.ListException ex) {
                }
            }
        };
    }

    private EventHandler<ActionEvent> pridejNovyProces() {
        return event -> {
            Platform.runLater(() -> {
                TypProcesuEnum typDialogu;
                typDialogu = (TypProcesuEnum) comBoxNovy.getSelectionModel().getSelectedItem();
                if (typDialogu == null) {
                    return;
                }
                if (comBoxPoz.getSelectionModel().getSelectedItem() == null) {
                    AlertHandler.informationAlert(null,
                            "Nebyla vybrána pozice pro vložení");
                    comBoxNovy.getSelectionModel().clearSelection();
                    return;
                }
                if (comBoxPoz.getSelectionModel().getSelectedItem() == VlozPoziceEnum.NASLEDNIK || comBoxPoz.getSelectionModel().getSelectedItem() == VlozPoziceEnum.PREDCHUDCE) {
                    try {
                        seznam.zpristupniProces(PoziceEnum.AKTUALNI);
                    } catch (NoSuchElementException ex) {
                        AlertHandler.informationAlert(null,
                                "V seznamu se nenachází žádný prvek");
                        comBoxNovy.getSelectionModel().clearSelection();
                        return;
                    } catch (IAbstrDoubleList.ListException e) {
                        AlertHandler.informationAlert(null,
                                "Není nastaven aktuální prvek");
                        comBoxNovy.getSelectionModel().clearSelection();
                        return;
                    }
                }
                switch (typDialogu) {
                    case ROBOT:
                        dialog = DialogRoboP.factoryDialogRobotickyProces(null, t -> {
                            seznam.vlozProces(t, (VlozPoziceEnum) comBoxPoz.getSelectionModel().getSelectedItem());
                            obnovZobrazeniSeznamu();
                        });
                        break;
                    case MANUAL:
                        dialog = DialogManualP.factoryDialogManualniProces(null, t -> {
                            seznam.vlozProces(t, (VlozPoziceEnum) comBoxPoz.getSelectionModel().getSelectedItem());
                            obnovZobrazeniSeznamu();
                        });
                        break;
                }

                dialog.setResizable(false);
                dialog.showAndWait();
                comBoxNovy.getSelectionModel().clearSelection();
                try {
                    list.getSelectionModel().select(seznam.zpristupniProces(PoziceEnum.AKTUALNI));
                } catch (IAbstrDoubleList.ListException ex) {
                }
            });
        };

    }

    private EventHandler<ActionEvent> upravProces() {
        return event -> {
            try {
                Proces vybranyProces = (Proces) list.getSelectionModel().getSelectedItem();
                TypProcesuEnum typDialogu = vybranyProces.getTyp();
                switch (typDialogu) {
                    case ROBOT:
                        dialog = DialogRoboP.factoryDialogRobotickyProces(()
                                -> (RoboProces) vybranyProces,
                                t -> obnovZobrazeniSeznamu());
                        break;
                    case MANUAL:
                        dialog = DialogManualP.factoryDialogManualniProces(()
                                -> (ManualProces) vybranyProces,
                                t -> obnovZobrazeniSeznamu());
                        break;
                }
                dialog.setResizable(false);
                dialog.showAndWait();
                list.getSelectionModel().select(seznam.zpristupniProces(PoziceEnum.AKTUALNI));
            } catch (NullPointerException | IAbstrDoubleList.ListException ex) {
                AlertHandler.informationAlert(null,
                        "K editaci nebyla vybrána žádná položka");
            }
        };
    }

    private EventHandler<ActionEvent> reorganizujProces() {
        return event -> {
            Platform.runLater(() -> {
                try {
                    seznam.zpristupniProces(PoziceEnum.PRVNI);
                } catch (NoSuchElementException | IAbstrDoubleList.ListException e) {
                    AlertHandler.informationAlert(null, "V seznamu se nenachází žádný prvek");
                    return;
                }
                ReorgEnum reorganizace;
                reorganizace = (ReorgEnum) comBoxReorg.getSelectionModel().getSelectedItem();
                if (reorganizace == null) {
                    return;
                }
                switch (reorganizace) {
                    case DEKOMPOZICE:
                        dialog = DialogReorg.factoryDialogReorganizace(seznam,
                                reorganizace, t -> obnovZobrazeniSeznamu());
                        break;
                    case AGREGACE:
                        dialog = DialogReorg.factoryDialogReorganizace(seznam,
                                reorganizace, t -> obnovZobrazeniSeznamu());
                        break;
                }
                dialog.setResizable(false);
                dialog.showAndWait();
                comBoxReorg.getSelectionModel().clearSelection();
                try {
                    list.getSelectionModel().select(seznam.zpristupniProces(PoziceEnum.AKTUALNI));
                } catch (IAbstrDoubleList.ListException ex) {
                }
            });
        };
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
