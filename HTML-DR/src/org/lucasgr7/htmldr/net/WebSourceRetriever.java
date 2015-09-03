package org.lucasgr7.htmldr.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class WebSourceRetriever {
	
	public static String getSource(String urlPath) throws IOException {
		final int bufferLength = 32;
		
        InputStream input = null;
        URLConnection connection = new URL(urlPath).openConnection();

        input = connection.getInputStream();
        byte[] buffer = new byte[bufferLength];
        int read;
        
        String result = "";
   
        while ((read = input.read(buffer)) > 0) {
        	result += new String(buffer);
        }
        
        return result;
	}
	
}
