package org.ysh.p2p.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("img/album1.png"));
		setIconImage(img);
	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				new FrontMainFrame();
			}
		});
	}
	
	class ContentPanel extends JPanel{

		private static final long serialVersionUID = 6147720156260500362L;
		
		public ContentPanel() throws IOException{
			this.setLayout(null);
			//顶部
			JPanel p1= new JPanel();
			
			p1.setLayout(null);
			
			JPanel p1_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p1_left.add(new JLabel("服务热线：400-828-1949"));
			p1_left.add(new JLabel("QQ群号：205816335"));
			p1_left.add(new JLabel("服务时间：9:00 - 18:00"));
			p1_left.setBounds(40,0,643,40);
			p1.add(p1_left);
			
			
			JPanel p1_right=new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
			
			JButton loginLabel = new JButton("<html><a href='javascript:void(0);'>登录</a></html>");
			p1_right.add(loginLabel);
			loginLabel.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			JButton personalLabel = new JButton("<html><a href='javascript:void(0);'>个人注册</a></html>");
			p1_right.add(personalLabel);
			
			personalLabel.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			JButton enterpriselLabel = new JButton("<html><a href='javascript:void(0);'>企业注册</a></html>");
			p1_right.add(enterpriselLabel);
			
			JButton helpLabel = new JButton("<html><a href='javascript:void(0);'>帮助中心</a></html>");
			p1_right.add(helpLabel);
			p1_right.setBounds(644, 0, 643, 40);
			p1.add(p1_right);
			
			p1.setBounds(0,0,1366,40);
			this.add(p1);
			//中间部分，主要是图片
			JLabel imgLabel = ViewUtil.makeImageLabel("/img/banner.jpg");
			imgLabel.setBounds(0, 40, 1366, 287);
			this.add(imgLabel);
			
			
			//大数据部分
			JPanel dataPart = new JPanel();
			JPanel dataPart_left = new JPanel(new GridLayout(1,1));
			JLabel dataImgLab1= ViewUtil.makeImageLabel("/img/dashuju.png");
			dataPart_left.add(dataImgLab1);
			dataPart.add(dataPart_left);
			
			JPanel dataPart_right = new JPanel(new GridLayout(1,1));
			dataPart_right.add(ViewUtil.makeImageLabel("/img/shouyi.png"));
			dataPart.add(dataPart_right);
			dataPart.setBounds(0,328,1366,350);
			this.add(dataPart);
			
			JPanel dataPart2 = new JPanel();
			dataPart2.setLayout(null);
			JLabel jLabel1 = new JLabel("客观信用，改变中国",JLabel.CENTER);
			jLabel1.setBounds(230,0,450,40);
			dataPart2.add(jLabel1);
			
			JLabel jLabel2 = new JLabel("国内领先的大数据综合服务提供商",JLabel.CENTER);
			jLabel2.setBounds(230,40,450,40);
			dataPart2.add(jLabel2);
			
			JLabel jLabel3 = new JLabel("融资莫愁，投资莫愁",JLabel.CENTER);
			jLabel3.setBounds(686,0,450,40);
			dataPart2.add(jLabel3);
			
			JLabel jLabel4 = new JLabel("国资文化金融互联网借贷平台",JLabel.CENTER);
			jLabel4.setBounds(686,40,450,40);
			dataPart2.add(jLabel4);
			
			dataPart2.setBounds(0,680,1366,80);
			this.add(dataPart2);
			
			
			//事件部分
		/*	JPanel eventPart = new JPanel(new GridLayout(1,3));
			JPanel eventPart1 = new JPanel(new GridLayout(2,1));
			eventPart1.add(ViewUtil.makeImageLabel("/img/event1.png"));
			eventPart1.add(new JLabel("关于我们"));
			eventPart.add(eventPart1);
			
			JPanel eventPart2 = new JPanel(new GridLayout(2,1));
			eventPart2.add(ViewUtil.makeImageLabel("/img/event2.png"));
			eventPart2.add(new JLabel("媒体报道"));
			eventPart.add(eventPart2);
			
			JPanel eventPart3 = new JPanel(new GridLayout(2,1));
			eventPart3.add(ViewUtil.makeImageLabel("/img/event3.png"));
			eventPart3.add(new JLabel("行业新闻"));
			eventPart.add(eventPart3);
			
			this.add(eventPart)*/;
			
			
		}
		
	}
}
