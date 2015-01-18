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
	private int penality;
	private String score;
	private String timeHeure;

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


	public boolean isArrived() {
		
		return "arrived".equals(status);
	}


	public int getPenality() {
		return penality;
	}


	public void setPenality(int penality) {
		this.penality = penality;
	}


	public String getScore() {
		return score;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}


	public void setTimeHeure(String timeHeure) {
		this.timeHeure = timeHeure;
	}


	/**
	 * @return the timeHeure
	 */
	public String getTimeHeure() {
		return timeHeure;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReponseMove [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", success=");
		builder.append(success);
		builder.append(", time=");
		builder.append(time);
		builder.append(", stopId=");
		builder.append(stopId);
		builder.append(", stopName=");
		builder.append(stopName);
		builder.append(", target=");
		builder.append(target);
		builder.append(", penality=");
		builder.append(penality);
		builder.append(", score=");
		builder.append(score);
		builder.append(", timeHeure=");
		builder.append(timeHeure);
		builder.append("]");
		return builder.toString();
	}
	
	
}
