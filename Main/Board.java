package Main;


import java.util.ArrayList;


public class Board {

	private int numTiles = 64; //0 is top left corner, going left to right then down
	private Tile[][] Square = new Tile[8][8];
	public Tile selectedTile;
	
	public CheckDetector checkd;
	
	public final int rows = 8;
	public final int columns = 8;
	
	public boolean currentPlayer = true;
	public boolean isWinner=false;
	public boolean WinningPlayer;

	
	
	//center the board if sizeX/Y is not integer
	public Board() {
		Square = new Tile[8][8];
		for(int i=0; i<Square.length; i++) {
			for(int j=0; j<Square[i].length; j++) {
				Square[i][j] = new Tile(i,j);
			}			
		}
		checkd = new CheckDetector(this);
		
		initBoard();
	}
	
	
	//Will have to not use Piece constructor, need to construct individually (king, queen, etc)
	//false = black true = white
	public void initBoard() {
		
		int[] BlackPawns = {0,1,2,3,4,5,6,7};
		int[] WhitePawns = {0,1,2,3,4,5,6,7};
		int[] BlackRooks = {0,7};
		int[] WhiteRooks = {0,7};
		int[] BlackBishops = {2,5};
		int[] WhiteBishops = {2,5};
		int[] WhitesKnights = {1,6};
		int[] BlackKnights = {1,6};
		int[] BlackKings = {4};
		int[] WhiteKings= {4};
		int[] WhiteQueens = {3};
		int[] BlackQueens = {3};
		
		//All pieces on rows 0 and 7.. pawns on 1 and 6
		for(int c=0; c<WhitePawns.length;c++) {
			Pawn p = new Pawn(6,WhitePawns[c], true);
			Square[6][WhitePawns[c]].addPiece(p);
		}
		for(int c=0; c<BlackPawns.length;c++) {
			Pawn p = new Pawn(1, BlackPawns[c], false);
			Square[1][BlackPawns[c]].addPiece(p);
		}
		
		
		for(int c=0; c<WhiteRooks.length;c++) {
			Rook p = new Rook(7,WhiteRooks[c], true);
			Square[7][WhiteRooks[c]].addPiece(p);
		}
		for(int c=0; c<BlackRooks.length;c++) {
			Rook p = new Rook(0, BlackRooks[c], false);
			Square[0][BlackRooks[c]].addPiece(p);
		}
		
		for(int c=0; c<WhiteBishops.length;c++) {
			Bishop p = new Bishop(7,WhiteBishops[c], true);
			Square[7][WhiteBishops[c]].addPiece(p);
		}		
		for(int c=0; c<BlackBishops.length;c++) {
			Bishop p = new Bishop(0, BlackBishops[c], false);
			Square[0][BlackBishops[c]].addPiece(p);
		}
		
		for(int c=0; c<WhitesKnights.length;c++) {
			Knight p = new Knight(7,WhitesKnights[c], true);
			Square[7][WhitesKnights[c]].addPiece(p);
		}		
		for(int c=0; c<BlackKnights.length;c++) {
			Knight p = new Knight(0, BlackKnights[c], false);
			Square[0][BlackKnights[c]].addPiece(p);
		}
		
		
		for(int c=0; c<BlackKings.length;c++) {
			King p = new King(0, BlackKings[c], false);
			Square[0][BlackKings[c]].addPiece(p);
		}
		for(int c=0; c<WhiteKings.length;c++) {
			King p = new King(7, WhiteKings[c], true);
			Square[7][WhiteKings[c]].addPiece(p);
		}
		
		
		
		
		//Queens
	
		
		for(int c=0; c<BlackQueens.length;c++) {
			Queen p = new Queen(0, BlackQueens[c], false);
			Square[0][BlackQueens[c]].addPiece(p);
		}
		
		for(int c=0; c<WhiteQueens.length;c++) {
			Queen p = new Queen(7, WhiteQueens[c], true);
			Square[7][WhiteQueens[c]].addPiece(p);
		}
		
		
		
	}	
		
		
		
	
	public int convertToTileID(int r, int c){
		int ID = 0;
		ID = r*8 + c;
		//System.out.println("Tile ID: " + ID);
		return ID;
	}
	
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Tile getselectedTile() {
		return selectedTile;
	}
	
	public Tile[][] getTileArray() {
		return this.Square;
	}
	
	
	
	public void setselectedTile(int x, int y, int TileSize) {
			int targetRow = (int) Math.floor(y/TileSize);
			int targetColumn = (x/TileSize)%8;
			this.selectedTile = Square[targetRow][targetColumn];
	}
	

	
	public ArrayList<Piece>findPieceLocations(){
		ArrayList<Piece> locations = new ArrayList<Piece>(0);
		
		int indexA = 0;
		int indexB = 0;
		
		for(int i=0;i<Square.length;i++) {
			for(int j=0; j<Square[i].length;j++) {
				if(Square[i][j].hasPiece()) {
					locations.add(Square[i][j].getPiece());
				}
			}
		}
		
		
		return locations;
	}
	
	

	
	public void takePiece() {
		
	}
	
	public void movePiece(Tile start, Tile finish) {
		/*
		if(checkd.dectectCheck(start, finish)) {
			System.out.println("You are in check A");
			return;
		}
		*/
		
		//System.out.println("movePiece called");
		Piece p = start.getPiece();
		if(p.getType()==4 && finish.hasPiece()) {
			System.out.println("a");
			if(p.getColor() == finish.getPiece().getColor()) {
				System.out.println("b");
				//movePieceCastling(start.getRow(),start.getColumn());
				Piece rook = finish.getPiece();
				start.removePiece();
				if(finish.hasPiece()) {
					finish.removePiece();
				}
				
				finish.addPiece(p);
				p.updateLocation(finish.getRow(),finish.getColumn());
				
				
				Square[start.getRow()][6].addPiece(rook);				
				rook.updateLocation(start.getRow(), 7);
				currentPlayer = !currentPlayer;
				
				return;
			}
		}
		
		start.removePiece();
		if(finish.hasPiece()) {
			if(finish.getPiece().getType() == 4) {			
				if(finish.getPiece().getColor()) {
					WinningPlayer = true;
				}else {
					WinningPlayer = false;
				}
				isWinner = true;
			}
			finish.removePiece();
		}
		finish.addPiece(p);
		p.updateLocation(finish.getRow(),finish.getColumn());
		
		currentPlayer = !currentPlayer;
	}
	
	public void movePieceCastling(int row, int column) {
		this.movePiece(Square[row][7], Square[row][6]);
		this.movePiece(Square[row][column], Square[row][7]);	
	}
		
	public boolean isWinner() {
		return isWinner;
	}
	
	public boolean getWinner() {
		return WinningPlayer;
	}
	


}
	
	
	
	

