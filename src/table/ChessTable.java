package table;

public interface ChessTable {
    /**
     * Creates a new table each turn
     *
     * @param arrayOfPieces Each piece has it´s own place in the array. When a piece moves,
     *                      it becomes a new place in the array and the table will be updated.
     */
    void create(int[] arrayOfPieces);
}
