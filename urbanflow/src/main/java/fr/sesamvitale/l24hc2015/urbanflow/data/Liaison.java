package fr.sesamvitale.l24hc2015.urbanflow.data;

public class Liaison 
{
	private Arret source;
	private Arret target;
	private String ligne;
	private int weight;
	
	public Liaison() 
	{
		super();
	}
	
	public Liaison(Arret source, Arret target, String ligne) 
	{
		super();
		this.source = source;
		this.target = target;
		this.ligne = ligne;
	}
	
	/**
	 * @return the source
	 */
	public Arret getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Arret source) {
		this.source = source;
	}
	/**
	 * @return the target
	 */
	public Arret getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(Arret target) {
		this.target = target;
	}
	/**
	 * @return the ligne
	 */
	public String getLigne() {
		return ligne;
	}
	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	

}
