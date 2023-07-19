package ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

import game.Game;
import gameboard.Spot;
import pieces.Piece;
import players.HumanPlayer;
import players.Player;

public class ChessGui extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
    private Game game;
    private final JPanel chessBoard;
    private final JButton newGameButton;
    private final JButton resetGameButton;
    private final JButton quitButton;
    private final JLabel whiteCheckLabel;
    private final JLabel blackCheckLabel;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private final int squareSize;
    private int xStart;
    private int yStart;

    public ChessGui() {
        Dimension boardSize = new Dimension(600, 600);
        squareSize = 600 / 8;
        int marginSize = 20;
        Border border = BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize);

        // main panel for whole app
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // panel for board and margin panels
        JPanel boardPanel = new JPanel(new BorderLayout());
        boardPanel.setBorder(border);
        mainPanel.add(boardPanel);

        // top panel for showing letters along board
        JPanel topPanel = new JPanel(new GridLayout(1, 8));
        topPanel.setPreferredSize(new Dimension(600, 50));
        for (char c = 'a'; c <= 'h'; c++) {
            JLabel label = new JLabel(String.valueOf(c), SwingConstants.CENTER);
            topPanel.add(label);
        }
        boardPanel.add(topPanel, BorderLayout.NORTH);

        // left side panel to show numbers along board
        JPanel leftPanel = new JPanel(new GridLayout(8, 1));
        leftPanel.setPreferredSize(new Dimension(50, 600));
        for (int i = 8; i >= 1; i--) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            leftPanel.add(label);
        }
        boardPanel.add(leftPanel, BorderLayout.WEST);

        // panel for the chess board
        JLayeredPane layeredPane = new JLayeredPane();
        boardPanel.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        // add chessboard to layout
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8,8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        // right panel added to main for buttons and other info
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        rightPanel.setBorder(border);
        rightPanel.setPreferredSize(new Dimension(200, 600));

        whiteCheckLabel = new JLabel("White King is in check!");
        blackCheckLabel = new JLabel("Black King is in check!");
        newGameButton = new JButton("New Game");
        resetGameButton = new JButton("Reset Game");
        quitButton = new JButton("Quit");

        Dimension buttonSize = new Dimension(150, 50);
        newGameButton.setPreferredSize(buttonSize);
        resetGameButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        newGameButton.addActionListener(this);
        resetGameButton.addActionListener(this);
        quitButton.addActionListener(this);

        rightPanel.add(whiteCheckLabel);
        rightPanel.add(blackCheckLabel);
        rightPanel.add(newGameButton);
        rightPanel.add(resetGameButton);
        rightPanel.add(quitButton);

        // labels for check shouldn't be shown on initial board
        whiteCheckLabel.setVisible(false);
        blackCheckLabel.setVisible(false);

        // Add pieces to board
        resetGame();

        mainPanel.add(rightPanel, BorderLayout.EAST);
    }

    private void setChessBoard() {

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
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
        blackCheckLabel.setVisible(game.getBlackKingChecked());
        whiteCheckLabel.setVisible(game.getWhiteKingChecked());
        chessBoard.revalidate();
        chessBoard.repaint();
    }

    private void resetGame() {
        Player p1 = new HumanPlayer(true);
        Player p2 = new HumanPlayer(false);
        this.game = new Game(p1, p2);
        setChessBoard();
        updateChessBoard();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            System.out.println("New Game pushed");
        } else if (e.getSource() == resetGameButton) {
            System.out.println("Reset Game pushed");
            resetGame();
        } else if (e.getSource() == quitButton) {
            System.out.println("Quit game button pressed");
            dispose();
        }
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

        System.out.println("start spot : " + startSpot);
        System.out.println("end spot : " + endSpot);
        System.out.println("piece : " + selectedPiece);

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
