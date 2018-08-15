package com.scp.qa.tests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.scp.qa.base.TestBase;
import com.scp.qa.client.RestClient;

public class Sample extends TestBase {

	TestBase testbase;
	String endpointURL;
	String url;
	String serviceurl;
	RestClient restclient;
	CloseableHttpResponse h1res;
	
	@BeforeMethod()
	public void setup(){
	   testbase = new TestBase();
	   serviceurl = prop.getProperty("URL");
	   endpointURL = prop.getProperty("EndpointURL");
	   url = serviceurl+endpointURL;
	}
	@Test
	public void getA() throws ParseException, IOException{
		
		int statuscode = h1res.getStatusLine().getStatusCode();
		String response = EntityUtils.toString(h1res.getEntity(), "UTF-8");
		     
	}
	
	
}
