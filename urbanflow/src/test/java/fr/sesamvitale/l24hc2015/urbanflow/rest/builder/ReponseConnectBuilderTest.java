package fr.sesamvitale.l24hc2015.urbanflow.rest.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.sesamvitale.l24hc2015.urbanflow.rest.ReponseConnect;

public class ReponseConnectBuilderTest {

	@Test
	public void test() {
		
		String jsonData = " {\"status\": \"registered\", \"url\" : "
	 + "\"/api/play/32dcb69ff50409296d30c4a5e2f874e8/ea3b31b7956ba470a0545b79e4069d71/verif\""
	 + ", \"message\": \"Your bot is now registered! Check the url to see when you will play.\","
	 + "\"success\": true}";
		
		ReponseConnect rc= ReponseConnectBuilder.getReponseConnect(jsonData);
		System.out.println("rc status"  + rc.getStatus());
		System.out.println("rc success"  + rc.isSuccess());
	}

}
