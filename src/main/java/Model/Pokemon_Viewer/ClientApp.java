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
	 private List<Pokemon> pokeList =new ArrayList<Pokemon>() ;
	 //public int total=20;
	 //public int index=0;
	 public int max=0;
	 public ClientApp(){}
	 public List<Pokemon> getPokeList(){
		 return pokeList;
	 }
    public  void fetch(int total) throws IOException, InterruptedException {
         JsonToObjList(getMethod("https://pokeapi.co/api/v2/pokemon?limit="+total), total);
    }
    public  void getNext(int total, int index) throws IOException, InterruptedException {
           JsonToObjList(getMethod("https://pokeapi.co/api/v2/pokemon?offset="+index+"&limit="+total),total);
           
    }
    public PokemonDetails getPokemon(Pokemon poke) throws IOException, InterruptedException {
    	return JsonToObjPokeList(poke,getMethod(poke.url));
    	
    }

    public  String getMethod(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
         //String jsonString = ;
         return jsonObject.toString();
    }
    public  void JsonToObjList(String jsonString, int total) {
        //String jsonString = jsonObject.toString();
        String ar =jsonString.split("results"+'"'+":")[1].replace("[","").replaceAll("\"","").replaceAll(":", ",").replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\\]", "");
        System.out.println(ar);
        String[] pokes= ar.split(",");
        for(int i=0; i<total;i++) {
       	 pokeList.add(new Pokemon(pokeList.size()+1,pokes[i*5+1],pokes[i*5+3]+":"+pokes[i*5+4],"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(pokeList.size()+1)+".png"));
       	 
        }
    }
        public  PokemonDetails JsonToObjPokeList(Pokemon poke,String jsonString) {             
             PokemonDetails pokeDet = new PokemonDetails(poke.id,poke.name,poke.url,poke.imageUrl);
    System.out.println(jsonString);
    //ability
    

    String[] list=jsonString.split("\""+"ability"+"\"");
    for (int i=1;i<list.length;i++) {
    	String test=list[i].split("\""+"name"+"\""+":")[1].replaceAll("\"","").split(",")[0];
    	System.out.println(test);
    	pokeDet.addAbility(test);
    }

    

    //System.out.println(list[2]);
    //base_experience
    list=list[list.length-1].replaceAll("\"","").split("base_experience:");
    String baseXP=list[1].split(",")[0];
    pokeDet.setbaseXP(baseXP);
    System.out.println(list[0]);
    System.out.println(list.length);
    System.out.println(baseXP);
    list=list[1].split("height:");
    String height =list[1].split(",")[0];
    pokeDet.setHeight(height);
    list= list[1].split("base_stat:");
    System.out.println(list.length);
    String Stat =list[1].split(",")[0];


    for (int i=1;i<list.length;i++) {
    	String base_stat=list[i].split(",")[0];
    	String effort =list[i].split("effort:")[1].split(",")[0];
    	String name =list[i].split("name:")[1].split(",")[0];
    	System.out.println(base_stat);
    	System.out.println(effort);
    	System.out.println(name);
    	pokeDet.addStat( name,  base_stat,  effort);
    	

    }
    String weight =list[list.length-1].split("weight:")[1].split(",")[0];
    pokeDet.setWeight(weight);
    return pokeDet ;
            
    }
   // public static String getAbilities
///    https://pokeapi.co/api/v2/ability/
  //  	https://pokeapi.co/api/v2/characteristic/{id}/
    //			https://pokeapi.co/api/v2/nature/{id or name}/
    				
    	//			https://pokeapi.co/api/v2/pokemon-color/{id or name}/
    		//				https://pokeapi.co/api/v2/pokemon-habitat/{id or name}/
    			//				https://pokeapi.co/api/v2/pokemon-species/{id or name}/
}