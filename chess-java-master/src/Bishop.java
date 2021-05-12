

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String img) {
        super(color, initSq, img);
    }
    
    @Override
    public List<Square> getLegalMoves(Board b) {
        Square[][] board = b.getSquareArray();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        
        return getDiagonalOccupations(board, x, y);
    }
}
