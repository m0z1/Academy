package com.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UniTest {

	public static void main(String[] args) {
	
			 String result = "";
				try {
					//  String api = "https://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=81d21d01fed4bedbd4e331e41a333a93&svcType=api&svcCode=SCHOOL&contentType=json&gubun=univ_list&region=100267&sch1=100323&sch2=100328";
					String api = "https://yts-proxy.now.sh/list_movies.json?sort_by=rating";
					URL url = new URL(api);
			            HttpURLConnection urlconnection;
			          
					  urlconnection = (HttpURLConnection) url.openConnection();
				//	  urlconnection.setRequestMethod("GET");
					  urlconnection.setRequestMethod("GET");
					  urlconnection.setRequestProperty("content-Type", "application/json;utf-8");
					  urlconnection.setRequestProperty("Accept", "application/json");
					  BufferedReader br = null;
					  int responseCode = urlconnection.getResponseCode();
			              if(responseCode == 200) {
			            	br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
			            }
			           
			            String line;
			            while((line = br.readLine()) != null) {
			                result = result + line + "\n";
			            }
			            System.out.println(result);
			         

				} catch (IOException e) {
					e.printStackTrace();
				}

			


	}

}
