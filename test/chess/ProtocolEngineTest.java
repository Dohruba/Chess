package chess;

import pieces.ChessPieceTypes;
import player.*;
import tcp.ConnectionException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ProtocolEngineTest {
    public static final String ALICE = "Alice";

    private Player getPlayerProtocolEngine(InputStream is, OutputStream os, Player gameEngine){
        return new PlayerProtocolEngine(is, os, gameEngine);
    }


    public void test1() throws GameException, StatusException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Player alice = new PlayerImpl();
        Player bob = new PlayerImpl();

        Player pPESender = this.getPlayerProtocolEngine(null, baos, alice);
        PlayerColor aliceColor = pPESender.chooseColor(ALICE, PlayerColor.white);

        //Simulate network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        Player playerProtocolReciver = this.getPlayerProtocolEngine(bais, null, bob);
        PlayerReadTester playerReciver = new PlayerReadTester();
    }


    private class PlayerReadTester implements Player{

        private boolean lastCallPick = false;
        private boolean lastCallSet = false;

        private String userName = null;
        private PlayerColor playerColor;


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
            this.lastCallSet = false;
            this.lastCallPick = true;
            this.userName = name;
            this.playerColor = color;

            return color;
        }
    }
}
