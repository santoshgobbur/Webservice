package com.scp.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	CloseableHttpResponse closeablehttpresponse = null;
	CloseableHttpClient httpclient;
	
	//GET Method without Headers
	public CloseableHttpResponse getWithoutHeaders(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create connection
		HttpGet getReq = new HttpGet(url);                            // get request
		closeablehttpresponse = httpclient.execute(getReq);           // hit get request
	    return closeablehttpresponse;
	}
	
	//GET Method with Headers
	public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create connection
		HttpGet getReq  = new HttpGet(url); // get request 
	    
		// header
	    for(Map.Entry<String,String> entry : headerMap.entrySet()){
	    	
	    	getReq.addHeader(entry.getKey(),entry.getValue());
	    }
	    // response
	    CloseableHttpResponse closeablehttpresponse = httpclient.execute(getReq); // hit get request  
	    return closeablehttpresponse;
	}

	// POST Method with HEADERS
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
	    CloseableHttpClient httpclient = HttpClients.createDefault(); // create connection
	    HttpPost postReq = new HttpPost(url); // post request
	    postReq.setEntity(new StringEntity(entityString)); // payload
	
	    //headers
	    for(Map.Entry<String,String> entry : headerMap.entrySet()){
	    	postReq.addHeader(entry.getKey(), entry.getValue());
	    }
	    
	    CloseableHttpResponse closeablehttpresponse = httpclient.execute(postReq); // hit post request
	    return closeablehttpresponse;
	}
     
	// PUT Method with Headers
	
	public CloseableHttpResponse put(String url, String entitySet, HashMap<String,String> headerput) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut putReq = new HttpPut(url);
		putReq.setEntity(new StringEntity(entitySet));
		
		//headers
		for(Map.Entry<String, String> entry : headerput.entrySet()){
			
			putReq.addHeader(entry.getKey(),entry.getValue());
		}
		 CloseableHttpResponse closeablehttpresponse = httpclient.execute(putReq); // hit put request
		 return closeablehttpresponse;
	}
}

