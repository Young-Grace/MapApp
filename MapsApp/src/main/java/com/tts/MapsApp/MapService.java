package com.tts.MapsApp;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class MapService {
	@Value("${api_key}")
	private String apiKey;
	
	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + "," + location.getState() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}
	
	
	public Location randomizeCoordinates() {
		Location location = new Location();
		Random rand = new Random();
		
		// generates a number between -180 - 180
		double longitude = (360 * rand.nextDouble()) - 180;
		double latitude = (180 * rand.nextDouble()) - 90;
		
		location.setLng(String.valueOf(longitude));
		location.setLat(String.valueOf(latitude));
		
		return location;
	}
	
}

