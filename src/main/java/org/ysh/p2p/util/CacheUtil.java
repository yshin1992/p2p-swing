package org.ysh.p2p.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import net.sf.json.JSONArray;

import org.ysh.p2p.model.Category;
import org.ysh.p2p.model.CategoryAttr;
import org.ysh.p2p.service.CategoryService;
import org.ysh.p2p.service.impl.CategoryServiceImpl;
import org.ysh.p2p.vo.DropDownDto;

public class CacheUtil {

	private static final Logger Log = LogUtil.getLogger(CacheUtil.class);
	
	private CategoryService categoryService = new CategoryServiceImpl(); 
	
	private static final Map<String,String> cacheMap = new ConcurrentHashMap<String, String>();
	
	private CacheUtil(){
		init();
	}
	
	private static final CacheUtil instance = new CacheUtil();
	
	public static CacheUtil getInstance(){
		return instance;
	}
	
	private void init(){
		Log.warning("开始加载系统配置....");
		
		List<Category> categories = categoryService.findAll();
		
		if(categories != null && !categories.isEmpty()){
			for(Category category : categories){
				Set<CategoryAttr> attrs = category.getAttrs();
				if(attrs != null && !attrs.isEmpty()){
					if(category.getConfiged() == 1){
						for(CategoryAttr attr : attrs){
							//这里如果是可配置的项，则以key-value的形式存储在内存中
							//否则以key-list（json-string）的形式存储
							if(attr.getActualval() != null){
								cacheMap.put(attr.getAttrCd(), attr.getActualval());
								Log.warning(attr.getAttrCd()+"="+attr.getActualval());
							}
						}
					}else{
						List<DropDownDto> attrList = new ArrayList<DropDownDto>();
						attrList.add(new DropDownDto("","全部"));
						for(CategoryAttr attr : attrs){
							attrList.add(new DropDownDto(attr.getAttrCd(),attr.getAttrNm(),attr.isEnabled(),attr.getActualval(),attr.getDefaultVal()));
						}
						String generateJsonStr = JSONArray.fromObject(attrList).toString();
						cacheMap.put(category.getCategoryCd(), generateJsonStr);
						Log.warning(category.getCategoryCd() + "=" + generateJsonStr);
					}
					
					
				}
			}
		}
		
		Log.warning("加载系统配置完成....");
		
	}
	
	
	public void refresh(){
		cacheMap.clear();
		init();
	}
	
	public static String getProperty(String key,String defaultVal){
		String value = cacheMap.get(key);
		return value == null ? defaultVal : value;
	}
	
	public static String getProperty(String key){
		return getProperty(key,null);
	}
	
	public static List<DropDownDto> getConfigList(String key){
		String value = cacheMap.get(key);
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<DropDownDto> bean = (List<DropDownDto>) JSONArray.toList(JSONArray.fromObject(value), DropDownDto.class); 
		return bean;
	}
	
	public static DropDownDto getConfigList(String key,String attrCd){
		List<DropDownDto> dtos = getConfigList(key);
		for(DropDownDto d: dtos){
			if(d.getAttrCd() != null && attrCd != null && attrCd.equals(d.getAttrCd())){
				return d;
			}
		}
		return null;
	}
	
	public static String getConfig(String key){
		return getProperty(key);
	}
	
	public static Integer getInt(String key,Integer defaultVal){
		String s1 = getProperty(key);
		return s1==null?defaultVal:Integer.parseInt(s1);
	}
	
	public static Integer getInt(String key){
		return getInt(key, null);
	}
}
