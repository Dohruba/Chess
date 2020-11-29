package pieces;

import player.PlayerColor;
import player.PlayerStatus;
import table.ChessTableImpl;
import table.ChessTableWidth;

import java.util.ArrayList;

public class ChessPieceImpl implements ChessPiece{
    protected int actualCoordinates;
    protected PlayerColor color;
    protected ChessPieceSymbols symbol;

    public int getActualCoordinates() {
        return actualCoordinates;
    }

    public void setActualCoordinates(int actualCoordinates) {
        this.actualCoordinates = actualCoordinates;
    }


    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public ChessPieceSymbols getSymbol() {
        return symbol;
    }

    public void setSymbol(ChessPieceSymbols symbol) {
        this.symbol = symbol;
    }

//Constructor
    public ChessPieceImpl(ChessTableWidth FirstCoordinatex, int FirstCoordinatey, PlayerColor color, ChessPieceSymbols symbol){
        int x = FirstCoordinatex.getValue()     ;
        int y = FirstCoordinatey*10;
        setActualCoordinates(x+y);
        setColor(color);
        setSymbol(symbol);
    }


    @Override
    public ArrayList<Integer> openCoordinates() {
        //HOne override for each kind of piece
        return null;
    }

    @Override
    public void appear(int desiredDestination) {

        ChessPieceImpl destinationSquarePiece = ChessTableImpl.piecesOnTable[desiredDestination];
        int destination = destinationSquarePiece.getActualCoordinates();

        if (openCoordinates().contains(destination)) {
            if ( destinationSquarePiece == null) {
                setActualCoordinates(destination);

            } else if (destinationSquarePiece.getColor() != color) {
                ChessPieceSymbols pieceToRemove = destinationSquarePiece.getSymbol();
                dieDramatically(destination);
                setActualCoordinates(destination);
                if ( pieceToRemove == ChessPieceSymbols.k || pieceToRemove == ChessPieceSymbols.K){
                    //End game, Checkmate
                }

            } else if (destinationSquarePiece.getColor() == color) {
                //Error, deal with it
            }
        }
    }

    @Override
    public void dieDramatically(int coordinates) {
        ChessTableImpl.piecesOnTable[coordinates] = null;
    }


    @Override
    public PlayerStatus doCheck() {
        ArrayList<Integer> destinations = openCoordinates();

        for (int i = 0; i < destinations.size(); i++){
            if ((ChessTableImpl.piecesOnTable[destinations.get(i)].getColor() != color)&& (
                    ChessTableImpl.piecesOnTable[destinations.get(i)].getSymbol() == ChessPieceSymbols.k ||
                            ChessTableImpl.piecesOnTable[destinations.get(i)].getSymbol() == ChessPieceSymbols.K)){
                System.out.print("Check ");
                if (color == PlayerColor.white){
                    System.out.println("on black.");
                } else System.out.print("on white.");
                return PlayerStatus.Check;
            }
        }
        return PlayerStatus.Normal;
    }

}
