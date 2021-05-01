public class Player{
    private String username;

    /**
     *
     * Construct to create a new player
     *
     * @param name
     */
    public Player (String name){
        username = name;
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

}
