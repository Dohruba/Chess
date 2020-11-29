package pieces;

import player.PlayerColor;
import table.ChessTableImpl;
import table.ChessTableWidth;

import java.util.ArrayList;

public class Pawn extends ChessPieceImpl{
    public Pawn(ChessTableWidth FirstCoordinatex, int FirstCoordinatey, PlayerColor color, ChessPieceSymbols symbol) {
        super(FirstCoordinatex, FirstCoordinatey, color, symbol);

    }

    @Override
    public ArrayList<Integer> openCoordinates(){
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        int front;
        int diagonalR;
        int diagonalL;
        int doubleStep;

        //Posible moves
        if (color == PlayerColor.white){
            front = getActualCoordinates() + 8;
            diagonalR = getActualCoordinates() + 9;
            diagonalL = getActualCoordinates() + 7;
            doubleStep = getActualCoordinates() + 16;
        } else {
            front = getActualCoordinates() - 8;
            diagonalR = getActualCoordinates() - 9;
            diagonalL = getActualCoordinates() - 7;
            doubleStep = getActualCoordinates() - 16;
        }
        //Where is it located?
        int column = getActualCoordinates() % 8;
        int row = getActualCoordinates() / 8;

        //Conditions
        if ((row < 7 && row > 0)){
            if (ChessTableImpl.piecesOnTable[front] == null) {
                toReturn.add(front);
                if((row == 1) && ChessTableImpl.piecesOnTable[doubleStep] == null) toReturn.add(doubleStep);
            }
            if (checkDiagonal(diagonalL, column)){
                toReturn.add(diagonalL);
            }
            if (checkDiagonal(diagonalR, column)){
                toReturn.add(diagonalR);
            }

        }

        return toReturn;
    }


    boolean checkDiagonal(int direction, int column){
        return ChessTableImpl.piecesOnTable[direction] != null && ChessTableImpl.piecesOnTable[direction].getColor() != color && column != 0;
    }
}
