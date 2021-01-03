package pieces;

import player.PlayerStatus;

import java.util.ArrayList;

public interface ChessPiece {



    /**
     * Saves, for the particular piece, it´s possible next coordinates.
     *
     * It works by giving the piece their respective set of moves, and then removing the moves from the list that
     * would not be possible, taking the position in account.
     * @return The list of available moves.
     */
    ArrayList<int[]> openCoordinates();

    /**
     * Makes the piece appear in the desired coordinates, when possible.
     * @param desiredX Where the piece should go in x.
     * @param desiredY Where it should go in y.
     */
    void appear( int desiredX, int desiredY);

    /**
     * Removes a piece.
     * If it is the king, the game is over. Status = checkmate
     * @param coordinates Where a piece is going to be removed.
     */
    void BeRemoved(int[] coordinates);

    /**
     * If one of the open coordinates for the next turn is the rival´s king,
     *
     * @return Check
     *
     */
    PlayerStatus doCheck();


}