

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

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
        squares = new LinkedList<Square>();
        movableSquares = new LinkedList<Square>();
        wMoves = new HashMap<Square,List<Piece>>();
        bMoves = new HashMap<Square,List<Piece>>();
        
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
        for (List<Piece> pieces : wMoves.values()) {
            pieces.removeAll(pieces);
        }
        
        for (List<Piece> pieces : bMoves.values()) {
            pieces.removeAll(pieces);
        }
        
        movableSquares.removeAll(movableSquares);
        
        // agregar movimientos
        while (wIter.hasNext()) {
            Piece p = wIter.next();

            if (!p.getClass().equals(King.class)) {
                if (p.getPosition() == null) {
                    wIter.remove();
                    continue;
                }

                List<Square> mvs = p.getLegalMoves(board);
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
                
                List<Square> mvs = p.getLegalMoves(board);
                Iterator<Square> iter = mvs.iterator();
                while (iter.hasNext()) {
                    List<Piece> pieces = bMoves.get(iter.next());
                    pieces.add(p);
                }
            }
        }
    }
    
  
    public boolean blackInCheck() {
        update();
        Square square = bking.getPosition();
        if (wMoves.get(square).isEmpty()) {
            movableSquares.addAll(squares);
            return false;
        } else return true;
    }
    
   
    public boolean whiteInCheck() {
        update();
        Square square = wking.getPosition();
        if (bMoves.get(square).isEmpty()) {
            movableSquares.addAll(squares);
            return false;
        } else return true;
    }
    
    
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
        Iterator<Square> iterator = kingsMoves.iterator();
        
        // If king is not threatened at some square, it can evade
        while (iterator.hasNext()) {
            Square square = iterator.next();
            if (!testMove(tKing, square)) continue;
            if (tMoves.get(square).isEmpty()) {
                movableSquares.add(square);
                evade = true;
            }
        }
        
        return evade;
    }
    
  
    private boolean canCapture(Map<Square,List<Piece>> poss, 
            List<Piece> threats, King king) {
        
        boolean capture = false;
        if (threats.size() == 1) {
            Square square = threats.get(0).getPosition();
            
            if (king.getLegalMoves(board).contains(square)) {
                movableSquares.add(square);
                if (testMove(king, square)) {
                    capture = true;
                }
            }
            
            List<Piece> caps = poss.get(square);
            ConcurrentLinkedDeque<Piece> capturers = new ConcurrentLinkedDeque<Piece>();
            capturers.addAll(caps);
            
            if (!capturers.isEmpty()) {
                movableSquares.add(square);
                for (Piece p : capturers) {
                    if (testMove(p, square)) {
                        capture = true;
                    }
                }
            }
        }
        
        return capture;
    }
    
    
    private boolean canBlock(List<Piece> threats, 
            Map <Square,List<Piece>> blockMoves, King king) {
        boolean blockable = false;
        
        if (threats.size() == 1) {
            Square ts = threats.get(0).getPosition();
            Square ks = king.getPosition();
            Square[][] brdArray = board.getSquareArray();
            
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
    
   
    public List<Square> getAllowableSquares(boolean b) {
        movableSquares.removeAll(movableSquares);
        if (whiteInCheck()) {
            whiteCheckMated();
        } else if (blackInCheck()) {
            blackCheckMated();
        }
        return movableSquares;
    }
    
   
    public boolean testMove(Piece p, Square square) {
        Piece c = square.getOccupyingPiece();
        
        boolean movetest = true;
        Square init = p.getPosition();
        
        p.move(square);
        update();
        
        if (p.getColor() == 0 && blackInCheck()) movetest = false;
        else if (p.getColor() == 1 && whiteInCheck()) movetest = false;
        
        p.move(init);
        if (c != null) square.put(c);
        
        update();
        
        movableSquares.addAll(squares);
        return movetest;
    }

}
