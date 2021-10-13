package Main;

import java.util.ArrayList;

public class Rook extends Piece{
	
	protected boolean color; //0 is black 1 is white
	protected int row, column;
	public final int type = 1;
	public final int[] startRowColumn;
	
	public Rook(int r, int c, boolean color) {
		this.row = r;
		this.column = c;
		this.color = color;
		
		startRowColumn = new int[2];
		startRowColumn[0] = r;
		startRowColumn[1] = c;
	}
	
	//Check same row and columns
	public ArrayList<Tile> getValidMoves(Board board) {
		ArrayList<Tile>moves = new ArrayList<Tile>(0);
	
		//Clockwise: Up, right, down, left
		int[] validDirections = {1,2,3,4};
		int direction = 1;
		
		for(int i=0; i<validDirections.length;i++) {
			direction = validDirections[i];
			ArrayList<Tile> submoves = checkDirectionMovesStraight(direction,board);
				for(int j=0;j<submoves.size();j++) {
					moves.add(submoves.get(j));
			}
		}	
		return moves;
	}
	
				
	private ArrayList<Tile> checkDirectionMovesStraight(int direction, Board board){
					
			int r = row;
			int c = column;
			int index=0;
					
					
			//up right down left
			switch(direction) {
				case 1: index=this.row; break;
				case 2: index = this.column; break;
				case 3: index =this.row; break;
				case 4: index=this.column; break;
			}
				
			ArrayList<Tile>submoves = new ArrayList<Tile>(0);
			Tile[][] Tset = board.getTileArray();
		
			while(index>=0 && index < 8) {
		
				if(!Tset[r][c].hasPiece()) {
					submoves.add(Tset[r][c]);
				}			
				else if(Tset[r][c].getPiece()==this){
					
				}		
				else {
					if(this.color != Tset[r][c].getPiece().getColor()){
						submoves.add(Tset[r][c]);						
						}
					break;
				}
						
				switch(direction) {
				//Upwards, c is fixed, index decreasing
				case 1:	index--;
						r=index;
						break;
				//Right, r is fixed index increasing
				case 2: index++;
						c=index;					
						break;
				//Down, c is fixed index decreasing
				case 3: index++;
						r=index;
						break;
				//Left r is fixed, index decreasing
				case 4: index--; 
						c=index;						
						break;
						}
						
			}
					return submoves;
		}				

		
		
	
	

	
	
	
	
	public int getColumn() {
		return column;	
	}
	
	public int getRow() {
		return row;
	}
	
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
		return startRowColumn;
	}
	
	
}
