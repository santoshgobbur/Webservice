package com.scp.qa.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Sam1 {
	
	CloseableHttpClient h1;
	CloseableHttpResponse h1res = null;

        public CloseableHttpResponse f1(String url) throws ClientProtocolException, IOException{
        	
        	CloseableHttpClient h1 = HttpClients.createDefault();
        	HttpGet g = new HttpGet(url);
        	CloseableHttpResponse h1res = h1.execute(g);
        	return h1res;
        	 	
        }
}
