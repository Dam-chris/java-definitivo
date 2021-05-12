

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

<<<<<<< HEAD
public class CheckmateDetector {
    private Board board;
    private LinkedList<Piece> wPieces;
    private LinkedList<Piece> bPieces;
    private LinkedList<Square> movableSquares;
    private LinkedList<Square> squares;
    private King bking;
    private King wking;
    private HashMap<Square,List<Piece>> wMoves;
    private HashMap<Square,List<Piece>> bMoves;
    
 
    public CheckmateDetector(Board board, LinkedList<Piece> wPieces, 
            LinkedList<Piece> bPieces, King wking, King bking) {
        this.board = board;
        this.wPieces = wPieces;
        this.bPieces = bPieces;
        this.bking = bking;
        this.wking = wking;
        
        // Initializacion
=======

/**
 * Component of the Chess game that detects check mates in the game.
 * 
 * @author Jussi Lundstedt
 *
 */
public class CheckmateDetector {
    private Board b;
    private LinkedList<Piece> wPieces;
    private LinkedList<Piece> bPieces;
    private LinkedList<Square> movableSquares;
    private final LinkedList<Square> squares;
    private King bk;
    private King wk;
    private HashMap<Square,List<Piece>> wMoves;
    private HashMap<Square,List<Piece>> bMoves;
    
    /**
     * Constructs a new instance of CheckmateDetector on a given board. By
     * convention should be called when the board is in its initial state.
     * 
     * @param b The board which the detector monitors
     * @param wPieces White pieces on the board.
     * @param bPieces Black pieces on the board.
     * @param wk Piece object representing the white king
     * @param bk Piece object representing the black king
     */
    public CheckmateDetector(Board b, LinkedList<Piece> wPieces, 
            LinkedList<Piece> bPieces, King wk, King bk) {
        this.b = b;
        this.wPieces = wPieces;
        this.bPieces = bPieces;
        this.bk = bk;
        this.wk = wk;
        
        // Initialize other fields
>>>>>>> java
        squares = new LinkedList<Square>();
        movableSquares = new LinkedList<Square>();
        wMoves = new HashMap<Square,List<Piece>>();
        bMoves = new HashMap<Square,List<Piece>>();
        
<<<<<<< HEAD
        Square[][] chessboard = board.getSquareArray();
        
        // añadir casillas al arrlist de calillas y piezas al hashmap
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares.add(chessboard[y][x]);
                wMoves.put(chessboard[y][x], new LinkedList<Piece>());
                bMoves.put(chessboard[y][x], new LinkedList<Piece>());
            }
        }
        
        // actualizar la siatuacion
        update();
    }
    
    
    public void update() {
        // trazar las piezas
        Iterator<Piece> wIter = wPieces.iterator();
        Iterator<Piece> bIter = bPieces.iterator();
        
        // movimientos disponibles en cada actualizacion
=======
        Square[][] brd = b.getSquareArray();
        
        // add all squares to squares list and as hashmap keys
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares.add(brd[y][x]);
                wMoves.put(brd[y][x], new LinkedList<Piece>());
                bMoves.put(brd[y][x], new LinkedList<Piece>());
            }
        }
        
        // update situation
        update();
    }
    
    /**
     * Updates the object with the current situation of the game.
     */
    public void update() {
        // Iterators through pieces
        Iterator<Piece> wIter = wPieces.iterator();
        Iterator<Piece> bIter = bPieces.iterator();
        
        // empty moves and movable squares at each update
>>>>>>> java
        for (List<Piece> pieces : wMoves.values()) {
            pieces.removeAll(pieces);
        }
        
        for (List<Piece> pieces : bMoves.values()) {
            pieces.removeAll(pieces);
        }
        
        movableSquares.removeAll(movableSquares);
        
<<<<<<< HEAD
        // agregar movimientos
=======
        // Add each move white and black can make to map
>>>>>>> java
        while (wIter.hasNext()) {
            Piece p = wIter.next();

            if (!p.getClass().equals(King.class)) {
                if (p.getPosition() == null) {
                    wIter.remove();
                    continue;
                }

<<<<<<< HEAD
                List<Square> mvs = p.getLegalMoves(board);
=======
                List<Square> mvs = p.getLegalMoves(b);
>>>>>>> java
                Iterator<Square> iter = mvs.iterator();
                while (iter.hasNext()) {
                    List<Piece> pieces = wMoves.get(iter.next());
                    pieces.add(p);
                }
            }
        }
        
        while (bIter.hasNext()) {
            Piece p = bIter.next();
            
            if (!p.getClass().equals(King.class)) {
                if (p.getPosition() == null) {
                    wIter.remove();
                    continue;
                }
                
<<<<<<< HEAD
                List<Square> mvs = p.getLegalMoves(board);
=======
                List<Square> mvs = p.getLegalMoves(b);
>>>>>>> java
                Iterator<Square> iter = mvs.iterator();
                while (iter.hasNext()) {
                    List<Piece> pieces = bMoves.get(iter.next());
                    pieces.add(p);
                }
            }
        }
    }
    
<<<<<<< HEAD
  
    public boolean blackInCheck() {
        update();
        Square square = bking.getPosition();
        if (wMoves.get(square).isEmpty()) {
=======
    /**
     * Checks if the black king is threatened
     * @return boolean representing whether the black king is in check.
     */
    public boolean blackInCheck() {
        update();
        Square sq = bk.getPosition();
        if (wMoves.get(sq).isEmpty()) {
>>>>>>> java
            movableSquares.addAll(squares);
            return false;
        } else return true;
    }
    
<<<<<<< HEAD
   
    public boolean whiteInCheck() {
        update();
        Square square = wking.getPosition();
        if (bMoves.get(square).isEmpty()) {
=======
    /**
     * Checks if the white king is threatened
     * @return boolean representing whether the white king is in check.
     */
    public boolean whiteInCheck() {
        update();
        Square sq = wk.getPosition();
        if (bMoves.get(sq).isEmpty()) {
>>>>>>> java
            movableSquares.addAll(squares);
            return false;
        } else return true;
    }
    
<<<<<<< HEAD
    
    public boolean blackCheckMated() {
        boolean checkmate = true;
        // si negro esta en jaque
        if (!this.blackInCheck())
        {
        	return false;
        }
        //si se puede evitar el mate
        if (canEvade(wMoves, bking)) 
        {
        	checkmate = false;
        }
        //si se puede capturar la amenaza
        List<Piece> threats = wMoves.get(bking.getPosition());
        if (canCapture(bMoves, threats, bking)) 
        {	
        	checkmate = false;
        }
        // bloquear la amenaza
        if (canBlock(threats, bMoves, bking)) 
        {	
        	checkmate = false;
        }
        // jaque mate
        return checkmate;
    }
    
   
    public boolean whiteCheckMated() {
        boolean checkmate = true;
        // si negro esta en jaque
        if (!this.whiteInCheck()) 
        {
        	return false;
        }
        //si se puede evitar el mate
        if (canEvade(bMoves, wking)) 
        {
        	checkmate = false;
        }
        //si se puede capturar la amenaza
        List<Piece> threats = bMoves.get(wking.getPosition());
        if (canCapture(wMoves, threats, wking)) 
        {
        	checkmate = false;
        }
        // bloquear la amenaza
        if (canBlock(threats, wMoves, wking)) 
        {	
        	checkmate = false;
        }
        // jaque mate
        return checkmate;
    }
    
   
    private boolean canEvade(Map<Square,List<Piece>> tMoves, King tKing) {
        boolean evade = false;
        List<Square> kingsMoves = tKing.getLegalMoves(board);
=======
    /**
     * Checks whether black is in checkmate.
     * @return boolean representing if black player is checkmated.
     */
    public boolean blackCheckMated() {
        boolean checkmate = true;
        // Check if black is in check
        if (!this.blackInCheck()) return false;
        
        // If yes, check if king can evade
        if (canEvade(wMoves, bk)) checkmate = false;
        
        // If no, check if threat can be captured
        List<Piece> threats = wMoves.get(bk.getPosition());
        if (canCapture(bMoves, threats, bk)) checkmate = false;
        
        // If no, check if threat can be blocked
        if (canBlock(threats, bMoves, bk)) checkmate = false;
        
        // If no possible ways of removing check, checkmate occurred
        return checkmate;
    }
    
    /**
     * Checks whether white is in checkmate.
     * @return boolean representing if white player is checkmated.
     */
    public boolean whiteCheckMated() {
        boolean checkmate = true;
        // Check if white is in check
        if (!this.whiteInCheck()) return false;
        
        // If yes, check if king can evade
        if (canEvade(bMoves, wk)) checkmate = false;
        
        // If no, check if threat can be captured
        List<Piece> threats = bMoves.get(wk.getPosition());
        if (canCapture(wMoves, threats, wk)) checkmate = false;
        
        // If no, check if threat can be blocked
        if (canBlock(threats, wMoves, wk)) checkmate = false;
        
        // If no possible ways of removing check, checkmate occurred
        return checkmate;
    }
    
    /*
     * Helper method to determine if the king can evade the check.
     * Gives a false positive if the king can capture the checking piece.
     */
    private boolean canEvade(Map<Square,List<Piece>> tMoves, King tKing) {
        boolean evade = false;
        List<Square> kingsMoves = tKing.getLegalMoves(b);
>>>>>>> java
        Iterator<Square> iterator = kingsMoves.iterator();
        
        // If king is not threatened at some square, it can evade
        while (iterator.hasNext()) {
<<<<<<< HEAD
            Square square = iterator.next();
            if (!testMove(tKing, square)) continue;
            if (tMoves.get(square).isEmpty()) {
                movableSquares.add(square);
=======
            Square sq = iterator.next();
            if (!testMove(tKing, sq)) continue;
            if (tMoves.get(sq).isEmpty()) {
                movableSquares.add(sq);
>>>>>>> java
                evade = true;
            }
        }
        
        return evade;
    }
    
<<<<<<< HEAD
  
    private boolean canCapture(Map<Square,List<Piece>> poss, 
            List<Piece> threats, King king) {
        
        boolean capture = false;
        if (threats.size() == 1) {
            Square square = threats.get(0).getPosition();
            
            if (king.getLegalMoves(board).contains(square)) {
                movableSquares.add(square);
                if (testMove(king, square)) {
=======
    /*
     * Helper method to determine if the threatening piece can be captured.
     */
    private boolean canCapture(Map<Square,List<Piece>> poss, 
            List<Piece> threats, King k) {
        
        boolean capture = false;
        if (threats.size() == 1) {
            Square sq = threats.get(0).getPosition();
            
            if (k.getLegalMoves(b).contains(sq)) {
                movableSquares.add(sq);
                if (testMove(k, sq)) {
>>>>>>> java
                    capture = true;
                }
            }
            
<<<<<<< HEAD
            List<Piece> caps = poss.get(square);
=======
            List<Piece> caps = poss.get(sq);
>>>>>>> java
            ConcurrentLinkedDeque<Piece> capturers = new ConcurrentLinkedDeque<Piece>();
            capturers.addAll(caps);
            
            if (!capturers.isEmpty()) {
<<<<<<< HEAD
                movableSquares.add(square);
                for (Piece p : capturers) {
                    if (testMove(p, square)) {
=======
                movableSquares.add(sq);
                for (Piece p : capturers) {
                    if (testMove(p, sq)) {
>>>>>>> java
                        capture = true;
                    }
                }
            }
        }
        
        return capture;
    }
    
<<<<<<< HEAD
    
    private boolean canBlock(List<Piece> threats, 
            Map <Square,List<Piece>> blockMoves, King king) {
=======
    /*
     * Helper method to determine if check can be blocked by a piece.
     */
    private boolean canBlock(List<Piece> threats, 
            Map <Square,List<Piece>> blockMoves, King k) {
>>>>>>> java
        boolean blockable = false;
        
        if (threats.size() == 1) {
            Square ts = threats.get(0).getPosition();
<<<<<<< HEAD
            Square ks = king.getPosition();
            Square[][] brdArray = board.getSquareArray();
=======
            Square ks = k.getPosition();
            Square[][] brdArray = b.getSquareArray();
>>>>>>> java
            
            if (ks.getXNum() == ts.getXNum()) {
                int max = Math.max(ks.getYNum(), ts.getYNum());
                int min = Math.min(ks.getYNum(), ts.getYNum());
                
                for (int i = min + 1; i < max; i++) {
                    List<Piece> blks = 
                            blockMoves.get(brdArray[i][ks.getXNum()]);
                    ConcurrentLinkedDeque<Piece> blockers = 
                            new ConcurrentLinkedDeque<Piece>();
                    blockers.addAll(blks);
                    
                    if (!blockers.isEmpty()) {
                        movableSquares.add(brdArray[i][ks.getXNum()]);
                        
                        for (Piece p : blockers) {
                            if (testMove(p,brdArray[i][ks.getXNum()])) {
                                blockable = true;
                            }
                        }
                        
                    }
                }
            }
            
            if (ks.getYNum() == ts.getYNum()) {
                int max = Math.max(ks.getXNum(), ts.getXNum());
                int min = Math.min(ks.getXNum(), ts.getXNum());
                
                for (int i = min + 1; i < max; i++) {
                    List<Piece> blks = 
                            blockMoves.get(brdArray[ks.getYNum()][i]);
                    ConcurrentLinkedDeque<Piece> blockers = 
                            new ConcurrentLinkedDeque<Piece>();
                    blockers.addAll(blks);
                    
                    if (!blockers.isEmpty()) {
                        
                        movableSquares.add(brdArray[ks.getYNum()][i]);
                        
                        for (Piece p : blockers) {
                            if (testMove(p, brdArray[ks.getYNum()][i])) {
                                blockable = true;
                            }
                        }
                        
                    }
                }
            }
            
            Class<? extends Piece> tC = threats.get(0).getClass();
            
            if (tC.equals(Queen.class) || tC.equals(Bishop.class)) {
                int kX = ks.getXNum();
                int kY = ks.getYNum();
                int tX = ts.getXNum();
                int tY = ts.getYNum();
                
                if (kX > tX && kY > tY) {
                    for (int i = tX + 1; i < kX; i++) {
                        tY++;
                        List<Piece> blks = 
                                blockMoves.get(brdArray[tY][i]);
                        ConcurrentLinkedDeque<Piece> blockers = 
                                new ConcurrentLinkedDeque<Piece>();
                        blockers.addAll(blks);
                        
                        if (!blockers.isEmpty()) {
                            movableSquares.add(brdArray[tY][i]);
                            
                            for (Piece p : blockers) {
                                if (testMove(p, brdArray[tY][i])) {
                                    blockable = true;
                                }
                            }
                        }
                    }
                }
                
                if (kX > tX && tY > kY) {
                    for (int i = tX + 1; i < kX; i++) {
                        tY--;
                        List<Piece> blks = 
                                blockMoves.get(brdArray[tY][i]);
                        ConcurrentLinkedDeque<Piece> blockers = 
                                new ConcurrentLinkedDeque<Piece>();
                        blockers.addAll(blks);
                        
                        if (!blockers.isEmpty()) {
                            movableSquares.add(brdArray[tY][i]);
                            
                            for (Piece p : blockers) {
                                if (testMove(p, brdArray[tY][i])) {
                                    blockable = true;
                                }
                            }
                        }
                    }
                }
                
                if (tX > kX && kY > tY) {
                    for (int i = tX - 1; i > kX; i--) {
                        tY++;
                        List<Piece> blks = 
                                blockMoves.get(brdArray[tY][i]);
                        ConcurrentLinkedDeque<Piece> blockers = 
                                new ConcurrentLinkedDeque<Piece>();
                        blockers.addAll(blks);
                        
                        if (!blockers.isEmpty()) {
                            movableSquares.add(brdArray[tY][i]);
                            
                            for (Piece p : blockers) {
                                if (testMove(p, brdArray[tY][i])) {
                                    blockable = true;
                                }
                            }
                        }
                    }
                }
                
                if (tX > kX && tY > kY) {
                    for (int i = tX - 1; i > kX; i--) {
                        tY--;
                        List<Piece> blks = 
                                blockMoves.get(brdArray[tY][i]);
                        ConcurrentLinkedDeque<Piece> blockers = 
                                new ConcurrentLinkedDeque<Piece>();
                        blockers.addAll(blks);
                        
                        if (!blockers.isEmpty()) {
                            movableSquares.add(brdArray[tY][i]);
                            
                            for (Piece p : blockers) {
                                if (testMove(p, brdArray[tY][i])) {
                                    blockable = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return blockable;
    }
    
<<<<<<< HEAD
   
=======
    /**
     * Method to get a list of allowable squares that the player can move.
     * Defaults to all squares, but limits available squares if player is in
     * check.
     * @param b boolean representing whether it's white player's turn (if yes,
     * true)
     * @return List of squares that the player can move into.
     */
>>>>>>> java
    public List<Square> getAllowableSquares(boolean b) {
        movableSquares.removeAll(movableSquares);
        if (whiteInCheck()) {
            whiteCheckMated();
        } else if (blackInCheck()) {
            blackCheckMated();
        }
        return movableSquares;
    }
    
<<<<<<< HEAD
   
    public boolean testMove(Piece p, Square square) {
        Piece c = square.getOccupyingPiece();
=======
    /**
     * Tests a move a player is about to make to prevent making an illegal move
     * that puts the player in check.
     * @param p Piece moved
     * @param sq Square to which p is about to move
     * @return false if move would cause a check
     */
    public boolean testMove(Piece p, Square sq) {
        Piece c = sq.getOccupyingPiece();
>>>>>>> java
        
        boolean movetest = true;
        Square init = p.getPosition();
        
<<<<<<< HEAD
        p.move(square);
=======
        p.move(sq);
>>>>>>> java
        update();
        
        if (p.getColor() == 0 && blackInCheck()) movetest = false;
        else if (p.getColor() == 1 && whiteInCheck()) movetest = false;
        
        p.move(init);
<<<<<<< HEAD
        if (c != null) square.put(c);
=======
        if (c != null) sq.put(c);
>>>>>>> java
        
        update();
        
        movableSquares.addAll(squares);
        return movetest;
    }

}
