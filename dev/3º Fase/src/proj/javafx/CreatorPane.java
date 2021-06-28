/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import proj.javafx.engine.DificultyLevel;
import proj.javafx.engine.Game;
import proj.javafx.engine.Pontuation;
import proj.javafx.engine.Ranking;
import proj.javafx.engine.TileType;

/**
 *
 * @author tomasbota
 */
public class CreatorPane extends GridPane{
    private Game game;
    
    private Button validate;
    private Button cancel;
    private Button save;
    private Button help;
    
    private String levelName;
    
    public CreatorPane(String diff, String level) {
        int i = 0, j = 0;
        
        this.game = new Game(Ranking.getInstance().getPlayerPlaying());
        this.validate = new Button("Validar");
        this.cancel = new Button("Terminar Jogo");
        this.save = new Button("Guardar");
        this.help = new Button("Ajuda");
        this.levelName = level;
        
        save.setDisable(true);
        
        if(diff.equals("Aleatório")) {
            this.game.startLevelCreator(DificultyLevel.ALEATÓRIO);
        }
        else if(diff.equals("Fácil")) {
            this.game.startLevelCreator(DificultyLevel.FÁCIL);
        }
        else if(diff.equals("Intermédio")) {
            this.game.startLevelCreator(DificultyLevel.INTERMÉDIO);
        }
        else {
            this.game.startLevelCreator(DificultyLevel.DIFÍCIL);
        }
        
        for(i = 0; i < this.game.getBoard().getHeight(); i++) {
            for(j = 0; j < this.game.getBoard().getWidth(); j++) {
                Rectangle tile = new Rectangle(90, 90);
                StackPane stackPane;
                Image image;
                
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);

                image = new Image("File:../../src/proj/javafx/image/water.png", 48, 48, false, false);

                stackPane = new StackPane(tile, new ImageView(image));
                
                add(stackPane, i, j);

                stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        double x = getColumnIndex(stackPane);
                        double y = getRowIndex(stackPane);
                        
                        String type;
                        Image newImage;
                        
                        type = game.getBoard().getTile((int)x, (int)y).getTileType();
                        
                        if(type.equals("Barco")) {
                            game.setTileType((int)x, (int)y, TileType.PORTO);
                        }
                        else if(type.equals("Porto")){
                            game.setTileType((int)x, (int)y, TileType.ÁGUA);
                        }
                        else {
                            game.setTileType((int)x, (int)y, TileType.BARCO);
                        }

                        type = game.getBoard().getTile((int)x, (int)y).getTileType();

                        for(Node node : stackPane.getChildren()) {
                            if(node instanceof ImageView)  {
                                stackPane.getChildren().remove(node);
                                break;
                            }
                        }

                        if(type.equals("Barco")) {
                            newImage = new Image("File:../../src/proj/javafx/image/boat.png", 48, 48, false, false);
                        }
                        else if(type.equals("Porto")){
                            newImage = new Image("File:../../src/proj/javafx/image/dock.png", 48, 48, false, false);
                        }
                        else {
                            newImage = new Image("File:../../src/proj/javafx/image/water.png", 48, 48, false, false);
                        }

                        stackPane.getChildren().add(new ImageView(newImage));

                        if(game.validateBoard()) {
                            validate.setDisable(false);
                        }
                        else {
                            validate.setDisable(true);
                        }
                    }
                });
            }
        }
        
        /**
        * Evento para terminar a criação dos niveis
        */
        this.cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)cancel.getScene().getWindow();
                MainMenuPontuation mainMenu = new MainMenuPontuation();
                
                game.endGame();
                
                stage.setTitle("Boats and Docks");
                stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        /**
        * Evento para mostrar a ajuda ao jogador
        */
        this.help.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Alert alert = alert = new Alert(Alert.AlertType.WARNING);
                
                alert.setTitle("B&D - Como Jogar");
                alert.setHeaderText("Como jogar Boats And Docks");
                alert.setContentText("Para conseguires jogar Boats And Docks apenas tens que clicar nos quadrados para ires passando pelos"
                        + "diferentes tipos de casa que existem! Existem 3 tipos de casa que podes alterar (Barco, Água e Desconhecida) que estão "
                        + "indicados pelas imagens barco azul, gota de água e ponto de interrogação, respetivamente. Para conseguires validar o tabuleiro,"
                        + "ou seja, saberes quantas casas que identificaste com o tipo incorreto tens que, pelo menos, atracar 1 barco em cada porto que existe."
                        + "Os portos estão identificados com a imagem de um porto de atracagem de barco e são casas 'fixas', ou seja, não podes alterar o seu tipo.");

                alert.showAndWait();
            }
        });
        
        /**
        * Evento para validar o tabuleiro
        */
        this.validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                boolean validation = game.validateBoardLevelCreator();
                Alert alert;
                
                if (!validation) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("B&D - Validação");
                    alert.setHeaderText("Ainda não atracaste um barco pelo menos em todos os portos!");

                    alert.showAndWait();
                } else {      
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("B&D - Fim do Jogo");
                    alert.setHeaderText("O tabuleiro está correto! Clique em 'SAVE' para guardar o nível criado!");
                    
                    alert.showAndWait();

                    save.setDisable(false);
                }
            }
        });
        
        /**
        * Evento para gravar o nivel que foi criado
        */
        this.save.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Stage stage = (Stage)validate.getScene().getWindow();
                MainMenuPontuation mainMenu = new MainMenuPontuation();
                
                String username = Ranking.getInstance().getPlayerPlaying().getUsername();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("B&D - Fim do Jogo");
                alert.setHeaderText("Nível criado!");

                alert.showAndWait();
                
                game.saveLevel(levelName, username);
                game.endGame();
                
                stage.setTitle("Boats and Docks");
                stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        add(this.validate, 0, j+1);
        add(this.save, i-2, j+1);
        add(this.help, 1, j+1);
        add(this.cancel, i-1, j+1);
        
        setAlignment(Pos.CENTER);
    }
}
