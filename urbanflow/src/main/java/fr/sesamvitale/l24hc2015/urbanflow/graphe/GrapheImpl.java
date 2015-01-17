package fr.sesamvitale.l24hc2015.urbanflow.graphe;


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

public class GrapheImpl 
{
	private static GrapheImpl instance;
	private DirectedWeightedMultigraph<Arret,Liaison> graphe;
	private HashMap<String, Arret> arrets;
	
	private GrapheImpl()
	{
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
	}
	
	public static GrapheImpl getInstance()
	{
		if (null == instance)
		{
			instance = new GrapheImpl();
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

	private int calculerTempsDeuxArretsConsecutifs(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		HashMap<String, List<HoraireJour>> horairesArrets = Reseau.getInstance().getHoraires(source, target, ligne);
		List<HoraireJour> horairesSource = horairesArrets.get(source.getId());
		List<HoraireJour> horairesDestination = horairesArrets.get(target.getId());
		String horairesMinimumDestination = "";
		int numArret = 0;
		int numJour = 0;
		for (int i=0;i<horairesSource.size();i++){
			if (horairesSource.get(i).getJour().equals(jour)){
				numJour = i;
				break;
			}
		}
		HoraireJour horaire = horairesSource.get(numJour);
		for (int j=0;j<horaire.getHoraires().size();numJour++){
			String tempsArret = horaire.getHoraires().get(j);
			if (Temps.isPosterieur(Temps.convertStringToTemps(tempsArret), Temps.convertStringToTemps(tempsDepart)) == 1){
				numArret = j;
				break;
			}
		}
		if (horairesDestination.size()>numArret){
			horairesMinimumDestination = horairesDestination.get(numJour).getHoraires().get(numArret+1);
		}
		return Temps.getDuree(horairesMinimumDestination, tempsDepart);
	}
}
