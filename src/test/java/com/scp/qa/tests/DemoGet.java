package com.scp.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.scp.qa.base.TestBase;
import com.scp.qa.client.Demo;

public class DemoGet extends TestBase {

	TestBase testbase;
	Demo demo;
	String url;
	String jsonresponse;
	
	CloseableHttpResponse httpres;
	
	@BeforeMethod
	public void setup(){
		
		testbase = new TestBase();
		String URL = prop.getProperty("URL");
		String serviceUrl = prop.getProperty("EndpointURL2");
		
	    url = URL+serviceUrl;
	}
	@Test
	public void getApiTest() throws ClientProtocolException, IOException{
		 
		demo = new Demo();
		
		HashMap<String,String> header = new HashMap<String,String>();
		header.put("Content-Type","application/json");
	    
		httpres = demo.get(url, header);
		
		//Status code
	    int statusCode =  httpres.getStatusLine().getStatusCode();
	    System.out.println("status code of respomse--->" +statusCode);
	 
	    //Assert.assertEquals(statusCode, 201);
	    SoftAssert sa = new SoftAssert();
	    sa.assertEquals(statusCode, 201);
	    System.out.println("TC failed");
	    
	  
	    //Headers
	    Header[] headerArray = httpres.getAllHeaders();
	    HashMap<String,String> allheaders = new HashMap<String,String>();
	    
	    for(Header header1 : headerArray ){
	    	allheaders.put(header1.getName(), header1.getValue());
	    }
		System.out.println(" headers of response ---> " +allheaders);
		
		//JSON response
		jsonresponse =  EntityUtils.toString(httpres.getEntity(), "UTF-8");
		System.out.println("json response ---> "+jsonresponse);
		
		//resp
		String time = httpres.getStatusLine().getReasonPhrase();
		System.out.println("time --> " +time);
		
	    ProtocolVersion version = httpres.getStatusLine().getProtocolVersion();
	    System.out.println("version --->" +version);
	    	
	}
}
