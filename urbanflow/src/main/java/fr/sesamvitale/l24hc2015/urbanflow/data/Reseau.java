package fr.sesamvitale.l24hc2015.urbanflow.data;

import java.util.HashMap;
import java.util.List;

/**
 * Modélisation du réseau de la SETRAM
 * @author user
 *
 */
public class Reseau {
	private HashMap<String, Ligne> lignes;
	private static Reseau reseau;

	private Reseau(){
		lignes = new HashMap<String, Ligne>();
		parserJson();
	}

	public static Reseau getInstance(){
		if (null == reseau){
			reseau = new Reseau();
		}
		return reseau;
	}

	private void parserJson(){

	}

	/**
	 * Récupération des horaires de deux arrets
	 * @param source arret de départ
	 * @param destination arret d'arrivée
	 * @param ligneString ligne
	 * @param jour jour
	 * @return
	 */
	public HashMap<String, HoraireJour> getHoraires(Arret source, Arret destination, String ligneString, String jour){
		// hashmap contient deux entrées : les listes des horaires des arrets source et destination pour la ligne choisie
		HashMap<String, HoraireJour> horairesArrets = new HashMap<String, HoraireJour>();
		Ligne l = lignes.get(ligneString);
		Arret arretSource = l.getArrets().get(source.getId());
		List<HoraireJour> horairesSource = l.getHoraires(arretSource.getId());
		for (int i=0;i<horairesSource.size();i++){
			if (horairesSource.get(i).getJour().equals(jour)){
				horairesArrets.put(source.getId(), horairesSource.get(i));
				break;
			}
		}
		if (null != destination){
			Arret arretDestination = l.getArrets().get(destination.getId());
			List<HoraireJour> horairesDestination = l.getHoraires(arretDestination.getId());
			for (int i=0;i<horairesDestination.size();i++){
				if (horairesDestination.get(i).getJour().equals(jour)){
					horairesArrets.put(destination.getId(), horairesDestination.get(i));
					break;
				}
			}
		}
		return horairesArrets;
	}

	/**
	 * @return the lignes
	 */
	public HashMap<String, Ligne> getLignes() {
		return lignes;
	}
}
