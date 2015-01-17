package fr.sesamvitale.l24hc2015.urbanflow.data;

public class Deplacement {
	private String numArret;
	private String numLigne;
	private String connexion;
	
	/**
	 * @return the numArret
	 */
	public String getNumArret() {
		return numArret;
	}
	/**
	 * @param numArret the numArret to set
	 */
	public void setNumArret(String numArret) {
		this.numArret = numArret;
	}
	/**
	 * @return the numLigne
	 */
	public String getNumLigne() {
		return numLigne;
	}
	/**
	 * @param numLigne the numLigne to set
	 */
	public void setNumLigne(String numLigne) {
		this.numLigne = numLigne;
	}
	/**
	 * @return the connexion
	 */
	public String getConnexion() {
		return connexion;
	}
	/**
	 * @param connexion the connexion to set
	 */
	public void setConnexion(String connexion) {
		this.connexion = connexion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deplacement [numArret=");
		builder.append(numArret);
		builder.append(", numLigne=");
		builder.append(numLigne);
		builder.append(", connexion=");
		builder.append(connexion);
		builder.append("]");
		return builder.toString();
	}
	
	

}
