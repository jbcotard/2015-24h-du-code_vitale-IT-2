/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

import fr.sesamvitale.l24hc2015.urbanflow.rest.builder.ReponseVerifyBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.util.RSClientUtil;

/**
 * @author jb
 *
 */
public class VerifyGameServer {

	public static ReponseVerify verify(String uRrlVerify,
			String botvitale2_SECRET) {
		// appel ws
		String jsonDataInput = "{\"secret_token\":\"" + botvitale2_SECRET
				+ "\"}";
		String reponse = RSClientUtil.sendPOST("http://24hc15.haum.org" + uRrlVerify
				, jsonDataInput);
		
		System.out.println("");
		System.out.println( "verify > " + jsonDataInput);
		
		// contruction ReponseVerify
		ReponseVerify reponseVerify = ReponseVerifyBuilder.getReponseVerify(reponse);
		
		return reponseVerify;
	}

}
