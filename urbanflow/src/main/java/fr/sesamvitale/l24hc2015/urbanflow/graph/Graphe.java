package fr.sesamvitale.l24hc2015.urbanflow.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;

public class Graphe 
{
	private static Graphe instance;
	private DirectedWeightedMultigraph<Arret,Liaison> graphe;
	
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
		//Parser les fichiers JSON
		
	}
	
	private void ajouterArret(Arret a)
	{
		graphe.addVertex(a);
	}
	
	private void ajouterLiaison(Liaison l)
	{
		graphe.addEdge(l.getSource(),l.getTarget(),l);
	}
}
