package com.myowndev.main;

import javax.swing.JFrame;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		new Main();
	}
	
	public Main() {
		add(new Panel());
		setSize(560, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Программа калибровки данных СП-9 по измерениям Аэронет");
		setDefaultCloseOperation(3);
		setVisible(true);
	}
}









































































































