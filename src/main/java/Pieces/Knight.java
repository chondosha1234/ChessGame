package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
