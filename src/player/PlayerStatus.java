package player;

public enum PlayerStatus {
    /**
     * In which state the player finds himself.
     * START, game began
     * ACTIVE, who´s turn is it
     * Normal, nothing special is going on.
     * Check, the king of the player can be attacked.
     *  The Player can lose.
     * CheckMate, the king of the player has been taken. Game ends
     */
    CONNECTED,START,ACTIVE_P1,ACTIVE_P2, CHECK, END
}
