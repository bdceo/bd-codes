package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileCodeConvert {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String inp ="/Users/bdceo/home/xxx.txt";
		String outp= "/Users/bdceo/home/out.txt";
		
		InputStream inputStream = new FileInputStream(inp);    
		 byte[] head = new byte[3];    
		 inputStream.read(head);      
		 String code = "";    
		 code = "gb2312";    
		 if (head[0] == -1 && head[1] == -2 )    
		           code = "UTF-16";    
		 if (head[0] == -2 && head[1] == -1 )    
		           code = "Unicode";    
		 if(head[0]==-17 && head[1]==-69 && head[2] ==-65)    
		           code = "UTF-8";       
		 System.out.println(code);  
	}

}
