package fr.sesamvitale.l24hc2015.urbanflow.graphe;

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;

public interface IGraphe {
	
	public Deplacement seDeplacer(int source, int target, String heure, String jour);

	public abstract void creerGraphe(Reseau reseau);

}
