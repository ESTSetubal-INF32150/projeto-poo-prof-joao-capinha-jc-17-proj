/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.javafx;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import proj.javafx.engine.Player;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class RegisterInput extends GridPane {
    private TextField username;
    private Button submit;
    private Button fechar;
    private Label label;
    private Image logo;
    
    public RegisterInput() {
        this.username = new TextField();
        this.submit = new Button("Registar Utilizador");
        this.fechar = new Button("Cancelar");
        this.logo = new Image("File:../../src/proj/javafx/image/logo.png");
        this.label = new Label("Username");
        
        this.submit.setAlignment(Pos.CENTER);
        this.fechar.setAlignment(Pos.CENTER);
        
        /**
        * Evento para registar o jogador
        */
        this.submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                boolean ok = false;
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                String usernameStr = username.getText();
                Optional<ButtonType> result;
                Player temp;
                
                alert.setTitle("Registo de Utilizador");
                
                if(usernameStr.equals("")) {
                    alert.setHeaderText("Nome de Utilizador Vazio!");
                    alert.setContentText("Por favor indique um nome de utilizador!");

                    alert.showAndWait();
                }
                else {
                    temp = new Player(usernameStr);
                    
                    if (Ranking.getInstance().getRanking().contains(temp)) { 
                        alert.setHeaderText("Utilizador já registado!");
                        alert.setContentText("O nome de utilizador fornecido já se encontra registado! Se quiser pode iniciar sessão, caso contrário forneça um nome de utilizador diferente!");

                        alert.showAndWait();
                    } 
                    else {
                        Ranking.getInstance().getRanking().add(temp);
                        
                        alert.setHeaderText("Utilizador registado com sucesso!");
                        alert.setContentText("Utilizador criado com sucesso! Pode agora iniciar sessão se assim pretender!");

                        alert.showAndWait();
                        
                        username.setText("");
                    }
                }
            }
        });
        
        /**
        * Evento para voltar ao menu inicial
        */
        this.fechar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)fechar.getScene().getWindow();
                stage.close();
            }
        });
        
        setVgap(40);
        
        setAlignment(Pos.CENTER);
        
        add(new ImageView(this.logo), 0, 0);
        add(this.label, 0, 1);
        add(this.username, 0, 2);
        add(this.submit, 0, 3);
        add(this.fechar, 0, 4);
    }
}
