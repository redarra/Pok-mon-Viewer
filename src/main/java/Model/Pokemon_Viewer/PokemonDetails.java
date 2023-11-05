package Model.Pokemon_Viewer;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetails extends Pokemon {

List<String> abilities =new ArrayList<String>();
String baseXP ;
String height;
String weight;
List<Stats> statsList =new ArrayList<Stats>();

		public PokemonDetails(String name, String url) {
			super(name,url);
			
			
		}
		public Stats getStat(int i) {
			return statsList.get(i);
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
		
		public String getHeightStr() {
			return("Height: "+height);
		}
		public String getWeightStr() {
			return("Weight: "+weight.replaceAll("\\}", ""));
		}
		public String getbaseXPStr() {
			return("Base Experience: "+baseXP);
		}
		public int getAbTot() {
			return abilities.size();
		}
		public int getStatTot() {
			return statsList.size();
		}
		public String getAbilitiesStr() {
			String temp= abilities.get(0);
			if(abilities.size()>1) {
			for(int i =1; i<abilities.size();i++) {
				temp=temp+", "+abilities.get(i);
			}}
			return("Abilities: "+temp);
		}
		public  String getStatsStr() {
			String temp="";
			for(int i =1; i<statsList.size();i++) {
				temp=temp+"\n "+i+":\n"+statsList.get(i).getStatsStr();
			}
			return temp;
		}

		}
