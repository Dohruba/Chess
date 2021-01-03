package pieces;

import player.PlayerColor;
import table.ChessTableImpl;
import table.ChessTableWidth;

import java.util.ArrayList;

public class Pawn extends ChessPieceImpl{


    public Pawn(ChessTableWidth FirstCoordinatex, int FirstCoordinatey, PlayerColor color, ChessPieceSymbols symbol) {
        super(FirstCoordinatex, FirstCoordinatey, color, symbol);

    }
/*
If i understand correctly, the inheritance will allow me to create Pawns (or any other piece), which inherit the methods and fields from ChesspieceImpl.
All except the one that has to be overridden for each kind of piece, as they have different behaviors on the borad.
 */


    @Override
    public ArrayList<int[]> openCoordinates(){
        ArrayList<int[]> toReturn = new ArrayList<>();
        int[] front;
        int[] diagonalR;
        int[] diagonalL;
        int[] doubleStep;

        //Posible moves
        if (color == PlayerColor.white){
            front = new int[]{0,1};
            diagonalR = new int[]{1,1};
            diagonalL = new int[]{-1,1};
            doubleStep = new int[]{0,2};
        } else {
            front = new int[]{0,-1};
            diagonalR = new int[]{-1,-1};
            diagonalL = new int[]{1,-1};
            doubleStep = new int[]{0,-2};
        }


        //Where is it located?
        int column = getActualCoordinates()[0];
        int row = getActualCoordinates()[1];

        //Conditions
        if ((row < 7 && row > 0)){
            //front
            if (ChessTableImpl.piecesOnTable[column][row + front[1]] == null) {
                addOption(front, getActualCoordinates(), toReturn);
            //Doublestep
                if((row == 1) && ChessTableImpl.piecesOnTable[getActualCoordinates()[0]][3] == null)
                    addOption(doubleStep, getActualCoordinates(), toReturn);
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


    boolean checkDiagonal(int[] direction, int column){
            return !(direction[0] == -1 && getActualCoordinates()[0] == 0) || (direction[0] == 1 && getActualCoordinates()[0] == 7) ;
    }

    void addOption(int[] direction, int[] actualPosition, ArrayList<int[]> toReturn){
        toReturn.add(new int[]{actualPosition[0] + direction[0], actualPosition[1] + direction[1]});
    }
}
