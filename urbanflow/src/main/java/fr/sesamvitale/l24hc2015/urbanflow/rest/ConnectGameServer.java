/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

/**
 * @author jb
 *
 */
public class ConnectGameServer {

	public static ReponseConnect connect(String botvitale2_SECRET,
			String botvitale2_TOKEN) {
		// appel ws
		String reponse = RSClientUtil.sendPOST("http://24hc15.haum.org/api/connect/"
				+ botvitale2_TOKEN, "{\"secret\":\"" + botvitale2_SECRET
				+ "\",\"mode\":\"training\"}");
		
		// constrution du ReponseConnect
		ReponseConnect reponseConnect = null;
		
		return reponseConnect;
	}

}
