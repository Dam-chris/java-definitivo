

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.*;


public class Square extends JComponent {
   
	private static final long serialVersionUID = 1L;

	private Board board;
    
    private final int color;
    private Piece occupyingPiece;
    private boolean dispPiece;
    
    private int xNum;
    private int yNum;
    
    public Square(Board board, int color, int xNum, int yNum) {
        
        this.board = board;
        this.color = color;
        this.dispPiece = true;
        this.xNum = xNum;
        this.yNum = yNum;
        
        
        this.setBorder(BorderFactory.createEmptyBorder());
    }
    
    public int getColor() {
        return this.color;
    }
    
    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }
    
    public boolean isOccupied() {
        return (this.occupyingPiece != null);
    }
    
    public int getXNum() {
        return this.xNum;
    }
    
    public int getYNum() {
        return this.yNum;
    }
    
    public void setDisplay(boolean v) {
        this.dispPiece = v;
    }
    
    public void put(Piece p) {
        this.occupyingPiece = p;
        p.setPosition(this);
    }
    
    public Piece removePiece() {
        Piece p = this.occupyingPiece;
        this.occupyingPiece = null;
        return p;
    }
    
    public void capture(Piece p) {
        Piece k = getOccupyingPiece();
        if (k.getColor() == 0) board.Bpieces.remove(k);
        if (k.getColor() == 1) board.Wpieces.remove(k);
        this.occupyingPiece = p;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.color == 1) 
        {
        	g.setColor(Color.decode("#edeed1"));
        }
        else 
        {
        	g.setColor(Color.decode("#779952"));
        }
        
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if(occupyingPiece != null && dispPiece) {
            occupyingPiece.draw(g);
        }
    }
    public void drawshapes(Graphics2D g, int x, int y, int width, int height) {
    	g.setColor(Color.YELLOW);
    	double thickness = 5;
    	Stroke oldStroke = g.getStroke();
    	g.setStroke(new BasicStroke((float) thickness));
    	g.drawRect(x, y, width, height);
    	g.setStroke(oldStroke);
    	
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + xNum;
        result = prime * result + yNum;
        return result;
    }
    
}
