package fr.sesamvitale.l24hc2015.urbanflow.graphe;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.HoraireJour;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.util.Temps;

public class GrapheImpl implements IGraphe
{
	private static IGraphe instance;
	private DirectedWeightedMultigraph<Arret,Liaison> graphe;
	private HashMap<String, Arret> arrets;
	
	private GrapheImpl()
	{
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
	}
	
	public static IGraphe getInstance()
	{
		if (null == instance)
		{
			instance = new GrapheImpl();
		}
		return instance;
	}
	
	@Override
	public void creerGraphe(Reseau reseau)
	{
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
		arrets = new HashMap<String, Arret>();
		//Reseau reseau = Reseau.getInstance();
		for(Entry<String, Ligne> entry : reseau.getLignes().entrySet()) {
			String nomLigne = entry.getKey();
			Ligne ligne = entry.getValue();
			Arret arretPrecedent = null;
			for(Entry<String, Arret> entryLigne : ligne.getArrets().entrySet()) {
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
	
	private String getHoraire(String tempsDepart, Arret source, Arret target, String ligne, String jour){
		HashMap<String, HoraireJour> horairesArrets = Reseau.getInstance().getHoraires(source, target, ligne,jour);
		HoraireJour horairesSource = horairesArrets.get(source.getId());
		HoraireJour horairesDestination = horairesArrets.get(target.getId());
		int numArret = 0;
		for (int j=0;j<horairesSource.getHoraires().size();j++){
			String tempsArret = horairesSource.getHoraires().get(j);
			if (Temps.isPosterieur(Temps.convertStringToTemps(tempsArret), Temps.convertStringToTemps(tempsDepart)) == 1){
				numArret = j;
				break;
			}
		}
		return horairesDestination.getHoraires().get(numArret+1);
	}

	private int calculerTempsDeuxArretsConsecutifs(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		String horairesMinimumDestination = getHoraire(tempsDepart, source, target, ligne, jour);

		return Temps.getDuree(horairesMinimumDestination, tempsDepart);
	}
	
	private int calculerPonderationDeuxArretsConsecutifs(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		int temps = calculerTempsDeuxArretsConsecutifs(tempsDepart, source, target, ligne, jour);
		int incidents = calculerIncidents();
		return temps+incidents;
	}
	
	private void mettreAJourPonderations(String tempsDepart, String jour){
		Set<Liaison> edges = graphe.edgeSet();
	
		for (Liaison e : edges) {
			int ponderation = calculerPonderationDeuxArretsConsecutifs(tempsDepart,e.getSource(),e.getTarget(),e.getLigne(),jour);
			graphe.setEdgeWeight(e, ponderation);
		}
	}
	
	private int calculerIncidents(){
		return 0;
	}
	
	public Deplacement seDeplacer(int s, int t, String heure, String jour){
		mettreAJourPonderations(heure, jour);
		Arret source = arrets.get(s);
		Arret target = arrets.get(t);
		List<Liaison> chemin = dijkstraShortestPath(source, target);
		if (chemin.size()>0){
			Arret prochain = chemin.get(0).getTarget();
			if (null != prochain){
				Deplacement d = new Deplacement();
				d.setNumArret(prochain.getName());
				d.setNumLigne(chemin.get(0).getLigne());
				d.setConnexion(getHoraire(heure, source, target, chemin.get(0).getLigne(), jour));
				return d;
			}
		}else{
			System.out.println("Chemin VIDE");
		}
		return null;
	}
	
	/** 
	 * @return shortest path between two connected spot, using Dijkstra's algorithm.The edge weights, if any, are ignored here, meaning that the returned path is the shortest in terms of number of edges. <p> Return <code>null</code> if the two spots are not connected by a track, or if  one of the spot do not belong to the graph, or if the  {@link #graph} field is<code>null</code>.
	 * @param source  the spot to start the path with
	 * @param target  the spot to stop the path with
	 */
	private List<Liaison> dijkstraShortestPath(final Arret source,final Arret target){
	  if (null == graphe) {
	    return null;
	  }
	  DijkstraShortestPath<Arret,Liaison> pathFinder=new DijkstraShortestPath<Arret,Liaison>(graphe,source,target);
	  List<Liaison> path=pathFinder.getPathEdgeList();
	  return path;
	}


}
