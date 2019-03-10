package org.ysh.p2p.view.background;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 主页面面板
 * @author yshin1992
 *
 */
public class IndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel leftPanel,rightPanel;
	
	public IndexPanel(){
		this.setLayout(new FlowLayout(FlowLayout.CENTER,30,50));
		leftPanel = new JPanel();
		this.add(leftPanel);
		
		leftPanel.setLayout(new GridLayout(10,1));
		
		leftPanel.add(new JLabel("我的待办"));
		leftPanel.add(new JButton("项目管理"));
		leftPanel.add(new JLabel("待审项目:0  审核驳回:0"));
		leftPanel.add(new JLabel("满标待审核：0 流标待处理：4"));
		leftPanel.add(new JLabel("借款申请:3"));
		
		leftPanel.add(new JButton("订单管理"));
		leftPanel.add(new JLabel("待审订单:0"));
		
		leftPanel.add(new JButton("财务管理"));
		leftPanel.add(new JLabel("项目待放款:0 项目待还款:0"));
		leftPanel.add(new JLabel("项目待退款:0 订单待退款:0"));
		
		rightPanel = new JPanel();
		this.add(rightPanel);
		
		rightPanel.setLayout(new GridLayout(6,1));
		rightPanel.add(new JLabel("消息提醒"));
		rightPanel.add(new JButton("项目关系"));
		rightPanel.add(new JLabel("待招标项目：0"));
		rightPanel.add(new JLabel("招标中项目：0"));
		rightPanel.add(new JLabel("到期提醒：0"));
		rightPanel.add(new JLabel("逾期提醒：10"));
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new IndexPanel());
		frame.setSize(1366,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
