import java.awt.Point;

public class Move {
	private Point start, end;
	private Piece piece;
	public Move(Point start, Point end, Piece piece) {
		this.start = start;
		this.end = end;
		this.piece = piece;
	}
	public Point getStart() {
		return start;
	}
	public void setStart(Point start) {
		this.start = start;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
