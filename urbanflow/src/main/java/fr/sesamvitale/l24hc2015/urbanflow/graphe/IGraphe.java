package fr.sesamvitale.l24hc2015.urbanflow.graphe;

import java.util.List;

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.rest.Incident;

public interface IGraphe {
	
	public abstract Deplacement seDeplacer(int source, int target, String heure, String jour);

	public abstract void creerGraphe(Reseau reseau);

	public abstract void gererIncidents(List<Incident> incidents);

}
