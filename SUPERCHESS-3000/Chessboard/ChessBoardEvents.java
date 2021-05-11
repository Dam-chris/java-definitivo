import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class ChessBoardEvents {
	private int corX, corY;
	private int width;
	private Square[][] chessboard;
	private Piece pieceSelect;
	private Square squareSelect;
	private boolean whiteTurn;
	private Move move;
	private Point pStart, pEnd;
	//constructor
	public ChessBoardEvents(Board board) {
		corX = 0;
		corY = 0;
		pieceSelect = null;
		squareSelect = null;
		whiteTurn = true;
		width = board.getWidth()/8;
		chessboard = board.getArraySquares();
		board.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				Rectangle rMouse, rPiece;
				int eX = e.getX();
				int eY = e.getY();
				int i = (int) Math.floor(eY/width);
				int j = (int) Math.floor(eX/width);
				
				rMouse = new Rectangle(eX, eY, 3, 3);
				if (chessboard[i][j] != null && chessboard[i][j].getPiece() != null) 
				{	
					
					rPiece = chessboard[i][j].getPiece().getRect();
					if ((rMouse.intersects(rPiece)) && (whiteTurn && chessboard[i][j].getPiece().isWhite) || (!whiteTurn && chessboard[i][j].getPiece().isBlack()))
					{
						
						pieceSelect = chessboard[i][j].getPiece();
						corX = eX - chessboard[i][j].getPiece().getPosX();
						corY = eY - chessboard[i][j].getPiece().getPosY();
						//coordsinada inicial que pasara a la clase move 
						pStart = new Point(pieceSelect.getPosX(), pieceSelect.getPosY());
						//System.out.println("point start "+pieceSelect.getPosX()+" "+pieceSelect.getPosY());
					}
				
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				if (squareSelect != null && pieceSelect != null) 
				{
					//coordinada final del movimiento que pasara a la clase move 
					pEnd = new Point(squareSelect.getPosX(), squareSelect.getPosY());
					move = new Move(pStart, pEnd, pieceSelect);
					
					if (pieceSelect.canMove(move)) 
					{
						pieceSelect.setPosX(squareSelect.getPosX());
						pieceSelect.setPosY(squareSelect.getPosY());
						squareSelect.setPiece(pieceSelect);
						pieceSelect.sethasMoved(true);
						chessboard[(int) pStart.getX()/100][(int) pStart.getY()/100].setPiece(null);
						for (int i = 0; i < 8; i++) 
						{
							for (int j = 0; j < 8; j++) 
							{
								chessboard[i][j].getPiece();
								System.out.print(chessboard[i][j].getPiece());
								
								
							}
							System.out.println();
						}
						
						//System.out.println(pieceSelect);
						//System.out.println("point End "+squareSelect.getPosX()+" "+squareSelect.getPosY());
						whiteTurn = !whiteTurn;
						
					}
					else
					{
						pieceSelect.setPosX((int) pStart.getX());
						pieceSelect.setPosY((int) pStart.getY());
					}
					
				}
				
				pieceSelect =  null;
				board.repaint();
			}
		});
		board.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				int eX = e.getX();
				int eY = e.getY();
				int i = (int) Math.floor(eY/width);
				int j = (int) Math.floor(eX/width);
				
				if (pieceSelect != null) 
				{
					if (eX < -5 || eX > board.getWidth() || eY < -2 || eY > board.getHeight()) 
					{
						pieceSelect.setPosX((int) pStart.getX());
						pieceSelect.setPosY((int) pStart.getY());
					}
					else
					{
						pieceSelect.setPosX(eX - corX);
						pieceSelect.setPosY(eY - corY);
					}
				
					Rectangle rSquare = new Rectangle(eX, eY, 8, 8);
					Rectangle rPiece = new Rectangle(pieceSelect.getPosX(), pieceSelect.getPosY(), 1, 1);
					squareSelect = null;
					rPiece = chessboard[i][j].getRect();
					rSquare = pieceSelect.getRect();
					if (rPiece.intersects(rSquare)) 
					{
						squareSelect = chessboard[i][j];
						
					}
					
					board.repaint();
				}
			}
		});
	}
}

























