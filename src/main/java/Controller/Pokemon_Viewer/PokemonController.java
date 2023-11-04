package Controller.Pokemon_Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Pokemon_Viewer.ClientApp;
import Model.Pokemon_Viewer.Pokemon;
import Model.Pokemon_Viewer.PokemonDetails;

public class PokemonController implements ActionListener{
	ClientApp clApp;
	public List<Pokemon> pokeList =new ArrayList<Pokemon>() ;
	public List<PokemonDetails> pokeDeList =new ArrayList<PokemonDetails>() ;
	//public PokemonDetails[] pokeDeArr = new PokemonDetails[5] ;
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
	public PokemonDetails getPokemon(Pokemon poke) throws IOException, InterruptedException {
		for(int i = 0; i<pokeDeList.size();i++) {
			if(pokeDeList.get(i).name==poke.name) {
				int pos = i;
				PokemonDetails pd=pokeDeList.get(pos);
				for(int x = 1; i<pokeDeList.size();x++) {
					
					pokeDeList.set(pokeDeList.size()-x, pokeDeList.get(pokeDeList.size()-1-x));
				}
			pokeDeList.set(0, pd);
			return pd;}
		}
		PokemonDetails pd =clApp.getPokemon(poke);
		if(pokeDeList.size()<5 && !pokeDeList.contains(pd)) {
			if(pokeDeList.size()==0) {pokeDeList.add(pd);}
			else {
				pokeDeList.add(pokeDeList.get(pokeDeList.size()-1));
				for(int i = 1; i<pokeDeList.size();i++) {
					
						pokeDeList.set(pokeDeList.size()-i, pokeDeList.get(pokeDeList.size()-1-i));
					}
				pokeDeList.set(0, pd);
			}
		}//pokeDeList.indexOf(pd)
		else if (!pokeDeList.contains(pd)){
			for(int i = 1; i<pokeDeList.size();i++) {
				
				pokeDeList.set(pokeDeList.size()-i, pokeDeList.get(pokeDeList.size()-1-i));
			}
		pokeDeList.set(0, pd);
	
		}
		
		else {
			int pos =pokeDeList.indexOf(pd);
			if(pos!=0) {
				for(int i = 1; i<pos+1;i++) {
					
					pokeDeList.set(pos+1-i, pokeDeList.get(pos-1-i));
				}
			pokeDeList.set(0, pd);
			}

		}
		return pd;

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
