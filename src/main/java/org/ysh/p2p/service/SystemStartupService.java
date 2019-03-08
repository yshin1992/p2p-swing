package org.ysh.p2p.service;

import java.util.Map;

public interface SystemStartupService {

	public Map<String,Object> start();
	
	public void shutdown();
}
