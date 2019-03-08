package org.ysh.p2p;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPTest {
	 public static void main(String[] args) {
		 InetAddress ia=null;
	     try {
	         ia=InetAddress.getLocalHost();
	         
	         String localname=ia.getHostName();
	         String localip=ia.getHostAddress();
	         System.out.println("本机名称是："+ localname);
	         System.out.println("本机的ip是 ："+localip);
	     } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	     
	     List<String> ipList = getLocalIPList();
	     for(String ip : ipList){
	    	 System.out.println(ip);
	     }
	}
	 
	 public static List<String> getLocalIPList() {
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
}
