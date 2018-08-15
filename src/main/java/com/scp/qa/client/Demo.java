package com.scp.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Demo {

	CloseableHttpResponse httpres = null;
	
	public CloseableHttpResponse get(String url, HashMap<String,String> header) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpreq = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		for(Map.Entry<String,String> entry : header.entrySet())
		{
				httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpres = httpreq.execute(httpget);
	    return httpres;
	}
}
