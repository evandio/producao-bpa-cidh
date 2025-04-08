/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.LoteBpaService;
import model.services.ProfissionalService;

/**
 *
 * @author evandio.pereira
 */
public class MainViewController implements Initializable {

    //IDs dos menus da GUI
    //Cadastro do Bpa
    @FXML
    private MenuItem menuItemCadastroBpa;

     @FXML
    public void onMenuItemCadastroBpaAction() {
        //Função de inicialização como parêmetro. Utilização de expressão Lambida
        loadView("/gui/BpaView.fxml", (BpaViewController controller) -> {
            controller.setLoteBpaService(new LoteBpaService());
            controller.updateTableView();
        });
    }

    @FXML
    private MenuItem menuItemAssociaCbo;


     @FXML
    public void onMenuItemAssociaCboAction() {
        loadView("/gui/ProfissionalView.fxml", (ProfissionalViewController controller) -> {
            controller.setProfissionalService(new ProfissionalService());
            controller.updateTableView();
        });
    }

    //Janela do Sobre
    @FXML
    private MenuItem menuItemSobre;

    @FXML
    public void onMenuItemSobreAction() {
        loadView("/gui/Sobre.fxml", x -> {
        });
    }

    //synchronized garante que as threds serao todas executadas sem interrupção 
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            //Pega referencia do palco principal
            Scene mainScene = Main.getMainScene();

            //Cast do palco pegando a Vbox do scroll pane
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            //Preserva o menu
            Node mainMenu = mainVbox.getChildren().get(0);

            //limpa todos os filhos do VBox principal
            mainVbox.getChildren().clear();

            //recupera o menu 
            mainVbox.getChildren().add(mainMenu);

            //adiciona os filhos da tela Sobre
            mainVbox.getChildren().addAll(newVBox.getChildren());

            //Ativar a funcao passada pelo consumer
            T controller = loader.getController();
            initializingAction.accept(controller);

        } catch (IOException e) {
            //Alerts.showAlert("IO Exception", "Erro ao carregar a página", e.getMessage(), Alert.AlertType.ERROR);

            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {

    }

}
