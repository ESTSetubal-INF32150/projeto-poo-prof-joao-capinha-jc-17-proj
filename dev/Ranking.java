/**
 * ArrayList de players
 * Singleton Constructor
 *
 * Missing order by pontuation
 */
import java.util.ArrayList;

public class Ranking {
    private static Ranking single_instance = null;
    private ArrayList<Player> ranking;

    private Ranking(){
        ranking = new ArrayList<>();
    }

    public static Ranking getInstance()
    {
        if (single_instance == null)
            single_instance = new Ranking();

        return single_instance;
    }

    public void orderByUserMax(ArrayList<Player> ranking){
        Player temp;
        int i, j = 0;
        for (i = 0; i < ranking.size()-1; i++)
            for (j = 0; j < ranking.size()-1-i; j++)
                if (ranking.get(j).getMaxPontuation() > ranking.get(j+1).getMaxPontuation() )
                {
                    temp = ranking.get(j);
                    ranking.set(j,ranking.get(j+1) );
                    ranking.set(j+1, temp);
                }
    }

    @Override
        public String toString(){
            orderByUserMax(ranking);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<ranking.size();i++) {
                int temp = 0;
                sb.append(String.format("Player Name: %s Pontuation: %f  \n", ranking.get(i).getUsername(), ranking.get(i).getMaxPontuation()));
            }
            return sb.toString();
        }


}
