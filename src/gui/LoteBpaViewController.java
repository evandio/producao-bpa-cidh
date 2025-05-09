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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.LoteBpa;
import model.entities.Profissional;
import model.services.LoteBpaService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class LoteBpaViewController implements Initializable, DataChangeListener {

    //Injetar a dependencia do servico sem o acoplamento forte
    private LoteBpaService service;

    private ObservableList<LoteBpa> obsList;

    //Principio solid de inversao de controle 
    public void setLoteBpaService(LoteBpaService service) {
        this.service = service;
    }

    //private ProfissionalService serviceProfissional;
    @FXML
    private TableView<LoteBpa> tableViewLoteBpa;

    @FXML
    private TableColumn<LoteBpa, Integer> tableColumnLote;

    @FXML
    private TableColumn<LoteBpa, Profissional> tableColumnProfissional;

    @FXML
    private TableColumn<LoteBpa, Date> tableColumnDataAtendimento;

    @FXML
    private TableColumn<LoteBpa, Integer> tableColumnQtdAtendimento;

    @FXML
    private TableColumn<LoteBpa, Integer> tableColumnTurno;

    //Responsavel por acessar o servico e ler os dados do Lote BPA e setar na observable list
    public void updateTableView() {

        //Caso o programador esqueca de injetar o serviço
        if (service == null) {
            throw new IllegalStateException("Serviço está nulo!");
        }

        List<LoteBpa> lista = service.listaTodos();
        obsList = FXCollections.observableArrayList(lista);
        tableViewLoteBpa.refresh();
        tableViewLoteBpa.setItems(obsList);
    }

    @FXML
    private Button btNovoBpa;

    @FXML
    public void onBtNovoBpaAction(ActionEvent event) {
        System.out.println("onBtNovoBpaAction");
        Stage parentStage = Utils.currentStage(event);

        LoteBpa obj = new LoteBpa();
        obj.setProfissional(new Profissional());

        createDialogForm(obj, "/gui/LoteBpaForm.fxml", parentStage, "Cadastro de Lote BPA");
    }

    private void createDialogForm(LoteBpa objLote, String absoluteName, Stage parentStage, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            LoteBpaFormController controller = loader.getController();
            controller.setLoteBpa(objLote);
            controller.setLoteBpaService(new LoteBpaService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormView();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(titulo);
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a View de criação de Lote!", e.getMessage(), AlertType.ERROR);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        initializeNodes();
        initDoubleClickHandler();
    }

    private void initializeNodes() {

        //Precisa associar as variáveis aos IDs do FXML na Tela do SceneBuild 
        tableColumnLote.setCellValueFactory(new PropertyValueFactory<>("loteBpa")); //nome da valriavel na entitie

        tableColumnDataAtendimento.setCellValueFactory(new PropertyValueFactory<>("dataAtendimento"));
        //Formatação da coluna
        Utils.formatTableColumnDate(tableColumnDataAtendimento, "dd/MM/yyyy");

        tableColumnProfissional.setCellValueFactory(new PropertyValueFactory<>("profissional"));
        //Formatação da coluna
        Utils.formatTableColumProfissional(tableColumnProfissional, 1);

        tableColumnQtdAtendimento.setCellValueFactory(new PropertyValueFactory<>("qtdAtendimento"));

        tableColumnTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewLoteBpa.prefHeightProperty().bind(stage.heightProperty());
    }

    private void initDoubleClickHandler() {
        tableViewLoteBpa.setRowFactory(tv -> {
            TableRow<LoteBpa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    LoteBpa selectedLote = row.getItem();
                    System.out.println("Duplo clique no lote: " + selectedLote.getLoteBpa());
                    Stage parentStage = Utils.currentStage(event);
                    createDialogForm(selectedLote, "/gui/LoteBpaForm.fxml", parentStage, "Edição de Lote BPA");
                }
            });
            return row;
        });
    }

    @Override
    public void onDataChange() {
        updateTableView();
    }

}
