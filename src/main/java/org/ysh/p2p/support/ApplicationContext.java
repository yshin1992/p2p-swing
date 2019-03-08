package org.ysh.p2p.support;

import java.util.EventObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ysh.p2p.support.listener.ApplicationListener;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.ObjectUtils;

public class ApplicationContext {

	/**
	 * 为什么使用Set，因为我们自定义的ListenerCacheKey的equals方法，在某些特殊的情况（可能性很小）有可能会相同，但是Listener确是不同的，因而将其放置在Set集合中
	 */
	private static final Map<ListenerCacheKey, Set<ApplicationListener<? extends EventObject>>> retrieverCache = new ConcurrentHashMap<ApplicationContext.ListenerCacheKey, Set<ApplicationListener<? extends EventObject>>>();
	
	private static final ApplicationContext cxt = new ApplicationContext();
	
	private static final ExecutorService ThreadPool = Executors.newCachedThreadPool();
	
	private ApplicationContext(){
		
	}
	
	public static ApplicationContext getApplicationContext(){
		return cxt;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void publishEvent(final EventObject event){
		Set<ApplicationListener<? extends EventObject>> listeners = this.getApplicationListeners(event);
		if(!listeners.isEmpty()){
			for(final ApplicationListener listener:listeners){
				ThreadPool.execute(new Runnable() {
					
					public void run() {
						listener.onApplicationEvent(event);
					}
				});
				
			}
		}
	}
	
	public void registerListener(EventObject event,ApplicationListener<? extends EventObject> listener){
		Class<?> sourceType = event.getSource().getClass();
		Class<?> eventType = event.getClass();
		
		LogUtil.getLogger(this).warning("EventObject[" +sourceType + "/" + eventType+"]");
		
		ListenerCacheKey cacheKey = new ListenerCacheKey(eventType,sourceType);
		
		if(retrieverCache.containsKey(cacheKey)){
			Set<ApplicationListener<? extends EventObject>> set = retrieverCache.get(cacheKey);
			set.add(listener);
		}else{
			Set<ApplicationListener<? extends EventObject>> set = new HashSet<ApplicationListener<? extends EventObject>>();
			set.add(listener);
			retrieverCache.put(cacheKey, set);
		}
		
	}
	
	private Set<ApplicationListener<? extends EventObject>> getApplicationListeners(EventObject event){
		Class<?> sourceType = event.getSource().getClass();
		Class<?> eventType = event.getClass();
		
		ListenerCacheKey cacheKey = new ListenerCacheKey(eventType,sourceType);
		
		return retrieverCache.get(cacheKey);
	}
	
	private static class ListenerCacheKey{
		
		private Class<?> sourceType;
		
		private Class<?> eventType;
		
		public ListenerCacheKey(Class<?> eventType,Class<?> sourceType){
			this.sourceType = sourceType;
			this.eventType = eventType;
		}
		
		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			ListenerCacheKey otherKey = (ListenerCacheKey) other;
			return ObjectUtils.nullSafeEquals(this.eventType, otherKey.eventType) &&
					ObjectUtils.nullSafeEquals(this.sourceType, otherKey.sourceType);
		}

		@Override
		public int hashCode() {
			return ObjectUtils.nullSafeHashCode(this.eventType) * 29 + ObjectUtils.nullSafeHashCode(this.sourceType);
		}
		
	}
	
}
