package pieces;

import player.PlayerColor;
import player.PlayerImpl;
import player.PlayerStatus;
import table.ChessTableImpl;
import table.ChessTableWidth;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class ChessPieceImpl implements ChessPiece{
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
        int x = FirstCoordinatex.getValue();
        int y = FirstCoordinatey*10;
        setActualCoordinates(x+y);
        setColor(color);
        setSymbol(symbol);
    }


    @Override
    public abstract ArrayList<Integer> openCoordinates();

    @Override
    public void appear(int desiredDestination) {

        ChessPieceImpl destinationSquarePiece = ChessTableImpl.piecesOnTable[desiredDestination];
        int destination = destinationSquarePiece.getActualCoordinates();

        if (openCoordinates().contains(destination)) {
            if ( destinationSquarePiece == null) {
                setActualCoordinates(destination);
                destinationSquarePiece = this;
            } else if (destinationSquarePiece.getColor() != color) {
                ChessPieceSymbols pieceToRemove = destinationSquarePiece.getSymbol();
                BeRemoved(destination);
                setActualCoordinates(destination);
                destinationSquarePiece = this;

                if ( pieceToRemove == ChessPieceSymbols.k || pieceToRemove == ChessPieceSymbols.K){
                    //End game, Checkmate
                }

            } else if (destinationSquarePiece.getColor() == color) {
                //Error, deal with it
            }
        }else
            System.out.print("Move not possible.");
    }

    @Override
    public void BeRemoved(int coordinates) {
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
            }
        }
        return PlayerImpl.getStatus() == PlayerStatus.ACTIVE_P1 ? PlayerStatus.ACTIVE_P2: PlayerStatus.ACTIVE_P1;
    }


    void something(){

        Pattern pattern = Pattern.compile("[A-H1-8]");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNext(pattern)){

        }
    }
}
