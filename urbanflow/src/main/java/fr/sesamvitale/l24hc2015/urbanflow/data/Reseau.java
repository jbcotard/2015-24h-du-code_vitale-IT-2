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
	
	public List<Horaire> getHoraires(Arret source, Arret destination, String ligne)
	{
		List<Horaire> horaires = new ArrayList<Horaire>();
		
		
		return horaires;
	}

	/**
	 * @return the lignes
	 */
	public HashMap<String, Ligne> getLignes() {
		return lignes;
	}
	
	

}
