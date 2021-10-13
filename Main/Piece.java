package Main;

import java.util.ArrayList;

public abstract class Piece {

	
	protected boolean color; //0 is black 1 is white
	protected int row, column;
	
	
	
	/*0 = Pawn
	 * 1=Rook
	 * 2=Knight
	 * 3=Bishop
	 * 4=King
	 * 5=Queen
	 */

	
	
	
	
	public abstract ArrayList<Tile> getValidMoves(Board board);

	public abstract int getColumn();
	public abstract int getRow();	
	public abstract boolean getColor();
	public abstract int getType();
	public abstract void updateLocation(int x, int y);
	public abstract int[] getstartRowColumn();
	
}
