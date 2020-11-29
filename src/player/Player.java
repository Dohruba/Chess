package player;

import pieces.ChessPieceTypes;

public interface Player {
    //Connection Stuff
    /**
     * the player can start a connection stating if he/she is going to be a server or client
     * @param type
     * @param port
     */
    void connect(ServerOrClient type, int port);



    //Game Stuff
    /**
     * The player changes the coordinates of the piece in the first given coordinates into the second one
     * as long as the second coordinates are one of the available in the pieces list
     * @param origin where is is
     * @param destination where it should end
     */
    void move(String origin, String destination);


    /**
     * If the player is in a normal state, checked or has lost
     * It has to be checked if one of the future possible moves of the rival,
     * lands on the coordinates of the king of the player
     */
    PlayerStatus state();


    /**
     * for Castlin: conditions are going to be interesting to code...
     * @param moved
     */
    void castlin(boolean moved);

    /**
     *When a pawn gets to the other side y=7 , the player gets the option to promote the piece to another
     * @param type
     */
    void promote(ChessPieceTypes type, ChessPieceTypes newType);

}