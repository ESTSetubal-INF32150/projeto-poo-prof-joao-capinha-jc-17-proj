/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class ProjJavafx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        LoginMenu root = new LoginMenu();
        primaryStage.setTitle("Boats and Docks");
        primaryStage.setScene(new Scene(root, 1152, 960, Color.web("#1c466c", 1.0)));
        primaryStage.show();
    }
    
    @Override
    public void stop(){
        Ranking.getInstance().saveRanking(Ranking.getInstance().getRanking());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
