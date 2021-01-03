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
    private static final int ORIGIN_X = 0;
    private static final int ORIGIN_Y = 1;

    private static final String DESTINATION = "A4";
    private static final int DESTINATION_X = 0;
    private static final int DESTINATION_Y = 2;

    private static final Pawn PAWN = new Pawn(ChessTableWidth.A,2, PlayerColor.white, ChessPieceSymbols.P);

    private PlayerImpl getPlayer(){//Isolation, einfacher später Änderungen zu machen
        return new PlayerImpl();
    }



    //Create Players
    @Test
    public void goodCreatePlayer(){
        PlayerImpl.setStatus(PlayerStatus.CREATING);
        PlayerImpl alice = this.getPlayer();
        Assert.assertEquals(PlayerStatus.CREATING, PlayerImpl.getStatus());

        alice.setName(ALICE);
        alice.setRol(ServerOrClient.SERVER);
        alice.setConnectionPort(PORT);

        Assert.assertEquals(ALICE,alice.getName());
        Assert.assertEquals(ServerOrClient.SERVER, alice.getRol());
        Assert.assertEquals(PORT, alice.getConnectionPort());
        Assert.assertEquals(PlayerStatus.CONNECTING, PlayerImpl.getStatus());
    }

    @Test
    public void goodCreatePlayer2(){
        PlayerImpl.setStatus(PlayerStatus.CREATING);
        PlayerImpl alice = this.getPlayer();

        Assert.assertEquals(PlayerStatus.CREATING, PlayerImpl.getStatus());

        alice.setName(ALICE);
        alice.setRol(ServerOrClient.CLIENT);
        alice.setConnectionPort(PORT);

        Assert.assertEquals(ALICE,alice.getName());
        Assert.assertEquals(ServerOrClient.CLIENT, alice.getRol());
        Assert.assertEquals(PORT, alice.getConnectionPort());
        Assert.assertEquals(PlayerStatus.CONNECTING, PlayerImpl.getStatus());
    }

    @Test
    public void goodCreatePlayer3(){
        PlayerImpl.setStatus(PlayerStatus.CREATING);
        PlayerImpl alice = this.getPlayer();
        alice.setName(ALICE);
        alice.setRol(ServerOrClient.SERVER);
        alice.setConnectionPort(PORT);


        PlayerImpl bob = this.getPlayer();
        bob.setName(BOB);
        bob.setRol(ServerOrClient.CLIENT);
        bob.setConnectionPort(PORT);

        Assert.assertEquals(PlayerStatus.CHOOSING, PlayerImpl.getStatus());

    }

    @Test(expected = GameException.class)
    public void badCreateTest1(){
        PlayerImpl noName = this.getPlayer();
        noName.setName(null);
    }

    @Test(expected = GameException.class)
    public void badCreateTest2(){
        PlayerImpl noName = this.getPlayer();
        noName.setName("!!??");
    }

    @Test(expected = GameException.class)
    public void badCreateTest3(){
        PlayerImpl noName = this.getPlayer();
        noName.setName("    ");
    }




//Connect
    @Test
    public void goodCconnectTest1() throws ConnectionException, StatusException {
        PlayerImpl alice = this.getPlayer();

        alice.connect(ServerOrClient.SERVER, PORT, ALICE );

        Assert.assertEquals(PlayerStatus.CONNECTING,PlayerImpl.getStatus());
    }


    @Test
    public void goodConnectTest2() throws ConnectionException, StatusException{
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        alice.connect(ServerOrClient.SERVER, PORT, ALICE);
        //Open connection and wait
        bob.connect(ServerOrClient.CLIENT, PORT, BOB);
        //Connect to server
        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());
    }

    @Test
    public void goodConnectTest3SameName() throws ConnectionException, StatusException{
        PlayerImpl alice = this.getPlayer();
        PlayerImpl alice2 = this.getPlayer();

        alice.connect(ServerOrClient.SERVER, PORT, ALICE);
        //Open connection and wait
        alice2.connect(ServerOrClient.CLIENT, PORT, ALICE);
        //Connect to server
        //It is known that both have the same name, after connection.
        //So the names are modified. Server gets a S, Client gets a C
        Assert.assertEquals("Alice_S",alice.getName());
        Assert.assertEquals("Alice_C",alice2.getName());

        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());
    }

    @Test(expected = ConnectionException.class)
    public void badConnectTest1() throws StatusException, ConnectionException{
        PlayerImpl bob = this.getPlayer();

        bob.connect(ServerOrClient.CLIENT,PORT,BOB);
        //There is no server yet

    }

    @Test(expected = StatusException.class)
    public void badConnectTest2_3Players() throws StatusException, ConnectionException{
        PlayerImpl bob = this.getPlayer();
        PlayerImpl alice = this.getPlayer();
        PlayerImpl clara = this.getPlayer();

        bob.connect(ServerOrClient.SERVER, PORT, BOB);
        alice.connect(ServerOrClient.CLIENT, PORT, ALICE);
        Assert.assertEquals(ServerOrClient.SERVER,alice);
        Assert.assertEquals(ServerOrClient.CLIENT,bob);

        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());

        clara.connect(ServerOrClient.CLIENT, PORT, CLARA);

    }






//Choose colors
    @Test
    public void goodChooseColor1() throws GameException, StatusException {
        PlayerImpl alice = this.getPlayer();

        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerStatus.CHOOSING,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor2() throws GameException, StatusException {
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor3() throws GameException, StatusException {
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor4() throws GameException, StatusException {
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();


        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.black);
        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());

    }

    @Test
    public void goodChooseColor5Reconsider() throws GameException, StatusException{
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.black);
        bob.chooseColor(BOB, PlayerColor.white);
        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.black);

        Assert.assertEquals(PlayerColor.black, aliceColor);
        Assert.assertEquals(PlayerColor.white, bobColor);
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());
    }

    @Test
    public void goodChooseColor6Reconsider() throws GameException, StatusException{
        PlayerImpl alice = this.getPlayer();
        PlayerImpl bob = this.getPlayer();

        PlayerColor bobColor = bob.chooseColor(BOB,PlayerColor.black);
        bob.chooseColor(BOB, PlayerColor.white);
        PlayerColor aliceColor = alice.chooseColor(ALICE,PlayerColor.white);

        Assert.assertEquals(PlayerColor.black, aliceColor);
        Assert.assertEquals(PlayerColor.white, bobColor);
        Assert.assertEquals(PlayerStatus.START,PlayerImpl.getStatus());
    }

    @Test (expected = StatusException.class)
    public void badChooseColor1() {
        PlayerImpl Alice = this.getPlayer();
        PlayerImpl Bob = this.getPlayer();

        PlayerColor bobColor = Bob.chooseColor(BOB,PlayerColor.white);
        PlayerColor aliceColor = Alice.chooseColor(ALICE,PlayerColor.black);
        //Reconsideration after colors are set
        bobColor = Bob.chooseColor(BOB,PlayerColor.black);
    }

    @Test
    public void badChooseColor2(){
        PlayerImpl Alice = this.getPlayer();
        PlayerImpl Bob = this.getPlayer();

        PlayerColor bobColor = Bob.chooseColor(BOB,PlayerColor.white);
        PlayerColor aliceColor = Alice.chooseColor(ALICE,PlayerColor.black);
        //Reconsideration after colors are set
        aliceColor = Alice.chooseColor(ALICE,PlayerColor.white);
    }




    //Start Rounds


    //Move

    @Test
    public void goodMoveTest1() throws GameException, StatusException{
        //Pawn moves two places forward
        PlayerImpl player = this.getPlayer();

        ChessTableImpl.piecesOnTable[ORIGIN_X][ORIGIN_Y] = PAWN;
        //Wirte System input
        player.move();

        Assert.assertEquals(ChessTableImpl.piecesOnTable[DESTINATION_X][DESTINATION_Y + 1].getSymbol(),
                ChessPieceSymbols.P );
    }

    @Test
    public void goodMoveTest2() throws  GameException, StatusException{
        //Diagonal Right
        PlayerImpl player = this.getPlayer();
//Write inputs for where the Piece should go
        ChessTableImpl.piecesOnTable[ORIGIN_X][ORIGIN_Y] = PAWN;
        player.move();
        Assert.assertEquals(ChessTableImpl.piecesOnTable[DESTINATION_X + 1][DESTINATION_Y].getSymbol(),ChessPieceSymbols.P);

    }

    @Test
    public void badMoveTest(){
        Player player = this.getPlayer();


    }


}
