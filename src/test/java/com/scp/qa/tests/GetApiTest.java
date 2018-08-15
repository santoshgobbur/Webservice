package com.scp.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.scp.qa.base.TestBase;
import com.scp.qa.client.RestClient;

public class GetApiTest extends TestBase {
	
	TestBase testbase;
	String endpointURL;
	String url;
	String serviceurl;
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod()
	public void setup(){
	   testbase = new TestBase();
	   serviceurl = prop.getProperty("URL");
	   endpointURL = prop.getProperty("EndpointURL");
	   url = serviceurl+endpointURL;
	}
	
	   @Test(priority=1)
	   public void getAPITestWithoutHeaders() throws ParseException, IOException, JSONException{
		   
		   RestClient restclient = new RestClient();
		   closeablehttpresponse = restclient.getWithoutHeaders(url);
		    
		   
			//1.status code
			
		    int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("status code-> "+statusCode);
			
			//2.JSON String
			
			String response = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
			JSONObject responsejson = new JSONObject(response);
			System.out.println("jsonresponse-> "+responsejson);
			
			//3.All headers of response
		    
			Header[] headerArray = closeablehttpresponse.getAllHeaders();
			HashMap<String,String> allHeaders = new HashMap<String,String>();
			
			for(Header header : headerArray){
				
				allHeaders.put(header.getName(), header.getValue());
			}
		    System.out.println("Header array -->"+allHeaders);
	   }
	   
	   @Test(priority=2)
	   public void getAPITestWithHeader() throws ParseException, IOException, JSONException {
		   
		   
		   RestClient restclient = new RestClient();
		   
		   HashMap<String,String> headerMap = new HashMap<String,String>();
		   headerMap.put("Content-Type", "application/json");
		   closeablehttpresponse = restclient.get(url, headerMap);
		    
			//1.status code
			
		    int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("Statuscode is ->"+statusCode);
			
			//2.JSON String
			
			String response = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
			JSONObject responsejson = new JSONObject(response);
			System.out.println("jsonresponse ->"+responsejson);
			System.out.println("length is : "+response.length());
			
			//3.All headers of response
			
			Header[] headerArray = closeablehttpresponse.getAllHeaders();
			HashMap<String,String> allHeaders = new HashMap<String,String>();
			
			for(Header header : headerArray){
				
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println(allHeaders.isEmpty());
			System.out.println(allHeaders.get(headerMap));
			System.out.println(allHeaders.get("Transfer-Encoding"));
		    System.out.println("Header array -->"+allHeaders);
		  
	   }
}
