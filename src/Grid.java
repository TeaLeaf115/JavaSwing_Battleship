import java.util.HashMap;

public class Grid {

    private int[][] grid;
    private HashMap<Integer,String> boardKey;

    public Grid() {
        grid = new int[10][10];
        boardKey = new HashMap<>();

        boardKey.put(0, "EMPTY");
        boardKey.put(1, "Miss");
        boardKey.put(2, "Hit");
        boardKey.put(3, "Destroyer");
        boardKey.put(4, "Submarine");
        boardKey.put(5, "Cruiser");
        boardKey.put(6, "Battleship");
        boardKey.put(7, "Aircraft Carrier");

    }
}
