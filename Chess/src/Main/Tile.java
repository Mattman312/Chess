package Main;

public class Tile {

	public int TileID;
	private Piece current_piece;
	public int row, column;

	public Tile(int r, int c) {
		this.column = c;
		this.row = r;
		this.TileID = r * 8 + c;
	}

	public boolean hasPiece() {
		if (this.current_piece != null) {
			return true;
		}
		return false;
	}

	public Piece getPiece() {
		return current_piece;
	}

	public void setID(int i) {
		this.TileID = i;
	}

	public int getID() {
		return this.TileID;
	}

	public void addPiece(Piece p) {
		this.current_piece = p;
	}

	public void removePiece() {
		this.current_piece = null;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}