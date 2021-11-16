package Main;

public class State {

	private int StateID;
	private Board board;

	public State(int id) {

		this.StateID = id;

		if (StateID == 0) {
		} // main menu
		if (StateID == 1) {
			this.board = new Board();
		} // create new board
	}

	public int getStateID() {
		return StateID;
	}

	public Board getBoard() {
		return board;
	}
}
