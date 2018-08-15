package com.scp.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scp.qa.base.TestBase;
import com.scp.qa.client.RestClient;
import com.scp.qa.payloaddata.Users;

public class PostApiTest extends TestBase {
	
	TestBase testbase;
	String serviceurl;
	String endpointURL;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod()
	public void setup(){
		
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		endpointURL = prop.getProperty("EndpointURL");
		url = serviceurl+endpointURL;		
	}
	
	@Test()
	public void postapiTest() throws ClientProtocolException, IOException, JSONException{
		   
		RestClient restclient = new RestClient();
		   
	    HashMap<String,String> headerMap = new HashMap<String,String>(); 
		headerMap.put("Content-Type", "application/json");		
	  
		// payload (json)
		Users user = new Users("morpheus","leader");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("Resource/users.json"),user);
		String usersJsonString = mapper.writeValueAsString(user); // Marshalling
		//System.out.println(usersJsonString);  
	    
		closeablehttpresponse = restclient.post(url,usersJsonString,headerMap); 

		
		// --------Validations----------
	
		//status code
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		//System.out.println("status code of response --> "+statusCode);
		Assert.assertEquals(statusCode, 201);
	
		//JSON response
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");		
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("json obj"+responsejson);
		System.out.println("jsonresponse from api is --> "+responseString);

		Users userReadObj = mapper.readValue(responseString, Users.class); // Unmarshalling
		System.out.println(userReadObj);
		System.out.println(userReadObj.getName());
		System.out.println(user.getName());
		
		Assert.assertTrue(user.getName().equals(userReadObj.getName())); // object level comparison
		Assert.assertTrue(user.getJob().equals(userReadObj.getJob()));
		
		//System.out.println(userReadObj.getId());
		//System.out.println(userReadObj.getCreatedAt());
	
		//headers
		Header[] headerArray = closeablehttpresponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		
		for(Header header:headerArray ){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("headers from response --> "+allHeaders);
		
		System.out.println(closeablehttpresponse.getStatusLine().getReasonPhrase());
		System.out.println(closeablehttpresponse.getEntity().getContentType());
	}
}
