package View.Pokemon_Viewer;

import javax.imageio.ImageIO;
//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;

import Controller.Pokemon_Viewer.PokemonController;
import Model.Pokemon_Viewer.Pokemon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUIHome {
	public PokemonGrid mainPanel;
	public PokemonController controller;
	public JFrame frame = new JFrame("Pokedex");
	public int rows = 10;
	public int cols = 4;
	public int page1=1;
	public int cellWidth = 15;
	public JButton previous = new JButton("Previous");
	public JButton next = new JButton("Next");
	public JLabel page;
	JPanel panel = new JPanel();
	JMenuBar mb = new JMenuBar();
	JComboBox<String> c1 = new JComboBox<>();
	JLabel l = new JLabel();
	String s1[];

	public GUIHome(PokemonController controller) {
		this.controller = controller;
		s1 = new String[controller.pokemonHabitat.size() + 1];
	}

	public void home() {
		s1[0] = "All";
		for (int i = 1; i < controller.pokemonHabitat.size() + 1; i++) {
			s1[i] = controller.pokemonHabitat.get(i - 1).name;
		}
		c1 = new JComboBox<>(s1);
		mb.add(new JLabel("Habitat Filter: "));
		mb.add(c1);
		mb.add(l);
		panel = new JPanel();
		panel.add(previous);
		//page = new JLabel("Page :"+page1);
		panel.add(new JLabel("Page :"+page1));
		panel.add(next);
		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox) e.getSource();
				Object selected = comboBox1.getSelectedItem();
				
				page1=1;
				
				if (selected == "All") {
					try {
						controller.loadList();
						compileFrame();
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						controller.find(selected);
						compileFrame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1000, 800);
		if (page1 == 1) {
			previous.setEnabled(false);
			
		} else {
			previous.setEnabled(true);
		}
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.getNextPokeList(true);
					page1=page1+1;
					previous.setEnabled(true);
					//page = new JLabel("Page"+page1);
					compileFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.getNextPokeList(false);
					if(page1>1) {
					page1=page1-1;}
					//page = new JLabel("Page"+page1);
					compileFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		compileFrame();
	}

	public void compileFrame() {

		mainPanel = new PokemonGrid(rows, cols, cellWidth, controller);
		JScrollPane n = new JScrollPane(mainPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);// new JScrollPane(table));
		frame.setVisible(true);
	}

}
