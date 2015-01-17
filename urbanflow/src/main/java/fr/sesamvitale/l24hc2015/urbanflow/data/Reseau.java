package fr.sesamvitale.l24hc2015.urbanflow.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reseau {
	private HashMap<String, Ligne> lignes;
	private static Reseau reseau;
	
	private Reseau()
	{
		lignes = new HashMap<String, Ligne>();
		parserJson();
	}
	
	public static Reseau getInstance()
	{
		if (null == reseau)
		{
			reseau = new Reseau();
		}
		return reseau;
	}
	
	private void parserJson()
	{
		
	}
	
	public HashMap<String, List<HoraireJour>> getHoraires(Arret source, Arret destination, String ligne)
	{
		// hashmap contant deux entrées : les listes des horaires des arrets source et destination pour la ligne choisie
		HashMap<String, List<HoraireJour>> horairesArrets = new HashMap<String, List<HoraireJour>>();
		List<HoraireJour> horairesA = new ArrayList<HoraireJour>();
		List<HoraireJour> horairesB = new ArrayList<HoraireJour>();
		// Remplissage des données avec JSON
		
		
		horairesArrets.put(source.getId(), horairesA);
		horairesArrets.put(destination.getId(), horairesB);
		return horairesArrets;
	}

	/**
	 * @return the lignes
	 */
	public HashMap<String, Ligne> getLignes() {
		return lignes;
	}
	
	

}
