package fr.sesamvitale.l24hc2015.urbanflow.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.*;

import fr.sesamvitale.l24hc2015.urbanflow.data.Reseau;

public class MapBuilderImpl implements MapBuilder {

	@Override
	public Reseau buildReseau() {
		try {
			FileInputStream fis = new FileInputStream("data/11_A.json");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

			BufferedReader br = new BufferedReader(isr);
			String jsonData = br.readLine();
			
	        JSONObject obj = new JSONObject(jsonData);
	        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

	        JSONArray arr = obj.getJSONArray("posts");
	        for (int i = 0; i < arr.length(); i++)
	        {
	            String post_id = arr.getJSONObject(i).getString("post_id");
	        }	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
 		return null;
	}

}
