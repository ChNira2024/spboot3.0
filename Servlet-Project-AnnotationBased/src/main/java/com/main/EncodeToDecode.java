package com.main;

import java.util.Base64;

public class EncodeToDecode {

	public static void main(String[] args) 
	{
		String encodedData = "c2lzdQ=="; //sisu
        
        try 
        {
            String decodeData = new String(Base64.getDecoder().decode(encodedData));
            System.out.println(decodeData);
        } 
        catch (Exception e) 
        {
            System.err.println("Error decoding data: " + e.getMessage());
            e.printStackTrace();
        }
	}
}
