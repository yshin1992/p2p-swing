package org.ysh.p2p;

import javax.swing.SwingUtilities;

import org.ysh.p2p.service.SystemStartupService;
import org.ysh.p2p.service.impl.SystemStartupServiceImpl;
import org.ysh.p2p.view.background.MainFrame;
import org.ysh.p2p.view.foreground.FrontMainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final SystemStartupService startupService = new SystemStartupServiceImpl();
    	startupService.start();	
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
//				new FrontMainFrame();
				new MainFrame();
			}
		});
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				startupService.shutdown();
			}
			
		});
    }
}
