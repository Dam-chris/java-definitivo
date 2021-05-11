
public class Rook extends Piece {
	
	private boolean hasMoved;
	
	public Rook(int posX, int posY, boolean isWhite,String imagePiece, Board board, boolean startPos) {
		super(posX, posY, isWhite, imagePiece, board, startPos);
		setHasMoved(false);
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	@Override
	public boolean canMove(Move move) {
		 if (move.getEnd().getY() == move.getStart().getY()
                 && move.getEnd().getX() != move.getStart().getX()) 
		 {
             return true;
		 }
		 if (move.getEnd().getX() == move.getStart().getX()
                 && move.getEnd().getY() != move.getStart().getY()) 
		 {
             return true;
		 }
		return super.canMove(move);
	}
}
