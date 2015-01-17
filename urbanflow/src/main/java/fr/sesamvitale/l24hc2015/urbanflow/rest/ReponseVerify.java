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

}
