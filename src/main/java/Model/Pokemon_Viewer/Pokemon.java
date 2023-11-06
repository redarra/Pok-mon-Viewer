package Model.Pokemon_Viewer;

public class Pokemon {
	public int id;
	public String name;
	public String url;
	public String imageUrl;
//Pokemon Object Class
	public Pokemon(String name, String url) {
		String[] te = url.split("/");
		String t=te[te.length-1];
		this.id = Integer.parseInt(t);
		this.name = name;
		this.url = "https://pokeapi.co/api/v2/pokemon/"+t+"/";
		this.imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + t + ".png";

	}
}
