package fr.sesamvitale.l24hc2015.urbanflow.graphe;

import java.util.List;

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.rest.Incident;

public interface IGraphe {
	
	/**
	 * Demande d'un mouvement
	 * @param source id de l'arret source
	 * @param target id de l'arret target
	 * @param heure hh:mm:ss actuel
	 * @param jour abréviation du jour
	 * @return Deplacement a effectuer
	 */
	public abstract Deplacement seDeplacer(int source, int target, String heure, String jour);

	/**
	 * Création du graphe
	 * @param reseau réseau de la setram
	 */
	public abstract void creerGraphe(Reseau reseau);

	/**
	 * Ajout des incidents
	 * @param incidents liste d'incidents
	 */
	public abstract void gererIncidents(List<Incident> incidents);

}
