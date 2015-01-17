package fr.sesamvitale.l24hc2015.urbanflow.rest;

import java.util.ArrayList;
import java.util.List;

public class ReponseIncidents {

	private String status;
	private boolean success;
	private String gameToken;
	
	
	private List<Incident> incidents;
	
	public ReponseIncidents() {
		incidents = new ArrayList<Incident>();
	}

	public void ajouterIncident(Incident incident) {
		incidents.add(incident);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getGameToken() {
		return gameToken;
	}

	public void setGameToken(String gameToken) {
		this.gameToken = gameToken;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
