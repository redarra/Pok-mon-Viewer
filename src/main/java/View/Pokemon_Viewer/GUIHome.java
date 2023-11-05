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
	public int cellWidth = 15;
	public JButton previous = new JButton("Previous");
	public JButton next = new JButton("Next");
	JPanel panel = new JPanel();
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("FILE");
	JMenu m2 = new JMenu("Help");
	JMenuItem m11 = new JMenuItem("Open");
	JMenuItem m22 = new JMenuItem("Save as");
	 JComboBox<String> c1= new JComboBox<>();
	 JLabel l= new JLabel();
	 String s1[] ;
	// JPanel p = new JPanel();

	public GUIHome(PokemonController controller) {
		this.controller = controller;
		s1= new String[controller.pokemonHabitat.size()+1];
	}

	public void home() {

		// Creating the Frame

		// CustomModel model = new CustomModel(controller) {
		// @Override
		// public Class<?> getColumnClass(int column) {
		// if (column==2) return ImageIcon.class;
		// return Object.class;
		// }
		// };
		// JTable table = new JTable();
		// table.setModel(model);
		
		//mainPanel = new PokemonGrid(rows, cols, cellWidth, controller);
		
		s1[0]="All";
		for(int i =1; i<controller.pokemonHabitat.size()+1;i++) {
			s1[i]=controller.pokemonHabitat.get(i-1).name;
		}
		 c1 = new JComboBox<>(s1);
		 
			mb.add(m1);
			mb.add(m2);
	mb.add(c1);
	mb.add(l);
			m1.add(m11);
			m1.add(m22);
	        // add ItemListener .addActionListener(event -> {
			 c1.addActionListener(new ActionListener() {
				 @Override
		            public void actionPerformed(ActionEvent e) {
				 JComboBox comboBox1 = (JComboBox) e.getSource();

		            // Print the selected items and the action command.
		            Object selected = comboBox1.getSelectedItem();
		           
		            if(selected=="All") {
                		try {
							controller.loadList();
							compileFrame();
			                  // mainPanel = new PokemonGrid(rows, cols, cellWidth, controller);
			                  

						} catch (IOException | InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                	}
                	else {
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

		if (controller.index == 20) {
			previous.setEnabled(false);
		}
		else {previous.setEnabled(true);}
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					controller.getNextPokeList(true);
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

					compileFrame();
					// mainPanel = new PokemonGrid(rows, cols, cellWidth,controller);
					// int rows = 10;
					// int cols = 4;
					// int cellWidth = 15;
					// PokemonGrid mainPanel = new PokemonGrid(rows, cols, cellWidth,controller);
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
		// panel.add(label); // Components Added using Flow Layout
		// panel.add(tf);

		// Text Area at the Center
		// JTextArea ta = new JTextArea();

		// Adding Components to the frame.

	}

	public void compileFrame() {

		panel.add(previous);
		panel.add(next);
		mainPanel = new PokemonGrid(rows, cols, cellWidth, controller);
		JScrollPane n= new JScrollPane(mainPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);// new JScrollPane(table));
		frame.setVisible(true);
	}


}
