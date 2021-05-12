

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

<<<<<<< HEAD

public class Board extends JPanel implements MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	private static final String RESOURCES_WBISHOP_PNG = "wb.png";
=======
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wb.png";
>>>>>>> java
	private static final String RESOURCES_BBISHOP_PNG = "bb.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wn.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bn.png";
	private static final String RESOURCES_WROOK_PNG = "wr.png";
	private static final String RESOURCES_BROOK_PNG = "br.png";
	private static final String RESOURCES_WKING_PNG = "wk.png";
	private static final String RESOURCES_BKING_PNG = "bk.png";
	private static final String RESOURCES_BQUEEN_PNG = "bq.png";
	private static final String RESOURCES_WQUEEN_PNG = "wq.png";
	private static final String RESOURCES_WPAWN_PNG = "wp.png";
	private static final String RESOURCES_BPAWN_PNG = "bp.png";
	
<<<<<<< HEAD
	//board
	private final Square[][] board;
    private final ChessGame chess;
    
    // List of pieces 
=======
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
    
    // List of pieces and whether they are movable
>>>>>>> java
    public final LinkedList<Piece> Bpieces;
    public final LinkedList<Piece> Wpieces;
    public List<Square> movable;
    
    private boolean whiteTurn;

    private Piece currPiece;
    private int currX;
    private int currY;
    private Graphics2D g2D;
<<<<<<< HEAD
    private CheckmateDetector checkMate;
    private int startX, startY, endX, endY;
    
    public Board(ChessGame chess) {
        this.chess = chess;
=======
    private CheckmateDetector cmd;
    
    public Board(GameWindow g) {
        this.g = g;
>>>>>>> java
        board = new Square[8][8];
        Bpieces = new LinkedList<Piece>();
        Wpieces = new LinkedList<Piece>();
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

<<<<<<< HEAD
        for (int x = 0; x < 8; x++) 
        {
            for (int y = 0; y < 8; y++) 
            {
                int xMod = x % 2;
                int yMod = y % 2;

                if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) 
                {
                    board[x][y] = new Square(this, 1, y, x);
                    this.add(board[x][y]);
                } 
                else 
                {
=======
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int xMod = x % 2;
                int yMod = y % 2;

                if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
                    board[x][y] = new Square(this, 1, y, x);
                    this.add(board[x][y]);
                } else {
>>>>>>> java
                    board[x][y] = new Square(this, 0, y, x);
                    this.add(board[x][y]);
                }
            }
        }

        initializePieces();

        this.setPreferredSize(new Dimension(800, 800));
        this.setMaximumSize(new Dimension(800, 800));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(800, 800));

        whiteTurn = true;

    }

    private void initializePieces() {
    	
        for (int x = 0; x < 8; x++) {
            board[1][x].put(new Pawn(0, board[1][x], RESOURCES_BPAWN_PNG));
            board[6][x].put(new Pawn(1, board[6][x], RESOURCES_WPAWN_PNG));
        }
        
        board[7][3].put(new Queen(1, board[7][3], RESOURCES_WQUEEN_PNG));
        board[0][3].put(new Queen(0, board[0][3], RESOURCES_BQUEEN_PNG));
        
        King bk = new King(0, board[0][4], RESOURCES_BKING_PNG);
        King wk = new King(1, board[7][4], RESOURCES_WKING_PNG);
        board[0][4].put(bk);
        board[7][4].put(wk);

        board[0][0].put(new Rook(0, board[0][0], RESOURCES_BROOK_PNG));
        board[0][7].put(new Rook(0, board[0][7], RESOURCES_BROOK_PNG));
        board[7][0].put(new Rook(1, board[7][0], RESOURCES_WROOK_PNG));
        board[7][7].put(new Rook(1, board[7][7], RESOURCES_WROOK_PNG));

        board[0][1].put(new Knight(0, board[0][1], RESOURCES_BKNIGHT_PNG));
        board[0][6].put(new Knight(0, board[0][6], RESOURCES_BKNIGHT_PNG));
        board[7][1].put(new Knight(1, board[7][1], RESOURCES_WKNIGHT_PNG));
        board[7][6].put(new Knight(1, board[7][6], RESOURCES_WKNIGHT_PNG));

        board[0][2].put(new Bishop(0, board[0][2], RESOURCES_BBISHOP_PNG));
        board[0][5].put(new Bishop(0, board[0][5], RESOURCES_BBISHOP_PNG));
        board[7][2].put(new Bishop(1, board[7][2], RESOURCES_WBISHOP_PNG));
        board[7][5].put(new Bishop(1, board[7][5], RESOURCES_WBISHOP_PNG));
        
        
<<<<<<< HEAD
        for(int y = 0; y < 2; y++) 
        {
            for (int x = 0; x < 8; x++) 
            {
=======
        for(int y = 0; y < 2; y++) {
            for (int x = 0; x < 8; x++) {
>>>>>>> java
                Bpieces.add(board[y][x].getOccupyingPiece());
                Wpieces.add(board[7-y][x].getOccupyingPiece());
            }
        }
        
<<<<<<< HEAD
        checkMate = new CheckmateDetector(this, Wpieces, Bpieces, wk, bk);
=======
        cmd = new CheckmateDetector(this, Wpieces, Bpieces, wk, bk);
>>>>>>> java
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

<<<<<<< HEAD
    public void setCurrPiece(Piece piece) {
        this.currPiece = piece;
=======
    public void setCurrPiece(Piece p) {
        this.currPiece = p;
>>>>>>> java
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
<<<<<<< HEAD
    	Square square;
=======
        // super.paintComponent(g);
>>>>>>> java
    	g2D = (Graphics2D) g;
		
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
<<<<<<< HEAD
        for (int x = 0; x < 8; x++) 
        {
            for (int y = 0; y < 8; y++) 
            {
                 square = board[y][x];
                 square.drawshapes(g2D, startX, startY, 100, 100);
                 square.drawshapes(g2D, endX, endY, 100, 100);
                 square.paintComponent(g2D);
            }
        }

        if (currPiece != null) 
        {
            if ((currPiece.getColor() == 1 && whiteTurn)
                    || (currPiece.getColor() == 0 && !whiteTurn)) 
            {
                Image img = currPiece.getImage();
                
                g2D.drawImage(img, currX, currY, 125, 125, null);
               
=======
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[y][x];
                sq.paintComponent(g2D);
            }
        }

        if (currPiece != null) {
            if ((currPiece.getColor() == 1 && whiteTurn)
                    || (currPiece.getColor() == 0 && !whiteTurn)) {
                final Image i = currPiece.getImage();
                g2D.drawImage(i, currX, currY,125,125, null);
>>>>>>> java
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX() - 65;
<<<<<<< HEAD
        currY = e.getY() - 85;
        
        Square square = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (square.isOccupied()) 
        {
            currPiece = square.getOccupyingPiece();
=======
        currY = e.getY() - 60;

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
>>>>>>> java
            if (currPiece.getColor() == 0 && whiteTurn)
                return;
            if (currPiece.getColor() == 1 && !whiteTurn)
                return;
<<<<<<< HEAD
            square.setDisplay(false);
            startX = currPiece.getPosition().getX();
            startY = currPiece.getPosition().getY();
=======
            sq.setDisplay(false);
>>>>>>> java
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
<<<<<<< HEAD
        Square square = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (currPiece != null) {
            if (currPiece.getColor() == 0 && whiteTurn)
            {
                return;
            }
            if (currPiece.getColor() == 1 && !whiteTurn)
            {
                return;
            }
            List<Square> legalMoves = currPiece.getLegalMoves(this);
            movable = checkMate.getAllowableSquares(whiteTurn);

            if (legalMoves.contains(square) && movable.contains(square)
                    && checkMate.testMove(currPiece, square)) 
            {
            	 
                square.setDisplay(true);
                currPiece.move(square);
                endX = currPiece.getPosition().getX();
                endY = currPiece.getPosition().getY();
                checkMate.update();

                if (checkMate.blackCheckMated()) {
                	System.out.println("check mate a las negras");
=======
        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (currPiece != null) {
            if (currPiece.getColor() == 0 && whiteTurn)
                return;
            if (currPiece.getColor() == 1 && !whiteTurn)
                return;

            List<Square> legalMoves = currPiece.getLegalMoves(this);
            movable = cmd.getAllowableSquares(whiteTurn);

            if (legalMoves.contains(sq) && movable.contains(sq)
                    && cmd.testMove(currPiece, sq)) {
                sq.setDisplay(true);
                currPiece.move(sq);
                cmd.update();

                if (cmd.blackCheckMated()) {
>>>>>>> java
                    currPiece = null;
                    repaint();
                    this.removeMouseListener(this);
                    this.removeMouseMotionListener(this);
<<<<<<< HEAD
                    chess.checkmateOccurred(0);
                } else if (checkMate.whiteCheckMated()) {
                	System.out.println("check mate a las blancas");
=======
                    g.checkmateOccurred(0);
                } else if (cmd.whiteCheckMated()) {
>>>>>>> java
                    currPiece = null;
                    repaint();
                    this.removeMouseListener(this);
                    this.removeMouseMotionListener(this);
<<<<<<< HEAD
                    chess.checkmateOccurred(1);
                } else {
                    currPiece = null;
                    whiteTurn = !whiteTurn;
                    movable = checkMate.getAllowableSquares(whiteTurn);
                }
               
                
            } 
            else 
            {
                currPiece.getPosition().setDisplay(true);
                currPiece = null;
            }
           
=======
                    g.checkmateOccurred(1);
                } else {
                    currPiece = null;
                    whiteTurn = !whiteTurn;
                    movable = cmd.getAllowableSquares(whiteTurn);
                }

            } else {
                currPiece.getPosition().setDisplay(true);
                currPiece = null;
            }
>>>>>>> java
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 65;
<<<<<<< HEAD
        currY = e.getY() - 85;
        
        repaint();
    }
    //mouse listener
=======
        currY = e.getY() - 65;

        repaint();
    }

    // Irrelevant methods, do nothing for these mouse behaviors
>>>>>>> java
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}