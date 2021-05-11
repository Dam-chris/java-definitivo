import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Piece {

	public final boolean isWhite;
	private int posX, posY;
	private String imagePiece;
	private  boolean hasMoved;
	public Board board;
	public Piece(int posX, int posY, boolean isWhite, String imagePiece, Board board, boolean hasMoved) {
		this.posX = posX;
		this.posY = posY;
		this.isWhite = isWhite;
		this.board = board;
		this.setImagePiece(imagePiece);
		this.hasMoved = hasMoved;
	}
	public void draw(Graphics g) {
	
		Image piece = new ImageIcon(getClass().getResource(imagePiece)).getImage();
		g.drawImage(piece, posX + 2, posY - 2, 95, 95, null);
		
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public boolean isBlack() {
		return !isWhite;
	}
	//funcion del movimiento de las piezas 
	//se sobreescribira en cada pieza en funcio de si se puede mover o no
	public boolean canMove(Move move) {
		return false;
	}

	public String getImagePiece() {
		return imagePiece;
	}

	public void setImagePiece(String imagePiece) {
		this.imagePiece = imagePiece;
	}
	public Rectangle getRect() {
		Rectangle r;
		r = new Rectangle(getPosX() + 20, getPosY() + 10, 75, 80);
		return r;
	}
	public boolean hasMoved() {
		return hasMoved;
	}

	
	public void sethasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}







