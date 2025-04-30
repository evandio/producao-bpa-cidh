/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.util.Alerts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.entities.LoteBpa;
import model.entities.Profissional;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class LoteBpaFormController implements Initializable {

    private LoteBpa entity;
    
    @FXML
    private TextField txtProfissional;
    
    @FXML
    private TextField txtEspecialidade;
    
    @FXML
    private TextField txtCbo;

    @FXML
    private RadioButton rbManha;
    
    @FXML
    private Button gravar;
    
    public void onBtGravar(ActionEvent event){
        //protecao
        
        System.out.println("onBtGravar");
    }

    @FXML
    private RadioButton rbTarde;
    
    private ToggleGroup turnoGroup;
    
    
    public void updateFormView() {
        if (entity == null) {
            Alerts.showAlert("Erro", null, "O lote esta nulo!", AlertType.ERROR);
        }

        txtProfissional.setText(entity.getProfissional().getDscUsuario());
        txtEspecialidade.setText(entity.getProfissional().getDscFormacao());
        //txtCbo.setText(entity.getProfissional().
        
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        //isso traz um erro
       // rbManha.setToggleGroup(turnoGroup);
        //rbTarde.setToggleGroup(turnoGroup);
    }

}
