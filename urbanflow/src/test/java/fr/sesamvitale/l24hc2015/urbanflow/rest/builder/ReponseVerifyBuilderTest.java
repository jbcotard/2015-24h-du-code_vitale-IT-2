package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseVerify;

public class ReponseVerifyBuilderTest {

	@Test
	public void testGetReponseVerify() {
		
		String jsonData = "{\"status\": \"game_started\", \"first_stop\": {\"id\": 1341, \"name\": \"Folie\"},"
	 +  "\"target\": {\"id\": 1248, \"name\": \"Leclerc-fleurus\"}," 
	 +  "\"success\": true, \"time\": 0, "
	 +  "\"url\": \"/api/play/b78f9a12e99a595c11f10ad5bb356a81/ea3b31b7956ba470a0545b79e4069d71/move\","
	 +   "\"message\": \"The game has started !\","
	 +    "\"dtstart\": \"2015-01-15T16:39:00+00:00\"}";
	 
		ReponseVerify rv =	ReponseVerifyBuilder.getReponseVerify(jsonData);
	
		System.out.println("rv game starting"  + rv.isGameStarting());
		System.out.println("rv "  + rv.getTarget());
		System.out.println("rv Jour "  + rv.getJour());
		System.out.println("rv Heure "  + rv.getHeure());
	}

}
