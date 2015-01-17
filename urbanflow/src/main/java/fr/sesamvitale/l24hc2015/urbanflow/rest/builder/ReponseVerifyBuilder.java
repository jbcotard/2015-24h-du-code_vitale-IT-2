package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseVerify;

public class ReponseVerifyBuilder {

	/**
	 * {"status": "game_started", "first_stop": {"id": 1341, "name": "Folie"},
	 *  "target": {"id": 1248, "name": "Leclerc-fleurus"}, 
	 *  "success": true, "time": 0, 
	 *  "url": "/api/play/b78f9a12e99a595c11f10ad5bb356a81/ea3b31b7956ba470a0545b79e4069d71/move",
	 *   "message": "The game has started !",
	 *    "dtstart": "2015-01-17T16:39:00+00:00"}
	 * 
	 * @param jsonData
	 * @return
	 */
	private static String[] semaine = {"di", "lu","ma", "me", "je","ve", "sa"};
	
	public static ReponseVerify getReponseVerify(String jsonData) {
		ReponseVerify rc = new ReponseVerify();

		JSONObject obj = new JSONObject(jsonData);
		rc.setStatus(obj.getString("status"));
		rc.setFirstStop(obj.getJSONObject("first_stop").getInt("id"));
		rc.setTarget(obj.getJSONObject("target").getInt("id"));
		rc.setMessage(obj.getString("message"));
		rc.setSuccess(obj.getBoolean("success"));
		String dateInString = obj.getString("dtstart");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd'T'HH:mm:ssXXX");
		
		Date date;
		try {
			date = sdf.parse(dateInString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			int d = calendar.get(Calendar.DAY_OF_WEEK);
			int h = calendar.get(Calendar.HOUR_OF_DAY);
			int m = calendar.get(Calendar.MINUTE );
			int s = calendar.get(Calendar.SECOND);
			
			rc.setJour(semaine[d - 1]);
			
			SimpleDateFormat sdfD = new SimpleDateFormat("HH:mm:ss");
			rc.setHeure(sdfD.format(date));
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return rc;
	}
}
