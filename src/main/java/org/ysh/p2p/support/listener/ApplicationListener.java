package org.ysh.p2p.support.listener;

import java.util.EventListener;
import java.util.EventObject;

public interface ApplicationListener<E extends EventObject> extends EventListener {
	
	public void onApplicationEvent(E event);
	
}
