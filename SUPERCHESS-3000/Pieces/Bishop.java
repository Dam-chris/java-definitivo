
public class Bishop extends Piece{
	
	private boolean hasMoved;

	public Bishop(int posX, int posY, boolean isWhite, String imagePiece, Board board, boolean startPos) {
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
		if (Math.abs(move.getEnd().getY() - move.getStart().getY())
                == Math.abs(move.getEnd().getX() - move.getStart().getX())) 
		{
            return true;
        }
		return super.canMove(move);
	}
}
