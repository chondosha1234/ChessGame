package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
