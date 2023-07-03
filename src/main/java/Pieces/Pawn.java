package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        // if difference of 1 in x and y means diagonal attack
        // check if that spot has enemy piece

        //difference of 1 in x and 0 in y means a forward move of 1

        // check if pawn has moved once and can possibly move 2

        // can't move if enemy piece blocks

        return true;
    }
}
