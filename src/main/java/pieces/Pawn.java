package pieces;

import gameboard.Board;
import gameboard.Spot;

public class Pawn extends Piece {

    private boolean firstMove;
    private final String imagePath;

    public Pawn(boolean white) {
        super(white);
        this.firstMove = true;
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/pawn_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/pawn_black.png";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        int x = startX - endX;
        int y = Math.abs(startY - endY);

        // if difference of 1 in x and y means diagonal attack
        // check if that spot has enemy piece
        if (this.isWhite() && x == -1 && y == 1) {
            if (end.getPiece() != null) {
                this.firstMove = false;
                this.spot = end;
                return true;
            }
        }

        if (!this.isWhite() && x == 1 && y == 1) {
            if (end.getPiece() != null) {
                this.firstMove = false;
                this.spot = end;
                return true;
            }
        }

        //difference of 1 in x and 0 in y means a forward move of 1
        // check if pawn has moved once and can possibly move 2 -- for Black pieces
        if (y == 0 && !this.isWhite() && x == 2 && firstMove) {
            if (board.getBox(startX - 1, endY).getPiece() != null) {
                return false;
            }
            this.firstMove = false;
            this.spot = end;
            return true;
        }

        // check if pawn has moved once and can possibly move 2 -- for White pieces
        if (y == 0 && this.isWhite() && x == -2 && firstMove) {
            if (board.getBox(startX + 1, endY).getPiece() != null) {
                return false;
            }
            this.firstMove = false;
            this.spot = end;
            return true;
        }

        // one space move -- for black pieces
        if (y == 0 && !this.isWhite() && x == 1) {
            if (end.getPiece() != null) {
                return false;
            }
            this.firstMove = false;
            this.spot = end;
            return true;
        }

        // one space move -- for white pieces
        if (y == 0 && this.isWhite() && x == -1) {
            if (end.getPiece() != null) {
                return false;
            }
            this.firstMove = false;
            this.spot = end;
            return true;
        }

        // doesn't meet valid move criteria
        return false;
    }
}
