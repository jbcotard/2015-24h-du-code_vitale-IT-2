package fr.sesamvitale.l24hc2015.urbanflow.data;

public class Arret {
	
	private String id;
	private String name;
	private String position;
	
	public Arret(String id, String name, String position) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
	}
	
	public Arret() {
		super();
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	

}
