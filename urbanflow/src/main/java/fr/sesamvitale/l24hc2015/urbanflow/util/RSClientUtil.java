package fr.sesamvitale.l24hc2015.urbanflow.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RSClientUtil {

	public static String sendPOST(String urlWS, String body) {
		String responseBody = null;
		try {
			URL url = new URL(urlWS);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = body;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			Scanner scanner;
			String response;
			if (conn.getResponseCode() != 200) {
				scanner = new Scanner(conn.getErrorStream());
				response = "Error From Server \n\n " + conn.getResponseCode();
			} else {
				scanner = new Scanner(conn.getInputStream());
				response = "Response From Server \n\n";
			}
			scanner.useDelimiter("\\Z");
			responseBody = response + scanner.next();
			scanner.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;
	}

}
