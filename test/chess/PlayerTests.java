package chess;

import org.junit.Assert;
import org.junit.Test;
import player.*;

public class PlayerTests {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String CLARA = "Clara";

    private static final int PORT = 5555;

    private PlayerImpl getPlayer(){//Isolation, einfacher später Änderungen zu machen
        return new PlayerImpl();
    }

    @Test
    public void goodChooseColor1() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor p1Color = player.chooseColor(ALICE,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, p1Color);

    }

    @Test
    public void goodChooseColor2() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);


    }

    @Test
    public void goodChooseColor3() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.white);
        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.white);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
    }

    @Test
    public void goodChooseColor4() throws GameException, StatusException {
        Player player = this.getPlayer();

        PlayerColor bobColor = player.chooseColor(BOB,PlayerColor.black);
        PlayerColor aliceColor = player.chooseColor(ALICE,PlayerColor.black);

        Assert.assertEquals(PlayerColor.white, aliceColor);
        Assert.assertEquals(PlayerColor.black, bobColor);
        //First come first served
    }

    @Test
    public void connectTest1(){
        PlayerImpl player = this.getPlayer();

        player.connect(ServerOrClient.SERVER, PORT, ALICE );
        ServerOrClient aliceType = player.getRol();

        Assert.assertEquals(ServerOrClient.SERVER,aliceType);
    }
}
