/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.javafx;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proj.javafx.engine.Player;
import proj.javafx.engine.Ranking;

/**
 *
 * @author rafae
 */
public class MainMenuPontuation extends BorderPane{
    private MainMenu menuStart;
    private ListView pontuations;
    
    public MainMenuPontuation(){
        menuStart = new MainMenu();
        pontuations = new ListView();
        
        pontuations.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        pontuations.setPrefWidth(400);
        
        setCenter(menuStart);

        /**
        * Evento para mostrar as pontucoes do jogador
        */
        this.menuStart.getPontuation().setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                String pontuation = Ranking.getInstance().getPlayerPlaying().toString();
                
                pontuations.getItems().clear();
                
                pontuations.getItems().add(pontuation);
                
                setBottom(pontuations);
            }
        });
        
        /**
        * Evento para mostrar as pontucoes globais
        */
        this.menuStart.getWorldPontuation().setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                ArrayList<Player> playerPontuations = Ranking.getInstance().getRankingOrdened();
                int i = 0;
                
                pontuations.getItems().clear();
                
                for(Player player : playerPontuations) {
                    pontuations.getItems().add(String.format("%dº Player Name: %s Pontuation: %d  \n", i++, player.getUsername(), player.getMaxPontuation()));
                }
                
                setBottom(pontuations);
            }
        });
    }
}
