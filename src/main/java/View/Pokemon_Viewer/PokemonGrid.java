package View.Pokemon_Viewer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.Pokemon_Viewer.PokemonController;

@SuppressWarnings("serial")
public class PokemonGrid extends JPanel {
	// private MyColor[][] myColors;
	private JLabel[][] myLabels;
	private PokemonController controller;
	

	public PokemonGrid(int rows, int cols, int cellWidth, PokemonController controller1) {
		controller = controller1;
		// myColors = new MyColor[rows][cols];
		myLabels = new JLabel[rows][cols];

		MyMouseListener myListener = new MyMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		setLayout(new GridLayout(rows, cols));

		for (int row = 0; row < myLabels.length; row++) {
			
			for (int col = 0; col < myLabels[row].length; col++) {
		
				JLabel myLabel = new JLabel();
				if ((row + 1) % 2 == 0) {
					if(controller.pokeList.size()>((row - 1) / 2 * 4 + col + controller.getIndex())){
					try {
						URL url = new URL(
								controller.getPokeList().get((row - 1) / 2 * 4 + col + controller.getIndex()).imageUrl);
						Image img = ImageIO.read(url);
						myLabel = new JLabel(new ImageIcon(img));
					} catch (IOException e) {
					}}
				} else {
					if(controller.pokeList.size()>(row / 2 * 4 + col + controller.getIndex())){
					myLabel = new JLabel(controller.getPokeList().get(row / 2 * 4 + col + controller.getIndex()).name);}
				}

				myLabel.setOpaque(true);
				// MyColor myColor = MyColor.GREEN;
				// myColors[row][col] = myColor;
				// myLabel.setBackground(myColor.getColor());
				myLabel.addMouseListener(myListener);
				myLabel.setPreferredSize(labelPrefSize);
				add(myLabel);
				myLabels[row][col] = myLabel;
			}
		}
	}
	public void gridRefresh(int rows, int cols, int cellWidth, PokemonController controller1)
	{
		controller = controller1;
		// myColors = new MyColor[rows][cols];
		myLabels = new JLabel[rows][cols];

		MyMouseListener myListener = new MyMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		setLayout(new GridLayout(rows, cols));

		for (int row = 0; row < myLabels.length; row++) {
			
			for (int col = 0; col < myLabels[row].length; col++) {
		
				JLabel myLabel = new JLabel();
				if ((row + 1) % 2 == 0) {
					if(controller.pokeList.size()>((row - 1) / 2 * 4 + col + controller.getIndex())){
					try {
						URL url = new URL(
								controller.getPokeList().get((row - 1) / 2 * 4 + col + controller.getIndex()).imageUrl);
						Image img = ImageIO.read(url);
						myLabel = new JLabel(new ImageIcon(img));
					} catch (IOException e) {
					}}
				} else {
					if(controller.pokeList.size()>(row / 2 * 4 + col + controller.getIndex())){
					myLabel = new JLabel(controller.getPokeList().get(row / 2 * 4 + col + controller.getIndex()).name);}
				}

				myLabel.setOpaque(true);
				// MyColor myColor = MyColor.GREEN;
				// myColors[row][col] = myColor;
				// myLabel.setBackground(myColor.getColor());
				myLabel.addMouseListener(myListener);
				myLabel.setPreferredSize(labelPrefSize);
				add(myLabel);
				myLabels[row][col] = myLabel;
			}
		}
	}	

	// public MyColor[][] getMyColors() {
	// return myColors;
	// }
	public void labelPressed(JLabel label) throws IOException, InterruptedException {
		for (int row = 0; row < myLabels.length; row++) {
			for (int col = 0; col < myLabels[row].length; col++) {
				if (label == myLabels[row][col]) {
					int pos = 0;
					if ((row + 1) % 2 == 0) {
						pos = (row - 1) / 2 * 4 + col + controller.getIndex();
					} else {
						pos = row / 2 * 4 + col + controller.getIndex();
					}
if(pos<controller.pokeList.size()) {
					DetailScreen details = new DetailScreen(controller, controller.getPokeList().get(pos));
					details.detail();}

				}
			}
		}
	}
}