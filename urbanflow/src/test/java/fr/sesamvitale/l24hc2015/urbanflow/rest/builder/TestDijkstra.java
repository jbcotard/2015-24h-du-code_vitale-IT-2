/**
 * 
 */
package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.data.Deplacement;
import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.GrapheImpl;
import fr.sesamvitale.l24hc2015.urbanflow.graphe.IGraphe;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilder;
import fr.sesamvitale.l24hc2015.urbanflow.map.MapBuilderImpl;

/**
 * @author user
 *
 */
public class TestDijkstra {

	@Test
	public void testAlgorithme() {
		MapBuilder mapBuilder = new MapBuilderImpl();
		Reseau r = mapBuilder.buildReseau();
		IGraphe g = GrapheImpl.getInstance();
		g.creerGraphe(r);
		Deplacement d = g.seDeplacer(1370, 1326, "07:46:00", "me");
		
		
	}

}
