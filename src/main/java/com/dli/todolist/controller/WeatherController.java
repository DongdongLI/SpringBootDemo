package com.dli.todolist.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WeatherController {
	
	private JsonNode weatherInfo = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonNode getWeatherInfo() {
		return weatherInfo;
	}
	
	private void initialize() {
		try {
			//UriBuilder uriBuilder  = new 
			String yql = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Bradeonton, FL\")";
			String url = "https://query.yahooapis.com/v1/public/yql?q="+URLEncoder.encode(yql, StandardCharsets.UTF_8.toString() ).replace("+", "%20")+"&format=json";
			
			URI uri = new URI("https", "query.yahooapis.com", "/v1/public/yql", "q="+yql+"&format=json", null);
			
			URL obj;
			obj = new URL(uri.toString());
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
		         new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			weatherInfo = objectMapper.readTree(response.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/weather")
	public String getWeather(Model model) {
		initialize();
		JsonNode weather = getTodayForecast();
		model.addAttribute("highTemp", getTodayForecast().get("high").asInt());
		model.addAttribute("lowTemp", getTodayForecast().get("low").asInt());
		model.addAttribute("weather", getTodayForecast().get("text").asText());
		model.addAttribute("humidity", getWeatherInfo().get("query").get("results").get("channel").get("atmosphere").get("humidity").asInt());

		return "weather";			
	}
	
	private JsonNode getTodayForecast() {
		String forecastStr = getWeatherInfo()
				.get("query")
				.get("results")
				.get("channel")
				.get("item")
				.get("forecast").toString();
		
		try {
			return objectMapper.readTree(forecastStr).get(0);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
