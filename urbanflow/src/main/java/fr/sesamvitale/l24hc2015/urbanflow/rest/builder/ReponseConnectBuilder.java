package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import org.json.JSONException;
import org.json.JSONObject;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;

public class ReponseConnectBuilder {

	/*
	 * {"status": "registered", "url":
	 * "/api/play/32dcb69ff50409296d30c4a5e2f874e8/ea3b31b7956ba470a0545b79e4069d71/verif"
	 * , "message":
	 * "Your bot is now registered! Check the url to see when you will play.",
	 * "success": true}
	 */

	public static ReponseConnect getReponseConnect(String jsonData) throws JSONException {
		ReponseConnect rc = new ReponseConnect();

		JSONObject obj = new JSONObject(jsonData);
		rc.setStatus(obj.getString("status"));
		rc.setUrlVerify(obj.getString("url"));
		rc.setMessage(obj.getString("message"));
		rc.setSuccess(obj.getBoolean("success"));

		return rc;
	}
}
