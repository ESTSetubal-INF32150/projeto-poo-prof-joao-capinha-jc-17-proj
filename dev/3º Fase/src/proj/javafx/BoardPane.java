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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getRowIndex;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import proj.javafx.engine.DificultyLevel;
import proj.javafx.engine.Game;
import proj.javafx.engine.Pontuation;
import proj.javafx.engine.Ranking;

/**
 *
 * @author tomasbota
 */
public class BoardPane extends GridPane{
    private Game game;
    
    private Button validate;
    private Button cancel;
    private Button pause;
    private Button help;
    private Timeline timer;
    
    private int mins;
    private int secs;
    private int millis;
    private boolean timerStoped;
    
    public BoardPane(String diff) {
        int i = 0, j = 0;
        
        this.game = new Game(Ranking.getInstance().getPlayerPlaying());
        this.validate = new Button("Validar");
        this.cancel = new Button("Terminar Jogo");
        this.pause = new Button("Pausar Jogo");
        this.help = new Button("Ajuda");
        this.mins = 0;
        this.secs = 0;
        this.millis = 0;
        this.timerStoped = false;
        
        validate.setDisable(true);
        
        timer = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        change();
                    }
		}
        ));
        
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.setAutoReverse(false);
        
        if(diff.equals("Aleatório")) {
            this.game.startGame(DificultyLevel.ALEATÓRIO);
        }
        else if(diff.equals("Fácil")) {
            this.game.startGame(DificultyLevel.FÁCIL);
        }
        else if(diff.equals("Intermédio")) {
            this.game.startGame(DificultyLevel.INTERMÉDIO);
        }
        else {
            this.game.startGame(DificultyLevel.DIFÍCIL);
        }
        
        for(i = 0; i < this.game.getBoard().getHeight(); i++) {
            for(j = 0; j < this.game.getBoard().getWidth(); j++) {
                String typeTile = this.game.getBoard().getTile(i, j).getCurrentType().toString();
                Rectangle tile = new Rectangle(90, 90);
                StackPane stackPane;
                Image image;
                
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);

                if(typeTile.equals("DESCONHECIDA")) {
                    image = new Image("File:../../src/proj/javafx/image/question.png", 48, 48, false, false);
                }
                else if(typeTile.equals("ÁGUA")) {
                    image = new Image("File:../../src/proj/javafx/image/water.png", 48, 48, false, false);
                }
                else if(typeTile.equals("BARCO")) {
                    image = new Image("File:../../src/proj/javafx/image/boat.png", 48, 48, false, false);
                }
                else {
                    image = new Image("File:../../src/proj/javafx/image/dock.png", 48, 48, false, false);
                }
                
                stackPane = new StackPane(tile, new ImageView(image));
                
                add(stackPane, i, j);

                stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        double x = getColumnIndex(stackPane);
                        double y = getRowIndex(stackPane);

                        String type;
                        String currentType = game.getBoard().getTile((int)x, (int)y).getCurrentType().toString();
                        Image newImage;
                        
                        if(!currentType.equals("PORTO") && timerStoped != true) {
                            game.toggleTile((int)x, (int)y);
                        
                            type = game.getBoard().getTile((int)x, (int)y).getCurrentType().toString();

                            for(Node node : stackPane.getChildren()) {
                                if(node instanceof ImageView)  {
                                    stackPane.getChildren().remove(node);
                                    break;
                                }
                            }

                            if(type.equals("DESCONHECIDA")) {
                                newImage = new Image("File:../../src/proj/javafx/image/question.png", 48, 48, false, false);
                            }
                            else if(type.equals("ÁGUA")) {
                                newImage = new Image("File:../../src/proj/javafx/image/water.png", 48, 48, false, false);
                            }
                            else if(type.equals("BARCO")) {
                                newImage = new Image("File:../../src/proj/javafx/image/boat.png", 48, 48, false, false);
                            }
                            else {
                                newImage = new Image("File:../../src/proj/javafx/image/dock.png", 48, 48, false, false);
                            }

                            stackPane.getChildren().add(new ImageView(newImage));
                            
                            if(game.validateBoard()) {
                                validate.setDisable(false);
                            }
                            else {
                                validate.setDisable(true);
                            }
                        }
                    }
                });
            }
        }
        
        /**
         * Evento para terminar o jogo
         */
        this.cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)cancel.getScene().getWindow();
                MainMenuPontuation mainMenu = new MainMenuPontuation();
                
                game.endGame();
                timer.stop();
                
                stage.setTitle("Boats and Docks");
                stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                stage.show();
            }
        });
        
        /**
         * Evento para mostrar o tutorial ao jogador
         */
        this.help.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Alert alert = alert = new Alert(Alert.AlertType.WARNING);
                
                timer.pause();
                
                alert.setTitle("B&D - Como Jogar");
                alert.setHeaderText("Como jogar Boats And Docks");
                alert.setContentText("Para conseguires jogar Boats And Docks apenas tens que clicar nos quadrados para ires passando pelos"
                        + "diferentes tipos de casa que existem! Existem 3 tipos de casa que podes alterar (Barco, Água e Desconhecida) que estão "
                        + "indicados pelas imagens barco azul, gota de água e ponto de interrogação, respetivamente. Para conseguires validar o tabuleiro,"
                        + "ou seja, saberes quantas casas que identificaste com o tipo incorreto tens que, pelo menos, atracar 1 barco em cada porto que existe."
                        + "Os portos estão identificados com a imagem de um porto de atracagem de barco e são casas 'fixas', ou seja, não podes alterar o seu tipo.");

                alert.showAndWait();
                
                timer.play();
            }
        });
        
        /**
         * Evento para mostrar o pausar o jogo
         */
        this.pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                if(timerStoped) {
                    timerStoped = false;
                    pause.setText("Pausar Jogo");
                    timer.play();
                }
                else {
                    pause.setText("Retomar Jogo");
                    timerStoped = true;
                    timer.pause();
                }
            }
        });
        
        /**
         * Evento para validar o tabuleiro
         */
        this.validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                int qtdWrongTiles = game.checkTilePlacementCorrect();
                Alert alert;
                
                if (qtdWrongTiles > 0) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("B&D - Validação");
                    alert.setHeaderText("Algumas casas estão mal identificadas!");
                    alert.setContentText("Ainda que tenhas atracado pelo menos um barco em cada porto tens " + qtdWrongTiles + " casa(s) mal identificadas!");

                    alert.showAndWait();
                } else {
                    timer.stop();
                    
                    Stage stage = (Stage)validate.getScene().getWindow();
                    MainMenuPontuation mainMenu = new MainMenuPontuation();
                    
                    String username = Ranking.getInstance().getPlayerPlaying().getUsername();
                    Pontuation pontuation = new Pontuation(game.getBasePoints(), game.getDifficultyLevelOrdinal(), game.getGameStarted());

                    Ranking.getInstance().getRanking().stream().filter(x -> x.getUsername().equals(username)).findAny().get().addGamePontuation(pontuation);

                    Ranking.getInstance().setPlayerPlaying(Ranking.getInstance().getRanking().stream().filter(x -> x.getUsername().equals(username)).findAny().get());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("B&D - Fim do Jogo");
                    alert.setHeaderText("Terminaste o jogo!");
                    alert.setContentText("Parabéns! Conseguiste identificar corretamente o posicionamento de todas as casas! O teu tempo foi: " + getTimerString());

                    alert.showAndWait();

                    game.endGame();

                    stage.setTitle("Boats and Docks");
                    stage.setScene(new Scene(mainMenu, 1152, 960, Color.web("#1c466c", 1.0)));
                    stage.show();
                }
            }
        });
        
        add(this.validate, 0, j+1);
        add(this.pause, i-2, j+1);
        add(this.help, 1, j+1);
        add(this.cancel, i-1, j+1);
        
        setAlignment(Pos.CENTER);
        
        timer.play();
    }
    
    /**
    * Método para alterar o titulo da janela do jogo de acordo com o timer
    */
    private void change() {
        Stage stage = (Stage)pause.getScene().getWindow();
        
        if(millis == 1000) {
            secs++;
            millis = 0;
        }
        if(secs == 60) {
            mins++;
            secs = 0;
        }
        
        stage.setTitle("B&D - Jogo " + this.game.getDificultyLevel().name() + " - " + (((mins/10) == 0) ? "0" : "") + mins + ":"
            + (((secs/10) == 0) ? "0" : "") + secs + ":" 
            + (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
    }
    
    /**
    * Método para obter a string com informação do timer
    */
    private String getTimerString() {
        return (((mins/10) == 0) ? "0" : "") + mins + ":"
            + (((secs/10) == 0) ? "0" : "") + secs + ":" 
            + (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++;
    }
}
