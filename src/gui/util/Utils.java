/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.entities.CboProfissional;
import model.entities.Profissional;

/**
 *
 * @author evand
 */
public class Utils {

    public static Stage currentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
    
    public static Stage currentStage(MouseEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static Integer tryParseToInt(String str) {

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static <T> void formatTableColumnDate(TableColumn<T, Date> tableColumn, String format) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Date> cell = new TableCell<T, Date>() {
                private SimpleDateFormat sdf = new SimpleDateFormat(format);

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(sdf.format(item));
                    }
                }
            };
            return cell;
        });
    }

    public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, int decimalPaces) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Double> cell = new TableCell<T, Double>() {

                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        Locale.setDefault(Locale.US);
                        setText(String.format("%." + decimalPaces + "f", item));
                    }

                }
            };
            return cell;
        });
    }

    /**
     *
     * @param <T>
     * @param tableColumn
     * @param valor (1=Nome Profissional; 2=Formação)
     */
    public static <T> void formatTableColumProfissional(TableColumn<T, Profissional> tableColumn, int valor) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Profissional> cell = new TableCell<T, Profissional>() {

                @Override
                protected void updateItem(Profissional item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                        setGraphic(null);
                    } else {
                        //Criar uma forma de coletar os valores de cada item da entidade passada
                        switch (valor) {
                            case 1:
                                setText(item.getDscUsuario());
                                break;
                            case 2:
                                setText(item.getDscFormacao());
                                break;
                            default:
                                setText("Precisa escolher uma opção");
                        }
                    }
                }
            };
            return cell;
        });
    }

    /**
     *
     * @param <T>
     * @param tableColumn
     * @param valor (1=Nome Profissional; 2=Formação)
     */
    public static <T> void formatTableColumCboProf(TableColumn<T, CboProfissional> tableColumn) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, CboProfissional> cell = new TableCell<T, CboProfissional>() {

                @Override
                protected void updateItem(CboProfissional item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item.getCbo() == null) {
                        setText("");
                        setGraphic(null);
                    } else {
                        //Criar uma forma de coletar os valores de cada item da entidade passada
                        setText(item.getCbo().getDsc_cbo());
                    }
                }
            };
            return cell;
        });
    }

    public static void formatDatePicker(DatePicker datePiker, String format) {
        datePiker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

            {
                datePiker.setPromptText(format.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public static Double tryParseToDouble(String str) {

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
