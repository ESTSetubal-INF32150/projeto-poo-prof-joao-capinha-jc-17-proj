package proj.javafx.engine;

public abstract class Tile {
    private int x;
    private int y;
    private boolean failed;

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.failed = false;
    }

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y position of the tile in the y axis of the matrix
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param failed change the state of the tile based of the incorrect selection in the game
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     *
     * @return get the position of the tile in the x axis of the matrix
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return get the position of the tile in the y axis of the matrix
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return get the state of the tile
     */
    public boolean getFailed() {
        return this.failed;
    }

    /**
     *
     * @return get the type of tile
     */
    public abstract String getTileType();

    /**
     *
     * @return Get the atual type of tile
     */
    public abstract TileType getTileTypeExt();

    /**
     * Change the type of tile based on the current type of tile
     */
    public abstract void toogleType();

    /**
     *
     * @return Get the current type of tile
     */
    public abstract TileType getCurrentType();

    /**
     *
     * @return Check if the tile is flagged as the correct type of tile. True if yes, False if not.
     */
    public abstract boolean isCorrectType();

    /**
     *
     * @return get the info about the tile
     */
    @Override
    public String toString() {
        return String.format("Posição %d %d", this.getX(), this.getY());
    }
}