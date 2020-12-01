package player;

import pieces.ChessPieceTypes;
import tcp.ConnectionException;

public interface Player {
    //Connection Stuff
    /**
     * the player can start a connection stating if he/she is going to be a server or client
     * @param type Player wants to be server or client
     * @param port Port to be used
     * @param name User name
     */
    void connect(ServerOrClient type, int port, String name) throws ConnectionException;




    //Game Stuff
    /**
     * Ask the player from where to where should the piece move
     * The player changes the coordinates of the piece in the first given coordinates into the second one
     * as long as the second coordinates are one of the available in the pieces list
     */
    void move() throws StatusException, GameException; //Game exception
//A7 A8


    /**
     * for Castlin: conditions are going to be interesting to code...
     * @param direction
     */
    void castlin(String direction) throws GameException, StatusException; //Richtung parameter addieren

    /**
     *When a pawn gets to the other side y=7 , the player gets the option to promote the piece to another
     * @param newType
     */
    void promote(ChessPieceTypes newType); //Status exception fällt

    /**
     * Lets the player choose a color, if it is not yet taken
     * @param color What color is desired
     * @param name  user name
     * @throws GameException When both colors are taken. Third attempt in a two player game
     * @throws StatusException
     * @return selected color
     */
    PlayerColor chooseColor(String name, PlayerColor color) throws GameException, StatusException;
}
