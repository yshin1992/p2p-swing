package org.ysh.p2p.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
	
	public static <T> List<Field> getClassFields(Class<T> clazz){
		List<Field> result = new ArrayList<Field>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field f : fields){
			if(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())){
				continue;
			}
			result.add(f);
		}
		
		Class<?> superClass = clazz.getSuperclass();
		while(superClass != Object.class){
			Field[] superFields = superClass.getDeclaredFields();
			for(Field f : superFields){
				if(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())){
					continue;
				}
				result.add(f);
			}
			superClass = superClass.getSuperclass();
		}
		
		return result;
	}
	
}
