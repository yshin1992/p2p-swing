package org.ysh.p2p.view.foreground;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.service.SystemStartupService;
import org.ysh.p2p.service.impl.SystemStartupServiceImpl;
import org.ysh.p2p.session.Session;
import org.ysh.p2p.util.ViewUtil;

public class FrontMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237547880438100268L;

	public FrontMainFrame(){
		
		try {
			getContentPane().add(new JScrollPane(new ContentPanel()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setSize(1366,800);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("img/album1.png"));
		setIconImage(img);
	
	}
	
	public static void main(String[] args) {
		
		SystemStartupService startupService = new SystemStartupServiceImpl();
		startupService.start();	
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				new FrontMainFrame();
			}
		});
	}
	
	class ContentPanel extends JPanel{

		private static final long serialVersionUID = 6147720156260500362L;
		
		
		private JPanel cardPanel = new JPanel();
		
		private CardLayout card = new CardLayout();
		
		private JPanel loginCardPanel = new JPanel();
		
		private CardLayout loginCard = new CardLayout();
		
		private JLabel loginNameLabel = new JLabel();
		
		public ContentPanel() throws IOException{
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			//顶部
			JPanel p1= new JPanel();
			
			p1.setLayout(new GridLayout(1,2));
			
			JPanel p1_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p1_left.add(new JLabel("服务热线：400-828-1949"));
			p1_left.add(new JLabel("QQ群号：205816335"));
			p1_left.add(new JLabel("服务时间：9:00 - 18:00"));
			p1.add(p1_left);
			
			
			loginCardPanel.setLayout(loginCard);
			
			loginCardPanel.add("beforeLogin",new BeforeLoginPanel());
			loginCardPanel.add("afterLogin",new AfterLoginPanel());
			
			loginCardPanel.setAlignmentX(LEFT_ALIGNMENT);
			p1.add(loginCardPanel);
			
			p1.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p1);
			
			JPanel pIndex= new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel pIndex_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
			pIndex_left.add(ViewUtil.makeImageLabel("/img/index2.png"));
			pIndex_left.add(new JLabel("国资互联网文化金融综合服务平台"));
			pIndex.add(pIndex_left);
			
			
			JPanel pIndex_right= new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton indexBtn = new JButton("首页");
			indexBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					card.show(cardPanel, "index");	
				}
			});
			pIndex_right.add(indexBtn);
			
			JButton investBtn = new JButton("我要投资");
			pIndex_right.add(investBtn);
			
			JButton bigDataBtn = new JButton("大数据介绍");
			bigDataBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					card.show(cardPanel, "bigData");
				}
			});
			pIndex_right.add(bigDataBtn);
			
			JButton infoBtn = new JButton("信息披露");
			pIndex_right.add(infoBtn);
			
			JButton accountBtn = new JButton("我的账户");
			pIndex_right.add(accountBtn);
			
			pIndex_left.add(pIndex_right);
			pIndex.setAlignmentX(LEFT_ALIGNMENT);
			
			this.add(pIndex);
			
			cardPanel.setLayout(card);
			cardPanel.add("index",new IndexPanel());
			cardPanel.add("bigData",new BigDataPanel());
			cardPanel.setAlignmentX(LEFT_ALIGNMENT);
			
			this.add(cardPanel);
		}
		
		class BeforeLoginPanel extends JPanel
		{
			private static final long serialVersionUID = -6557802548180632816L;
			
			public BeforeLoginPanel(){
				setLayout(new FlowLayout(FlowLayout.RIGHT)); 
				JButton loginLabel = new JButton("<html><a href='javascript:void(0);'>登录</a></html>");
				this.add(loginLabel);
				loginLabel.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						new MemberLoginDialog(FrontMainFrame.this);
						Member loginMember = Session.getInstance().getLoginMember();
						if(loginMember != null){
							loginNameLabel.setText("您好 ："+loginMember.getNickName());
							loginCard.show(loginCardPanel, "afterLogin");
						}
					}
				});
				
				JButton personalLabel = new JButton("<html><a href='javascript:void(0);'>个人注册</a></html>");
				this.add(personalLabel);
				
				personalLabel.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						new PersonalRegisterDialog(FrontMainFrame.this); 
						
						//登录完成之后切换面板
						
						//TODO
					}
				});
				
				JButton enterpriselLabel = new JButton("<html><a href='javascript:void(0);'>企业注册</a></html>");
				this.add(enterpriselLabel);
				
				JButton helpLabel = new JButton("<html><a href='javascript:void(0);'>帮助中心</a></html>");
				this.add(helpLabel);
			}
		}
		
		class AfterLoginPanel extends JPanel{

			private static final long serialVersionUID = 2942765142351670255L;
			
			public AfterLoginPanel(){
				this.setLayout(new FlowLayout(FlowLayout.RIGHT));
				JButton helpBtn = new JButton("帮助中心");
				helpBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				this.add(loginNameLabel);
				
				JButton exitBtn = new JButton("退出登录");
				exitBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						loginCard.show(loginCardPanel, "beforeLogin");
						Session.getInstance().setLoginMember(null);
					}
				});
				this.add(exitBtn);
			}
		}
	}
	
	
	
}
