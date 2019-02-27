package org.ysh.p2p.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public final class StringUtil {

	/**
	 * 生成UUID
	 * @return
	 */
	public static final String generateUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(); 
	}
	
	
	/**
	 * 判断字符串是否为空，不为空时返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(null != str && str.trim().length() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 数据加密（MD5）
	 * @param plainText
	 * @return
	 */
    public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
  
    }  
}
