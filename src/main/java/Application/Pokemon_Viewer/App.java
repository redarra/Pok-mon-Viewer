package Application.Pokemon_Viewer;

import java.io.IOException;
import javax.swing.SwingUtilities;

import Controller.Pokemon_Viewer.PokemonController;
import Model.Pokemon_Viewer.ClientApp;
import View.Pokemon_Viewer.GUIHome;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		  
	       // controller.updateView();
	 
    }
    public static void createAndShowGUI() throws Exception {
    	ClientApp clApp  = new ClientApp();
		  
   	 
        PokemonController controller = new PokemonController(clApp);
        controller.loadList();
        GUIHome guiHome = new GUIHome( controller);
		guiHome.home();
    }
    
}
