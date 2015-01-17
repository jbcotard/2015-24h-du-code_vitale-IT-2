package fr.sesamvitale.l24hc2015.urbanflow.data;

import java.util.ArrayList;
import java.util.List;

public class HoraireJour {
	private String jour;
	private List<String> horaires;
	
	public HoraireJour(){
		horaires = new ArrayList<String>();
	}

	/**
	 * @return the jour
	 */
	public String getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(String jour) {
		this.jour = jour;
	}

	/**
	 * @return the horaires
	 */
	public List<String> getHoraires() {
		return horaires;
	}
	
	public void ajouterHoraire(String horaire){
		horaires.add(horaire);
	}

}
