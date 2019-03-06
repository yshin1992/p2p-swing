package org.ysh.p2p.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public final class StringUtil {

	private static final char[] CHARS = {
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'0','1','2','3','4','5','6','7','8','9'
	};
	
	/**
	 * 生成指定长度的验证码
	 * @param length
	 * @return
	 */
	public static final String generateVerifyCode(int length){
		StringBuilder result = new StringBuilder(length);
		Random r = new Random();
		for(int i = 0;i<length;i++){
			result.append(CHARS[r.nextInt(CHARS.length)]);
		}
		return result.toString();
	}
	
	
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
	public static boolean isNotEmpty(Object obj){
		if(null == obj){
			return false;
		}
		
		/**
		 * 字符串
		 */
		if(obj instanceof String){
			String str = (String)obj;
			if(str.trim().length() == 0){
				return false;
			}else{
				return true;
			}
		}
		
		/**
		 * 集合
		 */
		if(obj instanceof Collection){
			Collection<?> collection = (Collection<?>)obj;
			return !collection.isEmpty();
		}
		
		return true;
	}
	
	public static boolean isEmpty(Object obj){
		return !isNotEmpty(obj);
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
