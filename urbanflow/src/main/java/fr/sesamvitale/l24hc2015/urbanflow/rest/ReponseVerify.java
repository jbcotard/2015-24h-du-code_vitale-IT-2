/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

/**
 * @author jb
 *
 */
public class ReponseVerify {

	
	/**
	 * {"status": "game_started", "first_stop": {"id": 1341, "name": "Folie"},
	 *  "target": {"id": 1248, "name": "Leclerc-fleurus"}, 
	 *  "success": true, "time": 0, 
	 *  "url": "/api/play/b78f9a12e99a595c11f10ad5bb356a81/ea3b31b7956ba470a0545b79e4069d71/move",
	 *   "message": "The game has started !",
	 *    "dtstart": "2015-10-28T16:39:00+00:00"}
	 */ 
	
	private static final String GAME_STARTED = "game_started";
	private String status;
	private int firstStop;
	private int target;
	private String heure;
	
	/** Jour de la semaine Me" */
	private String jour;
	private String message;
	private boolean success;
	private String urlMove;
	private String dateConnexion;
	private int connexionDay;
	private String monthName;
	private int connexionYear;

	public boolean isGameStarting() {
		return GAME_STARTED.equals(getStatus());
	}

	public int getFirstStop() {
		
		return firstStop;
	}

	public int getTarget() {
		
		return target;
	}

	public String getHeure() {
		
		return heure;
	}

	public String getJour() {
		
		return jour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFirstStop(int firstStop) {
		this.firstStop = firstStop;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUrlMove(String string) {
		this.urlMove = string;
	}

	/**
	 * @return the urlMove
	 */
	public String getUrlMove() {
		return urlMove;
	}

	public void setDateConnexion(String dateConnexion) {
		this.dateConnexion = dateConnexion;
	}

	/**
	 * @return the dateConnexion
	 */
	public String getDateConnexion() {
		return dateConnexion;
	}



	public void setConnexionDay(int connexionDay) {
		this.connexionDay = connexionDay;
	}

	public void setConnexionMonthName(String monthName) {
		this.monthName = monthName;
	}

	public void setConnexionYear(int connexionYear) {
		this.connexionYear = connexionYear;
	}

	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}

	/**
	 * @param monthName the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	/**
	 * @return the connexionDay
	 */
	public int getConnexionDay() {
		return connexionDay;
	}

	/**
	 * @return the connexionYear
	 */
	public int getConnexionYear() {
		return connexionYear;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReponseVerify [status=");
		builder.append(status);
		builder.append(", firstStop=");
		builder.append(firstStop);
		builder.append(", target=");
		builder.append(target);
		builder.append(", heure=");
		builder.append(heure);
		builder.append(", jour=");
		builder.append(jour);
		builder.append(", message=");
		builder.append(message);
		builder.append(", success=");
		builder.append(success);
		builder.append(", urlMove=");
		builder.append(urlMove);
		builder.append(", dateConnexion=");
		builder.append(dateConnexion);
		builder.append(", connexionDay=");
		builder.append(connexionDay);
		builder.append(", monthName=");
		builder.append(monthName);
		builder.append(", connexionYear=");
		builder.append(connexionYear);
		builder.append("]");
		return builder.toString();
	}
	
	

}
