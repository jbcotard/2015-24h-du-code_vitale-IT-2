/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.map;

import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;

/**
 * @author jb
 *
 */
public interface MapBuilder {

	/**
	 * Création du réseau a partir des fichiers JSON
	 * @return
	 */
	Reseau buildReseau();

}
