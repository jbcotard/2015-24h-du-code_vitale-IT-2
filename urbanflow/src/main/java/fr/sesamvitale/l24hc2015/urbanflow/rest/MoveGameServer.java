package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.rest.builder.ReponseConnectBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.rest.builder.ReponseMoveBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

public class MoveGameServer {

	public static ReponseMove move(String uRrlVerify, String botvitale2_SECRET,
			String numLigne, String connexion, String numArret, String type) {
		// appel ws
		String reponse = RSClientUtil.sendPOST("http://24hc15.haum.org" + uRrlVerify
				, "{\"secret_token\":\"" + botvitale2_SECRET
				+ "\", \"track\":\""+ numLigne 
				+ "\", \"connection\":\"" + connexion 
				+ "\", \"to_stop\":\"" + numArret
				+ "\", \"type\":\"" + type
				+ "\"}");
		
		// contruction ReponseMove
		ReponseMove reponseMove = ReponseMoveBuilder.getReponseConnect(reponse);;
		
		return reponseMove;
	}

}
