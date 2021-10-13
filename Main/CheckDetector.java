package Main;

import java.util.ArrayList;

public class CheckDetector {
	
	private Board board;
	private Tile[][] Tileset;
//	private ArrayList<Piece> pieces;
	
	
	public CheckDetector(Board b){
		this.board = b;
		Tileset = board.getTileArray();
		
	}
	
	public boolean dectectCheck(Tile start, Tile finish) {
		boolean isCheck = false;
		ArrayList<Tile> Moveset = new ArrayList<Tile>(0);
		ArrayList<Piece> pieces = board.findPieceLocations();
		
		
		Piece startPiece = start.getPiece();

		Piece king = new King(0,0, false);		
		
		for(int i=0; i<pieces.size();i++) {
			if(pieces.get(i).getType() == 4 &&  pieces.get(i).getColor() == start.getPiece().getColor()) {
				king = pieces.get(i);
				//System.out.println("k row: " + king.getRow() + "k column: " + king.getColumn());
			}
		}
		
		
		for(int i=0;i<pieces.size();i++) {
			if(startPiece.getColor() != pieces.get(i).getColor()) {
				Moveset = pieces.get(i).getValidMoves(board);
				
				//iterate through list to see if this player king in danger
				for(int j=0; j<Moveset.size();j++) {
					//System.out.println("Moveset row: " + Moveset.get(j).getRow() + " Moveset Column: " + Moveset.get(j).getColumn());
					if(Moveset.get(j).getRow() == king.getRow() && Moveset.get(j).getColumn() == king.getColumn()) {
						
						System.out.println("You are in check");
						isCheck = true;
						return true;
					}
					
				}
				
				
				
			}
		}
		
		return isCheck;
		
	}
	
	
	
	
	
	

}
