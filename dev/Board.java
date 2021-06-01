
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> tiles;

    public Board() {
        this.tiles = new ArrayList<>();
    }

    /**
     *
     * @param tile the tile to be added to the board
     */
    public void addNewTile(Tile tile) {
        this.tiles.add(tile);
    }

    /**
     *
     * @param board the matrix representing the level that the was chosen
     */
    public void startGame(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                switch(board[i][j]) {
                    case '.':
                        this.tiles.add(new AguaTile(i, j));
                        break;
                    case 'B':
                        int qtd = 0;

                        for(int k = i + 1; k < board.length && k != i+2; k++) {
                            if(board[k][j] == 'B') {
                                qtd++;
                            }
                            else {
                                qtd = 0;
                                break;
                            }
                        }

                        for(int k = i + 1; qtd == 0 && k > board.length && k != i-2; k--) {
                            if(board[k][j] == 'B') {
                                qtd++;
                            }
                            else {
                                qtd = 0;
                                break;
                            }
                        }

                        for(int k = j - 1; qtd == 0 && k > board.length && k != j - 2; k--) {
                            if(board[i][k] == 'B') {
                                qtd++;
                            }
                            else {
                                qtd = 0;
                                break;
                            }
                        }

                        for(int k = j + 1; qtd == 0 && k < board.length && k != j + 2; k++) {
                            if(board[i][k] == 'B') {
                                qtd++;
                            }
                            else {
                                qtd = 0;
                                break;
                            }
                        }

                        if(qtd == 0) {
                            this.tiles.add(new BarcoTile(i,j,BoatSize.PEQUENO));
                        }
                        else if(qtd == 1) {
                            this.tiles.add(new BarcoTile(i,j,BoatSize.MÉDIO));
                        }
                        else {
                            this.tiles.add(new BarcoTile(i,j,BoatSize.GRANDE));
                        }
                        break;
                    case 'P':
                        this.tiles.add(new PortoTile(i,j));
                        break;
                    default:
                        this.tiles.add(new DesconhecidaTile(i,j));
                        break;
                }
            }
        }
    }

    public void printBoard() {
        int line = this.tiles.get(0).getX();

        for(Tile tile : this.tiles) {
            if(line != tile.getX()) {
                line = tile.getX();
                System.out.println("");
            }

            switch(tile.getTileTypeExt()) {
                case ÁGUA:
                    System.out.print('.');
                    break;
                case PORTO:
                    System.out.print('P');
                    break;
                case BARCO:
                    System.out.print('B');
                    break;
            }
        }

        System.out.println("");
    }

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     * @return get the tile in the specified x and y axis of the matrix
     */
    public Tile getTile(int x, int y) {
        for(Tile tile : this.getBoard()) {
            if(tile.getX() == x && tile.getY() == y) {
                return tile;
            }
        }

        return null;
    }

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     * @return index of the tile in the specified x and y axis of the matrix in the list
     */
    private int getTilePositionInList(int x, int y) {
        for(int i = 0; i < this.tiles.size(); i++) {
            if(this.tiles.get(i).getY() == y && this.tiles.get(i).getX() == x) {
                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param newTile the new tile to replace the existing one
     * @param x position of the tile to be replaced in the x axis of the matrix
     * @param y position of the tile to be replaced in the y axis of the matrix
     */
    public void changeTileType(Tile newTile, int x, int y) {
        int tileIdx = this.getTilePositionInList(x, y);

        this.tiles.set(tileIdx, newTile);
    }

    /**
     *
     * @return get the list of tiles that represent the board of the game
     */
    public List<Tile> getBoard()  {
        return this.tiles;
    }

    /**
     *
     * @param tiles list of tiles to replace the present board
     */
    public void setBoard(List<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     *
     * @param tileTypeName the name of type of tile you are searching for
     * @return the number of tiles within that type that you specified
     */
    public int getNumberOfTiles(String tileTypeName) {
        int count = 0;

        for(Tile tile : this.getBoard()) {
            if(tile.getTileType().equals(tileTypeName)) {
                count++;
            }
        }

        return count;
    }

    /**
     *
     * @return description of the board containing the number of tiles of the different types available
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("This board has: \n");

        int portoTiles = this.getNumberOfTiles("Porto");
        int desconhecidaTiles = this.getNumberOfTiles("Desconhecida");
        int aguaTiles = this.getNumberOfTiles("Água");
        int barcoTiles = this.getNumberOfTiles("Barco");

        sb.append(String.format("%d Tiles of type 'Porto'\n", portoTiles));
        sb.append(String.format("%d Tiles of type 'Desconhecida'\n", desconhecidaTiles));
        sb.append(String.format("%d Tiles of type 'Água'\n", aguaTiles));
        sb.append(String.format("%d Tiles of type 'Barco'\n", barcoTiles));

        return sb.toString();
    }
}
