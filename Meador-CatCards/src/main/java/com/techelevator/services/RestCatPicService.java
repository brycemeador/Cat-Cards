package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	public static final String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random";
	private RestTemplate restTemplate = new RestTemplate();


	@Override
	public String getPic() {
		CatPic catPic = null;
		catPic = restTemplate.getForObject(API_BASE_URL, CatPic.class);

		return catPic.getFile();
	}

}	
