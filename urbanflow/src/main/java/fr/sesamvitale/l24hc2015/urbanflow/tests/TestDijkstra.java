/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.tests;

import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.GrapheImpl;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.IGraphe;

/**
 * @author user
 *
 */
public class TestDijkstra {

	@Test
	public void testAlgorithme() {
		Reseau r = Reseau.getInstance();
		IGraphe g = GrapheImpl.getInstance();
		g.creerGraphe(r);
		Deplacement d = g.seDeplacer(998, 1004, "07:46:00", "me");
		System.out.println("ARRET : "+d.getNumArret()+"\nLIGNE : "+d.getNumLigne());
		
		
	}

}
