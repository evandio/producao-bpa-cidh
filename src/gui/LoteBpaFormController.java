/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.LoteBpa;
import model.entities.Profissional;
import model.services.LoteBpaService;
import model.services.ProfissionalService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class LoteBpaFormController implements Initializable, DataChangeListener {

    private LoteBpa objLoteBpa;

    public void setLoteBpa(LoteBpa objLoteBpa) {
        this.objLoteBpa = objLoteBpa;
    }

    private LoteBpaService service;

    public void setLoteBpaService(LoteBpaService service) {
        this.service = service;
    }

    @FXML
    private TextField txtProfissional;

    @FXML
    private TextField txtEspecialidade;

    @FXML
    private TextField txtCbo;

    @FXML
    private DatePicker dtpDataCadastro;

    @FXML
    private RadioButton rbManha;

    @FXML
    private RadioButton rbTarde;

    private ToggleGroup turnoGroup;

    @FXML
    private Button btGravar;

    public void onBtGravar(ActionEvent event) {
        if (objLoteBpa == null) {
            Alerts.showAlert("Erro", null, "Lote BPA está nulo!", AlertType.ERROR);
            return;
        }
        if (service == null) {
            Alerts.showAlert("Erro", null, "Serviço está nulo!", AlertType.ERROR);
            return;
        }

        // Validação dos campos
        if (objLoteBpa.getProfissional() == null) {
            Alerts.showAlert("Erro", null, "Selecione um profissional!", AlertType.ERROR);
            return;
        }
        if (dtpDataCadastro.getValue() == null) {
            Alerts.showAlert("Erro", null, "Selecione uma data de atendimento!", AlertType.ERROR);
            return;
        }
        if (turnoGroup.getSelectedToggle() == null) {
            Alerts.showAlert("Erro", null, "Selecione um turno (Manhã ou Tarde)!", AlertType.ERROR);
            return;
        }

        // Salvar os dados
        saveLoteBpa(event);
    }

    @FXML
    private Button btProfissionais;

    public void onBtProfissionais(ActionEvent event) {
        System.out.println("onBtProfissionais");
        Stage parentStage = Utils.currentStage(event);

        createDialogForm(objLoteBpa, "/gui/ProfissionalView.fxml", parentStage);
    }

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    public void updateFormView() {
        if (objLoteBpa == null) {
            Alerts.showAlert("Erro", null, "O lote esta nulo!", AlertType.ERROR);
            return;
        }

        if (objLoteBpa.getProfissional() != null) {
            txtProfissional.setText(objLoteBpa.getProfissional().getDscUsuario());
            txtEspecialidade.setText(objLoteBpa.getProfissional().getDscFormacao());
            txtCbo.setText(objLoteBpa.getProfissional().getObjCboProf() != null
                    && objLoteBpa.getProfissional().getObjCboProf().getCbo() != null
                    ? objLoteBpa.getProfissional().getObjCboProf().getCbo().getDsc_cbo()
                    : "");
        } else {
            txtProfissional.setText("");
            txtEspecialidade.setText("");
            txtCbo.setText("");

        }

        // Atualiza o turno
        if (objLoteBpa.getTurno() != null && objLoteBpa.getTurno() == 1) {
            rbManha.setSelected(true);
        } else if (objLoteBpa.getTurno() != null && objLoteBpa.getTurno() == 2) {
            rbTarde.setSelected(true);
        }

        // Atualiza a data de atendimento
        if (objLoteBpa.getDataAtendimento() != null) {
            LocalDate localDate = objLoteBpa.getDataAtendimento()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dtpDataCadastro.setValue(localDate);
        } else {
            dtpDataCadastro.setValue(null);
        }

    }

    private void createDialogForm(LoteBpa objLoteBpa, String absoluteName, Stage parentStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            ProfissionalViewController controller = loader.getController();

            //Injeta o profissional preenchido no formulario
            controller.setProfissionalService(new ProfissionalService());
            controller.setSelectionMode(true); // Ativa o modo de seleção
            controller.updateTableView("");

            //Injeta o servico para encontrar todos os Cbos
            //controller.setService(new CboProfissionalService());
            //controller.subscribeDataChangeListener(this);
            //Injeta todos os CBOs no formulario
            //controller.updateFormData();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seleção de Profissional");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

            // Obtém o profissional selecionado
            Profissional selectedProf = controller.getObjProfissional();
            if (selectedProf != null) {
                objLoteBpa.setProfissional(selectedProf);
                updateFormView();
            }

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a View de Profissional", e.getMessage(), AlertType.ERROR);
        }
    }

    private void saveLoteBpa(ActionEvent event) {
        // Atualiza o objLoteBpa com os dados do formulário
        LocalDate localDate = dtpDataCadastro.getValue();
        if (localDate != null) {
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            objLoteBpa.setDataAtendimento(date);
        }

        // Atualiza o turno (o banco de dados espera um Integer: 1 para Manhã, 2 para Tarde)
        if (turnoGroup.getSelectedToggle() != null) {
            RadioButton selectedRadio = (RadioButton) turnoGroup.getSelectedToggle();
            if (selectedRadio == rbManha) {
                objLoteBpa.setTurno(1); // Manhã
            } else if (selectedRadio == rbTarde) {
                objLoteBpa.setTurno(2); // Tarde
            }
            System.out.println("Turno definido como: " + objLoteBpa.getTurno()); // Log para depuração
        } else {
            throw new IllegalStateException("Nenhum turno selecionado!");
        }

        // Valida o turno antes de salvar (garante que seja 1 ou 2)
        if (objLoteBpa.getTurno() == null || (objLoteBpa.getTurno() != 1 && objLoteBpa.getTurno() != 2)) {
            Alerts.showAlert("Erro", null, "Turno inválido! Deve ser 1 (Manhã) ou 2 (Tarde).", AlertType.ERROR);
            return;
        }

        // Salva no banco
        try {
            boolean loteExists = service.existsLoteBpa(
                    objLoteBpa.getProfissional().getIsnUsuario(),
                    objLoteBpa.getDataAtendimento(),
                    objLoteBpa.getTurno(),
                    objLoteBpa.getLoteBpa() // Ignora o próprio lote em caso de edição
            );

            if (loteExists) {
                Alerts.showAlert("Erro", null,
                        "Já existe um lote para este profissional na data e turno selecionados!",
                        AlertType.ERROR);
                return;
            }

            // Salva no banco
            service.saveOrUpdateLote(objLoteBpa);
            System.out.println("Notificando listeners...");
            notifyDataChangeListeners();
            Stage stage = Utils.currentStage(event);
            stage.close();
        } catch (Exception e) {
            Alerts.showAlert("Erro", null, "Erro ao salvar o Lote BPA: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChange();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        turnoGroup = new ToggleGroup();
        rbManha.setToggleGroup(turnoGroup);
        rbTarde.setToggleGroup(turnoGroup);
    }

    @Override
    public void onDataChange() {
        updateFormView();
    }

}
