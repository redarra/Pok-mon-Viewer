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
   @Override
   public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
         try {
			pokeGrid.labelPressed((JLabel)e.getSource());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         //System.out.println(colorGrid.labelPressed((JLabel)e.getSource()));
      } 
     // else if (e.getButton() == MouseEvent.BUTTON3) {
     //    MyColor[][] myColors = colorGrid.getMyColors();
     //    for (int row = 0; row < myColors.length; row++) {
     //       for (int col = 0; col < myColors[row].length; col++) {
    //           System.out.print(myColors[row][col] + " ");
    //        }
     //       System.out.println();
    //     }
    //     System.out.println();
     /// }
   }
}