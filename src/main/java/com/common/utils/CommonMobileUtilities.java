package com.common.utils;
import java.util.Random;

public class CommonMobileUtilities {

	

	public static String sPath;
	public static String parent="";
	
	public static String RandomString(int n) { 
		 
		  
		  String AlphaNumericString = 
//				  "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + 
		  "abcdefghijklmnopqrstuvxyz"; 
		 
		  StringBuilder sb = new StringBuilder(n); 
		  for (int i = 0; i < n; i++) {  
		   int index 
		    = (int)(AlphaNumericString.length() 
		      * Math.random()); 
		   sb.append(AlphaNumericString 
		      .charAt(index)); 
		  } 
		 
		  return sb.toString(); 
		 
	}
	
	public static String RandomstringNumber(int length) { 

		String chraracters = "0123456789";

		String randomString = "";

		Random rand = new Random();

		char[] text = new char[length];

		for (int i = 0; i < length; i++) {

		text[i] = chraracters.charAt(rand.nextInt(chraracters.length()));

		}

		for (int i = 0; i < text.length; i++) {

		randomString += text[i];

		}

		return randomString.toString();

		}
	//End
	
}
