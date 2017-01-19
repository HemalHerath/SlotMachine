package GUI;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[]args){
		Reel object=new Reel();
		object.setTitle("Slot Machine");
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.setSize(800,600);
		object.setLocationRelativeTo(null);
	    object.setResizable(true);
		object.setVisible(true);
	}

}
