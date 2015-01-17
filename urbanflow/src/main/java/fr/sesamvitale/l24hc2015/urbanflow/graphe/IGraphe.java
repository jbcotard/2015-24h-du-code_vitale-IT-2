package fr.sesamvitale.l24hc2015.urbanflow.graphe;

import java.util.HashMap;
import java.util.Map.Entry;

import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;

public interface IGraphe {
	
	public Deplacement seDeplacer(int source, int target, String heure, String jour);

	public abstract void creerGraphe(Reseau reseau);

}
