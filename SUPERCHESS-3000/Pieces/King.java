
public class King extends Piece{
	
	private boolean hasMoved;

	public King(int posX, int posY, boolean isWhite, String imagePiece, Board board, boolean startPos) {
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
		 if (Math.abs(move.getEnd().getY() - move.getStart().getY()) <= board.getWidth()/8
                 && Math.abs(move.getEnd().getX() - move.getStart().getX()) <= board.getWidth()/8) {
             return true;
         }
		return super.canMove(move);
	}
}
