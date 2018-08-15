package com.scp.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scp.qa.base.TestBase;
import com.scp.qa.client.RestClient;
import com.scp.qa.payloaddata.Users2;

public class PutApiTest extends TestBase {
      
	TestBase testbase;
	String serviceurl;
	String endpointURL;
	String url;
	
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setup(){
		
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		endpointURL = prop.getProperty("EndpointURL");
		url = serviceurl+endpointURL;	 
	}
	
	@Test
	public void put() throws JsonGenerationException, JsonMappingException, IOException, JSONException{
		
		restclient = new RestClient();
		
		HashMap<String,String> headerput = new HashMap<String,String>();
		headerput.put("Content-Type", "application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		Users2 users2 = new Users2("santosh","vice president");
		
		mapper.writeValue(new File("Resource/users2.json"),users2);
		
		String users2JsonString = mapper.writeValueAsString(users2);
		
		closeablehttpresponse = restclient.put(url, users2JsonString, headerput);
		
		// status code
		
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("status code of response -- > " +statusCode);
		
		// Headers
		Header[] headerArray = closeablehttpresponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		
		for(Header header : headerArray ){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("headers of response --> " +allHeaders);
		
		// Json response
		String response = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(response);
		System.out.println("jsonresponse from api is --> "+responsejson);	
		//System.out.println("response--->  " +response);
	}
	
}
