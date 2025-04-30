/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.DbException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Cbo;
import model.entities.CboProfissional;
import model.entities.Profissional;
import model.services.CboProfissionalService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class ProfissionalFormController implements Initializable {

    //Injetando a dependencia do Pforissional
    private Profissional objProf;
    private ObservableList<Cbo> obsListCbo;
    private CboProfissionalService service;
    private CboProfissional objCboProf;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    //Declaracao dos componentes da Tela
    @FXML
    private TextField txtProfissional;

    @FXML
    private TextField txtFormacao;

    @FXML
    private TextField txtNumConselho;

    @FXML
    private TextField txtSiglaConselho;

    @FXML
    private TextField txtCbo;

    @FXML
    private ComboBox<Cbo> cboxCbos;

    @FXML
    public void onComboBoxAction(ActionEvent event) {
        Cbo objCbo = (Cbo) cboxCbos.getSelectionModel().getSelectedItem();
        objCboProf = objProf.getObjCboProf();
        objCboProf.setCbo(objCbo);
        objProf.setObjCboProf(objCboProf);
        updateFormData();
    }

    @FXML
    private Button btAtualizar;

    @FXML
    public void onBtAtualizarAction(ActionEvent event) {

        if (objCboProf == null) {
            Alerts.showAlert("Escolha um CBO", null, "Não foi escolhido nenhum CBO", Alert.AlertType.INFORMATION);
            throw new IllegalStateException("CBO está nulo!");
        }

        try {
            service.saveOrUpdate(objProf);
            notifyDataChangeListener();
            Utils.currentStage(event).close();
        } catch (DbException e) {
            Alerts.showAlert("Erro!", null, "Não foi possível associar o \nCBO ao Profissional!", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private Button btCancel;

    @FXML
    @SuppressWarnings("empty-statement")
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();;
    }

    public void setObjProf(Profissional objProf) {
        this.objProf = objProf;
    }
    
    

    public void setService(CboProfissionalService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    public void updateFormData() {

        if (objProf == null) {
            throw new IllegalStateException("Profissional está nulo!");
        }

        if (service == null) {
            throw new IllegalStateException("O servico está nulo!");
        }

        txtProfissional.setText(objProf.getDscUsuario());
        txtFormacao.setText(objProf.getDscFormacao());
        txtNumConselho.setText(objProf.getNumConselho());
        txtSiglaConselho.setText(objProf.getSglConselho());

        //Caso venha null significa que não tem CBO associado ao profissional
        if (objProf.getObjCboProf().getCbo() == null) {
            txtCbo.setText("");
        } else {
            txtCbo.setText(objProf.getObjCboProf().getCbo().getDsc_cbo());
        }

        List<Cbo> list = service.todosCbos();
        obsListCbo = FXCollections.observableArrayList(list).sorted();
        cboxCbos.setItems(obsListCbo);

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Avisa para quem se inscreveu que houve mudancas;
    private void notifyDataChangeListener() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChange();
        }
    }

}
