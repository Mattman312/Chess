package Main;

import java.util.ArrayList;

public class Board {
	
	
	@SuppressWarnings("unused")
	private final int numTiles = 64;
	private Tile[][] Square = new Tile[8][8];
	public Tile selectedTile;

	public CheckDetector checkd;

	public final int rows = 8;
	public final int columns = 8;

	public boolean currentPlayer = true;
	public boolean isWinner = false;
	public boolean WinningPlayer;
	
	public boolean inCheck = false;

	// center the board if sizeX/Y is not integer
	public Board() {
		Square = new Tile[8][8];
		for (int i = 0; i < Square.length; i++) {
			for (int j = 0; j < Square[i].length; j++) {
				Square[i][j] = new Tile(i, j);
			}
		}
		checkd = new CheckDetector(this);

		initBoard();
	}

	// Will have to not use Piece constructor, need to construct individually (king,
	// queen, etc)
	// false = black true = white
	public void initBoard() {

		int[] BlackPawns = { 0, 1, 2, 3, 4, 5, 6, 7 };
		int[] WhitePawns = { 0, 1, 2, 3, 4, 5, 6, 7 };
		int[] BlackRooks = { 0, 7 };
		int[] WhiteRooks = { 0, 7 };
		int[] BlackBishops = { 2, 5 };
		int[] WhiteBishops = { 2, 5 };
		int[] WhitesKnights = { 1, 6 };
		int[] BlackKnights = { 1, 6 };
		int[] BlackKings = { 4 };
		int[] WhiteKings = { 4 };
		int[] WhiteQueens = { 3 };
		int[] BlackQueens = { 3 };

		// All pieces on rows 0 and 7.. pawns on 1 and 6
		for (int whitePawn : WhitePawns) {
			Pawn p = new Pawn(6, whitePawn, true);
			Square[6][whitePawn].addPiece(p);
		}
		for (int blackPawn : BlackPawns) {
			Pawn p = new Pawn(1, blackPawn, false);
			Square[1][blackPawn].addPiece(p);
		}

		for (int whiteRook : WhiteRooks) {
			Rook p = new Rook(7, whiteRook, true);
			Square[7][whiteRook].addPiece(p);
		}
		for (int blackRook : BlackRooks) {
			Rook p = new Rook(0, blackRook, false);
			Square[0][blackRook].addPiece(p);
		}

		for (int whiteBishop : WhiteBishops) {
			Bishop p = new Bishop(7, whiteBishop, true);
			Square[7][whiteBishop].addPiece(p);
		}
		for (int blackBishop : BlackBishops) {
			Bishop p = new Bishop(0, blackBishop, false);
			Square[0][blackBishop].addPiece(p);
		}

		for (int whitesKnight : WhitesKnights) {
			Knight p = new Knight(7, whitesKnight, true);
			Square[7][whitesKnight].addPiece(p);
		}
		for (int blackKnight : BlackKnights) {
			Knight p = new Knight(0, blackKnight, false);
			Square[0][blackKnight].addPiece(p);
		}

		for (int blackKing : BlackKings) {
			King p = new King(0, blackKing, false);
			Square[0][blackKing].addPiece(p);
		}
		for (int whiteKing : WhiteKings) {
			King p = new King(7, whiteKing, true);
			Square[7][whiteKing].addPiece(p);
		}

		// Queens

		for (int blackQueen : BlackQueens) {
			Queen p = new Queen(0, blackQueen, false);
			Square[0][blackQueen].addPiece(p);
		}

		for (int whiteQueen : WhiteQueens) {
			Queen p = new Queen(7, whiteQueen, true);
			Square[7][whiteQueen].addPiece(p);
		}

	}

	public int convertToTileID(int r, int c) {
		int ID = 0;
		ID = r * 8 + c;
		// System.out.println("Tile ID: " + ID);
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
		int targetRow = (int) Math.floor(y / TileSize);
		int targetColumn = (x / TileSize) % 8;
		this.selectedTile = Square[targetRow][targetColumn];
	}

	public ArrayList<Piece> findPieceLocations() {
		ArrayList<Piece> locations = new ArrayList<>(0);

		for (Tile[] element : Square) {
			for (int j = 0; j < element.length; j++) {
				if (element[j].hasPiece()) {
					locations.add(element[j].getPiece());
				}
			}
		}

		return locations;
	}

	

	public void undoMove(Tile[][] Tilegrid, Tile start, Tile finish, Piece removedPiece) {
		Piece p1 = finish.getPiece();
		
		finish.removePiece();
		finish.addPiece(removedPiece);
		
		start.removePiece();
		start.addPiece(p1);
		p1.updateLocation(start.getRow(), start.getColumn());
		
	}
	
	public void movePiece(Tile start, Tile finish) {
		
		Tile[][] tempboard = Square;
		Piece removedPiece = null;
		
		//Castling
		Piece p = start.getPiece();
		if (p.getType() == 4 && finish.hasPiece()) {
			if (p.getColor() == finish.getPiece().getColor()) {

				Piece rook = finish.getPiece();
				start.removePiece();
				if (finish.hasPiece()) {
					removedPiece = finish.getPiece();
					finish.removePiece();
				}

				finish.addPiece(p);
				p.updateLocation(finish.getRow(), finish.getColumn());

				Square[start.getRow()][6].addPiece(rook);
				rook.updateLocation(start.getRow(), 6);
				currentPlayer = !currentPlayer;

				return;
			}
		}

		start.removePiece();
		if (finish.hasPiece()) {
			if (finish.getPiece().getType() == 4) {
				if (finish.getPiece().getColor()) {
					WinningPlayer = true;
				} else {
					WinningPlayer = false;
				}
				isWinner = true;
			}
			removedPiece = finish.getPiece();
			finish.removePiece();
		}
		finish.addPiece(p);
		p.updateLocation(finish.getRow(), finish.getColumn());

		if(checkd.dectectCheck()) {
			inCheck = true;
			undoMove(tempboard, start, finish, removedPiece);
			return;
		}else {
			inCheck = false;
		}
		
		
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
