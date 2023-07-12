package game;

import gameboard.Board;
import gameboard.Move;
import gameboard.Spot;
import pieces.King;
import pieces.Piece;
import players.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player[] players;
    private final Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;
    private boolean whiteKingChecked;
    private boolean blackKingChecked;

    public Game(Player p1, Player p2) {
        this.players = new Player[2];
        this.board = new Board();
        this.movesPlayed = new ArrayList<>();
        this.whiteKingChecked = false;
        this.blackKingChecked = false;
        this.initialize(p1, p2);
    }

    private void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {

        Piece sourcePiece = move.getStart().getPiece();

        // check for selecting piece
        if (sourcePiece == null) {
            return false;
        }
        // check valid player
        if (player != currentTurn) {
            return false;
        }
        // check if player is selecting their own piece
        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        // check valid move
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        // check if piece is killed
        Piece destPiece = move.getEnd().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

        // check if castling move
        if (sourcePiece instanceof King && sourcePiece.isCastlingMove(move.getStart(), move.getEnd())) {
            move.setCastlingMove(true);
        }

        //store the move
        movesPlayed.add(move);

        //move piece from start box to end box
        move.getEnd().setPiece(sourcePiece);
        move.getStart().setPiece(null);
        // evaluate check condition for current player, if true revert move and return false
        if (evaluateCurrentPlayerCheck()) {
            // switch piece back
            move.getStart().setPiece(sourcePiece);
            move.getEnd().setPiece(null);
            return false;
        }

        // see if current player put enemy in check after move has been completed
        evaluateEnemyPlayerCheck();

        // game over condition
        if (destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        // change player turn
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }

        return true;
    }

    public boolean evaluateCurrentPlayerCheck() {
        if (this.currentTurn.isWhiteSide()) {
            // white is moving and need to check if this will put own king into check
            for (Piece blackPiece : board.getBlackPieces()) {
                if (!blackPiece.isKilled() && blackPiece.canMove(board, blackPiece.getSpot(), board.getWhiteKing().getSpot())) {
                    System.out.println("black piece and spot: " + blackPiece + " at " + blackPiece.getSpot());
                    System.out.println("white king spot: " + board.getWhiteKing().getSpot());
                    System.out.println("inside Check black piece can hit king");
                    return true;
                }
            }
        } else {
            // black is moving
            for (Piece whitePiece : board.getWhitePieces()) {
                if (!whitePiece.isKilled() && whitePiece.canMove(board, whitePiece.getSpot(), board.getBlackKing().getSpot())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean evaluateRemoveCheck() {
        // if current player is in check their move must prevent that, or cant move
        return false;
    }

    public void evaluateEnemyPlayerCheck() {
        if (this.currentTurn.isWhiteSide()) {
            // white is moving,  check if you put enemy into check
            for (Piece whitePiece : board.getWhitePieces()) {
                if (!whitePiece.isKilled() && whitePiece.canMove(board, whitePiece.getSpot(), board.getBlackKing().getSpot())) {
                    this.blackKingChecked = true;
                }
            }
        } else {
            // black is moving, check if you put enemy into check
            for (Piece blackPiece : board.getBlackPieces()) {
                if (!blackPiece.isKilled() && blackPiece.canMove(board, blackPiece.getSpot(), board.getWhiteKing().getSpot())) {
                    this.whiteKingChecked = true;
                }
            }
        }
    }
}
