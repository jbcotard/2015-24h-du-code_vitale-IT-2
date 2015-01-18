/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.HoraireJour;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.GrapheImpl;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.IGraphe;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilderImpl;
import fr.sesamvitale.l24hc2015.urbanflow.rest.Incident;

/**
 * @author user
 *
 */
public class TestDijkstra {

	@Test
	public void testAlgorithme() {
		MapBuilder mapBuilder = new MapBuilderImpl();
		Reseau r = mapBuilder.buildReseau();
		IGraphe g = GrapheImpl.getInstance();
		//g.gererIncidents(getIncidents());
		g.creerGraphe(r);
		int arrivee = 1246;
		int depart = 1232;
		System.out.println("Départ du 1232 a 06:37:00");
		
		do{
			Deplacement d = g.seDeplacer(depart, arrivee, "07:17:00", "je");
			System.out.println("Ligne "+d.getNumLigne());
			System.out.println("Arret "+d.getNumArret());
			System.out.println("Horaire "+d.getConnexion());
			System.out.println();
			depart = Integer.parseInt(d.getNumArret());
		}while(depart != arrivee);
		


	}

	private List<Incident> getIncidents(){
		List<Incident> liste = new ArrayList<Incident>();
		try {
			// create new file
			File file = new File("src/test/resources/incidents.json");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

			BufferedReader br = new BufferedReader(isr);
			String jsonData = br.readLine();

			JSONObject obj = new JSONObject(jsonData);

			JSONArray listeIncidents = obj.getJSONArray("incidents");
			for (int i = 0; i < listeIncidents.length(); i++) {
				Incident incident = new Incident();
				JSONObject a = listeIncidents.getJSONObject(i);
				int penality = a.getInt("penalty");
				String end = a.getString("dtend");
				String debut = a.getString("dtstart");
				JSONObject arret = a.getJSONObject("stop");
				incident.setDateEnd(end);
				incident.setDateStart(debut);
				incident.setPenalty(penality);
				incident.setArretId(""+arret.getInt("id"));
				liste.add(incident);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;

	}

}
