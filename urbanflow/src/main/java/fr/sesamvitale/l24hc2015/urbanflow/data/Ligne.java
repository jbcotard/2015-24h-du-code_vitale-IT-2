package fr.sesamvitale.l24hc2015.urbanflow.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ligne {
	private String identifiant;
	private String numero_de_ligne;
	private String nombre_d_arrets;
	private Arret destination;
	private HashMap<Integer, Arret> arrets;
	private List<HoraireJour> horaires;
	
	public Ligne(){
		horaires = new ArrayList<HoraireJour>();
		arrets = new HashMap<Integer, Arret>();
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
	public HashMap<Integer, Arret> getArrets() {
		return arrets;
	}
	/**
	 * @param arrets the arrets to set
	 */
	public void ajouterArrets(int identifiantArret, Arret arrets) {
		this.arrets.put(identifiantArret, arrets);
	}
	/**
	 * @return the horaires
	 */
	public List<HoraireJour> getHoraires() {
		return horaires;
	}
	/**
	 * @param horaires the horaires to set
	 */
	public void ajouterHoraires(HoraireJour horaires) {
		this.horaires.add(horaires);
	}

	
}