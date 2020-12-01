package player;

import pieces.ChessPieceTypes;

public class PlayerImpl implements Player{
    private String name;
    private ServerOrClient rol;
    private int connectionPort;
    private PlayerColor PlayerColor;
    private boolean itsMyTurn;
    private static PlayerStatus status;

    public static PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }


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


    @Override
    public void connect(ServerOrClient type, int port, String name) {

    }

    @Override
    public void move() {

    }

    @Override
    public void castlin(String direction) {

    }

    @Override
    public void promote(ChessPieceTypes newType) {

    }

    @Override
    public player.PlayerColor chooseColor(String name, player.PlayerColor color) {
        return color;
    }
}
