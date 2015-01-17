package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseMove;

public class ReponseMoveBuilderTest {

	@Test
	public void testGetReponseConnect() {
		String jsonData = "{ \"success\": True,"
    + " \"status\": \"moved\", "
    + "  \"message\": \"The request was correct. Your bot has moved.\", "
    + " \"stop\": {\"id\": 50, \"name\": \"<stop_name>\"},"
    + " \"time\": \"2015-01-15T16:39:00+00:00\" }" ;
		ReponseMove rm =	ReponseMoveBuilder.getReponseConnect(jsonData);
	
		System.out.println("rm time:" + rm.getTime());
		System.out.println("rm success: " + rm.isSuccess());
		System.out.println("rm status: " + rm.getStatus());
		System.out.println("rm target:" + rm.getTarget());
	}

	@Test
	public void testReroutedGetReponseConnect() {
		String jsonData = "{ \"success\": True,"
    + " \"status\": \"rerouted\", "
    + "  \"message\": \"The request was correct. Your bot has moved.\", "
    + "  \"target\": 50," 
    + " \"stop\": {\"id\": 50, \"name\": \"<stop_name>\"},"
    + " \"time\": \"2015-01-15T16:39:00+00:00\" }" ;
		ReponseMove rm =	ReponseMoveBuilder.getReponseConnect(jsonData);
	
		System.out.println("rm time:" + rm.getTime());
		
		System.out.println("rm target:" + rm.getTarget());
		System.out.println("rm success: " + rm.isSuccess());
		System.out.println("rm status: " + rm.getStatus());
	}
}
