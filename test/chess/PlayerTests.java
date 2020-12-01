package chess;

import org.junit.Assert;
import org.junit.Test;
import pieces.ChessPieceSymbols;
import pieces.Pawn;
import player.*;
import table.ChessTableImpl;
import table.ChessTableWidth;
import tcp.ConnectionException;

public class PlayerTests {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String CLARA = "Clara";

    private static final int PORT = 5555;


    private static final String ORIGIN = "A2";
    private static final int ORIGIN_INT = 8;
    private static final String DESTINATION = "A4";
    private static final int DESTINATION_INT = 32;

    private static final Pawn PAWN = new Pawn(ChessTableWidth.A,2, PlayerColor.white, ChessPieceSymbols.P);

    private PlayerImpl getPlayer(){//Isolation, einfacher später Änderungen zu machen
        return new PlayerImpl();
    }

//Connect
    @Test
    public void goodCconnectTest1() throws ConnectionException, StatusException {
        PlayerImpl alice = this.getPlayer();

        alice.connect(ServerOrClient.SERVER, PORT, ALICE );
        ServerOrClient aliceType = alice.getRol();

        Assert.assertEquals(ServerOrClient.SERVER,aliceType);
        Assert.assertEquals(PlayerStatus.CONNECTING,PlayerImpl.getStatus());
    }


    @Test
    public void goodConnectTest2() throws ConnectionException, StatusException{
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        alice.connect(ServerOrClient.SERVER, PORT, ALICE);
        //Open connection and wait
        ServerOrClient aliceType = alice.getRol();

        bob.connect(ServerOrClient.CLIENT, PORT, BOB);
        //Connect to server
        ServerOrClient bobType = bob.getRol();

        Assert.assertEquals(ServerOrClient.SERVER,aliceType);
        Assert.assertEquals(ServerOrClient.CLIENT,bobType);
        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());
    }

    @Test(expected = ConnectionException.class)
    public void badConnectTest1() throws StatusException, ConnectionException{
        PlayerImpl bob = this.getPlayer();

        bob.connect(ServerOrClient.CLIENT,PORT,BOB);
        //There is no server yet

    }

    @Test(expected = StatusException.class)
    public void badConnect3Players() throws StatusException, ConnectionException{
        PlayerImpl bob = this.getPlayer();
        PlayerImpl alice = this.getPlayer();
        PlayerImpl clara = this.getPlayer();

        bob.connect(ServerOrClient.SERVER, PORT, BOB);
        alice.connect(ServerOrClient.CLIENT, PORT, ALICE);
        clara.connect(ServerOrClient.CLIENT, PORT, CLARA);

    }



//Choose colors
    @Test
    public void goodChooseColor1() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor p1Color = player.chooseColor(ALICE,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, p1Color);
        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor2() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor3() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor4() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.black);
        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor5() throws GameException, StatusException{
        Player alice = this.getPlayer();
        Player bob = this.getPlayer();

        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.black);
        bob.chooseColor(BOB, PlayerColor.white);
        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.black);

        Assert.assertEquals(PlayerColor.black, aliceColor);
        Assert.assertEquals(PlayerColor.white, bobColor);
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());
    }

    @Test
    public void badChooseColor1() {
        Player Alice = this.getPlayer();
        Player Bob = this.getPlayer();

        try {
            PlayerColor bobColor = Bob.chooseColor(BOB,PlayerColor.white);
            PlayerColor aliceColor = Alice.chooseColor(ALICE,PlayerColor.black);
            //Reconsideration after colors are set
            bobColor = Bob.chooseColor(BOB,PlayerColor.black);
        } catch (GameException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            System.out.print("Game has already started.");
            e.printStackTrace();
        }
    }


    //Start Rounds

    @Test
    public void goodMoveTest1() throws GameException, StatusException{
        //Pawn moves two places forward
        PlayerImpl player = this.getPlayer();

        ChessTableImpl.piecesOnTable[ORIGIN_INT] = PAWN;
        player.move();

        Assert.assertEquals(ChessTableImpl.piecesOnTable[DESTINATION_INT].getSymbol(), ChessPieceSymbols.P );
    }

    @Test
    public void goodMoveTest2() throws  GameException, StatusException{
        //Diagonal Right
        PlayerImpl player = this.getPlayer();
//Write inputs for where the Piece should go
        ChessTableImpl.piecesOnTable[ORIGIN_INT] = PAWN;
        player.move();
        Assert.assertEquals(ChessTableImpl.piecesOnTable[DESTINATION_INT+1].getSymbol(),ChessPieceSymbols.P);

    }

    @Test
    public void badMoveTest(){
        Player player = this.getPlayer();


    }


}
