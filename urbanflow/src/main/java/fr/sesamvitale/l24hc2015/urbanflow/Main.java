package fr.sesamvitale.l24hc2015.urbanflow;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilderImpl;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Urban Flow");
		
		
		// 0 - Genere la MAP
		MapBuilder mapBuilder = new MapBuilderImpl();
		Reseau reseau = mapBuilder.buildReseau();
		
		// 2 - demande de jeu (connect )
		
		// 3 - Attente debut du jeu (verify)
		//loop attente
		// -> parse verify : statut partie + date + position init + position cible  + url
		
		
		// 4 - jeu (move)
		
		// boucle sur des mouvements
		// verify ? pour tester le timeout de la partie ??
		// 4-1 - recuperation incidents
		// 4-2 - regeneration map / recalcul itineraire
		// 4-3 - move
		// -> parse resultat move : url 
		// 4-4 - affichage
		
		
		
		

		String botvitale2_SECRET = "361581db8f84766d2912241aa17778fce64b91e05db18616877b2d44af3099aa";
		String botvitale2_TOKEN = "ea3b31b7956ba470a0545b79e4069d71";

		String responseBody = connectBOT(botvitale2_SECRET, botvitale2_TOKEN);
		System.out.println(responseBody);
		
		

	}

	private static String connectBOT(String botvitale2_SECRET,
			String botvitale2_TOKEN) {
		String responseBody = null;
		try {
			URL url = new URL("http://24hc15.haum.org/api/connect/"
					+ botvitale2_TOKEN);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"secret\":\"" + botvitale2_SECRET
					+ "\",\"mode\":\"training\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			Scanner scanner;
			String response;
			if (conn.getResponseCode() != 200) {
				scanner = new Scanner(conn.getErrorStream());
				response = "Error From Server \n\n " + conn.getResponseCode();
			} else {
				scanner = new Scanner(conn.getInputStream());
				response = "Response From Server \n\n";
			}
			scanner.useDelimiter("\\Z");
			responseBody = response + scanner.next();
			scanner.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;
	}

}
