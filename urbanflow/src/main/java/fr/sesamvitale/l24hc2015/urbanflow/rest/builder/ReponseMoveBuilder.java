/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;
import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseMove;

/**
 * @author jb
 *
 */
public class ReponseMoveBuilder {
	/*
	 * { Le bot s"est déplacé "success": True, "status": "moved", "message":
	 * "The request was correct. Your bot has moved.", "stop": {"id": <stop_id>,
	 * "name": <stop_name>}, "time": datetime_str
	 * 
	 * 
	 * Le bot s"est déplacé mais sa cible a change
	 * 
	 * "success": True, "status": "moved", "message":
	 * "The request was correct. Your bot has moved.", "stop": {"id": <stop_id>,
	 * "name": <stop_name>}, "time": datetime_str
	 * 
	 * Cible atteinte "success": True, "status": "arrived", "score":
	 * <bot_score>, "message": "Your bot has reached the target, congrats!"
	 */
	public static ReponseMove getReponseConnect(String jsonData) {
		ReponseMove rc = new ReponseMove();

		System.out.println(jsonData);

		JSONObject obj = new JSONObject(jsonData);
		rc.setStatus(obj.getString("status"));
		rc.setMessage(obj.getString("message"));
		rc.setSuccess(obj.getBoolean("success"));
		rc.setTime(obj.getString("time"));

		if (!rc.isSuccess()) {
			rc.setStopId(obj.getJSONObject("stop").getInt("id"));
			rc.setStopName(obj.getJSONObject("stop").getString("name"));
			rc.setPenality(obj.getInt("penality"));
		} else {
			if ("moved".equals(rc.getStatus())
					|| "rerouted".equals(rc.getStatus())) {
				rc.setStopId(obj.getJSONObject("stop").getInt("id"));
				rc.setStopName(obj.getJSONObject("stop").getString("name"));
			}

			if ("rerouted".equals(rc.getStatus())) {
				rc.setTarget(obj.getInt("target"));
			}

			if ("arrived".equals(rc.getStatus())) {
				rc.setScore(obj.getString("score"));
			}
		}
		if (!"arrived".equals(rc.getStatus())) {

			String dateInString = obj.getString("time");

			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-M-dd'T'HH:mm:ssXXX");

			Date date;
			try {
				date = sdf.parse(dateInString);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				SimpleDateFormat sdfD = new SimpleDateFormat("HH:mm:ss");
				rc.setTimeHeure(sdfD.format(date));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rc;
	}

}
