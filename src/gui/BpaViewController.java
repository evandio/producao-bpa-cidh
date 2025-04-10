/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import gui.util.Utils;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.entities.LoteBpa;
import model.entities.Profissional;
import model.services.LoteBpaService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class BpaViewController implements Initializable {

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

        List<LoteBpa> lista = service.localizarTodos();
        obsList = FXCollections.observableArrayList(lista);
        tableViewLoteBpa.setItems(obsList);

    }

    @FXML
    private Button btNovoBpa;

    @FXML
    public void onBtNovoBpaAction() {
        System.out.println("onBtNovoBpaAction");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {

        //Precisa associar as variáveis aos IDs do FXML na Tela do SceneBuild
        tableColumnLote.setCellValueFactory(new PropertyValueFactory<>("loteBpa"));
        

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

}
