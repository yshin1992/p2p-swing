package org.ysh.p2p.view.background;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.util.StringUtil;
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
	 * 新建项目和项目列表之间的cardLayout
	 */
	private CardLayout newPrdCard = new CardLayout();
	
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
		
		contentPane = new JScrollPane(new ContentPanel());
		
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menutreePane,contentPane);
		this.add(pane,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.add(new ProductPanel());
//		addComponentsToPane(frame.getContentPane());
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
	
	class ListPanel extends JPanel{

		private static final long serialVersionUID = -808736041217992349L;
		
		private JTabbedPane pane = new JTabbedPane();
		
		private JPanel undealPanel=new JPanel();
		
		private JPanel dealPanel = new JPanel();
		
		
		
	}
	
	class ContentPanel extends JPanel{
		private static final long serialVersionUID = 4057204102567936921L;

		private JTabbedPane pane = new JTabbedPane();
		
		private JTextField unitPriceF,quantityF,amountF,periodF,rateF,minTenderF,maxTenderF,minFullF,agreementNoF;
		
		private JComboBox<String> repayMethodF,periodTypeF,templateF,fundUseF,bussinessTypeF,kindF;
		
		private JTextField titleF,awardF;
		
		private JTextArea prdContentArea,fundUseArea,repaySourceArea;
		
		private DateChooserJButton groundBtn,investStartBtn,investEndBtn;
		
		private JRadioButton recommendYes,recommendNo,awardYes,awardNo;
		
		public ContentPanel(){
			initBaseInfoPanel();
		}
		void initBaseInfoPanel(){
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
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
			baseinfoPanel.add(quantityF);
			
			baseinfoPanel.add(new JLabel("借款金额"));
			amountF= new JTextField("0",15);
			baseinfoPanel.add(amountF);
			baseinfoPanel.add(new JLabel("元"));
			
			JPanel baseinfoPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			baseinfoPanel2.add(new JLabel("*还款方式"));
			List<DropDownDto> configList = CacheUtil.getConfigList(ProductEnum.REPAY_METHOD.getCode());
			System.out.println(Arrays.toString(configList.toArray()));
			repayMethodF = initComboBox(configList);
			baseinfoPanel2.add(repayMethodF);
			
			baseinfoPanel2.add(new JLabel("*借款期限"));
			periodF = new JTextField(15);
			baseinfoPanel2.add(periodF);
			
			List<DropDownDto> periodList = CacheUtil.getConfigList(ProductEnum.PERIOD_TYPE.getCode());
			periodTypeF = initComboBox(periodList);
			baseinfoPanel2.add(periodTypeF);
			
			baseinfoPanel2.add(new JLabel("*年利率"));
			rateF= new JTextField(15);
			baseinfoPanel2.add(rateF);
			baseinfoPanel2.add(new JLabel("%"));
			
			JPanel baseinfoPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			baseinfoPanel3.add(new JLabel("*最小投资数量"));
			minTenderF = new JTextField(15);
			baseinfoPanel3.add(minTenderF);
			baseinfoPanel3.add(new JLabel("最大投资数量"));
			maxTenderF = new JTextField(15);
			baseinfoPanel3.add(maxTenderF);
			baseinfoPanel3.add(new JLabel("最小满标数量"));
			minFullF = new JTextField(15);
			baseinfoPanel3.add(minFullF);
			
			JPanel baseinfoPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			baseinfoPanel4.add(new JLabel("*合同编号"));
			agreementNoF = new JTextField(15);
			baseinfoPanel4.add(agreementNoF);
			baseinfoPanel4.add(new JLabel("*合同模板"));
			
			
			templateF = new JComboBox<String>();
			baseinfoPanel4.add(templateF);
			
			baseinfoPanel4.add(new JLabel("*借款用途"));
			List<DropDownDto> borrowUseList = CacheUtil.getConfigList(ProductEnum.BRROW_USE.getCode());
			fundUseF = initComboBox(borrowUseList);
			baseinfoPanel4.add(fundUseF);
			
			JPanel baseinfoPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			baseinfoPanel5.add(new JLabel("*业务类型"));
			List<DropDownDto> businessTypeList = CacheUtil.getConfigList(ProductEnum.BUSINESS_TYPE.getCode());
			bussinessTypeF = initComboBox(businessTypeList);
			baseinfoPanel5.add(bussinessTypeF);
			baseinfoPanel5.add(new JLabel("*标种类型"));
			List<DropDownDto> tendKindList = CacheUtil.getConfigList(ProductEnum.TENDER_KIND.getCode());
			kindF = initComboBox(tendKindList);
			baseinfoPanel5.add(kindF);
			
			baseinfoPanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(baseinfoPanel);
			
			baseinfoPanel2.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(baseinfoPanel2);
			
			baseinfoPanel3.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(baseinfoPanel3);
			
			baseinfoPanel4.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(baseinfoPanel4);
			
			baseinfoPanel5.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(baseinfoPanel5);
			
			JLabel label2= new JLabel("项目介绍");
			label2.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(label2);
			
			JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			titlePanel.add(new JLabel("*标题"));
			titleF = new JTextField(50);
			titlePanel.add(titleF);
			
			titlePanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(titlePanel);
			
			JPanel prdCntPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
			prdCntPanel.add(new JLabel("*项目简介"));
			prdContentArea = new JTextArea(4,50);
			prdCntPanel.add(new JScrollPane(prdContentArea));
			prdCntPanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(prdCntPanel);
			
			JPanel borrowUsePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			borrowUsePanel.add(new JLabel("*资金用途"));
			fundUseArea = new JTextArea(4,50);
			borrowUsePanel.add(new JScrollPane(fundUseArea));
			borrowUsePanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(borrowUsePanel);
			
			JPanel repaySourcePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			repaySourcePanel.add(new JLabel(" 还款来源"));
			repaySourceArea = new JTextArea(4,50);
			repaySourcePanel.add(new JScrollPane(repaySourceArea));
			repaySourcePanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(repaySourcePanel);
			
			panel.add(new JLabel("其他信息"));
			
			JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			Date now = new Date();
			groundBtn = new DateChooserJButton(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), DateUtil.defaultFormat(now));
			timePanel.add(new JLabel("*申请上线时间"));
			timePanel.add(groundBtn);
			
			investStartBtn = new DateChooserJButton(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), DateUtil.defaultFormat(now));
			timePanel.add(new JLabel("*申请投标开始"));
			timePanel.add(investStartBtn);
			
			investEndBtn = new DateChooserJButton(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), DateUtil.defaultFormat(DateUtil.addHours(now, 1)));
			timePanel.add(new JLabel("*申请投标结束"));
			timePanel.add(investEndBtn);
			
			timePanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(timePanel);
			
			JPanel awardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			ButtonGroup btnGroup1 =new ButtonGroup();
			awardPanel.add(new JLabel("首页推荐"));
			recommendNo = new JRadioButton("否",true);
			btnGroup1.add(recommendNo);
			recommendYes = new JRadioButton("是");
			btnGroup1.add(recommendYes);
			awardPanel.add(recommendNo);
			awardPanel.add(recommendYes);
			
			awardPanel.add(new JLabel("投资奖励"));
			ButtonGroup btnGroup2 = new ButtonGroup();
			awardNo = new JRadioButton("否",true);
			awardNo.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					awardF.setText("");
					awardF.setEditable(false);
				}
			});
			awardYes = new JRadioButton("是");
			awardYes.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(awardYes.isSelected()){
						awardF.setEditable(true);
					}
				}
			});
			btnGroup2.add(awardNo);
			btnGroup2.add(awardYes);
			awardPanel.add(awardNo);
			awardPanel.add(awardYes);
			
			awardPanel.add(new JLabel("奖励比例"));
			awardF = new JTextField(15);
			awardF.setEditable(false);
			awardPanel.add(awardF);
			awardPanel.add(new JLabel("%"));
			
			awardPanel.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(awardPanel);
			
			pane.add("基本信息",panel);
			this.add(pane);
		}
		
		
		private JComboBox<String> initComboBox(List<DropDownDto> dtoList){
			if(StringUtil.isEmpty(dtoList)){
				return new JComboBox<String>();
			}
			String[] items = new String[dtoList.size() -1];
			int i=0;
			for(DropDownDto dto : dtoList){
				if("全部".equals(dto.getAttrNm()))
					continue;
				items[i++] = dto.getAttrNm();
				System.out.println(dto.getAttrNm());
			}
			return new JComboBox<String>(items);
		}
	}
	

}


