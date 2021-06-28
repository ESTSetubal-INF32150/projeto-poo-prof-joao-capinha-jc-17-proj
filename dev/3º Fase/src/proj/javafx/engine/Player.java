package proj.javafx.engine;/*
 * Possibilidade de arrayList com as Pontuações do Player
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
public class Player{
    private String username;
    private HashMap<Integer, Pontuation > gamePontuation;
    private int maxPontuation;

    /**
     *
     * Construct to create a new player
     *
     * @param name
     */
    public Player (String name){
        username = name;
        gamePontuation = new HashMap<Integer, Pontuation>();
        maxPontuation = 0;
    }

    /**
     *
     * @return the player username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username change the username name (use String)
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return sum of every point that the player obtained
     */
    public int getMaxPontuation() {
        return maxPontuation;
    }

    /**
     *
     * @param maxPontuation for the player
     */
    public void setMaxPontuation(int maxPontuation) {
        this.maxPontuation = maxPontuation;
    }

    /**
     *
     * @param pontuation from a new game to add to the user
     */
    public void addGamePontuation(Pontuation pontuation) {
        if(gamePontuation.containsKey(pontuation.getLevel())){
            this.gamePontuation.replace(pontuation.getLevel(),pontuation);
            maxPontuation = pontuationSum(gamePontuation);
        }else{
            gamePontuation.put(pontuation.getLevel(), pontuation);
            maxPontuation = pontuationSum(gamePontuation);
        }

    }

    /**
     * Get the hashmap regarding all game points of the player
     * @return hashmap with all game points of the player
     */
    public HashMap<Integer, Pontuation> getGamePontuation() {
        return this.gamePontuation;
    }

    /**
     *
     * @param pontuations A pontuation Array
     * @return sum of every punction that the player made
     */
    public int pontuationSum(HashMap<Integer, Pontuation> pontuations){
        int sum = 0;
        for (Integer key: pontuations.keySet()) {
            sum += pontuations.get(key).getGamePoints();
        }
        return sum;
    }

    /**
     *
     * @return print the player, pontuation and time of that pontuation
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String level = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Integer key: this.gamePontuation.keySet()) {
            if(key == 1) {
                level = "Fácil";
            }
            else if(key == 2) {
                level = "Intermédio";
            }
            else {
                level = "Difícil";
            }

            sb.append(String.format("Level: %s - Pontuation: %d - Date: %s\n", level, this.gamePontuation.get(key).getGamePoints(), this.gamePontuation.get(key).getDate().format(formatter)));
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj.getClass() != this.getClass()) {
            return false;
        }

        if(((Player)obj).getUsername().equals(this.getUsername())) {
            return true;
        }
        else {
            return false;
        }
    }
}
