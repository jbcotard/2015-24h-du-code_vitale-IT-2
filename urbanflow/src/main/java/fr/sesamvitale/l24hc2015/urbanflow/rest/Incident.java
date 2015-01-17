package fr.sesamvitale.l24hc2015.urbanflow.rest;

public class Incident {

	private String arretId;
	private String arretName;
	private String dateEnd;
	private String dateStart;
	private String type;
	private int penalty = 0;
	public String getArretId() {
		return arretId;
	}
	public void setArretId(String arretId) {
		this.arretId = arretId;
	}
	public String getArretName() {
		return arretName;
	}
	public void setArretName(String arretName) {
		this.arretName = arretName;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	
	
}
