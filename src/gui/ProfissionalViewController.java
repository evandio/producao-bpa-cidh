/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.CboProfissional;
import model.entities.Profissional;
import model.services.CboProfissionalService;
import model.services.ProfissionalService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class ProfissionalViewController implements Initializable, DataChangeListener {

    private ProfissionalService service;

    private ObservableList<Profissional> obsList;

    //Principio solid de inversao de controle 
    public void setProfissionalService(ProfissionalService service) {
        this.service = service;
    }

    @FXML
    private TextField filtroNome;

    @FXML
    public void onTextFieldFiltroKeyPress(KeyEvent event) {
        updateTableView(filtroNome.getText());
    }

    @FXML
    private Button apagar;

    @FXML
    public void onBtApagarAction(ActionEvent event) {
        updateTableView("");
        System.out.println("onBtApagarAction");
    }

    @FXML
    private TableColumn<Profissional, Profissional> tableColumEDIT;

    @FXML
    private TableView<Profissional> tableViewProfissional;

    @FXML
    private TableColumn<Profissional, String> tableColumnNomeProf;

    @FXML
    private TableColumn<Profissional, String> tableColumnFormacao;

    @FXML
    private TableColumn<Profissional, String> tableColumnNumConselho;

    @FXML
    private TableColumn<Profissional, String> tableColumnSigla;

    @FXML
    private TableColumn<Profissional, CboProfissional> tableColumnCbo;

    @FXML
    private Button btAssociaCbo;

    public void onBtAssociaCboAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Profissional prof = service.localizarProfissional("ALINE CAVALCANTE BEZERRA");

        createDialogForm(prof, "/gui/ProfissionalForm.fxml", parentStage);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnNomeProf.setCellValueFactory(new PropertyValueFactory<>("dscUsuario"));
        tableColumnFormacao.setCellValueFactory(new PropertyValueFactory<>("dscFormacao"));
        tableColumnNumConselho.setCellValueFactory(new PropertyValueFactory<>("numConselho"));
        tableColumnSigla.setCellValueFactory(new PropertyValueFactory<>("sglConselho"));
        tableColumnCbo.setCellValueFactory(new PropertyValueFactory<>("cboProf"));
        Utils.formatTableColumCboProf(tableColumnCbo);

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewProfissional.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(String nome) {
        if (service == null) {
            throw new IllegalStateException("Serviço está nulo!");
        }

        List<Profissional> lista = service.localizarProfissionais(nome);
        obsList = FXCollections.observableList(lista);
        tableViewProfissional.setItems(obsList);
        filtroNome.setFocusTraversable(false);
        initEditButton();

    }

    private void createDialogForm(Profissional prof, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            ProfissionalFormController controller = loader.getController();

            //Injeta o profissional preenchido no formulario
            controller.setEntityProf(prof);

            //Injeta o servico para encontrar todos os Cbos
            controller.setService(new CboProfissionalService());

            controller.subscribeDataChangeListener(this);

            //Injeta todos os CBOs no formulario
            controller.updateFormData();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Associação de CBO ao profissional");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
            

            filtroNome.setText("");
            updateTableView("");

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a View", e.getMessage(), AlertType.ERROR);
        }
    }

    private void initEditButton() {
        tableColumEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumEDIT.setCellFactory(param -> new TableCell<Profissional, Profissional>() {
            private final Button button = new Button("Atualizar CBO");

            @Override
            protected void updateItem(Profissional obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm(obj, "/gui/ProfissionalForm.fxml", Utils.currentStage(event))
                );
            }

        });
    }

    @Override
    public void onDataChange() {
        updateTableView("");
    }
}
