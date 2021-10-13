package Main;

public class Statemachine {

	private int numStates = 2;
	private State[] States;
	private State currentState;
	
	
	
	
	public Statemachine() {
		States = new State[numStates];
		for(int i=0; i<numStates;i++) {
			States[i] = new State(i);
		}
		
		this.currentState = States[1];
		//main menu, actual game
	}
	
	public void setState(int i) {
		this.currentState = States[i];
	}
	
	public State getState() {
		return currentState;
	}
	
	public int getStateID() {
		return currentState.getStateID();
	}
}
