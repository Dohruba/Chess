package player;

import pieces.ChessPieceTypes;
import tcp.ConnectionException;

import java.io.InputStream;
import java.io.OutputStream;

public class PlayerProtocolEngine implements Player {
    private final OutputStream os;
    private final InputStream is;
    private Player player;

    public PlayerProtocolEngine(InputStream is, OutputStream os, Player gameEngine) {
        this.is = is;
        this.os = os;
        this.player = gameEngine;
    }


    //Hier soll die Serializierung passieren
    @Override
    public void connect(ServerOrClient type, int port, String name) throws ConnectionException {
        
    }

    @Override
    public void move() throws StatusException, GameException {

    }

    @Override
    public void castlin(String direction) throws GameException, StatusException {

    }

    @Override
    public void promote(ChessPieceTypes newType) {

    }

    @Override
    public PlayerColor chooseColor(String name, PlayerColor color) throws GameException, StatusException {
        return null;
    }
}
