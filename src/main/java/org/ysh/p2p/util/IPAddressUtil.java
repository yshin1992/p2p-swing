package org.ysh.p2p.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPAddressUtil {

	private static final String[] DISPLAY_NAME={
		"Software Loopback Interface","VMware Virtual Ethernet Adapter","Realtek PCIe GBE Family Controller","Wireless"
	};
	
	private static final String LOCALIP =  "127.0.0.1";
	
	private static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(networkInterface.getDisplayName() + "/" + ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }
	
	
	public static String getLocalAvailableIP(){
		List<String> ipList = getLocalIPList();
		if(!ipList.isEmpty()){
			for(String ip : ipList){
				if(ip.startsWith(DISPLAY_NAME[2])){
					return ip.substring(ip.lastIndexOf("/"));
				}else if(ip.contains(DISPLAY_NAME[3])){
					return ip.substring(ip.lastIndexOf("/"));
				}
			}
		}
		
		return LOCALIP;
	}
}
