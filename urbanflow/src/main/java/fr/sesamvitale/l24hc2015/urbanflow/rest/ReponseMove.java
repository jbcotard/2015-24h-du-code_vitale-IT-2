/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

/**
 * @author jb
 *
 */
public class ReponseMove {

	private String status;
	private String message;
	private boolean success;
	private String time;
	private int stopId;
	private String stopName;

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setStopId(int stopID) {
		this.stopId = stopID;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	
}
