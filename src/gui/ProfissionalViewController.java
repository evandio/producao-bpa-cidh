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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    private Profissional objProfissional;

    private ObservableList<Profissional> obsList;

    private boolean isSelectionMode = false; // Flag para modo de seleção

    public void setSelectionMode(boolean isSelectionMode) {
        this.isSelectionMode = isSelectionMode;
    }

    //Principio solid de inversao de controle 
    public void setProfissionalService(ProfissionalService service) {
        this.service = service;
    }

    public Profissional getObjProfissional() {
        return objProfissional;
    }

    @FXML
    private TextField filtroNome;

    @FXML
    public void onTextFieldFiltroKeyPress(KeyEvent event) {
        updateTableView(filtroNome.getText());
    }

    @FXML
    private Button btSelecionar;

    @FXML
    public void onBtSelecionarAction(ActionEvent event) {
        if (objProfissional != null) {
            Stage currentStage = Utils.currentStage(event);
            currentStage.close();
        } else {
            Alerts.showAlert("Avaiso", null, "Nenhum profissional selecionado!", AlertType.WARNING);
        }
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
        Profissional prof = getObjProfissional();

        createDialogForm(prof, "/gui/ProfissionalForm.fxml", parentStage);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDoubleClickHandler();
        initKeyboardNavigation();
        initializeNodes();

        // Configura a visibilidade do botão Selecionar
        if (btSelecionar != null) {
            btSelecionar.setVisible(isSelectionMode);
        }
    }

    public void updateTableView(String nome) {
        if (service == null) {
            throw new IllegalStateException("Serviço está nulo!");
        }

        List<Profissional> lista = service.localizarProfissionais(nome);
        obsList = FXCollections.observableList(lista);
        tableViewProfissional.setItems(obsList);
        filtroNome.setFocusTraversable(false); //não focar na variavel filtroNome;

        // Garante que a TableView tenha foco após atualizar
        tableViewProfissional.requestFocus();
        // Seleciona a primeira linha por padrão, se houver itens
        if (!obsList.isEmpty()) {
            tableViewProfissional.getSelectionModel().selectFirst();
            objProfissional = obsList.get(0);
        } else {
            objProfissional = null;
        }

        // Atualiza a visibilidade do botão Selecionar
        if (btSelecionar != null) {
            btSelecionar.setVisible(isSelectionMode);
        }
    }

    private void createDialogForm(Profissional prof, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            ProfissionalFormController controller = loader.getController();

            //Injeta o profissional preenchido no formulario
            controller.setObjProf(prof);

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

    private void initDoubleClickHandler() {
        tableViewProfissional.setRowFactory(tv -> {
            TableRow<Profissional> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Profissional profissionalSelecionado = row.getItem();
                    objProfissional = profissionalSelecionado;
                    tableViewProfissional.getSelectionModel().select(row.getIndex());
                }

                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Profissional profissionalSelecionado = row.getItem();
                    createDialogForm(profissionalSelecionado,
                            "/gui/ProfissionalForm.fxml", Utils.currentStage(event));
                }
            });
            return row;
        });
    }

    private void initKeyboardNavigation() {
        tableViewProfissional.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                int selectedIndex = tableViewProfissional.getSelectionModel().getSelectedIndex();

                // Navegação para cima
                if (event.getCode() == KeyCode.UP && selectedIndex > 0) {
                    tableViewProfissional.getSelectionModel().select(selectedIndex - 1);
                    tableViewProfissional.scrollTo(selectedIndex - 1);
                    objProfissional = tableViewProfissional.getItems().get(selectedIndex - 1);
                } // Navegação para baixo
                else if (event.getCode() == KeyCode.DOWN && selectedIndex < tableViewProfissional.getItems().size() - 1) {
                    tableViewProfissional.getSelectionModel().select(selectedIndex + 1);
                    tableViewProfissional.scrollTo(selectedIndex + 1);
                    objProfissional = tableViewProfissional.getItems().get(selectedIndex + 1);
                }

                // Consome o evento para evitar comportamento padrão indesejado
                event.consume();
            }
        });

        // Garante que a TableView possa receber foco
        tableViewProfissional.setFocusTraversable(true);
    }

    @Override
    public void onDataChange() {
        updateTableView("");
    }

    private void initializeNodes() { ///Sempre associar o nome da variavel dentro da entidade
        tableColumnNomeProf.setCellValueFactory(new PropertyValueFactory<>("dscUsuario"));
        tableColumnFormacao.setCellValueFactory(new PropertyValueFactory<>("dscFormacao"));
        tableColumnNumConselho.setCellValueFactory(new PropertyValueFactory<>("numConselho"));
        tableColumnSigla.setCellValueFactory(new PropertyValueFactory<>("sglConselho"));
        tableColumnCbo.setCellValueFactory(new PropertyValueFactory<>("objCboProf"));
        Utils.formatTableColumCboProf(tableColumnCbo);

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewProfissional.prefHeightProperty().bind(stage.heightProperty());
    }
}
