package player;

public enum PlayerStatus {
    /**
     * In which state the player finds himself.
     * Normal, nothing special is going on.
     * Check, the king of the player can be attacked.
     *  The Player can lose.
     * CheckMate, the king of the player has been taken. Game ends
     */
    Normal, Check, CheckMate
}
