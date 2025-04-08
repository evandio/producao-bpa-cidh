/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Profissional;
import model.services.ProfissionalService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class ProfissionalViewController implements Initializable {

    private ProfissionalService service;

    private ObservableList<Profissional> obsList;

    //Principio solid de inversao de controle 
    public void setProfissionalService(ProfissionalService service) {
        this.service = service;
    }

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
    private Button btAssociaCbo;

    public void onBtAssociaCboAction() {
        System.out.println("onBtAssociaCboAction");
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

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewProfissional.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Serviço está nulo!");
        }

        List<Profissional> lista = service.localizarTodos();
        obsList = FXCollections.observableArrayList(lista);
        tableViewProfissional.setItems(obsList);
    }

}
