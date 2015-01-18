package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.rest.builder.ReponseIncidentBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

public class IncidentsGameServer {

	public static ReponseIncidents incidents(String gameToken) {
		// appel ws
		String reponse = RSClientUtil.sendGET("http://24hc15.haum.org/api/incidents/" + gameToken);
		
		// contruction ReponseIncidents
		ReponseIncidents reponseIncidents = ReponseIncidentBuilder.getReponseIncident(reponse);
		
		return reponseIncidents;
	}
}
