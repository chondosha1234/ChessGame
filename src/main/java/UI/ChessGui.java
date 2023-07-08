package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.Game;
import gameboard.Spot;
import pieces.Piece;
import players.HumanPlayer;
import players.Player;

public class ChessGui extends JFrame implements MouseListener, MouseMotionListener {
    private final Game game;
    private final JLayeredPane layeredPane;
    private final JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private final int squareSize;
    private int xStart;
    private int yStart;

    public ChessGui() {
        Dimension boardSize = new Dimension(600, 600);
        squareSize = 600 / 8;

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        // add chessboard to layout
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8,8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        // Add pieces to board
        Player p1 = new HumanPlayer(true);
        Player p2 = new HumanPlayer(false);
        game = new Game(p1, p2);
        setChessBoard();
    }

    private void setChessBoard() {
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.blue : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.blue);
            }

            // add piece icon to square based on game state
            int x = i % 8;
            int y = i / 8;
            Spot spot = game.getBoard().getBox(x, y);
            Piece piece = spot.getPiece();
            if (piece != null) {
                // Add jLabel with image for piece
                String imagePath = piece.getImagePath();
                ImageIcon icon = new ImageIcon(imagePath);
                JLabel pieceLabel = new JLabel(icon);
                square.add(pieceLabel);
            }
        }
    }

    private void updateChessBoard() {
        chessBoard.removeAll();
        setChessBoard();
        chessBoard.revalidate();
        chessBoard.repaint();
    }

    // select piece
    public void mousePressed(MouseEvent e) {
        this.xStart = e.getX() / squareSize;
        this.yStart = e.getY() / squareSize;

        /*
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        */
    }

    // Move piece around
    public void mouseDragged(MouseEvent e) {
        if (chessPiece == null) return;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX() / squareSize;
        int y = e.getY() / squareSize;

        // get selected piece and position
        Spot startSpot = game.getBoard().getBox(xStart, yStart);
        Piece selectedPiece = startSpot.getPiece();

        //get destination spot
        Spot endSpot = game.getBoard().getBox(x, y);

        boolean isValidMove = game.playerMove(game.getCurrentTurn(), xStart, yStart, x, y);
        if (isValidMove) {
            updateChessBoard();

            if (game.isEnd()) {
                // do something
            }
        }

        /*
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        Container parent;
        if (c instanceof JLabel) {
            parent = c.getParent();
            parent.remove(0);
        } else {
            parent = (Container) c;
        }
        parent.add(chessPiece);
        chessPiece.setVisible(true);

         */
    }

    public void mouseClicked(MouseEvent e){}

    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
