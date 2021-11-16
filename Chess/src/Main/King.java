package Main;

import java.util.ArrayList;

public class King extends Piece {

	protected boolean color; // 0 is black 1 is white
	protected int row, column;
	public final int[] startRowColumn;
	public final int type = 4;

	public King(int r, int c, boolean color) {
		this.row = r;
		this.column = c;
		this.color = color;

		startRowColumn = new int[2];
		startRowColumn[0] = r;
		startRowColumn[1] = c;
	}

	@Override
	public ArrayList<Tile> getValidMoves(Board board) {
		int[] validDirections = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int direction = 0;

		ArrayList<Tile> moves = new ArrayList<>(0);

		if (checkCastling(board)) {
			moves.add(board.getTileArray()[this.row][7]);

			// board.movePieceCastling(this.row, this.column);
			/*
			 * Tile[][] Tset = board.getTileArray(); board.movePiece(Tset[this.row][7],
			 * Tset[this.row][6]); board.movePiece(Tset[this.row][this.column],
			 * Tset[this.row][7]);
			 */
		}

		for (int i = 0; i < validDirections.length; i++) {
			direction = validDirections[i];
			ArrayList<Tile> submoves = checkDirectionMovesKing(direction, board);
			for (int j = 0; j < submoves.size(); j++) {
				moves.add(submoves.get(j));
			}
		}

		return moves;
	}

	private ArrayList<Tile> checkDirectionMovesKing(int direction, Board board) {

		// Clockwise: Up, right, down, left
		// Up-Right, Down-Right,Down-Left, Up-Left
		int r = this.row;
		int c = this.column;

		switch (direction) {
		case 1:
			r--;
			break;
		case 2:
			c++;
			break;
		case 3:
			r++;
			break;
		case 4:
			c--;
			break;
		case 5:
			r--;
			c++;
			break;
		case 6:
			c++;
			r++;
			break;
		case 7:
			r++;
			c--;
			break;
		case 8:
			r--;
			c--;
			break;
		}

		Tile[][] Tset = board.getTileArray();
		ArrayList<Tile> submoves = new ArrayList<>(0);

		if (c >= 0 && c < 8 && r >= 0 && r < 8) {

			if (!Tset[r][c].hasPiece()) {
				submoves.add(Tset[r][c]);
			} else {
				if (this.color != Tset[r][c].getPiece().getColor()) {
					submoves.add(Tset[r][c]);
				}
			}

		}
		return submoves;

	}

	public boolean checkCastling(Board b) {

		if (startRowColumn[0] != this.row || startRowColumn[1] != this.column) {
			return false;
		}

		Tile[][] Tset = b.getTileArray();
		boolean Rookunmoved = false;
		boolean otherPiecesGone = false;

		// retrieve rook
		Tile T = Tset[this.row][this.column + 3];
		if (T.hasPiece()) {
			int[] S = T.getPiece().getstartRowColumn();
			if (S[0] == T.getRow() && S[1] == T.getColumn()) {
				Rookunmoved = true;
			}
		}

		if (!Tset[this.row][this.column + 2].hasPiece() && !Tset[this.row][this.column + 1].hasPiece()) {
			otherPiecesGone = true;
		}

		if (Rookunmoved && otherPiecesGone) {
			System.out.println("Castling");
			return true;
		}

		return false;

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
		return 4;
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
