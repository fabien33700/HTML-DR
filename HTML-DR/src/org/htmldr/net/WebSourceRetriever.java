package org.htmldr.net;

import java.io.IOException;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabien LH
 */

/**
 * Abstract class which implements the static method getSource()
 * @author Fabien LH
 * @version 0.1
 *
 */
public abstract class WebSourceRetriever {
	
	/**
	 * Method which download a web page source code with its URL.
	 * @param urlPath
	 * 		The URL of the web page to retrieve.
	 * @return The web page source code as a String
	 * @throws IOException Raised if an I/O error occurs when using the InputStream
	 */
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
