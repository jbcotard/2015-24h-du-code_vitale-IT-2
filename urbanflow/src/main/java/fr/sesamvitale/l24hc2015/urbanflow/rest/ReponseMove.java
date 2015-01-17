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
	private int target;

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getStatus() {
		return status;
	}


	public String getMessage() {
		return message;
	}


	public boolean isSuccess() {
		return success;
	}


	public String getTime() {
		return time;
	}


	public int getStopId() {
		return stopId;
	}


	public String getStopName() {
		return stopName;
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


	public int getTarget() {
		return target;
	}


	public void setTarget(int target) {
		this.target = target;
	}

	public boolean isRerouted() {
		return "rerouted".equals(status);
	}
}
