package proj.gr17; /**
 * ArrayList de players
 * Singleton Constructor
 *
 * Missing order by pontuation
 */
import levelloader.LevelInfo;
import levelloader.LoadLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ranking {
    private static Ranking single_instance = null;
    private ArrayList<Player> ranking;

    /**
     * Singleton constructor with an array with every player info
     */
    private Ranking(ArrayList<Player> players){
        ranking = players;
    }

    /**
     * Code to inicialize the singleton constructor
     * @return
     */
    public static Ranking getInstance() {
        if (single_instance == null) {
            single_instance = new Ranking(loadRanking());
        }

        return single_instance;
    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }

    public Player getPlayer(String username) {
        if(this.ranking.stream().anyMatch(x -> x.getUsername().equals(username))) {
            return this.ranking.stream().filter(x -> x.getUsername().equals(username)).findAny().get();
        }
        else {
            return null;
        }
    }

    public void setRanking(ArrayList<Player> players) {
        this.ranking = players;
    }

    /**
     *
     * Order the player ranking by the amount of pontuation they got
     */
    public void orderByUserMax(ArrayList<Player> ranking){
        Player temp;
        int i, j = 0;
        for (i = 0; i < ranking.size()-1; i++) {
            for (j = 0; j < ranking.size() - 1 - i; j++) {
                if (ranking.get(j).getMaxPontuation() > ranking.get(j + 1).getMaxPontuation()) {
                    temp = ranking.get(j);
                    ranking.set(j, ranking.get(j + 1));
                    ranking.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Save all players and their progress
     * @param ranking ArrayList of players and their progress
     */
    public void saveRanking(ArrayList<Player> ranking) {
        String path = "./saves";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for(int i = 0; i < ranking.size(); i++) {
            File newLevel = new File(path + "/" + ranking.get(i).getUsername() + ".save");
            StringBuilder sb = new StringBuilder(ranking.get(i).getUsername() + "\n");
            String level = "";

            try {
                if (newLevel.createNewFile() == false) {
                    newLevel.delete();

                    newLevel = new File(path + "/" + ranking.get(i).getUsername() + ".save");

                    newLevel.createNewFile();
                }

                FileWriter myWriter = new FileWriter(path + "/" + ranking.get(i).getUsername() + ".save");

                for(Integer key : ranking.get(i).getGamePontuation().keySet()) {
                    if(key == 1) {
                        level = "Fácil";
                    }
                    else if(key == 2) {
                        level = "Intermédio";
                    }
                    else {
                        level = "Difícil";
                    }

                    sb.append(level + "\n");
                    sb.append(ranking.get(i).getGamePontuation().get(key).getGamePoints() + "\n");
                    sb.append(ranking.get(i).getGamePontuation().get(key).getDate().format(formatter) + "\n");
                    sb.append("\n");
                }

                myWriter.write(sb.toString());
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return Printes the game ranking
     */
    @Override
    public String toString(){
            orderByUserMax(ranking);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<ranking.size();i++) {
                sb.append(String.format("%dº Player Name: %s Pontuation: %d  \n", i + 1, ranking.get(i).getUsername(), ranking.get(i).getMaxPontuation()));
            }
            return sb.toString();
        }

    /**
     * Load all players and their progress
      * @return ArrayList of players and their progress
     */
    public static ArrayList<Player> loadRanking() {
        File f = new File("./saves");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<Player> ranking = new ArrayList<>();

        if (f.exists()) {
            String[] fileList = f.list();
            String[] var3 = fileList;
            int var4 = fileList.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String str = var3[var5];
                System.out.println(str);
                if (str.endsWith(".save")) {
                    File file = new File("./saves/" + str);
                    Player player = new Player("");

                    try {
                        int cnt = 0;
                        int level = 0;
                        int gamePoints = 0;
                        String date = "";

                        Scanner myReader = new Scanner(file);

                        String data = myReader.nextLine();
                        player.setUsername(data);

                        while (myReader.hasNextLine()) {
                            data = myReader.nextLine();

                            if(cnt == 0) {
                                switch(data) {
                                    case "Fácil":
                                        level = 1;
                                        break;
                                    case "Intermédio":
                                        level = 2;
                                        break;
                                    case "Difícil":
                                        level = 3;
                                        break;
                                }
                            }
                            else if(cnt == 1) {
                                gamePoints = Integer.parseInt(data);
                            }
                            else if(cnt == 2) {
                                date = data;;
                            }
                            else if(cnt == 3) {
                                Pontuation points = new Pontuation(gamePoints, level, LocalDateTime.of(LocalDate.parse(date, formatter), LocalDateTime.now().toLocalTime()));

                                player.addGamePontuation(points);

                                cnt = -1;
                            }

                            cnt++;
                        }

                        ranking.add(player);
                    }
                    catch (FileNotFoundException e)  {
                        System.out.println("Ficheiro não encontrado!");
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Caminho não encontrado!");
        }

        return ranking;
    }
}
