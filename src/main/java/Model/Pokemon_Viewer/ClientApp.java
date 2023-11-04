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

    public  JsonObject getMethod(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
         return jsonObject;
    }
    public  void JsonToObjList(JsonObject jsonObject, int total) {
        String jsonString = jsonObject.toString();
        String ar =jsonString.split("results"+'"'+":")[1].replace("[","").replaceAll("\"","").replaceAll(":", ",").replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\\]", "");
        System.out.println(ar);
        String[] pokes= ar.split(",");
        for(int i=0; i<total;i++) {
       	 pokeList.add(new Pokemon(pokeList.size()+1,pokes[i*5+1],pokes[i*5+3]+":"+pokes[i*5+4],"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(pokeList.size()+1)+".png"));
       	 
        }
    }
   // public static String getAbilities
///    https://pokeapi.co/api/v2/ability/
  //  	https://pokeapi.co/api/v2/characteristic/{id}/
    //			https://pokeapi.co/api/v2/nature/{id or name}/
    				
    	//			https://pokeapi.co/api/v2/pokemon-color/{id or name}/
    		//				https://pokeapi.co/api/v2/pokemon-habitat/{id or name}/
    			//				https://pokeapi.co/api/v2/pokemon-species/{id or name}/
}