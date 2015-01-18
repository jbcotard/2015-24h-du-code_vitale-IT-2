package fr.sesamvitale.l24hc2015.urbanflow;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import fr.sesamvitale.l24hc2015.urbanflow.rest.IncidentsGameServer;
import fr.sesamvitale.l24hc2015.urbanflow.rest.MoveGameServer;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseIncidents;
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

		// 2bis - gestion des incidents
		// gestion des incidents
		// ReponseIncident reponseInsident =
		// IncidentGameServer.incidents(equipe_TOKEN);
		// graphe.addIncidents(reponseInsident.getIncidents());
		ReponseIncidents reponseInsidents = IncidentsGameServer.incidents(reponseConnect.getGameToken());
		graphe.gererIncidents(reponseInsidents.getIncidents());

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
		// 4-2 - regeneration map / recalcul itineraire
		// 4-3 - move
		// -> parse resultat move : url
		// 4-4 - affichage

		System.out.println("");
		System.out.println("Démarrage du jeu");

		ReponseMove reponseMove;
		int compteur = 0;
		int currentStopId = reponseVerify.getFirstStop();
		int currentTarget = reponseVerify.getTarget();
		String currentConnexion = reponseVerify.getHeure();

		System.out.println("");
		System.out.println("Position initiale : " + reponseVerify.toString());

		do {

			// calcul itineraire - deplacement suivant
			Deplacement deplacement = graphe.seDeplacer(currentStopId,
					currentTarget, currentConnexion, reponseVerify.getJour());

			System.out.println("");
			System.out.println("calcul deplacement : " + deplacement);

			// deplacement suivant
			reponseMove = MoveGameServer.move(
					reponseVerify.getUrlMove(),
					botvitale2_SECRET,
					deplacement.getNumLigne(),
					reponseVerify.getConnexionDay() + " "
							+ reponseVerify.getMonthName() + " "
							+ deplacement.getConnexion() + " "
							+ reponseVerify.getConnexionYear(),
					/*deplacement.getNumArret()*/ String.valueOf(currentStopId), "move");

			// analyse reponse move
			currentStopId = reponseMove.getStopId();
			if (reponseMove.isRerouted()) {
			currentTarget = reponseMove.getTarget();
			}
			currentConnexion = reponseMove.getTimeHeure();

			++compteur;

			// display
			System.out.println("");
			System.out.println("Mouvement " + reponseMove.toString());
			System.out
					.println("de " + currentStopId + " vers " + currentTarget);
			System.out.println(reponseMove.toString());

			// verification partie encore active
			// reponseVerify = VerifyGameServer.verify(
			// reponseConnect.getURrlVerify(), botvitale2_SECRET);
		} while (!reponseMove.isArrived());

		System.out.println("");
		System.out.println("Fin du jeu");

		// affichage SCORE

		System.out.println("");
		System.out.println("SCORE : " + reponseMove.getScore());
		System.out.println("nb mouvements : " + compteur);

	}
}
