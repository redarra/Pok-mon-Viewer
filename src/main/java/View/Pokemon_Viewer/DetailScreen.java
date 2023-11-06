package View.Pokemon_Viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.Pokemon_Viewer.PokemonController;
import Model.Pokemon_Viewer.Pokemon;
import Model.Pokemon_Viewer.PokemonDetails;
//this screen displays all the pokemon info
public class DetailScreen {
	PokemonController controller;
	Pokemon poke;
	PokemonDetails pokeDe;

	public DetailScreen(PokemonController controller, Pokemon poke) {
		this.controller = controller;
		this.poke = poke;

	}

	public void detail() throws IOException, InterruptedException {

		try {

			pokeDe = controller.getPokemon(poke);
			JPanel pan = new JPanel();
			pan.setLayout(new GridLayout(1, 3));
			JPanel pan1 = new JPanel();
			JPanel pan2 = new JPanel();
			JPanel panel11 = new JPanel(new GridLayout(3, 1));

			JFrame frame = new JFrame("Pokemon Details");
			JPanel panel1 = new JPanel(new GridLayout(5, 1));
//Image for the pokemon
			URL url = new URL(pokeDe.imageUrl);
			Image img = ImageIO.read(url);
//Labels displaying all the info
			JLabel pic = new JLabel(new ImageIcon(img));
			JLabel name = new JLabel(pokeDe.name);
			JLabel height = new JLabel(pokeDe.getHeightStr());
			JLabel weight = new JLabel(pokeDe.getWeightStr());
			JLabel xP = new JLabel(pokeDe.getbaseXPStr());
			JLabel ability = new JLabel(pokeDe.getAbilitiesStr());
			JLabel stat = new JLabel("Stats:");
			// Grid for all the information

			pan.add(pan1);
			panel1.add(pic);
			panel1.add(name);
			panel11.add(height);
			panel11.add(weight);
			panel11.add(xP);
			panel1.add(panel11);
			// Grid to display the stats table
			JPanel StatPanel = new JPanel(new GridLayout(pokeDe.getStatTot() + 1 + 1, 2));
			StatPanel.add(stat);
			StatPanel.add(new JLabel());
			StatPanel.add(new JLabel());
			StatPanel.add(new JLabel("Name:"));
			StatPanel.add(new JLabel("Base Stats:"));
			StatPanel.add(new JLabel("Effort:"));
			// add the stats objects to the grid
			for (int i = 0; i < pokeDe.getStatTot(); i++) {
				StatPanel.add(new JLabel(pokeDe.getStat(i).name));
				StatPanel.add(new JLabel(pokeDe.getStat(i).baseStat));
				StatPanel.add(new JLabel(pokeDe.getStat(i).effort));
			}
			// Add it all together
			panel1.add(StatPanel);
			panel1.add(ability);
			pan.add(panel1);
			pan.add(pan2);

			frame.pack();

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(1000, 800);

			JMenuBar mb = new JMenuBar();

			JPanel panel = new JPanel();

			frame.getContentPane().add(BorderLayout.SOUTH, panel);
			frame.getContentPane().add(BorderLayout.NORTH, mb);
			frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(pan));// new JScrollPane(table));
			frame.setVisible(true);

		} catch (IOException e) {
		}

	}

}
