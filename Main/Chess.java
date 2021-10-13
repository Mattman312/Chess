package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Chess {

	private static int currentState = 1;
	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Statemachine StateManager = new Statemachine();
		StateManager.setState(1);
		GUI window = new GUI(StateManager.getState());
		window.setState(StateManager.getState());
		
		//StateManager.setState(1);
		//System.out.println("State: " + StateManager.getStateID());
		//window.setState(StateManager.getState());

		
		
		
	}

}
