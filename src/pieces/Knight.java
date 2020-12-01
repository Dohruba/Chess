package pieces;

import player.PlayerColor;
import table.ChessTableWidth;

import java.util.ArrayList;

public class Knight extends ChessPieceImpl{
    public Knight(ChessTableWidth FirstCoordinatex, int FirstCoordinatey, PlayerColor color, ChessPieceSymbols symbol) {
        super(FirstCoordinatex, FirstCoordinatey, color, symbol);
    }

    public ArrayList<Integer> openCoordinates() {
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        int column = getActualCoordinates() % 8;
        int row = getActualCoordinates() / 8;

        //Movements
        toReturn.add(17);//up R
        toReturn.add(15);//up L
        toReturn.add(10);//right u
        toReturn.add(6);//left u
        toReturn.add(-6);// right d
        toReturn.add(-10);//left d
        toReturn.add(-15);//down R
        toReturn.add(-17);//down L

    return  toReturn;
    }

    // 8 cases are interesting, when the knight is on a limit or one space from it, on the four sides.

    /**
     * Depending on the column and row where the knight is, some indexes from the 8 Open Spaces will be returned to later delete from the
     * available options.
     * This method specifically, deals with the limitations on columns
     * The row limitations will be handled on a second method
     * @param column    position x
     * @param row       position y
     */
    ArrayList<Integer> iterativeCheck(int column, int row){
        ArrayList<Integer> removeThis = new ArrayList<>();
        for (int i = 0; i<8; i++)removeThis.add(null);
        switch (column){
            case 0:
                for (int i = 0; i < 7; i+=2){
                    removeThis.set(i,7-i);
                }

                rowCheck(row, removeThis);
                break;
            case 1:
                for (int i = 2; i < 5; i+=2){
                    removeThis.set(i,7-i);
                }
                rowCheck(row, removeThis);
                break;
            case 6:
                for (int i = 2; i < 5; i+=2){
                    removeThis.set(i,7-i);
                }
                removeThis.add(4);// right b
                removeThis.add(2);
                rowCheck(row, removeThis);
                break;
            case 7:
                removeThis.add(6);
                removeThis.add(4);
                removeThis.add(2);
                removeThis.add(0);
                rowCheck(row, removeThis);
                break;
            default:

                break;
        }
        return removeThis;
    }

    /**
     * This method deals with the movement limitations on the rows.
     * @param row
     * @param removeThis
     */
    void rowCheck(int row, ArrayList<Integer> removeThis){
        switch(row){
            case 0: //7654
                for (int i = 7; i > 3; i--) CheckAndAdd(removeThis, i);
                break;
            case 1: //76
                for (int i = 7; i > 5; i--) CheckAndAdd(removeThis, i);
                break;
            case 6: //10
                for (int i = 1; i >= 0; i--) CheckAndAdd(removeThis, i);
                break;
            case 7://3210
                for (int i = 3; i >= 0; i--) CheckAndAdd(removeThis, i);

                break;
            default:
                break;
        }
    }


    /**
     * Adds
     * @param removeThis
     * @param i
     */
    void CheckAndAdd(ArrayList<Integer> removeThis, int i){
        if (!removeThis.contains(i)) removeThis.add(i, 7-i);
    }


}
