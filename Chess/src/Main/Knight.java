package Main;

import java.util.ArrayList;

public class Knight extends Piece {

	protected boolean color; // 0 is black 1 is white
	protected int row, column;
	public final int type = 2;
	public final int[] startRowColumn;

	public Knight(int r, int c, boolean color) {
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

		// Clockwise: Up, right, down, left
		int[] validDirections = { 1, 2, 3, 4 };
		int direction = 1;

		for (int validDirection : validDirections) {
			direction = validDirection;
			ArrayList<Tile> submoves = checkDirectionMovesKnight(direction, board);
			for (int j = 0; j < submoves.size(); j++) {
				moves.add(submoves.get(j));
			}
		}
		return moves;
	}

	private ArrayList<Tile> checkDirectionMovesKnight(int direction, Board board) {
		ArrayList<Tile> submoves = new ArrayList<>(0);

		int r = this.row;
		int c = this.column;

		/*
		 * Valid Knight moves r=-2, c=1,-1 r=-1, c=2,-2 r=1, c=2,-2 r=2, c=1,-1
		 */
		int[] validRows = { -2, -1, 1, 2 };
		int[] validColumns = { 1, -1, 2, -2, 2, -2, 1, -1 };

		int index = 0;

		Tile[][] Tset = board.getTileArray();

		for (int i = 0; i < validRows.length; i++) {
			index = 2 * i;
			r = this.row + validRows[i];
			for (int j = 0; j < 2; j++) {
				c = this.column + validColumns[index + j];
				if (0 <= r && r < 8 && 0 <= c && c < 8) {
					if (!Tset[r][c].hasPiece()) {
						submoves.add(Tset[r][c]);
					} else if (Tset[r][c].getPiece() == this) {

					} else {
						if (this.color != Tset[r][c].getPiece().getColor()) {
							submoves.add(Tset[r][c]);
						}
					}

				}

			}
		}
		return submoves;
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
		return 2;
	}

	@Override
	public void updateLocation(int x, int y) {
		this.row = x;
		this.column = y;
	}

	@Override
	public int[] getstartRowColumn() {
		return startRowColumn;
	}

}
