package Main;


public class Chess{

	public static void main(String[] args) {


		Statemachine StateManager = new Statemachine();
		StateManager.setState(1);
		GUI window = new GUI(StateManager.getState());
		window.setState(StateManager.getState());


	}

}
