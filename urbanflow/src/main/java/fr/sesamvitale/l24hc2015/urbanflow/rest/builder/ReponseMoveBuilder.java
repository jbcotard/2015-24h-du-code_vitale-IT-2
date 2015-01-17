/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import org.json.JSONObject;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseMove;

/**
 * @author jb
 *
 */
public class ReponseMoveBuilder {
/*{
    "success": True,
    "status": "moved",
    "message": "The request was correct. Your bot has moved.",
    "stop": {"id": <stop_id>, "name": <stop_name>},
    "time": datetime_str
}
 */
	public static ReponseMove getReponseConnect(String jsonData) {
		ReponseMove rc = new ReponseMove();

		JSONObject obj = new JSONObject(jsonData);
		rc.setStatus(obj.getString("status"));
		rc.setMessage(obj.getString("message"));
		rc.setSuccess(obj.getBoolean("success"));
		rc.setTime(obj.getString("time"));
		rc.setStopId(obj.getJSONObject("stop").getInt("id"));
		rc.setStopName(obj.getJSONObject("stop").getString("name"));

		return rc;
	}

}
