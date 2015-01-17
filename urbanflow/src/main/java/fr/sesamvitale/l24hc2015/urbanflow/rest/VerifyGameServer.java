/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

/**
 * @author jb
 *
 */
public class VerifyGameServer {

	public static ReponseVerify verify(String uRrlVerify,
			String botvitale2_SECRET) {
		// appel ws
		String reponse = RSClientUtil.sendPOST("http://24hc15.haum.org" + uRrlVerify
				, "{\"secret_token\":\"" + botvitale2_SECRET
				+ "\"}");
		// contruction ReponseVerify
		ReponseVerify reponseVerify = null;
		
		return reponseVerify;
	}

}
