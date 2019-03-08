package org.ysh.p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\yshin1992\\Desktop\\MERD");
		File[] fLst = file.listFiles();
		for(File tmp:fLst){
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(tmp));
				String content = null;
				while((content = br.readLine())!=null){
					if(content.contains("COUPON")){
						System.out.println(content);
//						System.out.println(tmp.getName());
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(null != br){
					br.close();
				}
			}
		}
	
		
		
	}

}
