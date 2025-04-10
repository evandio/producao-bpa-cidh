/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Cbo;
import model.entities.CboProfissional;
import model.entities.Profissional;
import model.services.CboService;

/**
 * FXML Controller class
 *
 * @author evandio.pereira
 */
public class ProfissionalFormController implements Initializable {

    //Injetando a dependencia do Pforissional
    private Profissional entityProf;
    private ObservableList<Cbo> obsListCbo;
    private CboService serviceCbo;

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
        System.out.println("onComboBoxAction");
    }

    @FXML
    private Button btAtualizar;

    @FXML
    public void onBtAtualizarAction(ActionEvent event) {
        System.out.println("onBtAtualizarAction");
        Cbo c = (Cbo) cboxCbos.getSelectionModel().getSelectedItem();
        
        CboProfissional p = new CboProfissional();
        p.setIsnProfissional(entityProf.getIsnUsuario());
        p.setCbo(c);
        entityProf.setCboProf(p);
        updateFormData();
        //modificar a entidade CboProf
        //entityProf.setCboProf(cboProf);
        
    }

    @FXML
    private Button btCancel;

    @FXML
    public void onBtCancelAction(ActionEvent event) {
        System.out.println("onBtCancelAction");
    }

    public void setEntityProf(Profissional entityProf) {
        this.entityProf = entityProf;
    }

    public void setServiceCbo(CboService serviceCbo) {
        this.serviceCbo = serviceCbo;
    }

    public void updateFormData() {

        if (entityProf == null) {
            throw new IllegalStateException("Entidade está nula");
        }

        txtProfissional.setText(entityProf.getDscUsuario());
        txtFormacao.setText(entityProf.getDscFormacao());
        txtNumConselho.setText(entityProf.getNumConselho());
        txtSiglaConselho.setText(entityProf.getSglConselho());

        //Caso venha null significa que não tem CBO associado ao profissional
        if (entityProf.getCboProf() == null) {
            txtCbo.setText("");
        } else {
            txtCbo.setText(entityProf.getCboProf().getCbo().getDsc_cbo());
        }

        List<Cbo> list = serviceCbo.todosCbos();
        obsListCbo = FXCollections.observableArrayList(list).sorted();
        cboxCbos.setItems(obsListCbo);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
