/*
 * Possibilidade de arrayList com as Pontuações do Player
 */
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
        for(int i = 0; i<gamePontuation.size();i++) {
            sb.append(String.format("Player Name: %s Pontuation: %f  Time: %t \n", getUsername(), gamePontuation.get(i).getGamePoints(), gamePontuation.get(i).getTime()));
        }
        return sb.toString();
    }
}
