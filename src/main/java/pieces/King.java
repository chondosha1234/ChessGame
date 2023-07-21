package pieces;

import gameboard.Board;
import gameboard.Spot;

public class King extends Piece {

    private boolean castlingDone = false;
    private boolean firstMove;
    private final String imagePath;

    public King(boolean white) {
        super(white);
        this.firstMove = true;
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
        this.firstMove = false;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // can't move to spot with piece of same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        int actualX = end.getX() - start.getX();

        // castling situation
        System.out.println("king move coordinates: " + actualX + " " + y);
        if (actualX == 1 && y == 0 && this.firstMove) {
            // check to make sure knight isn't there
            System.out.println("inside castling move code");
            Spot knightSpot = board.getBox(end.getX() + 1, end.getY());
            if (knightSpot.getPiece() != null) {
                return false;
            }
            Spot rookSpot = board.getBox(end.getX() + 2, end.getY());
            this.spot = rookSpot;
            return true;
        }

        System.out.println("passed castling move code");

        if (x + y == 1) {
            //check to make sure it will not result in king getting attacked
            this.spot = end;
            this.firstMove = false;
            return true;
        }

        if (x == 1 && y == 1) {
            this.spot = end;
            this.firstMove = false;
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
        int y = Math.abs(start.getY() - end.getY());
        int actualX = end.getX() - start.getX();
        if (actualX == 1 && y == 0 && this.firstMove) {
            return true;
        }
        return false;
    }
}
