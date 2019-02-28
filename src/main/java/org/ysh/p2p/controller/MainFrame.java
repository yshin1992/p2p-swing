package org.ysh.p2p.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.ysh.p2p.session.Session;

/**
 * 主窗口
 * @author yshin1992
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -6383944154199542681L;

	public MainFrame(){
		
		LoginDialog loginDialog = new LoginDialog(this);
		
		while(null == Session.getInstance().getLoginUser()){
			loginDialog.setVisible(true);
		}
		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new MainFrame();
			}
		});
	}
}
