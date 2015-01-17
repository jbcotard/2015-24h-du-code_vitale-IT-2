/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest;

/**
 * @author jb
 *
 */
public class ReponseVerify {

	private static final String GAME_STARTED = "game_started";
	private String status;
	private int firstStop;
	private int target;
	private String heure;
	private String jour;

	public boolean isGameStarting() {
		return GAME_STARTED.equals(status);
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

}
