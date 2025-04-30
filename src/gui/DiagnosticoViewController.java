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
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Diagnostico;
import model.services.DiagnosticoService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class DiagnosticoViewController implements Initializable, DataChangeListener {

    private DiagnosticoService service;

    private ObservableList<Diagnostico> obsList;

    public void setService(DiagnosticoService service) {
        this.service = service;
    }

    @FXML
    private TableView<Diagnostico> tableViewDiagnostico;

    @FXML
    private TableColumn<Diagnostico, Integer> tableColumnIsnDiagnostico;

    @FXML
    private TableColumn<Diagnostico, String> tableColumnDscDiagnostico;

    @FXML
    private TableColumn<Diagnostico, Date> tableColumnDataCadastro;

    @FXML
    private TableColumn<Diagnostico, Diagnostico> tableColumnEDIT;

    @FXML
    private Button btNovo;

    @FXML
    public void ontBtNovoAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Diagnostico obj = new Diagnostico();
        createDialogForm(obj, "/gui/DiagnosticoForm.fxml", parentStage);
    }

    public void updateTableView() {
        if (service == null) {
            Alerts.showAlert("Erro", null, "O servico está nulo", Alert.AlertType.ERROR);
            throw new IllegalStateException("");
        }

        List<Diagnostico> lista = service.todosDiagnostico();

        obsList = FXCollections.observableList(lista);
        tableViewDiagnostico.setItems(obsList);
        initEditButton();
    }

    private void createDialogForm(Diagnostico obj, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            DiagnosticoFormController controller = loader.getController();
            controller.setDiagnostico(obj);
            controller.setDiagnosticoService(new DiagnosticoService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();

            //Criar a tela para o form
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
            updateTableView();

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a View", e.getMessage(), Alert.AlertType.ERROR);
            e.getMessage();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeNodes();
    }

    private void initializeNodes() {

        tableColumnIsnDiagnostico.setCellValueFactory(new PropertyValueFactory<>("isnDiagnostico"));

        tableColumnDscDiagnostico.setCellValueFactory(new PropertyValueFactory<>("dscDiagnostico"));

        tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        Utils.formatTableColumnDate(tableColumnDataCadastro, "dd/MM/yyyy");

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDiagnostico.prefHeightProperty().bind(stage.heightProperty());
    }

    private void initEditButton() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Diagnostico, Diagnostico>() {
            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Diagnostico obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(event -> createDialogForm(obj, "/gui/DiagnosticoForm.fxml", Utils.currentStage(event))
                );
            }
        });
    }

    @Override
    public void onDataChange() {
        updateTableView();
    }

}
