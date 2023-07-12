package pieces;

import gameboard.Board;
import gameboard.Spot;

/**
 * Pieces.Piece abstract class to represent common functionality of all chess pieces
 */
public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;
    Spot spot;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Spot getSpot() {
        return this.spot;
    }

    public void setSpot(Spot spot) { this.spot = spot; }

    public abstract String getImagePath();

    public abstract boolean canMove(Board board, Spot start, Spot end);

    // most pieces don't castle, King and rook can override
    public boolean isCastlingMove(Spot start, Spot end) {
        return false;
    }
}
