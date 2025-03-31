package gui.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.application.Application;
 
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableFX2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        final ObservableList<Dados> data
                = FXCollections.observableArrayList(
                   new Dados("Maria", new Date(System.currentTimeMillis()) ),
                   new Dados("Elisabete",  new Date(System.currentTimeMillis()) )                        
                );
        
        final TableView<Dados> table = new TableView<>();
        table.setItems(data);
        table.setEditable(false);
        
        TableColumn<Dados, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setMinWidth(100);
        nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        TableColumn<Dados, Date> dateCol = new TableColumn<>("Data");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        dateCol.setCellFactory( cell -> {          
              
            return new TableCell<Dados, Date>() {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                
                @Override
                protected void updateItem( Date item, boolean empty) {
                   super.updateItem(item, empty);
                   if( !empty ) {
                      setText( format.format(item) );
                   }else {
                      setText("");
                      setGraphic(null);
                   }
                }
            };        
         } );
 
        table.getColumns().addAll(nomeCol, dateCol);

        Scene scene = new Scene(table, 600, 400);
 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static class Dados {

        private final StringProperty nome;        
        private final ObjectProperty<Date> date;

        public Dados(String nome, Date date ) {
            this.nome = new SimpleStringProperty(this, "Nome", nome);            
            this.date = new SimpleObjectProperty<>(this, "Data", date);
        }

        public String getNome() {
            return nome.get();
        }

        public void setNome(String value) {
            nome.set(value);
        }

        public Date getDate() {
            return date.get();
        }
        
        public void setDate(Date date) {
            this.date.set(date);
        }
        
        public StringProperty nomeProperty() {
            return nome ;
        }
        
        public ObjectProperty<Date> dateProperty() {
            return date ;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}