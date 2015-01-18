package fr.sesamvitale.l24hc2015.urbanflow.data;

import java.util.HashMap;
import java.util.List;

/**
 * Modélisation d'une ligne de bus/tram
 * @author user
 *
 */
public class Ligne {
	private String identifiant;
	private String numero_de_ligne;
	private String nombre_d_arrets;
	private Arret destination;
	private HashMap<String, Arret> arrets;
	private HashMap<String,List<HoraireJour>> horaires; // clé = numero de larret
	
	public Ligne(){
		horaires = new HashMap<String,List<HoraireJour>>();
		arrets = new HashMap<String, Arret>();
	}
	
	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	/**
	 * @return the numero_de_ligne
	 */
	public String getNumero_de_ligne() {
		return numero_de_ligne;
	}
	
	/**
	 * @param numero_de_ligne the numero_de_ligne to set
	 */
	public void setNumero_de_ligne(String numero_de_ligne) {
		this.numero_de_ligne = numero_de_ligne;
	}
	
	/**
	 * @return the nombre_d_arrets
	 */
	public String getNombre_d_arrets() {
		return nombre_d_arrets;
	}
	
	/**
	 * @param nombre_d_arrets the nombre_d_arrets to set
	 */
	public void setNombre_d_arrets(String nombre_d_arrets) {
		this.nombre_d_arrets = nombre_d_arrets;
	}
	
	/**
	 * @return the destination
	 */
	public Arret getDestination() {
		return destination;
	}
	
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Arret destination) {
		this.destination = destination;
	}
	
	/**
	 * @return the arrets
	 */
	public HashMap<String, Arret> getArrets() {
		return arrets;
	}
	
	/**
	 * @param arrets the arrets to set
	 */
	public void ajouterArrets(String identifiantArret, Arret arrets) {
		this.arrets.put(identifiantArret, arrets);
	}
	
	/**
	 * @return the horaires
	 */
	public List<HoraireJour> getHoraires(String arret) {
		return horaires.get(arret);
	}
	
	/**
	 * @param horaires the horaires to set
	 */
	public void ajouterHoraires(List<HoraireJour> horaires, String numArret ) {
		this.horaires.put(numArret,horaires);
	}
}