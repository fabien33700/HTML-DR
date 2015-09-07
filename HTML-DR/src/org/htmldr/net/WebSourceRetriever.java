package org.htmldr.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class for getting a web page source code
 * @author Fabien LH
 * @version 0.2
 *
 */
public class WebSourceRetriever {
	private String documentUrl = "";
	
	
	/**
	 * Class' constructor
	 * @param url 
	 * 		The URL of the web page to retrieve.
	 */
	public WebSourceRetriever(String url) {
		this.documentUrl = url;
	}
	
	/**
	 * Method to get source code as a String.
	 * @return The web page source code as a String
	 * @throws IOException Raised if an I/O error occurs when using the InputStream
	 */
	public String getSourceAsString() throws IOException {
		
        InputStream input = new URL(documentUrl).openStream();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));

        String result = "", s = "";
        while ((s = buffReader.readLine()) != null) {
        	result += s + "\n";
        }

        return result;
	}
	
	/**
	 * Method to get source code as an InputStream.
	 * @return The web page source code as a InputStream
	 * @throws IOException Raised if an I/O error occurs when using the InputStream
	 */
	public InputStream getSourceAsStream() throws IOException {
		
		InputStream input = new URL(documentUrl).openStream();
	    return input;

	}
}
