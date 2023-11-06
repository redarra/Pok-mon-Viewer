package View.Pokemon_Viewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;

public class MyMouseListener extends MouseAdapter {
	private PokemonGrid pokeGrid;

	public MyMouseListener(PokemonGrid pokeGrid) {
		this.pokeGrid = pokeGrid;
	}
//handle mouse clicks that happen on the panel
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			try {
				pokeGrid.labelPressed((JLabel) e.getSource());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}