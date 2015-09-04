package org.htmldr.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmldr.net.WebSourceRetriever;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class for parsing hypertext link from web page source code.
 * @author Fabien LH
 * @version 0.2
 *
 */
public class HypertextLinkParser {
	/**
	 * A string which contains the web page source code.
	 */
	private String sourceCode = "";
	
	/**
	 * An ArrayList of String which contains the target of each links found in the code.
	 */
	private ArrayList<String> listLinks = null;
	
	/**
	 * HypertextLinkParser' constructor
	 * @param documentUrl
	 * 		The URL of the page to parse.
	 */
	public HypertextLinkParser(String documentUrl) {
		/**
		 * Local WebSourceRetriever for getting source code.
		 * @see WebSourceRetriever
		 */
		WebSourceRetriever retriever = new WebSourceRetriever(documentUrl);
		
		// Creating the instance
		listLinks = new ArrayList<String>();
		
		try { 
			// Getting the source code as a String 
			sourceCode = retriever.getSourceAsString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// Calling the method for parsing HTML
		this.parseLinks();
	}
	
	/**
	 * Method for parsing HTML to extract hypertext links.
	 * The results are stored in 'listLinks' field.
	 */
	private void parseLinks() {
		String currentTag = "";
		String beginTag = "<a ", endTag = "</a>";
		
		// Clearing the list if not empty
		listLinks.clear();
		
		// Regex pattern for extracting link target from <a href="...">...</a> tag
		  Pattern p = Pattern.compile("<a href=['\"](.*?)['\"].*>(.*)</a>");
		  
		// The cursor for searching into source code
		int position = 0;
		
		// While we find the start of an "<a ..." tag
		while (position > -1) {
			
			// Ignoring the initial loop
			if (position > 0) {	
				// Retrieving the full link tag
				currentTag = sourceCode.substring(position, sourceCode.indexOf(endTag, position) + endTag.length()); 

				// Applying the Regex pattern to extract link target
				Matcher m = p.matcher(currentTag);
				
				// Add to the list if there is a match
				if (m.find())
				   listLinks.add(m.group(1));
			}
			// Seeking the cursor after the found link
			position = sourceCode.indexOf(beginTag, position+1);
		}
	}
	
	/**
	 * Accessor for 'listLinks' field.
	 * @return An ArrayList of String which contains the target of each links found in the code.
	 */
	public ArrayList<String> getListLinks() {
		return this.listLinks;
	}
}