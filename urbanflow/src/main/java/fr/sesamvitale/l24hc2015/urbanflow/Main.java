package fr.sesamvitale.l24hc2015.urbanflow;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("hello world ! bozo");

		
		
		List<Object> providers = new ArrayList<Object>();
	     providers.add( new JacksonJaxbJsonProvider() );
	   
//	     WebClient client = WebClient.create("http://localhost:8080/poc_restapi_cxf/api", providers);
//	     client = client.accept("application/json").type("application/json").path("/order").query("id", "1");
//	   
//	     Order order = client.get(Order.class);
//	     System.out.println("Order:" + order.getCustomerName());
		
	}

}
