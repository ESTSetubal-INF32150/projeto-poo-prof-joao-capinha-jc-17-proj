package proj.javafx.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private int height;
    private int width;

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
                        this.tiles.add(new AguaTile(j, i));
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
                            this.tiles.add(new BarcoTile(j,i,BoatSize.PEQUENO));
                        }
                        else if(qtd == 1) {
                            this.tiles.add(new BarcoTile(j,i,BoatSize.MÉDIO));
                        }
                        else {
                            this.tiles.add(new BarcoTile(j,i,BoatSize.GRANDE));
                        }
                        break;
                    case 'P':
                        this.tiles.add(new PortoTile(j,i));
                        break;
                }
            }
        }

        this.width = board.length;
        this.height = board[0].length;
    }

    /**
     * Set up the board in order to create a level
     * @param dificultyLevel Difficulty level chosen to create a level
     */
    public void startLevelCreator(DificultyLevel dificultyLevel) {
        int size = 0;

        switch (dificultyLevel) {
            case FÁCIL:
                size = 5;
                break;
            case INTERMÉDIO:
                size = 7;
                break;
            case DIFÍCIL:
                size = 10;
                break;
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.tiles.add(new AguaTile(j, i));
            }
        }

        this.width = size;
        this.height = size;
    }

    /**
     * Print the board for a normal game
     */
    public void printBoard() {
        int line = 0;
        int numX = 0;
        boolean first = false;

        System.out.print("  ");

        printBoatsColumns();

        System.out.println("");

        for(Tile tile : this.tiles) {
            line = tile.getX();
            int y = tile.getY();

            if(!first) {
                printBoatsLine(y);
                first = true;
            }

            switch(tile.getCurrentType()) {
                case ÁGUA:
                    System.out.print(".|");
                    break;
                case PORTO:
                    System.out.print("P|");
                    break;
                case BARCO:
                    System.out.print("B|");
                    break;
                case DESCONHECIDA:
                    System.out.print(" |");
            }

            if(line == this.width - 1) {
                System.out.print(numX);
                System.out.println("");
                numX++;

                first = false;
            }
        }

        System.out.print("  ");

        for(int i = 0; i < this.width; i++) {
            System.out.print(i + " ");
        }

        System.out.println("");
    }

    /**
     * Print the board for the level creator
     */
    public void printLevelCreator() {
        int line = 0;

        for(Tile tile : this.tiles) {
            line = tile.getX();

            switch(tile.getTileTypeExt()) {
                case ÁGUA:
                    System.out.print(".|");
                    break;
                case PORTO:
                    System.out.print("P|");
                    break;
                case BARCO:
                    System.out.print("B|");
                    break;
            }

            if(line == this.width - 1) {
                System.out.println("");
            }
        }
    }

    /**
     * Print the number of boats in the columns
     */
    private void printBoatsColumns() {
        int numBoats = 0;

        for(int i = 0; i < this.width; i++) {
            int x = i;
            for(int j = 0; j < this.height; j++) {
                int y = j;

                if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                    numBoats++;
                }
            }

            System.out.print(numBoats + " ");

            numBoats = 0;
        }
    }
    
        /**
     * Print the number of boats in the columns
     */
    public ArrayList<Integer> getBoatsColumns() {
        int numBoats = 0;
        ArrayList<Integer> boats = new ArrayList<>();
        
        for(int i = 0; i < this.width; i++) {
            int x = i;
            for(int j = 0; j < this.height; j++) {
                int y = j;

                if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                    numBoats++;
                }
            }

            boats.add(numBoats);
            
            numBoats = 0;
        }
        
        return boats;
    }

    /**
     *
     * @param y The current line where the printer is positioned
     */
    private void printBoatsLine(int y) {
        int numBoats = 0;

        for(int j = 0; j < this.width; j++) {
            int x = j;

            if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                numBoats++;
            }
        }

        System.out.print(numBoats + "|");
    }
    
        /**
     *
     * @param y The current line where the printer is positioned
     */
    public int getBoatsLine(int y) {
        int numBoats = 0;

        for(int j = 0; j < this.width; j++) {
            int x = j;

            if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                numBoats++;
            }
        }

        return numBoats;
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
     * Change to the next type of tile of a specific tile
     * @param x The horizontal position of the tile
     * @param y The vertical position of the tile
     */
    public void toggleTile(int x, int y) {
        if(this.tiles.stream().anyMatch(k -> k.getX() == x && k.getY() == y)) {
            Tile tile = this.tiles.stream().filter(k -> k.getX() == x && k.getY() == y).findFirst().get();
            tile.toogleType();
        }
    }

    /**
     * Check if all ports have a boat docked in a normal game
     * @return Return true if all ports have a boat docked or Return false if not
     */
    public boolean validateBoard() {
        List<Tile> portoTiles = this.tiles.stream().filter(x -> x.getTileTypeExt() == TileType.PORTO).collect(Collectors.toList());
        boolean result = false;

        for(Tile tile : portoTiles) {
            int x = tile.getX();
            int y = tile.getY();

            int numOfBoats = 0;

            if(y == this.height - 1) {
                for(int i = y; i >= 0; i--) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.DESCONHECIDA) {
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else if(y == 0) {
                for(int i = y; i < this.height; i++) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else {
                for(int i = y; i >= 0; i--) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }

                numOfBoats = 0;

                for(int i = y; i < this.height; i++) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }

            numOfBoats = 0;

            if(x == this.width - 1) {
                for(int j = x; j >= 0; j--) {
                    int temp = j;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else if(x == 0) {
                for(int l = x; l < this.width; l++) {
                    int temp = l;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else {
                for(int j = x; j >= 0; j--) {
                    int temp = j;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }

                numOfBoats = 0;

                for(int l = x; l < this.width; l++) {
                    int temp = l;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getCurrentType() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }

            if(result == false) {
                return false;
            }

            result = false;
        }

        return true;
    }

    /**
     * Check if all ports have a boat docked in the level creator
     * @return Return true if all ports have a boat docked or Return false if not
     */
    public boolean validateBoardLevelCreator() {
        List<Tile> portoTiles = this.tiles.stream().filter(x -> x.getTileTypeExt() == TileType.PORTO).collect(Collectors.toList());
        boolean result = false;

        for(Tile tile : portoTiles) {
            int x = tile.getX();
            int y = tile.getY();

            int numOfBoats = 0;

            if(y == this.height - 1) {
                for(int i = y; i >= 0; i--) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA) {
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else if(y == 0) {
                for(int i = y; i < this.height; i++) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else {
                for(int i = y; i >= 0; i--) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1 || numOfBoats == 0) {
                    result = false;
                }
                else {
                    result = true;
                }

                numOfBoats = 0;

                for(int i = y; i < this.height; i++) {
                    int temp = i;

                    if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == x && k.getY() == temp).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }

            numOfBoats = 0;

            if(x == this.width - 1) {
                for(int j = x; j >= 0; j--) {
                    int temp = j;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else if(x == 0) {
                for(int l = x; l < this.width; l++) {
                    int temp = l;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }
            else {
                for(int j = x; j >= 0; j--) {
                    int temp = j;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }

                numOfBoats = 0;

                for(int l = x; l < this.width; l++) {
                    int temp = l;

                    if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.BARCO) {
                        numOfBoats++;
                    }
                    else if(this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.ÁGUA ||
                            this.tiles.stream().filter(k -> k.getX() == temp && k.getY() == y).findFirst().get().getTileTypeExt() == TileType.DESCONHECIDA){
                        break;
                    }
                }

                if(numOfBoats > 1) {
                    result = false;
                }
                else if(numOfBoats == 0 && result == true) {
                    result = true;
                }
                else if(numOfBoats == 0 && result == false) {
                    result = false;
                }
                else {
                    result = true;
                }
            }

            if(result == false) {
                return false;
            }

            result = false;
        }

        return true;
    }

    /**
     * Check if the player marked all tiles as their correct type
     * @param points player points in order do add or remove points depending of the number of failed tiles
     * @return
     */
    public int checkCorrectPlacement(int points) {
        int cnt = 0;

        for(Tile tile : this.tiles) {
            if(!tile.isCorrectType()) {
                cnt++;

                if(tile.getFailed() == false) {
                    if (tile.getTileTypeExt() == TileType.ÁGUA) {
                        points -= 2;
                    } else if (tile.getTileTypeExt() == TileType.BARCO) {
                        points -= 5;
                    }

                    tile.setFailed(true);
                }
            }
        }

        return cnt;
    }

    /**
     *
     * @return description of the board containing the number of tiles of the different types available
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int line = 0;

        for(Tile tile : this.tiles) {
            line = tile.getX();

            switch(tile.getTileTypeExt()) {
                case ÁGUA:
                    sb.append(".");
                    break;
                case PORTO:
                    sb.append("P");
                    break;
                case BARCO:
                    sb.append("B");
                    break;
            }

            if(line == this.width - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
