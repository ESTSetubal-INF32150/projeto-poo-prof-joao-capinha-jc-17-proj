/*
 * Possibilidade de arrayList com as Pontuações do Player
 */
import java.util.ArrayList;
public class Player{
    private String username;
    private ArrayList<Pontuation> gamePontuation;
    private int maxPontuation;
    /**
     *
     * Construct to create a new player
     *
     * @param name
     */
    public Player (String name){
        username = name;
        gamePontuation = new ArrayList<>();
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
     * @param set the maximum pontuation for the player
     */
    public void setMaxPontuation(int maxPontuation) {
        this.maxPontuation = maxPontuation;
    }

    /**
     *
     * @param gamePoints points earned
     * @param level     level played
     */
    public void addGamePontuation(int gamePoints, int level) {
        gamePontuation.add(pontuation(gamePoints,level));
        maxPontuation = pontuationSum(gamePontuation);
    }

    /**
     *
     * @param pontuations
     * @return sum of every punction that the player made
     */
    public int pontuationSum(ArrayList<Pontuation> pontuations){
        int sum = 0;
        for (int i = 0; i<pontuations.size();i++){
            sum += pontuations.get(i).getGamePoints();
        }
        return sum;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<gamePontuation.size();i++) {
            sb.append(String.format("Player Name: %s Pontuation: %f  Time: %t \n", getUsername(), gamePontuation.get(i).getGamePoints(), gamePontuation.get(i).getTime()));
        }
        return sb.toString();
    }
}
