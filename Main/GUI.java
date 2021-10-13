package Main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel implements MouseListener {

	private State currentState;
	
	private int TileSize;
	private int sizeX, sizeY;
	Dimension d = new Dimension(512,512);
	Dimension dFrame = new Dimension(720,720);
	
	private boolean hastileSelected = false;
	//0 is black 1 is white
	//private boolean currentPlayer = true;
	
	BufferedImage imagePieces[][];
	//BufferedImage shrunkimagePieces[][];
	

	//private Tile SelectedTile;
	
	public GUI(State s) {
		
		this.currentState = s;
		JFrame Frame = new JFrame();
		
		Frame.setVisible(true);
		Frame.setPreferredSize(dFrame);;

		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setTitle("Chess");
		Frame.pack();
		Frame.setVisible(true);
		
		Frame.add(this,BorderLayout.CENTER);
		
		addMouseListener(this);	
		
		this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		this.setLayout(new GridLayout(0,1));
		this.setBackground(Color.black);		
		
		initGUI();
		repaint();
	}
	
	private void initGUI() {
		loadImages();
	}
	
	private void loadImages() {
		//1200x400
		
		imagePieces = new BufferedImage[2][6];
		BufferedImage imageA = null;
		try {
			imageA = ImageIO.read(new File("Images/Chess_Pieces.png"));
			if(imageA == null) {
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//0 is black 1 is white
		//IDs:
		/*0 = Pawn
		 * 1=Rook
		 * 2=Knight
		 * 3=Bishop
		 * 4=King
		 * 5=Queen
		 */
		
		int initHeight = 400; 
		int initWidth = 1200;
		
		int Height = initHeight/2;
		int Width = initWidth/6;
		//try {
		for(int a=0; a<imagePieces.length;a++) {
			for(int b=0;b<imagePieces[a].length;b++) {

				imagePieces[1-a][5-b] = imageA.getSubimage(b*Width, a*Height,Width,Height);
			}
		}
		//}catch(Exception e) {}
	}
	
	public void setState(State s) {
		this.currentState = s;
	}
	
	
	//Graphics
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d =(Graphics2D)g;
		drawBackground(g2d);
		drawScreen(g2d);
		drawPlayerString(g2d);
		if(currentState.getBoard().isWinner()) {			
			drawWinnerString(currentState.getBoard().getWinner(),g2d);
		}
	}
	
	public void drawScreen(Graphics2D g2) {
		switch(this.currentState.getStateID()) {
			//Drawmenu
			case 0: //System.out.println("drawSceen case 0");
					return;
			//Draw basic game
			case 1: //System.out.println("drawScreen case 1");
					initBoard();    //This only needs to be called once, not every time with paintComponent
					drawBoard(g2);
					drawPieces(this.currentState.getBoard(), g2);
					return;
		}
	}
	
	private void drawBackground(Graphics2D g2) {
		g2.setColor(new Color(102,51,0));
		g2.fillRect(0, 0, d.width, d.height);

	}
	

	
	
	private void initBoard() {
		try {
			sizeX =  Math.round(d.width / this.currentState.getBoard().getRows());
			sizeY = Math.round(d.height / this.currentState.getBoard().getColumns());
			TileSize = Math.min(sizeX,sizeY);
			
			
			}catch(Exception e) {
				System.out.println("Board is null!");
				e.printStackTrace();
				}
	}
	
	private void drawBoard(Graphics2D g2) {
		g2.setColor(Color.gray);
		g2.drawRect(0, 0, TileSize, TileSize);
		
		int rows = this.currentState.getBoard().getRows();
		int columns =this.currentState.getBoard().columns;
		
		g2.setColor(Color.GRAY);
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if((i+j) % 8 % 2 == 0) {
					g2.fillRect(i*TileSize, j*TileSize, TileSize, TileSize);
				}
			}
		}
		
		
		
		if(hastileSelected) {
			
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(5));
			
			Tile T = currentState.getBoard().getselectedTile();

			
			
		
			if(currentState.getBoard().selectedTile.hasPiece() && currentState.getBoard().selectedTile.getPiece().getColor() == currentState.getBoard().currentPlayer) {
				this.hastileSelected = true;
				ArrayList<Tile> Moves = currentState.getBoard().selectedTile.getPiece().getValidMoves(currentState.getBoard());
				
				g2.setColor(Color.pink);
				for(int i=0; i<Moves.size();i++) {
					g2.drawRect(Moves.get(i).column*TileSize, Moves.get(i).row*TileSize, TileSize, TileSize);
				}
				
			}
			else {
				this.hastileSelected = false;
			}
			
			g2.setColor(Color.green);
			g2.drawRect(T.column*TileSize, T.row*TileSize, TileSize, TileSize);
			
			g2.setStroke(oldStroke);
			
			
		}
		
	}
	
	//TODO Change from tiles to piece, have board retrieve where the pieces are
	private void drawPieces(Board b, Graphics2D g2) {
		g2.setColor(Color.black);
		//Tile[][] T = b.findPieceLocations();
	
		ArrayList<Piece> P = b.findPieceLocations();
		
		int a1 = 0;
		int a2 =0;
		
		for(int i=0; i<P.size();i++){

				if(P.get(i) != null) {
					if(P.get(i).getColor()) {g2.setColor(Color.white);}
						//System.out.println("Draw: " + T[i][i].getColumn()*TileSize + " , " + T[i][i].getRow() + " Value: " + T[i][i].getPiece().getColor());
						//g2.fillRect(T[i][i].getColumn()*TileSize, T[i][i].getRow()*TileSize, TileSize/2, TileSize/2);
											
						if(P.get(i).getColor()) {a1=1;}
						else {a1=0;}
						a2 = P.get(i).getType();
						
						
						g2.drawImage(imagePieces[a1][a2], P.get(i).getColumn()*TileSize, P.get(i).getRow()*TileSize, TileSize, TileSize, null);
						g2.setColor(Color.black);
				}
			
		}
			
	}
	
	private void drawPlayerString( Graphics2D g2d) {
	 
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		g2d.setColor(Color.white);
		String currentPlayer = " ";
		if(currentState.getBoard().currentPlayer == true) {
			currentPlayer = "White";
		}else {
			currentPlayer = "Black";
		}
		g2d.drawString("Current Player: " + currentPlayer ,200 , d.width+50);
	}
	
	private void drawWinnerString(boolean winner, Graphics2D g2d) {
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		g2d.setColor(Color.white);
		String currentWinner = " ";
		if(!currentState.getBoard().getWinner()) {
			currentWinner = "White ";
		}else {
			currentWinner = "Black ";
		}
		g2d.drawString(currentWinner + "is the Winner!" ,200 , d.width+75);
	}
	
	private void selectTile(int X, int Y) {
		currentState.getBoard().setselectedTile(X,Y, TileSize);
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {	
		// TODO Auto-generated method stub
	
		if(e.getX() < d.width && e.getY() < d.height) {
			
			if(!hastileSelected==true) {  //tile is not selected, select it
				selectTile(e.getX(),e.getY());
				hastileSelected = true;
							
			}else {
				if(currentState.getBoard().getselectedTile().hasPiece()) {
					ArrayList<Tile> T = currentState.getBoard().getselectedTile().getPiece().getValidMoves(currentState.getBoard());
					int targetRow = (int) Math.floor(e.getY()/TileSize);
					int targetColumn = (e.getX()/TileSize)%8;
					

					
					Tile[][] temp = currentState.getBoard().getTileArray();
					Tile AttemptedTile = temp[targetRow][targetColumn];
					for(int i=0; i<T.size();i++) {
						if( T.get(i).getRow() == AttemptedTile.getRow() && AttemptedTile.getColumn() == T.get(i).getColumn()) {
							currentState.getBoard().movePiece(currentState.getBoard().getselectedTile(), AttemptedTile);
							break;
						}
					}
				}
				
				
				hastileSelected=false;
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	}


	
			
	
	
	
	
	
	
	

