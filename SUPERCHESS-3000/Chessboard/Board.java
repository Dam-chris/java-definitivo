import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class Board extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private final int rows = 8;
    private final int cols = 8;
	private Square[][] arraySquares;
	private ArrayList<Piece> whitePieces;
	private ArrayList<Piece> blackPieces;
	private int[][] BoardGrid;
	private Graphics2D g2D;
	private Graphics virtualScreen;
	private Image buffer;
	//contructor para pasar el tablero
	public Board(Square[][] squares) {
		this.arraySquares = squares;
	}
	//constructor
	public Board(Chess chess) {
		this.setBounds(18, 16, 801, 801);
		this.width = this.getWidth()/8;
		this.height = this.getHeight()/8;
		this.createBoard();
		
		//eventos de chessboard
		new ChessBoardEvents(this);
	}

		
	//methods
	@Override
	public void paint(Graphics g) {
		g2D = (Graphics2D) g;
		
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		super.paint(g2D);
		//dibujar tablero
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{
				arraySquares[i][j].draw(g2D);
			}
		}
		//dibujar piezas
		for (int i = 0; i < whitePieces.size(); i++) 
		{
			whitePieces.get(i).draw(g2D);
			blackPieces.get(i).draw(g2D);
		}
		
		
	}
	// buffered
	public void update(Graphics g) {
	
		buffer = createImage(this.getWidth(), this.getHeight());
		virtualScreen = buffer.getGraphics();
		paint(virtualScreen);
		g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	public void initGridPieces() {
		//para ia
		BoardGrid = new int[rows][cols];
		for (int i = 0; i < rows / 2; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                BoardGrid[i][j] = 0;
                if (j%8 == 0) {
					//System.out.println();
				}
                //System.out.print(BoardGrid[i][j]);
            }
        }
		
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		
		blackPieces.add(new Rook(0*width, 0, false,"br.png", this, false));
		blackPieces.add(new Knight(1*width, 0, false,"bn.png", this, false));
		blackPieces.add(new Bishop(2*width, 0, false,"bb.png", this, false));
		blackPieces.add(new Queen(3*width, 0, false,"bq.png", this, false));
		blackPieces.add(new King(4*width, 0, false,"bk.png", this, false));
		blackPieces.add(new Bishop(5*width, 0, false,"bb.png", this, false));
		blackPieces.add(new Knight(6*width, 0, false,"bn.png", this, false));
		blackPieces.add(new Rook(7*width, 0, false,"br.png", this, false));
		for (int i = 0; i < cols; i++) 
		{
			blackPieces.add(new Pawn(i*width, 1*height, false,"bp.png", this, false));
		}
		for (int i = 0; i < cols; i++) 
		{
			whitePieces.add(new Pawn(i*width, 6*height, true,"wp.png", this, false));
		}
		whitePieces.add(new Rook(0*width, 7*height, true,"wr.png", this, false));
		whitePieces.add(new Knight(1*width, 7*height, true,"wn.png", this, false));
		whitePieces.add(new Bishop(2*width, 7*height, true,"wb.png", this, false));
		whitePieces.add(new Queen(3*width, 7*height, true,"wq.png", this, false));
		whitePieces.add(new King(4*width, 7*height, true,"wk.png", this, false));
		whitePieces.add(new Bishop(5*width, 7*height, true,"wb.png", this, false));
		whitePieces.add(new Knight(6*width, 7*height, true,"wn.png", this, false));
		whitePieces.add(new Rook(7*width, 7*height, true,"wr.png", this, false));
		
	}
	public void createBoard() {
		//Añdir piezas al tablero
		initGridPieces();
		arraySquares = new Square[rows][cols];
		int corX = 0;
		int corY = 0;
		boolean isWhite = true;
		char letters;
		int conB = -1;
		int conW = -1;
		String letter = "";
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{
				corX = j*width;
				corY = i*height;
				letters = (char) (j+'A');
				
				switch (i) 
				{
				case 0:
				case 1: conB++;
						arraySquares[i][j] = new Square(corX, corY, blackPieces.get(conB), isWhite, "", "");	
					break;
				case 2: 
				case 3: 
				case 4: 
				case 5: arraySquares[i][j] = new Square(corX, corY, null, isWhite, "", "");
					break;
				case 6: 
				case 7: conW++; 
						arraySquares[i][j] = new Square(corX, corY, whitePieces.get(conW), isWhite, "", "");	
					break;
				}
				//para la numeracion y las letras laterales del tablero
				if (i == 7) 
				{
					arraySquares[i][j] = new Square(corX, corY, arraySquares[i][j].getPiece(), isWhite, letters + "", "");	
					 letter = "A";
				}
				if(j == 0) 
				{
					arraySquares[i][j] = new Square(corX, corY, arraySquares[i][j].getPiece(), isWhite, letter, (8-i) + "");
				}
				//control de cambio de color en las caillas
				isWhite = !isWhite;
			}
			isWhite = !isWhite;
		}

	}
	public Square[][] getArraySquares() {
		return arraySquares;
	}
	public void setArraySquares(Square[][] arraySquares) {
		this.arraySquares = arraySquares;
	}
	public ArrayList<Piece> getWhitePieces() {
		return whitePieces;
	}
	public void setWhitePieces(ArrayList<Piece> whitePieces) {
		this.whitePieces = whitePieces;
	}
	public ArrayList<Piece> getBlackPieces() {
		return blackPieces;
	}
	public void setBlackPieces(ArrayList<Piece> blackPieces) {
		this.blackPieces = blackPieces;
	}
	
}




















