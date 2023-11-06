package Model.Pokemon_Viewer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientApp {
	private List<Pokemon> pokeList = new ArrayList<Pokemon>();
	private List<Pokemon> pokeList2 = new ArrayList<Pokemon>();
	private List<Filter> ability = new ArrayList<Filter>();
	private List<Filter> pokemonHabitat = new ArrayList<Filter>();
	private List<Filter> pokemonSpecies = new ArrayList<Filter>();

	// public int total=20;
	// public int index=0;
	public int max = 0;
	public int count;

	public ClientApp() {
	}

	public List<Pokemon> getPokeList() {
		return pokeList;
	}
	public List<Filter> getHabList() {
		return pokemonHabitat;
	}

	public int fetch(int total) throws IOException, InterruptedException {
		pokeList = new ArrayList<Pokemon>();
		String temp = getMethod("https://pokeapi.co/api/v2/pokemon?limit=" + total);
		JsonToObjList(clean(temp,"results:"));
		return Integer.parseInt(temp.split("\"" + "count" + "\"" + ":")[1].split(",")[0]);

	}
	public int fetch(String url) throws IOException, InterruptedException {
		pokeList = new ArrayList<Pokemon>();

		String jsonString = getMethod(url);
		JsonToObjList( clean(getMethod(url),"pokemon_species:")); 
		return count;}

	public void fetchPokemonHab() throws IOException, InterruptedException {

		String jsonString = getMethod("https://pokeapi.co/api/v2/pokemon-habitat/");
		int count = Integer.parseInt(jsonString.split("\"" + "count" + "\"" + ":")[1].split(",")[0]);
		String ar = clean(jsonString,"results:");
		String[] pokes = ar.split(",");
		//System.out.println(pokes.length/5);
		for (int i = 0; i < (pokes.length/5); i++) {
		//	System.out.println(pokes[i * 5+1]+" https:" + pokes[i * 5 + 4]);
		//	pokemonHabitat.add(new Filter(pokes[count * 4], "https:" + pokes[count * 4 + 3]));
			pokemonHabitat.add(new Filter(pokes[i*5+1], "https:" + pokes[i*5+4]));

		}
		//return count;
	}

	public void getNext(int total, int index) throws IOException, InterruptedException {
		JsonToObjList(clean(getMethod("https://pokeapi.co/api/v2/pokemon?offset=" + index + "&limit=" + total),"results:"));

	}

	public PokemonDetails getPokemon(Pokemon poke) throws IOException, InterruptedException {
		return JsonToObjPokeList(poke, getMethod(poke.url));

	}

	public String getMethod(String url) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
		return jsonObject.toString();
	}

	public void JsonToObjList(String jsonString) {
		
		//System.out.println(jsonString);
		String[] pokes = jsonString.split(",");
		count=pokes.length/5;
		//System.out.println(count);
		for (int i = 0; i < pokes.length/5; i++) {
		
			pokeList.add(new Pokemon(pokes[i * 5 + 1], pokes[i * 5 + 3] + ":" + pokes[i * 5 + 4]));

		}
	}
	public String clean(String s,String word) {
		return s.replaceAll("\"", "").split(word)[1].replace("[", "")
				.replaceAll(":", ",").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "");
	}

	public PokemonDetails JsonToObjPokeList(Pokemon poke, String jsonString) {
		PokemonDetails pokeDet = new PokemonDetails( poke.name, poke.url);
		//System.out.println(jsonString);
		// ability

		String[] list = jsonString.split("\"" + "ability" + "\"");
		for (int i = 1; i < list.length; i++) {
			String test = list[i].split("\"" + "name" + "\"" + ":")[1].replaceAll("\"", "").split(",")[0];
			pokeDet.addAbility(test);
		}
		list = list[list.length - 1].replaceAll("\"", "").split("base_experience:");
		String baseXP = list[1].split(",")[0];
		pokeDet.setbaseXP(baseXP);

		list = list[1].split("height:");
		pokeDet.setHeight(list[1].split(",")[0]);
		list = list[1].split("base_stat:");
		String Stat = list[1].split(",")[0];

		for (int i = 1; i < list.length; i++) {
			String base_stat = list[i].split(",")[0];
			String effort = list[i].split("effort:")[1].split(",")[0];
			String name = list[i].split("name:")[1].split(",")[0];
			pokeDet.addStat(name, base_stat, effort);

		}
		String weight = list[list.length - 1].split("weight:")[1].split(",")[0];
		pokeDet.setWeight(weight);
		return pokeDet;

	}
	// public static String getAbilities
///    https://pokeapi.co/api/v2/ability/
	// https://pokeapi.co/api/v2/characteristic/{id}/
	// https://pokeapi.co/api/v2/nature/{id or name}/

	// https://pokeapi.co/api/v2/pokemon-color/{id or name}/
	// https://pokeapi.co/api/v2/pokemon-habitat/{id or name}/
	// https://pokeapi.co/api/v2/pokemon-species/{id or name}/
}