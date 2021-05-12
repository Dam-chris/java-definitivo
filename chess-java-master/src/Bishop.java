

import java.util.List;

public class Bishop extends Piece {

<<<<<<< HEAD
    public Bishop(int color, Square initSq, String img) {
        super(color, initSq, img);
=======
    public Bishop(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
>>>>>>> java
    }
    
    @Override
    public List<Square> getLegalMoves(Board b) {
        Square[][] board = b.getSquareArray();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        
        return getDiagonalOccupations(board, x, y);
    }
}
