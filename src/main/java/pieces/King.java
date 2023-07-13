package pieces;

import gameboard.Board;
import gameboard.Spot;

public class King extends Piece {

    private boolean castlingDone = false;
    private final String imagePath;

    public King(boolean white) {
        super(white);
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/king_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/king_black.png";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public boolean isCastlingDone() {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // can't move to spot with piece of same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x + y == 1) {
            //check to make sure it will not result in king getting attacked
            this.spot = end;
            return true;
        }

        if (x == 1 && y == 1) {
            this.spot = end;
            return true;
        }

        return false;
        //return this.isValidCastling(board, start, end);
    }

    private boolean isValidCastling(Board board, Spot start, Spot end) {
        if (this.isCastlingDone()) {
            return false;
        }
        // add logic for returning true of false
        return true;
    }

    @Override
    public boolean isCastlingMove(Spot start, Spot end) {
        // check if starting and ending position are correct
        return true;
    }
}
