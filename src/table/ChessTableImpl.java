package table;

import pieces.ChessPieceImpl;
import pieces.ChessPieceSymbols;
import pieces.Pawn;
import player.PlayerColor;

public class ChessTableImpl implements ChessTable{
    public static ChessPieceImpl[][] piecesOnTable = new ChessPieceImpl[8][8];

    public void setTableFirstTime(PlayerColor playerPlayerColor){
        piecesOnTable[1][0]= new Pawn(ChessTableWidth.A,1,PlayerColor.white, ChessPieceSymbols.P);
    }
    public void resetTable(){
        //Print table. In appear(ChessPieceImpl) the new  positions for the pieces are fixed. Simply print new.
    }

    @Override
    public void create(int[][] arrayOfPieces) {

    }
}

/* White side
*       -------------------------------------------------
* 8     |  r  |  h  |  b  |  q  |  k  |  b  |  h  |  r  |
*       -------------------------------------------------
* 7     |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |
*       -------------------------------------------------
* 6     |     |     |     |     |     |     |     |     |
*       -------------------------------------------------
* 5     |     |     |     |     |     |     |     |     |
*       -------------------------------------------------
* 4     |     |     |     |     |     |     |     |     |
*       -------------------------------------------------
* 3     |     |     |     |     |     |     |     |     |
*       -------------------------------------------------
* 2     |  P  |  P  |  P  |  P  |  P  |  P  |  P  |  P  |
*       -------------------------------------------------
* 1     |  R  |  H  |  B  |  Q  |  K  |  B  |  H  |  R  |
*       _________________________________________________
*          A     B     C     D     E     F     G     H
*
*
* Black side
 *      -------------------------------------------------
 * 8    |  R  |  H  |  B  |  Q  |  K  |  B  |  H  |  R  |
 *      -------------------------------------------------
 * 7    |  P  |  P  |  P  |  P  |  P  |  P  |  P  |  P  |
 *      _________________________________________________
 * 6    |     |     |     |     |     |     |     |     |
 *      -------------------------------------------------
 * 5    |     |     |     |     |     |     |     |     |
 *      -------------------------------------------------
 * 4    |     |     |     |     |     |     |     |     |
 *      -------------------------------------------------
 * 3    |     |     |     |     |     |     |     |     |
 *      -------------------------------------------------
 * 2    |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |
 *      -------------------------------------------------
 * 1    |  r  |  h  |  b  |  q  |  k  |  b  |  h  |  r  |
 *      -------------------------------------------------
*          A     B     C     D     E     F     G     H
*
* */