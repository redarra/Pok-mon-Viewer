package Controller.Pokemon_Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Pokemon_Viewer.ClientApp;
import Model.Pokemon_Viewer.Pokemon;

public class PokemonController implements ActionListener{
	ClientApp clApp;
	public List<Pokemon> pokeList =new ArrayList<Pokemon>() ;
	 public int total=20;
	 public int index=0;
	 public int max=0;
	public PokemonController(ClientApp clApp) {
		this.clApp=clApp;
		//clApp.fetch(total);
		pokeList=clApp.getPokeList();
	}
	public void loadList () throws IOException, InterruptedException {
		clApp.fetch(total);
		pokeList=clApp.getPokeList();
	}
	public List<Pokemon> getNextPokeList( boolean shift) throws IOException, InterruptedException{
		if(shift){
	           index=index+total;
	           if(max<index) {
	           max=max+total;
	           clApp.getNext(total, index);
	           pokeList=clApp.getPokeList();}
		}
		else if (index>total) {
			index=index-total;
			
			
		}
		
		return pokeList;
	}
	public List<Pokemon> getPokeList(){return pokeList;}
	public int getTotal() {return total;}
	public int getIndex() {return index;}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
