package Main;

import java.awt.EventQueue;
import java.io.IOException;

public class Main {
	
	static WelcomeWindow welcome_window;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//ReadCountries.flagOfMap = 0;
		getWelcomeWindow();
		
	}

	private static void getWelcomeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome_window = new WelcomeWindow();
					welcome_window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
