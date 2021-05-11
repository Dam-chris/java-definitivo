import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Square {
	
	private Piece piece;
	private int posX, posY;
	private boolean isWhite;
	private String posLet, posNum;
	public Square(int posX, int posY, Piece piece, boolean isWhite, String posLet, String posNum) {
		this.piece = piece;
		this.posX = posX;
		this.posY = posY;
		this.isWhite = isWhite;
		this.posLet = posLet;
		this.posNum = posNum;

		
	}
	public void draw(Graphics g) {
		
		//dibujar los bores de las caillas
		g.setColor(Color.decode("#779952"));
		g.drawRect(posX, posY, 100, 100);
		
		//dibujar las caillas
		if (isWhite) 
		{
			g.setColor(Color.decode("#edeed1"));
		}
		else 
		{
			g.setColor(Color.decode("#779952"));
		}
		g.fillRect(posX, posY, 100, 100);
		
		//dibujar la numeracion y letras de los laterales del tablero
		if (!isWhite) 
		{
			g.setColor(Color.decode("#edeed1"));
		}
		else 
		{
			g.setColor(Color.decode("#779952"));
		}
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString(posLet, posX+75, posY+95);
		g.drawString(posNum, posX+10, posY+25);
	
	}
	

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
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
	public Rectangle getRect() {
		Rectangle r;
		r = new Rectangle(getPosX(), getPosY(), 100, 100);
		return r;
	}


	
	
}
