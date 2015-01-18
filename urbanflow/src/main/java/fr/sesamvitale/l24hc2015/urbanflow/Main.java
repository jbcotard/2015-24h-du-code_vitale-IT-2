package fr.sesamvitale.l24hc2015.urbanflow;

import org.json.JSONException;

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

		String botvitale3_TOKEN = "5ea07f975cb3bdf02d73174a9e79a754";
		String botvitale3_SECRET = "1b1813ac005fc70c505524d5e3294aae6cc453eaffbdb025b0d3d5616ec1d2f5";

		String botvitale4_TOKEN = "12cc040653de50b60bea6563e68629dc";
		String botvitale4_SECRET = "3569c2dae3e3116877c9b41fd81e73cf983197a24d4261cca0190eb856758e41";

		String MODE_GAME = "arena";

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
		System.out.println("Demande d'un jeu en mode " + MODE_GAME);
		ReponseConnect reponseConnect = ConnectGameServer.connect(
				botvitale2_SECRET, botvitale2_TOKEN, MODE_GAME);

//		if ("arena".equals(MODE_GAME)) {
//			reponseConnect = ConnectGameServer.connect(botvitale3_SECRET,
//					botvitale3_TOKEN, MODE_GAME);
//			reponseConnect = ConnectGameServer.connect(botvitale4_SECRET,
//					botvitale4_TOKEN, MODE_GAME);
//		}

		// 2bis - gestion des incidents
		// gestion des incidents
		// ReponseIncident reponseInsident =
		// IncidentGameServer.incidents(equipe_TOKEN);
		// graphe.addIncidents(reponseInsident.getIncidents());
		ReponseIncidents reponseInsidents = IncidentsGameServer
				.incidents(reponseConnect.getGameToken());
		graphe.gererIncidents(reponseInsidents.getIncidents());

		// 3 - Attente debut du jeu (verify)
		// loop attente
		// -> parse verify : statut partie + date + position init + position
		// cible + url

		System.out.println("");
		System.out.println("Vérification du début du jeu");

		ReponseVerify reponseVerify;
		if ("arena".equals(MODE_GAME)) {
			System.out.println("en attente d'autres BOT ...");
		}
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

		ReponseMove reponseMove = null;
		int compteur = 0;
		int nextStopId = reponseVerify.getFirstStop();
		int currentTarget = reponseVerify.getTarget();
		String currentTime = reponseVerify.getHeure();
		int currentStopId = 0;

		System.out.println("");
		System.out.println("Position initiale : " + reponseVerify.toString());

		System.out.println("");
		System.out.println("graphe > nextStopId: " + nextStopId
				+ " currentTarget:" + currentTarget + " currentTime:"
				+ currentTime + " reponseVerify.getJour():"
				+ reponseVerify.getJour());

		// calcul itineraire - 1er deplacement
		Deplacement deplacement = graphe.seDeplacer(nextStopId, currentTarget,
				currentTime, reponseVerify.getJour());

		System.out.println("    << " + deplacement);

		boolean isFirst = true;
		boolean isFin = false;
		// deplacement.setConnexion(currentTime);
		do {

			// try {
			// deplacement suivant
			reponseMove = MoveGameServer.move(
					reponseVerify.getUrlMove(),
					botvitale2_SECRET,
					deplacement.getNumLigne(),
					reponseVerify.getConnexionDay() + " "
							+ reponseVerify.getMonthName() + " "
							+ deplacement.getConnexion() + " "
							+ reponseVerify.getConnexionYear(),
					String.valueOf(nextStopId), "move");
			// } catch (JSONException e) {
			// // Retry
			// reponseMove = MoveGameServer.move(
			// reponseVerify.getUrlMove(),
			// botvitale2_SECRET,
			// deplacement.getNumLigne(),
			// reponseVerify.getConnexionDay() + " "
			// + reponseVerify.getMonthName() + " "
			// + deplacement.getConnexion() + " "
			// + reponseVerify.getConnexionYear(),
			// String.valueOf(nextStopId), "move");
			// }

			// analyse reponse move
			currentStopId = reponseMove.getStopId();
			if (reponseMove.isRerouted()) {
				currentTarget = reponseMove.getTarget();
			}
			currentTime = reponseMove.getTimeHeure();

			++compteur;

			if (currentStopId == currentTarget) {
				isFin = true;
			} else {

				// display
				System.out.println("");
				System.out.println("graphe > currentStopId: " + currentStopId
						+ " currentTarget:" + currentTarget + " currentTime:"
						+ currentTime + " reponseVerify.getJour():"
						+ reponseVerify.getJour());

				// calcul itineraire - deplacement suivant
				deplacement = graphe.seDeplacer(currentStopId, currentTarget,
						currentTime, reponseVerify.getJour());

				// if (isFirst) {
				// deplacement.setConnexion(currentTime);
				// isFirst = false;
				// }

				System.out.println("    << " + deplacement);

				nextStopId = Integer.parseInt(deplacement.getNumArret());
			}
			// verification partie encore active
			// reponseVerify = VerifyGameServer.verify(
			// reponseConnect.getURrlVerify(), botvitale2_SECRET);
		} while (!reponseMove.isArrived() && compteur < 50 && !isFin);

		System.out.println("");
		System.out.println("Fin du jeu");

		// affichage SCORE

		System.out.println("");
		System.out.println("SCORE : " + reponseMove.getScore());
		System.out.println("nb mouvements : " + compteur);

	}
}
