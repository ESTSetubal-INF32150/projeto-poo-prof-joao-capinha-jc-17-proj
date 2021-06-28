package proj.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proj.javafx.engine.Ranking;

public class MainMenu extends GridPane {
    private Button start;
    private Button load;
    private Button pontuation;
    private Button globalPontuations;
    private Button createLevel;
    private Button fechar;
    private Image logo;
    
    public MainMenu(){
        start = new Button("Novo Jogo");
        load = new Button("Carregar Jogo");
        pontuation = new Button("Pontuação Pessoal");
        globalPontuations = new Button("Pontuação Global");
        createLevel = new Button("Criador De Níveis");
        fechar = new Button("Sair");
        this.logo = new Image("File:../../src/proj/javafx/image/logo.png");
        
        /**
        * Evento para abrir o menu para escolher a dificuldade
        */
        this.start.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)start.getScene().getWindow();
                DifficultyInput diffInput = new DifficultyInput(false);
                    
                stage.setTitle("B&D - Escolher Dificuldade");
                stage.setScene(new Scene(diffInput, 1152, 1024, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        /**
        * Evento encerrar a sessão do utilizador e voltar ao menu inicial
        */
        this.fechar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)start.getScene().getWindow();
                LoginMenu root = new LoginMenu();
                
                Ranking.getInstance().setPlayerPlaying(null);
                
                stage.setTitle("Boats and Docks");
                stage.setScene(new Scene(root, 1152, 1024, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        /**
        * Evento para abrir o menu para escolher a dificuldade
        */
        this.createLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)start.getScene().getWindow();
                DifficultyInput diffInput = new DifficultyInput(true);
                    
                stage.setTitle("B&D - Escolher Dificuldade");
                stage.setScene(new Scene(diffInput, 1152, 1024, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        setVgap(40);
        
        add(new ImageView(this.logo), 2, 0);
        add(start,1,0);
        add(load,1,1);
        add(pontuation,3,0);
        add(globalPontuations,3,1);
        add(createLevel,1,2);
        add(fechar,3,2);
        
        setAlignment(Pos.CENTER);
    }
    
    /**
    * Método seletor do botao de mostrar as pontuações do jogador
    */
    public Button getPontuation(){
        return pontuation;
    }
    
    /**
    * Método seletor do botao de mostrar as pontuações globais
    */
    public Button getWorldPontuation(){
        return globalPontuations;
    }
}
