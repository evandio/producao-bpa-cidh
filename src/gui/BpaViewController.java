/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.LoteBpa;
import model.entities.Profissional;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class BpaViewController implements Initializable {

    @FXML
    private TableView<LoteBpa> tableViewLoteBpa;
    
    @FXML
    private TableColumn<LoteBpa, Integer> tableColumnLote;
    
    @FXML
    private TableColumn<Profissional, String> tableColumnProfissional;
    
    @FXML
    private TableColumn<LoteBpa, Date> tableColumnDataAtendimento;
    
    @FXML
    private TableColumn<LoteBpa, Integer> tableColumnQtdAtendimento;
    
    @FXML
    private Button btNovoBpa;
    
    @FXML
    public void onBtNovoBpaAction(){
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
        tableColumnProfissional.setCellValueFactory(new PropertyValueFactory<>("profissional"));
        tableColumnQtdAtendimento.setCellValueFactory(new PropertyValueFactory<>("qtdAtendimento"));
        
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewLoteBpa.prefHeightProperty().bind(stage.heightProperty());
    }
    
}
