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

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.GrapheImpl;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.IGraphe;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilderImpl;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ConnectGameServer;
import fr.sesamvitale.l24hc2015.urbanflow.rest.MoveGameServer;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseMove;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseVerify;
import fr.sesamvitale.l24hc2015.urbanflow.rest.VerifyGameServer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Urban Flow");
		System.out.println("===================================");

		String equipe_TOKEN = "482f6c3c786e98f89dbe6e7e1ace1aef717949dc3f92fa01870fdd8ea6e18f96";
		String botvitale2_SECRET = "361581db8f84766d2912241aa17778fce64b91e05db18616877b2d44af3099aa";
		String botvitale2_TOKEN = "ea3b31b7956ba470a0545b79e4069d71";

		// 0 - Genere la MAP
		System.out.println("");
		System.out.println("Génération de la MAP");
		MapBuilder mapBuilder = new MapBuilderImpl();
		Reseau reseau = mapBuilder.buildReseau();

		IGraphe graphe = GrapheImpl.getInstance();
		graphe.creerGraphe(reseau);

		// 2 - demande de jeu (connect )
		// String responseBody = connectBOT(botvitale2_SECRET,
		// botvitale2_TOKEN);
		// System.out.println(responseBody);

		System.out.println("");
		System.out.println("Demande d'un jeu");
		ReponseConnect reponseConnect = ConnectGameServer.connect(
				botvitale2_SECRET, botvitale2_TOKEN);

		// 3 - Attente debut du jeu (verify)
		// loop attente
		// -> parse verify : statut partie + date + position init + position
		// cible + url

		System.out.println("");
		System.out.println("Vérification du début du jeu");

		ReponseVerify reponseVerify;
		do {
			reponseVerify = VerifyGameServer.verify(
					reponseConnect.getURrlVerify(), botvitale2_SECRET);
		} while (!reponseVerify.isGameStarting());

		// 4 - jeu (move)

		// boucle sur des mouvements
		// 4-0 - verify : pour tester le timeout de la partie
		// ou à la fin ????
		// 4-1 - recuperation incidents
		// 4-2 - regeneration map / recalcul itineraire
		// 4-3 - move
		// -> parse resultat move : url
		// 4-4 - affichage

		System.out.println("");
		System.out.println("Démarrage du jeu");

		ReponseMove reponseMove;
		int compteur = 0;
		do {
			// gestion des incidents
			//IncidentGameServer.incidents(equipe_TOKEN);

			// calcul itineraire - deplacement suivant
			Deplacement deplacement = graphe.seDeplacer(
					reponseVerify.getFirstStop(), reponseVerify.getTarget(),
					reponseVerify.getHeure(), reponseVerify.getJour());

			// deplacement suivant
			reponseMove = MoveGameServer.move(reponseConnect.getURrlVerify(),
					botvitale2_SECRET, deplacement.getNumLigne(),
					deplacement.getConnexion(), deplacement.getNumArret(),
					"move");

			++compteur;

			// display
			System.out.println("");
			System.out.println("Mouvement");
			System.out.println("de " + reponseVerify.getFirstStop()  + " vers " + deplacement.getNumArret());
			

			// verification partie encore active
			reponseVerify = VerifyGameServer.verify(
					reponseConnect.getURrlVerify(), botvitale2_SECRET);
		} while (reponseVerify.isGameStarting());

		System.out.println("");
		System.out.println("Fin du jeu");

		// affichage SCORE

		System.out.println("");
		System.out.println("SCORE :");
		System.out.println("nb mouvements : " + compteur);

	}

	/*
	 * private static String connectBOT(String botvitale2_SECRET, String
	 * botvitale2_TOKEN) { String responseBody = null; try { URL url = new
	 * URL("http://24hc15.haum.org/api/connect/" + botvitale2_TOKEN);
	 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * conn.setDoOutput(true); conn.setRequestMethod("POST");
	 * conn.setRequestProperty("Content-Type", "application/json");
	 * 
	 * String input = "{\"secret\":\"" + botvitale2_SECRET +
	 * "\",\"mode\":\"training\"}";
	 * 
	 * OutputStream os = conn.getOutputStream(); os.write(input.getBytes());
	 * os.flush();
	 * 
	 * Scanner scanner; String response; if (conn.getResponseCode() != 200) {
	 * scanner = new Scanner(conn.getErrorStream()); response =
	 * "Error From Server \n\n " + conn.getResponseCode(); } else { scanner =
	 * new Scanner(conn.getInputStream()); response =
	 * "Response From Server \n\n"; } scanner.useDelimiter("\\Z"); responseBody
	 * = response + scanner.next(); scanner.close(); conn.disconnect(); } catch
	 * (MalformedURLException e) { e.printStackTrace(); } catch (IOException e)
	 * { e.printStackTrace(); } return responseBody; }
	 */

}
