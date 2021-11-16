package Main;

import java.util.ArrayList;

public class Queen extends Piece {

	protected boolean color; // 0 is black 1 is white
	protected int row, column;
	public final int[] startRowColumn;
	public final int type = 5;

	public Queen(int r, int c, boolean color) {
		this.row = r;
		this.column = c;
		this.color = color;

		startRowColumn = new int[2];
		startRowColumn[0] = r;
		startRowColumn[1] = c;
	}

	@Override
	public ArrayList<Tile> getValidMoves(Board board) {
		int[] validDirectionsS = { 1, 2, 3, 4 };
		int[] validDirectionsD = { 5, 6, 7, 8 };
		int direction = 0;

		ArrayList<Tile> moves = new ArrayList<>(0);

		for (int i = 0; i < validDirectionsS.length; i++) {
			direction = validDirectionsS[i];

			ArrayList<Tile> submoves = checkDirectionMovesStraight(direction, board);
			for (int j = 0; j < submoves.size(); j++) {
				moves.add(submoves.get(j));
			}
		}

		for (int i = 0; i < validDirectionsD.length; i++) {
			direction = validDirectionsD[i];

			ArrayList<Tile> submoves = checkDirectionMovesDiagonal(direction, board);
			for (int j = 0; j < submoves.size(); j++) {
				moves.add(submoves.get(j));
			}
		}

		return moves;
	}

	private ArrayList<Tile> checkDirectionMovesStraight(int direction, Board board) {

		int r = row;
		int c = column;
		int index = 0;

		// up right down left
		switch (direction) {
		case 1:
			index = this.row;
			break;
		case 2:
			index = this.column;
			break;
		case 3:
			index = this.row;
			break;
		case 4:
			index = this.column;
			break;
		}

		ArrayList<Tile> submoves = new ArrayList<>(0);
		Tile[][] Tset = board.getTileArray();

		while (index >= 0 && index < 8) {

			if (!Tset[r][c].hasPiece()) {
				submoves.add(Tset[r][c]);
			} else if (Tset[r][c].getPiece() == this) {

			} else {
				if (this.color != Tset[r][c].getPiece().getColor()) {
					submoves.add(Tset[r][c]);
				}
				break;
			}

			switch (direction) {
			// Upwards, c is fixed, index decreasing
			case 1:
				index--;
				r = index;
				break;
			// Right, r is fixed index increasing
			case 2:
				index++;
				c = index;
				break;
			// Down, c is fixed index decreasing
			case 3:
				index++;
				r = index;
				break;
			// Left r is fixed, index decreasing
			case 4:
				index--;
				c = index;
				break;
			}

		}
		return submoves;
	}

	private ArrayList<Tile> checkDirectionMovesDiagonal(int direction, Board board) {
		ArrayList<Tile> submoves = new ArrayList<>(0);

		int r = this.row;
		int c = this.column;

		Tile[][] Tset = board.getTileArray();

		while (c >= 0 && c < 8 && r >= 0 && r < 8) {

			if (!Tset[r][c].hasPiece()) {
				submoves.add(Tset[r][c]);
			} else if (Tset[r][c].getPiece() == this) {

			} else {
				if (this.color != Tset[r][c].getPiece().getColor()) {
					submoves.add(Tset[r][c]);
				}
				break;
			}

			// Up-Right, Down-Right,Down-Left, Up-Left
			switch (direction) {
			// Up-Right
			case 5:
				c++;
				r++;
				break;
			// Down Right
			case 6:
				c++;
				r--;
				break;
			// Down Left
			case 7:
				c--;
				r++;
				break;
			// Up Left
			case 8:
				c--;
				r--;
				break;
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
