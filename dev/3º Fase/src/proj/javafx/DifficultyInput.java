/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class DifficultyInput extends GridPane {
    private ComboBox selector;
    private Button next;
    private Button fechar;
    private Label lbl;
    private Image logo;   
    private TextField levelName;
    
    public DifficultyInput(boolean creator) {
        this.selector = new ComboBox();
        this.next = new Button("Começar");
        this.fechar = new Button("Fechar");
        this.lbl = new Label("Escolher Dificuldade");
        this.logo = new Image("File:../../src/proj/javafx/image/logo.png");
        this.levelName = new TextField();
        
        this.selector.getItems().add("Aleatório");
        this.selector.getItems().add("Fácil");
        this.selector.getItems().add("Intermédio");
        this.selector.getItems().add("Difícil");
        
        /**
        * Evento para voltar para o menu principal
        */
        this.fechar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)fechar.getScene().getWindow();
                MainMenuPontuation mainMenu = new MainMenuPontuation();

                stage.setTitle("Boats and Docks");
                stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        /**
        * Evento para aceitar a dificuldade e abrir o ecrã com o tabuleiro
        */
        this.next.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)next.getScene().getWindow();
                String diffChoosen = selector.getValue().toString();
                
                if(!creator) {
                    BoardPane jogo = new BoardPane(diffChoosen);
                
                    stage.setTitle("B&D - Jogo " + diffChoosen + " - 00:00:00");
                    stage.setScene(new Scene(jogo, 1152, 960, Color.web("#1c466c", 1.0)));
                    stage.show(); 
                }
                else {
                    String lvl = levelName.getText();
                    
                    if(!lvl.equals("")) {
                        CreatorPane jogo = new CreatorPane(diffChoosen, levelName.getText());
                
                        stage.setTitle("B&D - Jogo " + diffChoosen + " - 00:00:00");
                        stage.setScene(new Scene(jogo, 1152, 960, Color.web("#1c466c", 1.0)));
                        stage.show(); 
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("B&D - Criado de Níveis");
                        alert.setHeaderText("Indique o nome do nivel que pretende criar!");

                        alert.showAndWait();
                    }
                }
            }
        });
        
        setVgap(30);
        
        add(new ImageView(this.logo), 0, 0);
        add(this.lbl, 0, 1);
        add(this.selector, 0, 2);
        add(this.next, 0, 3);
        add(this.fechar, 0, 4);
        
        if(creator) {
            add(this.levelName, 0, 5);
        }
        
        setAlignment(Pos.CENTER);
    }
}
