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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proj.javafx.engine.Player;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class LoginInput extends GridPane {
    private TextField username;
    private Button submit;
    private Button fechar;
    private Label label;
    private Image logo;
    
    public LoginInput() {
        this.username = new TextField();
        this.submit = new Button("Iniciar Sessão");
        this.fechar = new Button("Cancelar");
        this.logo = new Image("File:../../src/proj/javafx/image/logo.png");
        this.label = new Label("Username");
        
        this.submit.setAlignment(Pos.CENTER);
        this.fechar.setAlignment(Pos.CENTER);
        
        /**
        * Evento para realizar o login
        */
        this.submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                boolean ok = false;
                
                Alert alert = new Alert(AlertType.INFORMATION);
                String usernameStr = username.getText();
                Optional<ButtonType> result;
                Player player;
                
                if(usernameStr.equals("")) {
                    alert.setTitle("Início de Sessão");
                    alert.setHeaderText("Nome de Utilizador Vazio!");
                    alert.setContentText("Por favor indique um nome de utilizador!");

                    result = alert.showAndWait();
                }
                else {
                    player = Ranking.getInstance().getPlayer(usernameStr);
                    
                    if(player == null) {
                        alert.setTitle("Início de Sessão");
                        alert.setHeaderText("Utilizador não registado!");
                        alert.setContentText("O nome de utilizador indicado não se encontra registado! Por favor registe-se para poder jogar!");

                        result = alert.showAndWait();
                    }
                    else {
                        alert.setTitle("Início de Sessão");
                        alert.setHeaderText("Sessão iniciada!");
                        alert.setContentText("Bem vindo " + usernameStr + "!");

                        result = alert.showAndWait();
                        Ranking.getInstance().setPlayerPlaying(player);
                        ok = true;
                    }
                }
                
                if(!result.isPresent() || result.get() == ButtonType.OK) {
                    if(ok == true) {
                        Stage stage = (Stage)username.getScene().getWindow();
                        stage.close();
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
