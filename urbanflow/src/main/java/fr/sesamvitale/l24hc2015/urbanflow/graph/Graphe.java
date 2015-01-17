package fr.sesamvitale.l24hc2015.urbanflow.graph;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.HoraireJour;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.utils.Temps;

public class Graphe 
{
	private static Graphe instance;
	private DirectedWeightedMultigraph<Arret,Liaison> graphe;
	private HashMap<String, Arret> arrets;
	
	private Graphe()
	{
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
	}
	
	public static Graphe getInstance()
	{
		if (null == instance)
		{
			instance = new Graphe();
		}
		return instance;
	}
	
	public void creerGraphe()
	{
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
		arrets = new HashMap<String, Arret>();
		Reseau reseau = Reseau.getInstance();
		for(Entry<String, Ligne> entry : reseau.getLignes().entrySet()) {
			String nomLigne = entry.getKey();
			Ligne ligne = entry.getValue();
			Arret arretPrecedent = null;
			for(Entry<Integer, Arret> entryLigne : ligne.getArrets().entrySet()) {
				Arret arret = entryLigne.getValue();
				if (!arrets.containsKey(arret.getId()))
				{
					arrets.put(arret.getId(), arret);
				}
				ajouterArret(arrets.get(arret.getId()));
				if (null != arretPrecedent){
					Liaison liaison = new Liaison(arretPrecedent, arrets.get(arret.getId()),nomLigne);
					ajouterLiaison(liaison);
				}
				arretPrecedent = arrets.get(arret.getId());
			}
		}
	}
	
	private void ajouterArret(Arret a)
	{
		graphe.addVertex(a);
	}
	
	private void ajouterLiaison(Liaison l)
	{
		graphe.addEdge(l.getSource(),l.getTarget(),l);
	}

	private Temps calculerTempsDeuxArrets(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		Temps temps = new Temps();
		HashMap<String, List<HoraireJour>> horairesArrets = Reseau.getInstance().getHoraires(source, target, ligne);
		List<HoraireJour> horairesSource = horairesArrets.get(source.getId());
		List<HoraireJour> horairesDestination = horairesArrets.get(target.getId());
		String horairesMinimumSource = "";
		for (int i=0;i<horairesSource.size();i++){
			//if (horairesSource.get(i).getHoraires())
		}
		return temps;
	}
}
