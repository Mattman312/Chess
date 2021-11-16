package Main;

import java.util.ArrayList;

public class Pawn extends Piece {

	// Class ID is 0
	private boolean color; // 0 is black 1 is white
	private int row, column;
	public final int[] startRowColumn;

	private int type = 0;

	public Pawn(int r, int c, boolean color) {
		this.row = r;
		this.column = c;
		this.color = color;

		startRowColumn = new int[2];
		startRowColumn[0] = r;
		startRowColumn[1] = c;
	}

	@Override
	public ArrayList<Tile> getValidMoves(Board board) {
		ArrayList<Tile> moves = new ArrayList<>(0);

		int pawndirection;
		if (this.color) {
			pawndirection = -1;
		} else {
			pawndirection = 1;
		}

		Tile[][] Tset = board.getTileArray();

		if (startRowColumn[0] == this.row && startRowColumn[1] == this.column) {
			if (!Tset[this.row + pawndirection][this.column].hasPiece()
					&& !Tset[this.row + (2 * pawndirection)][this.column].hasPiece()) {
				moves.add(Tset[this.row + (2 * pawndirection)][this.column]);

			}
		}

		int r = this.row + pawndirection;
		int c = this.column;

		int index = r;
		if (0 <= index && index < 8) {
			if (!Tset[r][c].hasPiece()) {
				moves.add(Tset[r][c]);
			}
		}

		index = c + pawndirection;
		if (0 <= index && index < 8) {
			if (Tset[r][c + pawndirection].hasPiece()) {
				if (this.color != Tset[r][c + pawndirection].getPiece().getColor()) {
					// System.out.println("called " + r+pawndirection);
					moves.add(Tset[r][c + pawndirection]);
				}
			}
		}

		index = c - pawndirection;
		if (0 <= index && index < 8) {
			if (Tset[r][c - pawndirection].hasPiece()) {
				System.out.println("true");
				if (this.color != Tset[r][c - pawndirection].getPiece().getColor()) {
					// System.out.println("called " + r+pawndirection);
					moves.add(Tset[r][c - pawndirection]);
				}
			}
		}

		return moves;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public boolean getColor() {
		return color;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void updateLocation(int x, int y) {
		this.row = x;
		this.column = y;
	}

	@Override
	public int[] getstartRowColumn() {
		// TODO Auto-generated method stub
		return startRowColumn;
	}

}
