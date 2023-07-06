package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Game.Game;

public class ChessGui extends JFrame implements MouseListener, MouseMotionListener {
    private Game game;
    private final JLayeredPane layeredPane;
    private final JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;

    public ChessGui() {
        Dimension boardSize = new Dimension(600, 600);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseListener(this);

        // add chessboard to layout
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8,8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.blue : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.blue);
            }
        }

        // Add pieces to board
        game = new Game();
        initializeChessBoard();
    }

    private void initializeChessBoard() {

    }

    private void updateChessBoard() {

    }

    // select piece
    public void mousePressed(MouseEvent e) {
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
    }

    // Move piece around
    public void mouseDragged(MouseEvent e) {
        if (chessPiece == null) return;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }

    public void mouseReleased(MouseEvent e) {
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
    }

    public void mouseClicked(MouseEvent e){}

    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
