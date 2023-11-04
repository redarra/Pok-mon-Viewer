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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
	public class GUIHome {
		PokemonController controller;
		public GUIHome(PokemonController controller) {
			this.controller=controller;
		}
	    public  void home() {

	        //Creating the Frame

        
            
            CustomModel model = new CustomModel(controller) {
                @Override
                public Class<?> getColumnClass(int column) {
                    if (column==2) return ImageIcon.class;
                    return Object.class;
                }
            };
            JTable table = new JTable();
            table.setModel(model);
            
            int rows = 10;
            int cols = 4;
            int cellWidth = 15;
            PokemonGrid mainPanel = new PokemonGrid(rows, cols, cellWidth,controller);
            
	        JFrame frame = new JFrame("Pokedex");

            //frame.add();
	        //Pokemon student = controller.getPokeList().get(1);

            //try {
              // URL url = new URL( student.imageUrl);
              // Image img = ImageIO.read(url);
              // frame.add(new JLabel(new ImageIcon(img)));
               
           //} catch (IOException e) {
           //}

            frame.pack();

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); 
	        frame.setSize(1000, 800);

	        //Creating the MenuBar and adding components
	        JMenuBar mb = new JMenuBar();
	        JMenu m1 = new JMenu("FILE");
	        JMenu m2 = new JMenu("Help");
	        mb.add(m1);
	        mb.add(m2);
	        JMenuItem m11 = new JMenuItem("Open");
	        JMenuItem m22 = new JMenuItem("Save as");
	        m1.add(m11);
	        m1.add(m22);

	        //Creating the panel at bottom and adding components
	        JPanel panel = new JPanel(); // the panel is not visible in output
	        JLabel label = new JLabel("Enter Text");
	       // JTextField tf = new JTextField(10); // accepts upto 10 characters
	        JButton previous = new JButton("Previous");
	        JButton next = new JButton("Next");
	        panel.add(label); // Components Added using Flow Layout
	       // panel.add(tf);
	        panel.add(previous);
	        panel.add(next);

	        // Text Area at the Center
	        JTextArea ta = new JTextArea();

	        //Adding Components to the frame.
	        frame.getContentPane().add(BorderLayout.SOUTH, panel);
	        frame.getContentPane().add(BorderLayout.NORTH, mb);
	        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(mainPanel));//new JScrollPane(table));
	        frame.setVisible(true);
	        
	

	        
	    
	}}

