package pieces;

import player.PlayerColor;
import table.ChessTableImpl;
import table.ChessTableWidth;

import java.util.ArrayList;

public class Rook extends ChessPieceImpl{
    public Rook(ChessTableWidth FirstCoordinatex, int FirstCoordinatey, PlayerColor color, ChessPieceSymbols symbol) {
        super(FirstCoordinatex, FirstCoordinatey, color, symbol);
    }

    @Override
    public ArrayList<Integer> openCoordinates() {
        //One override for each kind of piece
        ArrayList<Integer> toReturn = new ArrayList<>();
        int[] limits = new int[4];
        int[] directions = new int[4];
        //Where is it located?
        int column = getActualCoordinates() % 8;
        int row = getActualCoordinates() / 8;

        //Movements
        directions[0] = -1;         //int moveLeft = -1;
        directions[1] = 1;          // int moveRight = 1;
        directions[2] = 8;          // int moveUp = 8;
        directions[3] = -8;         //int moveDown = -8;
        //limits
         limits[0] = column;        //maxToLeft = column;
         limits[1] = 8-column-1;    //maxToRight = 8 - column - 1;
         limits[2] = 8-row-1;       //maxUp =  8 - row - 1;
         limits[3] = row;           //maxDown = row;

        iterativeCheck(limits, toReturn, directions);

        return toReturn;
    }

    void iterativeCheck(int[] limits, ArrayList<Integer> theOpenSpaces, int[] direction){
        for (int j = 0; j < 4; j++){
            for (int i = 1; i <= limits[j]; i++ ){
                if (ChessTableImpl.piecesOnTable[getActualCoordinates() + direction[j]*i] == null) theOpenSpaces.add(getActualCoordinates() + direction[j]*i);
                else if(ChessTableImpl.piecesOnTable[getActualCoordinates() + direction[j]*i].getColor() != color) {
                    theOpenSpaces.add((getActualCoordinates() + direction[j]*i));
                    break;
                } else break;
            }
        }
    }



}