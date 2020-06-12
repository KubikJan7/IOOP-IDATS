package gui;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import korporatniFirma.Firma;
import korporatniFirma.IFirma;
import korporatniFirma.IPobocka;
import korporatniFirma.Pozice;
import korporatniFirma.TypPoziceEnum;
import korporatniFirma.Zamestnanec;

public class Kubik_KorporatniFirma extends Application {

    private ComboBox<TypPoziceEnum> comBoxNovy;
    protected IFirma firma = new Firma();

    ListView<IPobocka> listViewPobocky = new ListView<>();
    ListView<Zamestnanec> listViewZamestnanci = new ListView<>();
    TreeView<Pozice> treeViewPozice = new TreeView<>();

    String jmenoSouboru = "korporatni_firma.ser";

    @Override
    public void start(Stage primaryStage) {
        AnchorPane rootItem = new AnchorPane();
        ObservableList<Node> children = rootItem.getChildren();
        Scene scene = new Scene(rootItem, 1240, 600);
        primaryStage.setTitle("Kubík - Korporátní Firma");
        primaryStage.setScene(scene);

        FlowPane pane = new FlowPane();
        //pane.setOrientation(Orientation.VERTICAL);
        pane.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setLeftAnchor(pane, 5.0);
        AnchorPane.setRightAnchor(pane, 5.0);
        AnchorPane.setBottomAnchor(pane, 5.0);
        pane.setPrefHeight(35);
        pane.setHgap(5);

        pane.getChildren().addAll(
                /*comBoxNovy = VytvareniKomponent.
                        newComboBox("Vložit nový proces", TypProcesuEnum.values(), pridejNovyProces()),*/
                VytvareniKomponent.newBtn("Zálohovat do souboru", null, handlerUloz),
                VytvareniKomponent.newBtn("Obnovit ze souboru", null, handlerObnov),
                VytvareniKomponent.newBtn("Zrušit firmu", null, zrusCelouFirmu()),
                VytvareniKomponent.newBtn("Test", null, TestData.runTestData(this)));
        listViewPobocky = VytvareniKomponent.newListView(0.0, 5.0, null, 48.5);
        listViewZamestnanci = VytvareniKomponent.newListView(0.0, null, 5.0, 48.5);
        treeViewPozice = VytvareniKomponent.newTreeView(0.0, 405.0, 405.0, 48.5);
        listViewPobocky.setPrefWidth(400);
        listViewZamestnanci.setPrefWidth(400);
        listViewPobocky.setOnMouseClicked(vyberMysi());
        treeViewPozice.setOnMouseClicked(vyberMysi());

        MenuItem pridejPozMI = new MenuItem("Přidat");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pridatItemPoz();
            }
        });

        MenuItem upravitPozMI = new MenuItem("Upravit");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                upravitItemPoz();
            }
        });

        MenuItem odeberPozMI = new MenuItem("Odebrat");
        odeberPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                odebratItemPoz();
            }
        });
        MenuItem pridejPobMI = new MenuItem("Přidat");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pridatItemPob();
            }
        });

        MenuItem upravitPobMI = new MenuItem("Upravit");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                upravitItemPob();
            }
        });

        MenuItem odeberPobMI = new MenuItem("Odebrat");
        odeberPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                odebratItemPob();
            }
        });
        MenuItem pridejZamMI = new MenuItem("Přidat");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pridatItemZam();
            }
        });

        MenuItem upravitZamMI = new MenuItem("Upravit");
        pridejPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                upravitItemZam();
            }
        });

        MenuItem odeberZamMI = new MenuItem("Odebrat");
        odeberPozMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                odebratItemZam();
            }
        });
        ContextMenu contextMenuPoz = new ContextMenu(pridejPozMI, upravitPozMI, odeberPozMI);
        ContextMenu contextMenuPob = new ContextMenu(pridejPobMI, upravitPobMI, odeberPobMI);
        ContextMenu contextMenuZam = new ContextMenu(pridejZamMI, upravitZamMI, odeberZamMI);
        treeViewPozice.setContextMenu(contextMenuPoz);
        listViewPobocky.setContextMenu(contextMenuPob);
        listViewZamestnanci.setContextMenu(contextMenuZam);
        children.addAll(pane, listViewPobocky, listViewZamestnanci, treeViewPozice);
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
    private TreeItem<Pozice> root;
    private TreeItem<Pozice> vedouciUseku;
    private TreeItem<Pozice> vedouciOddeleni;
    private TreeItem<Pozice> pracovnikOddeleni;

    protected void obnovZobrazeniSeznamu() {
        IPobocka pob = listViewPobocky.getSelectionModel().getSelectedItem();
        TreeItem<Pozice> akt = treeViewPozice.getSelectionModel().getSelectedItem();
        Pozice poz = (treeViewPozice.getSelectionModel().getSelectedItem() != null)
                ? treeViewPozice.getSelectionModel()
                        .getSelectedItem().getValue() : null;
        listViewPobocky.getItems().clear();
        treeViewPozice.setRoot(null);
        listViewZamestnanci.getItems().clear();
        firma.stream()
                .forEach(t -> {
                    listViewPobocky.getItems().add(t);
                    if (t == pob) {
                        t.stream()
                                .forEach(p -> {
                                    switch (p.getTyp()) {
                                        case REDITEL_POBOCKY:
                                            root = new TreeItem<>(p);
                                            treeViewPozice.setRoot(root);
                                            root.setExpanded(true);
                                            break;
                                        case VEDOUCI_USEKU:
                                            vedouciUseku = new TreeItem<>(p);
                                            root.getChildren().add(vedouciUseku);
                                            vedouciUseku.setExpanded(true);
                                            break;
                                        case VEDOUCI_ODDELENI:
                                            vedouciOddeleni = new TreeItem<>(p);
                                            vedouciUseku.getChildren()
                                                    .add(vedouciOddeleni);
                                            vedouciOddeleni.setExpanded(true);
                                            break;
                                        case PRACOVNIK_ODDELENI:
                                            pracovnikOddeleni = new TreeItem<>(p);
                                            vedouciOddeleni.getChildren()
                                                    .add(pracovnikOddeleni);
                                            pracovnikOddeleni.setExpanded(true);
                                            break;
                                    }
                                    listViewZamestnanci.getItems().add(p.getZam());
                                    if (poz == p && poz != null) {
                                        listViewZamestnanci.getSelectionModel()
                                                .select(poz.getZam());
                                    }
                                });
                    }
                }
                );
        listViewPobocky.getSelectionModel().select(pob);
        treeViewPozice.getSelectionModel().select(akt);
    }

    private final EventHandler<ActionEvent> handlerUloz = event -> {
        try {
            Serialization.uloz(jmenoSouboru, this);
        } catch (IOException ex) {
//            AlertHandler.errorAlert(null, "Chyba v uložení seznamu do souboru");
            Logger.getLogger(Kubik_KorporatniFirma.class.getName()).log(Level.SEVERE, null, ex);
        }

    };
    private final EventHandler<ActionEvent> handlerObnov = event -> {
        try {
            Serialization.obnov(jmenoSouboru, this);
            obnovZobrazeniSeznamu();
        } catch (IOException ex) {
//          AlertHandler.errorAlert(null, "Chyba v obnově seznamu ze souboru");
            Logger.getLogger(Kubik_KorporatniFirma.class.getName()).log(Level.SEVERE, null, ex);
        }
    };

    private EventHandler<MouseEvent> vyberMysi() {
        return event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                boolean rootB = (root != null) ? root.isExpanded() : false;
                boolean vedUs = (vedouciUseku != null) ? vedouciUseku.isExpanded() : false;
                boolean vedOd = (vedouciOddeleni != null) ? vedouciOddeleni.isExpanded() : false;
                boolean pracOd = (pracovnikOddeleni != null) ? pracovnikOddeleni.isExpanded() : false;
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    obnovZobrazeniSeznamu();
                }
                if (root != null && vedouciUseku != null && vedouciOddeleni != null && pracovnikOddeleni != null) {
                    root.setExpanded(rootB);
                    vedouciUseku.setExpanded(vedUs);
                    vedouciOddeleni.setExpanded(vedOd);
                    pracovnikOddeleni.setExpanded(pracOd);
                }
            }
        };
    }

    private void pridatItemPoz() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        if (aktItem != null) {
            firma.stream().forEach(t -> t.stream()
                    .filter(p -> p == aktItem.getValue())
                    .forEach(p -> {
                        switch (p.getTyp()) {
                            case REDITEL_POBOCKY:
                                t.vlozPozici(p);
                                break;
                            case VEDOUCI_USEKU:
                                break;
                            case VEDOUCI_ODDELENI:
                                break;
                            case PRACOVNIK_ODDELENI:
                                break;
                        }
                    }));
        }
    }

    private void upravitItemPoz() {
    }

    private void odebratItemPoz() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        firma.stream().forEach(t -> t.stream().filter(p -> p == aktItem.getValue()).forEach(p -> t.odeberPozici(0)));
    }

    private void pridatItemPob() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        if (aktItem != null) {
            firma.stream().forEach(t -> t.stream()
                    .filter(p -> p == aktItem.getValue())
                    .forEach(p -> {
                        switch (p.getTyp()) {
                            case REDITEL_POBOCKY:
                                t.vlozPozici(p);
                                break;
                            case VEDOUCI_USEKU:
                                break;
                            case VEDOUCI_ODDELENI:
                                break;
                            case PRACOVNIK_ODDELENI:
                                break;
                        }
                    }));
        }
    }

    private void upravitItemPob() {
    }

    private void odebratItemPob() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        firma.stream().forEach(t -> t.stream().filter(p -> p == aktItem.getValue()).forEach(p -> t.odeberPozici(0)));
    }

    private void pridatItemZam() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        if (aktItem != null) {
            firma.stream().forEach(t -> t.stream()
                    .filter(p -> p == aktItem.getValue())
                    .forEach(p -> {
                        switch (p.getTyp()) {
                            case REDITEL_POBOCKY:
                                t.vlozPozici(p);
                                break;
                            case VEDOUCI_USEKU:
                                break;
                            case VEDOUCI_ODDELENI:
                                break;
                            case PRACOVNIK_ODDELENI:
                                break;
                        }
                    }));
        }
    }

    private void upravitItemZam() {
    }

    private void odebratItemZam() {
        TreeItem<Pozice> aktItem = treeViewPozice.getSelectionModel().getSelectedItem();
        firma.stream().forEach(t -> t.stream().filter(p -> p == aktItem.getValue()).forEach(p -> t.odeberPozici(0)));
    }

    private EventHandler<ActionEvent> zrusCelouFirmu() {
        return event -> {
            firma.zrus();
            obnovZobrazeniSeznamu();
        };
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
