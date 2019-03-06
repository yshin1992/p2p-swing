package org.ysh.p2p.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.ysh.p2p.annotation.Transient;

public class ReflectionUtil {
	
	public static List<Field> getClassFields(Class<?> clazz){
		List<Field> result = new ArrayList<Field>();
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			Field[] fields = clazz.getDeclaredFields();
			
			for(Field f : fields){
				if(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())){
					continue;
				}
				
				Transient trans = f.getAnnotation(Transient.class);
				if(trans != null)
					continue;
				result.add(f);
			}
		}
		return result;
	}
	
	public static Field getDeclaredField(String fieldName,Class<?> clazz){
		Field field = null;
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			try{
				field = clazz.getDeclaredField(fieldName);
			}catch(Exception e){
				//什么也不做
			}
		}
		return field;
	}
	
	
	public static <T> void setFieldValue(String fieldName,Object value,Class<T> clazz,T t){
		Field field = getDeclaredField(fieldName,clazz);
		field.setAccessible(true);
		
		try {
			field.set(t, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public static <T> Object getFieldValue(String fieldName,Class<T> clazz,T t){
		Field field = getDeclaredField(fieldName,clazz);
		field.setAccessible(true);
		
		try {
			return field.get(t);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
