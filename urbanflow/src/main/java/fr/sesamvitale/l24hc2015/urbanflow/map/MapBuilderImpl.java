package fr.sesamvitale.l24hc2015.urbanflow.map;

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

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.HoraireJour;
import fr.sesamvitale.l24hc2015.urbanflow.data.Ligne;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;

public class MapBuilderImpl implements MapBuilder {

	String[] jours = { "lu", "ma", "me", "je", "ve", "sa", "di" };

	@Override
	public Reseau buildReseau() {
		File directory = null;
		File[] paths;

		Reseau reseau = Reseau.getInstance();
		try {
			// create new file
			directory = new File("src/main/resources/data");
			paths = directory.listFiles();
			for (File file : paths) {
				if (file.getAbsolutePath().endsWith("json")) {
					FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

					BufferedReader br = new BufferedReader(isr);
					String jsonData = br.readLine();

					Ligne ligne = new Ligne();
					JSONObject obj = new JSONObject(jsonData);
					ligne.setIdentifiant(""+obj.getInt("track_id"));
					ligne.setNumero_de_ligne(obj.getString("track_number"));
					ligne.setNombre_d_arrets(""+obj.getInt("n_stops"));

					reseau.getLignes().put(ligne.getIdentifiant(), ligne);

					JSONObject track_dest = obj.getJSONObject("track_dest");

					String idDest = ""+track_dest.getInt("id");

					Arret destination = null;

					JSONObject schedule = obj.getJSONObject("schedule");

					JSONArray stops = obj.getJSONArray("stops");
					for (int i = 0; i < stops.length(); i++) {
						JSONObject a = stops.getJSONObject(i);
						String idArret = ""+a.getInt("id");
						Arret arret = new Arret(idArret, a.getString("name"),
								""+a.getInt("position"));

						if (idDest.equals(idArret)) {
							destination = arret;

							ligne.setDestination(destination);
						}
						ligne.ajouterArrets(idArret, arret);
						Iterator<String> keys = schedule.keys();
						while(keys.hasNext()){
							String key = keys.next();
							try{
								JSONObject scheduleArret = schedule.getJSONObject(key);
								List<HoraireJour> horaireJours = new ArrayList<HoraireJour>();

								for (int ij = 0; ij < jours.length; ij++) {
									String jour = jours[ij];
									JSONArray horaires = scheduleArret.getJSONArray(jour);
									HoraireJour horaireJour = new HoraireJour();
									horaireJour.setJour(jour);

									for (int ihj = 0; ihj < horaires.length(); ihj++) {
										String horaire = horaires.getString(ihj);
										horaireJour.ajouterHoraire(horaire);

										ligne.ajouterHoraires(horaireJours, key);
									}
								}

							}catch(Exception e){

							}

						}
					}

				}
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

		return reseau;
	}

}
