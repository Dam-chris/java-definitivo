
public class Pawn extends Piece{
	
	private boolean hasMoved;
	
	public Pawn(int posX, int posY, boolean isWhite,String imagePiece, Board board, boolean hasMoved) {
		super(posX, posY, isWhite, imagePiece, board, hasMoved);
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
		
		if (move.getPiece().hasMoved() == false ) 
		{
			if (Math.abs(move.getEnd().getY() - move.getStart().getY()) == board.getWidth()/4 && move.getEnd().getX() == move.getStart().getX()) 
			{
               
                return true;
            }
		}
		
		if (Math.abs(move.getEnd().getY() - move.getStart().getY()) == board.getWidth()/8) 
		{
           
            return true;
        }
		return super.canMove(move);
	}

}
