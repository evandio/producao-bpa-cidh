/*
 * Aplicação JavaFX para exibir e exportar o relatório de profissionais usando JavaBeans Datasource
 * Aplicação desenvolvida para producao de relatorios com iReport 5.6.0 - 
 * a aquitetura deve ser produzida as entities com o compilador 1.7 e atribuir o JAR ao iReport para possibilitar a leitura dos 
 * dados.
 */

import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import model.entities.Profissional;
import model.services.ProfissionalService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class RelatorioProfissionaisApp extends Application {

    private JasperPrint jasperPrint;
    private ProfissionalService service;

    @Override
    public void start(Stage primaryStage) {
        service = new ProfissionalService();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Configurar o SwingNode para o relatório
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        root.setCenter(swingNode);

        // Botão para exportar PDF
        Button btnExportPdf = new Button("Exportar para PDF");
        btnExportPdf.setOnAction(event -> exportToPdf());

        // Painel para o botão
        HBox buttonPanel = new HBox(btnExportPdf);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10));
        root.setBottom(buttonPanel);

        // Configurações da janela
        primaryStage.setTitle("Relatório de Profissionais");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            // Carregar os dados dos profissionais
            List<Profissional> profissionais;
            try {
                profissionais = service.localizarTodos();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar profissionais: " + e.getMessage());
                return;
            }

            // Carregar o relatório com JavaBeans Datasource
            
            HashMap hashMap = new HashMap();
            hashMap.put("lista", profissionais);
            try {
                String reportPath = "src/reports/RelatorioProfissionais.jasper";
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(profissionais);
                jasperPrint = JasperFillManager.fillReport(reportPath, hashMap, dataSource);
                JRViewer viewer = new JRViewer(jasperPrint);
                swingNode.setContent(viewer);
            } catch (JRException e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar relatório: " + e.getMessage());
            }
        });
    }

    private void exportToPdf() {
        try {
            String outputPath = "RelatorioProfissionais.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Relatório exportado para: " + outputPath);
        } catch (JRException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao exportar PDF: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}