package View.Pokemon_Viewer;


	import javax.imageio.ImageIO;
//Usually you will require both swing and awt packages
	// even if you are working with just swings.
	import javax.swing.*;

import Controller.Pokemon_Viewer.PokemonController;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
	public class GUIDetails {
		PokemonController controller;
		public GUIDetails(PokemonController controller) {
			this.controller=controller;
		}
	    public  void home() {

	        //Creating the Frame
	    	Runnable r = new Runnable() {

                @Override
                public void run() {
                    CustomModel model = new CustomModel(controller);
                    JTable table = new JTable();
                    table.setModel(model);

                    JFrame frame = new JFrame();
                    frame.add(new JScrollPane(table));
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);             
                }
            };

            EventQueue.invokeLater(r);
        
            
            
	        JFrame frame = new JFrame("Chat Frame");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 400);

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
	        JTextField tf = new JTextField(10); // accepts upto 10 characters
	        JButton send = new JButton("Send");
	        JButton reset = new JButton("Reset");
	        panel.add(label); // Components Added using Flow Layout
	        panel.add(tf);
	        panel.add(send);
	        panel.add(reset);

	        // Text Area at the Center
	        JTextArea ta = new JTextArea();

	        //Adding Components to the frame.
	        frame.getContentPane().add(BorderLayout.SOUTH, panel);
	        frame.getContentPane().add(BorderLayout.NORTH, mb);
	        frame.getContentPane().add(BorderLayout.CENTER, ta);
	        frame.setVisible(true);
	        
	

	        
	    
	}

}
    JPanel panel1 = new JPanel(new GridLayout(10, 4,10, 15));
    
    for (int i = 0; i < 5; i++) {
    	for(int x=0;x<4;x++) {
    		JLabel label1=new JLabel(controller.getPokeList().get(i*4+x).name);
    		label1.addMouseListener(new MouseAdapter() { 
        	     public void mousePressed(MouseEvent me) { 
        	    	// final int pos =i*4+x;
        	    	 
        	    	 if (me.getButton() == MouseEvent.BUTTON1) {
        	             panel1.labelPressed((JLabel)me.getSource());
        	          } else if (me.getButton() == MouseEvent.BUTTON3) {
        	             MyColor[][] myColors = colorGrid.getMyColors();
        	             for (int row = 0; row < myColors.length; row++) {
        	                for (int col = 0; col < myColors[row].length; col++) {
        	                   System.out.print(myColors[row][col] + " ");
        	                }
        	                System.out.println();
        	             }
        	             System.out.println();
        	          }
        	       
        	     
        	     
        	    	 DetailScreen details =new DetailScreen(controller, controller.getPokeList().get(i*4+x));
	                    details.detail();
        	        } 
        	       });
       panel1.add(label1);}
    	for(int x=0;x<4;x++) {
    		try {
             URL url = new URL( controller.getPokeList().get(i*4+x).imageUrl );
             Image img = ImageIO.read(url);
             JLabel label2=new JLabel(new ImageIcon(img));
        		label2.addMouseListener(new MouseAdapter() { 
	        	     public void mousePressed(MouseEvent me) { 
	        	    	 DetailScreen details =new DetailScreen(controller, controller.getPokeList().get(i*4+x));
		                    details.detail();
	        	        } 
	        	       });
             panel1.add(new JLabel(new ImageIcon(img)));
             
         } catch (IOException e) {
         }
	        }
    }

