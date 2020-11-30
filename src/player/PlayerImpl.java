package player;

import pieces.ChessPieceTypes;

public class PlayerImpl implements Player{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerOrClient getRol() {
        return rol;
    }

    public void setRol(ServerOrClient rol) {
        this.rol = rol;
    }

    public int getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(int connectionPort) {
        this.connectionPort = connectionPort;
    }

    public player.PlayerColor getPlayerColor() {
        return PlayerColor;
    }

    public void setPlayerColor(player.PlayerColor playerColor) {
        PlayerColor = playerColor;
    }

    public boolean isItsMyTurn() {
        return itsMyTurn;
    }

    public void setItsMyTurn(boolean itsMyTurn) {
        this.itsMyTurn = itsMyTurn;
    }

    private String name;
    private ServerOrClient rol;
    private int connectionPort;
    private PlayerColor PlayerColor;
    private boolean itsMyTurn;


    @Override
    public void connect(ServerOrClient type, int port, String name) {

    }

    @Override
    public void move(String origin, String destination) {

    }

    @Override
    public PlayerStatus state() {
        return null;
    }

    @Override
    public void castlin(boolean moved) {

    }

    @Override
    public void promote(ChessPieceTypes type, ChessPieceTypes newType) {

    }

    @Override
    public player.PlayerColor chooseColor(String name, player.PlayerColor color) {
        return color;
    }
}
