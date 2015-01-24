package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.sesamvitale.l24hc2015.urbanflow.rest.Incident;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseIncidents;

public class ReponseIncidentBuilder {

	public static ReponseIncidents getReponseIncident(String jsonData) {
		ReponseIncidents ris = new ReponseIncidents();

		
		System.out.println("    << " + jsonData);
		JSONObject obj = new JSONObject(jsonData);

		//JSONArray incidents = obj.getJSONArray("incidents");
		
		ris.setStatus(obj.getString("status"));
		//ris.setGameToken(obj.getString("game_token"));
		ris.setSuccess(obj.getBoolean("success"));
		
//		for (int i = 0; i < incidents.length(); i++) {
//			JSONObject jsonIncident = incidents.getJSONObject(i);
//			
//			Incident incident = new Incident();
//			
//			incident.setPenalty(jsonIncident.getInt("penalty"));
//			incident.setDateEnd(jsonIncident.getString("dtend"));
//			incident.setDateStart(jsonIncident.getString("dtstart"));
//			incident.setType(jsonIncident.getString("type"));
//			
//			incident.setArretId(""+jsonIncident.getJSONObject("stop").getInt("id"));
//			incident.setArretName(jsonIncident.getJSONObject("stop").getString("name"));
//			
//			ris.ajouterIncident(incident);
//		}

		return ris;
	}
}
/*
{    "status": "incident_list",
    "incidents":
    [{
    "penalty": 60,
    "stop":
    {
        "id": <stop_id>,
        "name": <stop_name>
    },
    "dtend": datetime_str,
    "dtstart": datetime_str,
    "type": "accident"
    },
    {
    "penalty": 120,
    "stop":
    {
        "id": <stop_id>,
        "name": <stop_name>
    },
    "dtend": datetime_str,
    "dtstart": datetime_str,
    "type": "travaux"
    },
    {
    "penalty": 10,
    "stop":
    {
        "id": <stop_id>,
        "name": <stop_name>
    },
    "dtend": datetime_str,
    "dtstart": datetime_str,
    "type": "malaise"
    },
    {
    "penalty": <stop_id>,
    "stop":
    {
        "id": <stop_id>,
        "name": <stop_name>
    },
    "dtend": datetime_str,
    "dtstart": datetime_str,
    "type": "manif"
    },
    {
    "penalty": 480,
    "stop":
    {
        "id": <stop_id>,
        "name": <stop_name>
    },
    "dtend": ,
    "dtstart": datetime_str,
    "type": "greve"
    }],
    "game_token": <game_token>,
    "success": true
    }

}*/
