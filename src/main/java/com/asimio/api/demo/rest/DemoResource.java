package com.asimio.api.demo.rest;

import com.asimio.api.demo.cache.ProfanityCache;
import com.asimio.api.demo.counter.FailCounter;
import com.asimio.api.demo.model.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class DemoResource {

	@Autowired
	ProfanityCache profanityCache;

	private RestTemplate restTemplate;
	private final Random random = new Random();


	@RequestMapping(path = "/send/data", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfanityResponse> getDemoDelegate() {
		List<ProfanityRequest> profanityRequestList = profanityCache.getProfanityRequest();
		ProfanityRequest profanityRequest = profanityRequestList.get(random.nextInt(profanityRequestList.size()));
		System.out.println(profanityRequest);

		ProfanityResponse responseEntity = null;
		String responseString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			FailCounter.incrementTotalRequest();
			 responseString = this.restTemplate.postForObject("http://localhost:8080",profanityRequest, String.class);
			responseEntity = objectMapper.readValue(responseString,ProfanityResponse.class);
		} catch (RestClientException e) {
			System.out.println("***********ERROR***********: " + e.getMessage());
			FailCounter.incrementFailRequest();
			FailCounter.displayResult();
			return new ResponseEntity<ProfanityResponse>(responseEntity,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonParseException e) {
			System.out.println("ERROR OCCURED RESPONSE: " + e.getMessage());
			System.out.println(responseString);
		} catch (JsonMappingException e) {
			System.out.println("ERROR OCCURED RESPONSE: " + e.getMessage());
			System.out.println(responseString);
		} catch (IOException e) {
			System.out.println("ERROR OCCURED RESPONSE: " + e.getMessage());
			System.out.println(responseString);
		}
		FailCounter.incrementSuccessRequest();
		List<Bulk> bulkList = responseEntity.getBulkList();

		bulkList.stream().forEach(a -> a.getFilterResultList().stream().forEach(b -> {
			FailCounter.incrementSampleCount();
			if(b.getFilterCode() == -1){
				FailCounter.incrementFailCount();
			}else{
				FailCounter.incrementSuccessCount();
			}
		}));
		FailCounter.displayResult();
		return new ResponseEntity<ProfanityResponse>(responseEntity,HttpStatus.OK);
	}


	@RequestMapping(value = "/zemberek/call", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZemberekResponseModel> zemberekCall(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		List<ProfanityRequest> profanityRequest = profanityCache.getProfanityRequest();
		String str = profanityRequest.get(random.nextInt(profanityRequest.size())).getBulkList().get(0).getTextList().get(0).getText();

		if(str == null){
			str = "nabersiniz iyimisiniz oralarda";
		}

		map.add("sentence",str);
		HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,headers);
		ResponseEntity<ZemberekResponseModel> responseEntity = null;
		try {
			FailCounter.incrementTotalRequest();
			responseEntity = restTemplate.postForEntity("http://localhost:8080/normAndMorp", entity, ZemberekResponseModel.class);
			System.out.println("Request[" + FailCounter.TOTAL_REQUEST + "] String: "  + str + " | " + responseEntity.getBody().toString());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		if(null == responseEntity.getBody().getText()){
			System.out.println("null");
		}
		return responseEntity;
 	}

	@RequestMapping(value = "/zemberek/morp", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public String morp(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		List<ProfanityRequest> profanityRequest = profanityCache.getProfanityRequest();
		String  str = profanityRequest.get(random.nextInt(profanityRequest.size())).getBulkList().get(1).getTextList().get(1).getText();
		map.add("sentence",str);
		HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,headers);
		ResponseEntity<String> responseEntity = null;
		try {
			FailCounter.incrementTotalRequest();
			responseEntity = restTemplate.postForEntity("http://localhost:8080/morp", entity, String.class);
			System.out.println("Request[" + FailCounter.TOTAL_REQUEST + "] = " + responseEntity.getBody().toString());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return responseEntity.getBody();
	}


	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
