package org.ysh.p2p.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.ysh.p2p.session.Session;

/**
 * 主窗口
 * @author yshin1992
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -6383944154199542681L;

	//主界面
	private JTabbedPane mainPane;
	
	
	public MainFrame(){
		
		/*LoginDialog loginDialog = new LoginDialog(this);
		
		while(null == Session.getInstance().getLoginUser()){
			loginDialog.setVisible(true);
		}*/
		this.initPane();
		this.setSize(1366,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initPane(){
		mainPane = new JTabbedPane();
		mainPane.add("首页",new IndexPanel());
		mainPane.add("项目管理",new ProductPanel());
		
		
		
		this.add(mainPane);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new MainFrame();
			}
		});
	}
}
