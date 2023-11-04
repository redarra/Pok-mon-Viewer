package Model.Pokemon_Viewer;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetails extends Pokemon {

List<String> abilities =new ArrayList<String>();
String baseXP ;
String height;
String weight;
List<Stats> statsList =new ArrayList<Stats>();

		public PokemonDetails(int id, String name, String url,String imageUrl) {
			super(id,name,url,imageUrl);
			
			
		}

		public void addAbility(String name) {
			abilities.add(name);
		}
		public void setbaseXP(String name) {
			baseXP=name;
			
		}
		public void setHeight(String name) {
			height=name;
			
		}
		public void setWeight(String name) {
			weight=name;
			
		}
		public void addStat(String name, String baseStat, String effort) {
			statsList.add(new Stats( name,  baseStat,  effort));
		}
		
		}
