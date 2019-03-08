package org.ysh.p2p.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
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
	
	/**
	 * 通过反射机制获取位于同一个包下的接口的所有实现类
	 * @param c
	 * @return
	 */
	public static List<Class<?>> getAllImplClassesByInterface(Class<?> c){
		 List<Class<?>> returnClassList = new ArrayList<Class<?>>();// 返回结果 
		 if(c.isInterface()){
			 String packageName = c.getPackage().getName();// 获得当前包名 
			 
			 try {
				List<Class<?>> allClass = getClassesByPackageName(packageName);
				
				if(!allClass.isEmpty()){
					for(Class<?> clazz:allClass){
						if(c.isAssignableFrom(clazz) && !c.equals(clazz)){
							returnClassList.add(clazz);
						}
					}
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		 }
		 
		 return returnClassList;
	}
	
	/**
	 * 从一个包中查找出所有类,在jar包中不能查找
	 * @param packageName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getClassesByPackageName(String packageName)throws IOException, ClassNotFoundException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		
		while(resources.hasMoreElements()){
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for(File directory:dirs){
			classes.addAll(findClasses(directory, packageName));
		}
		
		return classes;
	}
	
	private static List<Class<?>> findClasses(File directory,String packageName) throws ClassNotFoundException{
		List<Class<?>> classes = new ArrayList<Class<?>>();
		
		if(!directory.exists()){
			return classes;
		}
		
		File[] files = directory.listFiles();
		
		for(File file:files){
			if(file.isDirectory()){
				//递归调用
				classes.addAll(findClasses(file, packageName + '.' + file.getName()));
			}else if(file.getName().endsWith(".class")){
				classes.add(Class.forName(packageName + "." + file.getName().substring(0,file.getName().length() - 6)));
			}
		}
		
		return classes;
	}
}
