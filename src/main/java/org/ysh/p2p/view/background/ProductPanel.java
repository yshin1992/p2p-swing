package org.ysh.p2p.view.background;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.ysh.p2p.enums.ProductEnum;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.view.DateChooserJButton;
import org.ysh.p2p.vo.DropDownDto;

/**
 * 项目管理
 * @author yshin1992
 *
 */
public class ProductPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSplitPane pane;
	
	/**
	 * 树形菜单面板
	 */
	private JScrollPane menutreePane;
	
	private JTree menuTree;
	
	/**
	 * 内容面板
	 */
	private JScrollPane contentPane;
	
	/**
	 * 后期根据数据库读取用户权限
	 */
	private void initMenuTreePane(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("项目管理");
		
		DefaultMutableTreeNode productNode = new DefaultMutableTreeNode("项目管理");
		root.add(productNode);
		
		DefaultMutableTreeNode orderMgrNode = new DefaultMutableTreeNode("订单管理");
		productNode.add(orderMgrNode);
		DefaultMutableTreeNode productApplyNode = new DefaultMutableTreeNode("项目申请");
		productNode.add(productApplyNode);
		
		
		DefaultMutableTreeNode creditNode = new DefaultMutableTreeNode("债权转让");
		root.add(creditNode);
		DefaultMutableTreeNode creditSetNode = new DefaultMutableTreeNode("债权转让设置");
		creditNode.add(creditSetNode);
		DefaultMutableTreeNode creditMgrNode = new DefaultMutableTreeNode("债权转让管理");
		creditNode.add(creditMgrNode);
		
		DefaultMutableTreeNode loanNode = new DefaultMutableTreeNode("借款申请");
		root.add(loanNode);
		DefaultMutableTreeNode loanMgrNode = new DefaultMutableTreeNode("借款申请管理");
		loanNode.add(loanMgrNode);
		
		menuTree = new JTree(root);
		
		menuTree.setRootVisible(false);
		
		menutreePane=new JScrollPane(menuTree);
		
		menuTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				if(null == path)
					return;
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
				if(selectedNode.isLeaf()){
					System.out.println(selectedNode.getUserObject());
				}
				
			}
		});
	}
	
	public ProductPanel(){
		
		initMenuTreePane();
		
		this.setLayout(new BorderLayout());
		
		contentPane = new JScrollPane(new JLabel("Hello"));
		
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menutreePane,contentPane);
		this.add(pane,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
//		frame.add(new ProductPanel());
		addComponentsToPane(frame.getContentPane());
		frame.setSize(1366,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void addComponentsToPane(Container pane) {
	    JPanel xPanel = new JPanel();
	    xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.X_AXIS));
	    addButtons(xPanel);
	    JPanel yPanel = new JPanel();
	    yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));
	    addButtons(yPanel);
	   
	    pane.add(yPanel, BorderLayout.PAGE_START);
	    pane.add(xPanel, BorderLayout.PAGE_END);
	}
	 
	private static void addAButton(String text, Container container) {
	    JButton button = new JButton(text);
	    button.setAlignmentX(Component.LEFT_ALIGNMENT);
	    container.add(button);
	}
	 
	private static void addButtons(Container container) {
	    addAButton("Button 1", container);
	    addAButton("Button 2", container);
	    addAButton("Button 3", container);
	    addAButton("Long-Named Button 4", container);
	    addAButton("5", container);
	}
	
	class ContentPanel extends JPanel{
		
		private JTabbedPane pane = new JTabbedPane();
		
		private JTextField unitPriceF,quantityF,amountF,periodF,rateF,minTenderF,maxTenderF,minFullF,agreementNoF;
		
		private JComboBox<String> repayMethodF,periodTypeF,templateF,fundUseF,bussinessTypeF,kindF;
		
		private JTextField titleF,awardF;
		
		private JTextArea prdContentArea,fundUseArea,repaySourceArea;
		
		private DateChooserJButton groundBtn,investStartBtn,investEndBtn;
		
		private JRadioButton recommendYes,recommendNo,awardYes,awardNo;
		
		void initBaseInfoPanel(){
			JPanel panel = new JPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			JLabel label1 = new JLabel("基础信息");
			label1.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(label1);
			
			JPanel baseinfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			baseinfoPanel.add(new JLabel("*产品单价"));
			unitPriceF = new JTextField(15);
			baseinfoPanel.add(unitPriceF);
			baseinfoPanel.add(new JLabel("元"));
			
			baseinfoPanel.add(new JLabel("*产品数量"));
			quantityF = new JTextField(15);
			baseinfoPanel.add(baseinfoPanel);
			
			baseinfoPanel.add(new JLabel("借款金额"));
			amountF= new JTextField("0",15);
			baseinfoPanel.add(amountF);
			baseinfoPanel.add(new JLabel("元"));
			
			baseinfoPanel.add(new JLabel("*还款方式"));
			List<DropDownDto> configList = CacheUtil.getConfigList(ProductEnum.REPAY_METHOD.getCode());
			String[] repayMethods = new String[configList.size()-1];//剔除“全部”这一项
			for(int i=0;i<repayMethods.length;i++){
				if("全部".equals(configList.get(i).getAttrNm()))
					continue;
				repayMethods[i] = configList.get(i).getAttrNm();
			}
			repayMethodF = new JComboBox<String>(repayMethods);
			baseinfoPanel.add(repayMethodF);
			
			baseinfoPanel.add(new JLabel("*借款期限"));
			periodF = new JTextField(15);
			baseinfoPanel.add(periodF);
			
			List<DropDownDto> periodList = CacheUtil.getConfigList(ProductEnum.PERIOD_TYPE.getCode());
			String[] periodTypes = new String[periodList.size()-1];
			for(int i=0;i<periodTypes.length;i++){
				periodTypes[i] = periodList.get(i).getAttrNm();
			}
			periodTypeF = new JComboBox<String>(periodTypes);
			baseinfoPanel.add(periodTypeF);
			
			baseinfoPanel.add(new JLabel("*年利率"));
			rateF= new JTextField(15);
			baseinfoPanel.add(rateF);
			baseinfoPanel.add(new JLabel("%"));
			baseinfoPanel.add(new JLabel("*最小投资数量"));
			minTenderF = new JTextField(15);
			baseinfoPanel.add(minTenderF);
			baseinfoPanel.add(new JLabel("最大投资数量"));
			maxTenderF = new JTextField(15);
			baseinfoPanel.add(maxTenderF);
			baseinfoPanel.add(new JLabel("最小满标数量"));
			minFullF = new JTextField(15);
			baseinfoPanel.add(minFullF);
			baseinfoPanel.add(new JLabel("*合同编号"));
			agreementNoF = new JTextField(15);
			baseinfoPanel.add(agreementNoF);
			baseinfoPanel.add(new JLabel("*合同模板"));
			
			
			templateF = new JComboBox<String>();
			baseinfoPanel.add();
			baseinfoPanel.add();
			baseinfoPanel.add();
			
			
			
			pane.add("基本信息",panel);
			
		}
		
	}
	

}


