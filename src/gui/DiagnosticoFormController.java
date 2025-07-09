/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.DbException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Diagnostico;
import model.exception.ValidationException;
import model.services.DiagnosticoService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class DiagnosticoFormController implements Initializable {

    @FXML
    private Button btCadastrar;

    public void onBtCadastrarAction(ActionEvent event) {
        if (entity == null) {
            throw new DbException("Entidade está vazia");
        }

        if (service == null) {
            throw new DbException("Serviçõ esta nulo");
        }

        try {
            entity = getFormData();
            service.inserirOuAtualizar(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        } catch (ValidationException e) {
            e.getMessage();
        } catch (DbException e) {
            Alerts.showAlert("Erro ao sarvar o objeto", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private Button btCancelar;
    
    public void onBtCancelar(ActionEvent event){
        Utils.currentStage(event).close();
    }

    @FXML
    private TextField txtIsnDiagnostico;

    @FXML
    private TextField txtDscDiagnostico;

    @FXML
    private DatePicker dtpDataCadastro;

    private Diagnostico entity;

    private DiagnosticoService service;

    private List<DataChangeListener> dataChangeListener = new ArrayList<>();

    public void setDiagnostico(Diagnostico entity) {
        this.entity = entity;
    }

    public void setDiagnosticoService(DiagnosticoService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListener.add(listener);
    }

    public void updateFormData() {
        if (entity == null) {
            Alerts.showAlert("Erro", null, "O Diagnóstico está nulo!", Alert.AlertType.ERROR);
            throw new IllegalStateException("O Diagnóstico está nulo!");
        }

        txtIsnDiagnostico.setText(String.valueOf(entity.getIsnDiagnostico()).toString());
        txtDscDiagnostico.setText(entity.getDscDiagnostico());
        if (entity.getDataCadastro() != null) {
            dtpDataCadastro.setValue(LocalDateTime.ofInstant(entity.getDataCadastro().toInstant(), ZoneId.systemDefault()).toLocalDate());
        }
    }

    private Diagnostico getFormData() {
        Diagnostico obj = new Diagnostico();

        ValidationException exception = new ValidationException("Erros de validação!");

        obj.setIsnDiagnostico(Utils.tryParseToInt(txtIsnDiagnostico.getText()));

        if (txtDscDiagnostico.getText() == null || txtDscDiagnostico.getText().equals("")) {
            exception.addErros("Diagnostico", "O Diagnostico está vazio!");
        }

        obj.setDscDiagnostico(txtDscDiagnostico.getText());

        if (dtpDataCadastro.getValue() == null) {
            exception.addErros("Data de cadastro", "A data do cadastro está vazio!");
        } else {
            Instant instant = Instant.from(dtpDataCadastro.getValue().atStartOfDay(ZoneId.systemDefault()));
            obj.setDataCadastro(Date.from(instant));
        }

        if (exception.getErros().size() > 0) {
            throw exception;
        }

        return obj;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListener) {
            listener.onDataChange();
        }
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtIsnDiagnostico);
        Utils.formatDatePicker(dtpDataCadastro, "dd/MM/yyyy");
    }

    private void setErrorMensages(Map<String, String> erros) {
        Set<String> fields = erros.keySet();

        //labelErroName.setText((fields.contains("name") ? erros.get("name") : ""));
        //labelErroEmail.setText(fields.contains("email") ? erros.get("email") : "");
        //labelErroBaseSalary.setText(fields.contains("baseSalary") ? erros.get("baseSalary") : "");
        //labelErroBirthDate.setText(fields.contains("birthDate") ? erros.get("birthDate") : "");
    }
}
