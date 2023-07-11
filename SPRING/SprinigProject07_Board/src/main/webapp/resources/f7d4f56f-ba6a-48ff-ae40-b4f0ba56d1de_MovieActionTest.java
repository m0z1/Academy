package com.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



class MovieActionTest{
	
        public static void main(String[] args) throws Exception {

		URL url = new URL("https://yts-proxy.now.sh/list_movies.json?sort_by=rating");
		HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
	   // urlCon.setRequestMethod("GET");
	//	urlCon.setRequestProperty("content-Type", "application/json;utf-8");
	//	urlCon.setRequestProperty("Accept", "application/json");

		//urlCon.setRequestProperty("User-Agent", "Mozilla/5.0");
	
		urlCon.setConnectTimeout(10000);    
		urlCon.setReadTimeout(5000);           

	
	   System.out.println(urlCon);
		BufferedReader br = null;
		 String line;
		 StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
			while((line = br.readLine()) != null) {
				System.out.println(line);
				 sb.append(line);
			  }
		} catch (IOException e) {
				e.printStackTrace();
		}
	
		System.out.println(sb.toString());
	
	}

	

	
	
}
