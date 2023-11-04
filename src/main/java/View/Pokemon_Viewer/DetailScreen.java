package View.Pokemon_Viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Controller.Pokemon_Viewer.PokemonController;
import Model.Pokemon_Viewer.Pokemon;
import Model.Pokemon_Viewer.PokemonDetails;

public class DetailScreen {
	PokemonController controller;
	Pokemon poke;
	PokemonDetails pokeDe;
	public DetailScreen(PokemonController controller, Pokemon poke) {
		this.controller=controller;
		this.poke=poke;
		
		
	}
    public  void detail() throws IOException, InterruptedException {

        //Creating the Frame

    
    	pokeDe=controller.getPokemon(poke);
        CustomModel model = new CustomModel(controller) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==2) return ImageIcon.class;
                return Object.class;
            }
        };
        JTable table = new JTable();
        table.setModel(model);
        JFrame frame = new JFrame("Chat Frame");
        JPanel panel1 = new JPanel(new GridLayout(10, 4,10, 15));
        



        try {
           URL url = new URL( pokeDe.imageUrl);
           Image img = ImageIO.read(url);
           
           JLabel label2 =new JLabel(new ImageIcon(img));
           JLabel label1 =new JLabel(pokeDe.name);
           JPanel panel11 = new JPanel();
           panel11.add(label1);
           panel11.add(label2);

    

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(panel11));//new JScrollPane(table));
        frame.setVisible(true);
        

        
        } catch (IOException e) {
        }
           
    
        }
    
}
