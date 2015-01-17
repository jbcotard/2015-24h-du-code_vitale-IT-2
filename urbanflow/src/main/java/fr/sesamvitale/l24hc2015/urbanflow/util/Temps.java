package fr.sesamvitale.l24hc2015.urbanflow.util;

public class Temps {
	private int heures;
	private int minutes;
	private int secondes;
	
	public Temps()
	{
		
	}
	
	public static Temps convertStringToTemps(String temps)
	{
		Temps t = new Temps();
		String[] split =  temps.split(":");
		t.setHeures(Integer.parseInt(split[0]));
		t.setMinutes(Integer.parseInt(split[1]));
		t.setSecondes(Integer.parseInt(split[2]));
		return t;
	}
	
	public static Temps additionnerTemps(Temps t1, Temps t2){
		Temps retour = new Temps();
		retour.setSecondes(0);
		int minutes = t1.getMinutes()+t2.getMinutes();
		int retenue = minutes/60;
		int heure = t1.getHeures()+t2.getHeures()+retenue;
		retour.setMinutes(minutes%60);
		retour.setHeures(heure);
		return retour;
	}
	
	/**
	 * 
	 * @param t1
	 * @param t2
	 * @return 1 si t1 est posterieur -1 si t2 est posterieur 0 si egalite
	 */
	public static int isPosterieur(Temps t1, Temps t2){
		if (t1.getHeures()>t2.getHeures())
		{
			return 1;
		}else
		{
			if (t1.getHeures()<t2.getHeures())
			{
				return -1;
			}else
			{
				if (t1.getMinutes()>t2.getMinutes())
				{
					return 1;
				}else
				{
					if (t1.getMinutes()<t2.getMinutes())
					{
						return -1;
					}else
					{
						return 0;
					}
				}
			}
		}
	}
	
	public static int getDuree(String t1S, String t2S){
		Temps t1 = convertStringToTemps(t1S);
		Temps t2 = convertStringToTemps(t2S);
		int posteriorite = isPosterieur(t1,t2);
		if (posteriorite == 0) {
			return 0;
		}
		int minutesT1 = t1.getHeures()*60+t1.getMinutes();
		int minutesT2 = t2.getHeures()*60+t2.getMinutes();
		if (posteriorite==1){ //t1-t2 positif
			return minutesT1-minutesT2;
		}else{
			return minutesT2-minutesT1;
		}
	}
	
	public static int getDuree(Temps t1, Temps t2){
		int posteriorite = isPosterieur(t1,t2);
		if (posteriorite == 0) {
			return 0;
		}
		int minutesT1 = t1.getHeures()*60+t1.getMinutes();
		int minutesT2 = t2.getHeures()*60+t2.getMinutes();
		if (posteriorite==1){ //t1-t2 positif
			return minutesT1-minutesT2;
		}else{
			return minutesT2-minutesT1;
		}
	}
	
	public static String convertTempsToString(Temps t){
		String secondes = ""+ t.getSecondes();
		String minutes = ""+ t.getHeures();
		String heures = ""+ t.getMinutes();
		if (secondes.length()<2)
		{
			secondes = "0"+secondes;
		}
		if (minutes.length()<2)
		{
			minutes = "0"+minutes;
		}
		if (heures.length()<2)
		{
			heures = "0"+heures;
		}
		return heures+":"+minutes+":"+secondes;
	}

	/**
	 * @return the heures
	 */
	public int getHeures() {
		return heures;
	}

	/**
	 * @param heures the heures to set
	 */
	public void setHeures(int heures) {
		this.heures = heures;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the secondes
	 */
	public int getSecondes() {
		return secondes;
	}

	/**
	 * @param secondes the secondes to set
	 */
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}
	
	

}
