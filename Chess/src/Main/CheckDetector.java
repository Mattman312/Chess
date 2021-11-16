package Main;

import java.util.ArrayList;

public class CheckDetector {

	private Board board;

	public CheckDetector(Board b) {
		this.board = b;

	}
	
	public boolean dectectCheck() {
		boolean isCheck = false;
		ArrayList<Tile> Moveset = new ArrayList<>(0);
		ArrayList<Piece> pieces = board.findPieceLocations();

		Piece king = new King(0, 0, false);
		
		for (Piece element : pieces) {
			if (element.getType() == 4 && element.getColor() == board.currentPlayer){
				king = element;
			}
		}
		

		for (Piece element : pieces) {

				Moveset = element.getValidMoves(board);

				// iterate through list to see if this player king in danger
				for (Tile element2 : Moveset) {
					if (element2.getRow() == king.getRow() && element2.getColumn() == king.getColumn()) {
						isCheck = true;
						return true;
					}

				}

			}

		return isCheck;

	}

}
