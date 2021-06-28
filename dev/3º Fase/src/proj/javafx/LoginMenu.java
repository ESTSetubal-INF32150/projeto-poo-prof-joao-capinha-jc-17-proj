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
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class LoginMenu extends GridPane {
    private Button login;
    private Button register;
    private Button exit;
    private Image logo;
    
    public LoginMenu() {
        this.login = new Button("Iniciar Sessão");
        this.register = new Button("Registar Utilizador");
        this.exit = new Button("Sair");
        this.logo = new Image("File:../../src/proj/javafx/image/logo.png");
        
        this.login.setAlignment(Pos.CENTER);
        this.register.setAlignment(Pos.CENTER);
        this.exit.setAlignment(Pos.CENTER);
        
        /**
        * Evento para fechar o programa
        */
        this.exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)exit.getScene().getWindow();
                Ranking.getInstance().saveRanking(Ranking.getInstance().getRanking());
                stage.close();
            }
        });
        
        /**
        * Evento para abrir o menu de login
        */
        this.login.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                LoginInput login = new LoginInput();
                Stage loginStage = new Stage();
                
                loginStage.initModality(Modality.APPLICATION_MODAL);
                loginStage.setTitle("B&D - Iniciar Sessão");
                loginStage.setScene(new Scene(login, 400, 600, Color.web("#1c466c", 1.0)));
                loginStage.showAndWait();
                
                if(Ranking.getInstance().getPlayerPlaying() != null) {
                    Stage stage = (Stage)exit.getScene().getWindow();
                    MainMenuPontuation mainMenu = new MainMenuPontuation();
                    
                    stage.setTitle("Boats and Docks");
                    stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                    stage.show();
                }
            }
        });
        
        /**
        * Evento para abrir o menu de registo do jogador
        */
        this.register.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                RegisterInput registar = new RegisterInput();
                Stage loginStage = new Stage();
                
                loginStage.initModality(Modality.APPLICATION_MODAL);
                loginStage.setTitle("B&D - Registar Utilizador");
                loginStage.setScene(new Scene(registar, 400, 600, Color.web("#1c466c", 1.0)));
                loginStage.showAndWait();
                
                if(Ranking.getInstance().getPlayerPlaying() != null) {
                    Stage stage = (Stage)exit.getScene().getWindow();
                    MainMenuPontuation mainMenu = new MainMenuPontuation();
                    
                    stage.setTitle("Boats and Docks");
                    stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                    stage.show();
                }
            }
        });
        
        
        setVgap(40);
        
        add(new ImageView(this.logo), 0, 0);
        add(this.login, 0, 1);
        add(this.register, 0, 2);
        add(this.exit, 0, 3);
        setAlignment(Pos.CENTER);
    }
}
