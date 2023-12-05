package com.team01.controller;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class MapController {

	@CrossOrigin
    @GetMapping("/setMap/{address}")
    public String setMap(@PathVariable String address) {
    	
    	System.out.println(">>> setMap");
    	
    	ByteBuffer buffer = StandardCharsets.UTF_8.encode(address);
    	String encode = StandardCharsets.UTF_8.decode(buffer).toString();
    	
    	URI uri = UriComponentsBuilder
    			.fromUriString("https://naveropenapi.apigw.ntruss.com/")
    			.path("map-geocode/v2/geocode")
    			//.queryParam("query", query)
    			.queryParam("query", encode)
    			.queryParam("coordinate", "129.1063097,35.1427970")
    			.encode()
    			.build()
    			.toUri();
    	
    	RequestEntity<Void> req = RequestEntity
    			.get(uri)
    			.header("X-NCP-APIGW-API-KEY-ID", "hxbhuy0gcl")
    			.header("X-NCP-APIGW-API-KEY", "2FkATPQ6b6n8MluApN06ap5Nzs0STXP6LgfBfwa5")
    			//.header("Accept", "JSON")
    			.build();
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	ResponseEntity<String> response = restTemplate.exchange(req, String.class);
    	
    	return response.getBody();
    }
	
}
