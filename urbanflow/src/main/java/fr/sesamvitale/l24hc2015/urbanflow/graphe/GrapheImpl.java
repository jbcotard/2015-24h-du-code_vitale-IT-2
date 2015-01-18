package fr.sesamvitale.l24hc2015.urbanflow.graphe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.HoraireJour;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.rest.Incident;
import fr.sesamvitale.l24hc2015.urbanflow.util.Temps;

public class GrapheImpl implements IGraphe
{
	private static IGraphe instance;
	private DirectedWeightedMultigraph<Arret,Liaison> graphe;
	private HashMap<String, Arret> arrets;
	private Reseau reseau;
	private List<Incident> incidents;

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
		this.reseau = reseau;
		graphe = new DirectedWeightedMultigraph<Arret,Liaison>(Liaison.class);
		arrets = new HashMap<String, Arret>();
		//Reseau reseau = Reseau.getInstance();
		for(Entry<String, Ligne> entry : reseau.getLignes().entrySet()) {
			String nomLigne = entry.getKey();
			Ligne ligne = entry.getValue();
			Arret arretPrecedent = null;
			final List<Entry<String, Arret>> arretsTries = new ArrayList<Entry<String, Arret>>(ligne.getArrets().entrySet());
			Collections.sort(arretsTries, new Comparator<Entry<String, Arret>>() {
				public int compare(final Entry<String, Arret> e1, final Entry<String, Arret> e2) {
					Integer pos1 = Integer.parseInt(e1.getValue().getPosition());
					Integer pos2 = Integer.parseInt(e2.getValue().getPosition());
					return pos1.compareTo(pos2);
				}
			});
			for (final Entry<String, Arret> entryLigne : arretsTries) {
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
		HashMap<String, HoraireJour> horairesArrets = reseau.getHoraires(source, target, ligne,jour);
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
		if (numArret+1>=horairesDestination.getHoraires().size()){
			return horairesDestination.getHoraires().get(numArret);
		}
		return horairesDestination.getHoraires().get(numArret+1);
	}
	
	private String getHoraireDepart(String tempsDepart, Arret source, String ligne, String jour){
		HashMap<String, HoraireJour> horairesArrets = reseau.getHoraires(source, null, ligne,jour);
		HoraireJour horairesSource = horairesArrets.get(source.getId());
		for (int j=0;j<horairesSource.getHoraires().size();j++){
			String tempsArret = horairesSource.getHoraires().get(j);
			if (Temps.isPosterieur(Temps.convertStringToTemps(tempsArret), Temps.convertStringToTemps(tempsDepart)) == 1){
				return tempsArret;
			}
		}
		return "";
	}

	private int calculerTempsDeuxArretsConsecutifs(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		String horairesMinimumDestination = getHoraire(tempsDepart, source, target, ligne, jour);

		return Temps.getDuree(horairesMinimumDestination, tempsDepart);
	}

	private int calculerPonderationDeuxArretsConsecutifs(String tempsDepart, Arret source, Arret target, String ligne, String jour)
	{
		int temps = calculerTempsDeuxArretsConsecutifs(tempsDepart, source, target, ligne, jour);
		int incidents = calculerIncidents(source, tempsDepart);
		return temps+incidents;
	}

	private void mettreAJourPonderations(String tempsDepart, String jour){
		Set<Liaison> edges = graphe.edgeSet();

		for (Liaison e : edges) {
			int ponderation = calculerPonderationDeuxArretsConsecutifs(tempsDepart,e.getSource(),e.getTarget(),e.getLigne(),jour);
			e.setWeight(ponderation);
		}
	}

	private int calculerIncidents(Arret arret, String temps){
		if (null == incidents) {
			return 0;
		}
		int tempsTT = 0;
		//parcourt de la liste des incidents
		for (int i=0;i<incidents.size();i++){
			Incident incident = incidents.get(i);
			if (incident.getArretId().equals(arret.getId())){
				if ((Temps.isPosterieur(Temps.convertStringToTemps(incident.getDateStart()), 
						Temps.convertStringToTemps(temps)) == -1)&&
						(Temps.isPosterieur(Temps.convertStringToTemps(temps), 
								Temps.convertStringToTemps(incident.getDateEnd())) == -1)){
					tempsTT+= incident.getPenalty();
				}
			}
		}
		return tempsTT;
	}

	public Deplacement seDeplacer(int s, int t, String heure, String jour){
		mettreAJourPonderations(heure, jour);
		Arret source = arrets.get(""+s);
		Arret target = arrets.get(""+t);

		Liaison chemin = dijkstraShortestPath(source, target);

		Arret prochain = chemin.getTarget();
		if (null != prochain){
			Deplacement d = new Deplacement();
			d.setNumArret(prochain.getId());
			Ligne l = reseau.getLignes().get(chemin.getLigne());
			d.setNumLigne(l.getNumero_de_ligne());
			d.setConnexion(getHoraireDepart(heure, source, chemin.getLigne(), jour));
			return d;
		}
		return null;
	}

	/** 
	 * @return shortest path between two connected spot, using Dijkstra's algorithm.The edge weights, if any, are ignored here, meaning that the returned path is the shortest in terms of number of edges. <p> Return <code>null</code> if the two spots are not connected by a track, or if  one of the spot do not belong to the graph, or if the  {@link #graph} field is<code>null</code>.
	 * @param source  the spot to start the path with
	 * @param target  the spot to stop the path with
	 */
	private Liaison dijkstraShortestPath(final Arret source,final Arret target){
		if (null == graphe) {
			return null;
		}
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graphe);
		dijkstra.execute(source);
		Liaison arret = dijkstra.getNextArret(target);
		return arret;
	}

	@Override
	public void gererIncidents(List<Incident> incidents) {
		this.incidents = incidents;
		
	}


}
