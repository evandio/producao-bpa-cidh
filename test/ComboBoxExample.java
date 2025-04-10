import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComboBoxExample extends Application {
    
    private ComboBox<String> comboBox;
    private ObservableList<String> items;
    private TextField inputField;
    
    // Lista de nomes aleatórios com até 15 caracteres
    private final List<String> nomesAleatorios = Arrays.asList(
        "Ana", "Carlos", "Beatriz", "Daniel", "Eduardo",
        "Fernanda", "Gabriel", "Helena", "Igor", "Juliana"
    );

    @Override
    public void start(Stage primaryStage) {
        // Inicializa a lista observável com os nomes aleatórios
        items = FXCollections.observableArrayList(nomesAleatorios);
        comboBox = new ComboBox<>(items);
        
        // Campo de texto para entrada de novos itens
        inputField = new TextField();
        inputField.setPromptText("Digite um novo item");
        
        // Botões para adicionar e remover itens
        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> adicionarItem());
        
        Button removeButton = new Button("Remover");
        removeButton.setOnAction(e -> removerItemSelecionado());
        
        // Botão para adicionar nome aleatório
        Button addRandomButton = new Button("Adicionar Aleatório");
        addRandomButton.setOnAction(e -> adicionarNomeAleatorio());
        
        // Layout
        HBox buttonBox = new HBox(10, addButton, removeButton, addRandomButton);
        VBox root = new VBox(10, 
                new Label("ComboBox Example:"), 
                comboBox, 
                inputField, 
                buttonBox);
        root.setPadding(new Insets(15));
        
        // Configuração da cena
        Scene scene = new Scene(root, 350, 200);
        primaryStage.setTitle("ComboBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Método para adicionar um novo item à ComboBox
     */
    public void adicionarItem() {
        String novoItem = inputField.getText().trim();
        if (!novoItem.isEmpty() && !items.contains(novoItem)) {
            items.add(novoItem);
            inputField.clear();
            // Ordena a lista após adicionar
            FXCollections.sort(items);
        }
    }
    
    /**
     * Método para remover o item selecionado da ComboBox
     */
    public void removerItemSelecionado() {
        String itemSelecionado = comboBox.getSelectionModel().getSelectedItem();
        if (itemSelecionado != null) {
            items.remove(itemSelecionado);
        }
    }
    
    /**
     * Método para adicionar um nome aleatório da lista
     */
    public void adicionarNomeAleatorio() {
        Random random = new Random();
        String nomeAleatorio = nomesAleatorios.get(random.nextInt(nomesAleatorios.size()));
        
        if (!items.contains(nomeAleatorio)) {
            items.add(nomeAleatorio);
            FXCollections.sort(items);
        }
    }
    
    /**
     * Método para adicionar um item específico (para uso externo)
     * @param item O item a ser adicionado
     */
    public void adicionarItem(String item) {
        if (item != null && !item.trim().isEmpty() && !items.contains(item.trim())) {
            items.add(item.trim());
            FXCollections.sort(items);
        }
    }
    
    /**
     * Método para remover um item específico (para uso externo)
     * @param item O item a ser removido
     */
    public void removerItem(String item) {
        if (item != null && items.contains(item)) {
            items.remove(item);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}