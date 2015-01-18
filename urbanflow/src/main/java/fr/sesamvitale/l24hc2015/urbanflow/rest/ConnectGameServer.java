/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.rest.builder.ReponseConnectBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

/**
 * @author jb
 *
 */
public class ConnectGameServer {

	public static ReponseConnect connect(String botvitale2_SECRET,
			String botvitale2_TOKEN) {
		// appel ws
		String jsonDataInput = "{\"secret\":\"" + botvitale2_SECRET
				+ "\",\"mode\":\"training\"}";
		String reponse = RSClientUtil.sendPOST("http://24hc15.haum.org/api/connect/"
				+ botvitale2_TOKEN, jsonDataInput);
		
		System.out.println("");
		System.out.println( "connect > " + jsonDataInput);
		
		// construction du ReponseConnect
		ReponseConnect reponseConnect = ReponseConnectBuilder.getReponseConnect(reponse);
		
		return reponseConnect;
	}

}
