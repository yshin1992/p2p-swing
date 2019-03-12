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
import java.util.Vector;

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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.ysh.p2p.enums.ProductEnum;
import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.model.Product;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.util.ViewUtil;
import org.ysh.p2p.view.DateChooserJButton;
import org.ysh.p2p.vo.DropDownDto;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

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
		
		
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menutreePane,new JScrollPane(new ContentPanel()));
		this.add(pane,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.add(new ProductPanel());
		frame.setSize(1366,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	class ListPanel extends JPanel{

		private static final long serialVersionUID = -808736041217992349L;
		
		private JTabbedPane pane = new JTabbedPane();
		
		private JPanel undealPanel=new JPanel();
		
		private JPanel dealPanel = new JPanel();
		
		class UndealPanel extends JPanel{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1320512721989014506L;
			
			private JTextField startF=new JTextField(15);
			private JTextField endF = new JTextField(15);
			private JTextField keyWordF = new JTextField(25);
			
			private JButton searchBtn = new JButton("查询");
			private JButton detailBtn = new JButton("查看详情");
			private JLabel recordCountL = new JLabel();
			private JButton delBtn = new JButton("废弃");
			
			private JPanel pagerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			private JComboBox<Integer> pageSizeCb = new JComboBox<Integer>(new Integer[]{1,5,10,20,30,40});
			private JButton refreshBtn = new JButton("刷新");
			private JTextField pageF=new JTextField("1",3);
			private JButton goBtn = new JButton("Go");
			private JButton preBtn = new JButton("上一页");
			private JButton nextBtn = new JButton("下一页");
			
			private String keyword="";
			private String queryStart="";
			private String queryEnd="";
			
			private String[] tableHead={
					"序号","会员账号","会员昵称","会员姓名","积分获得总量","已使用积分数","未使用积分数","已使用积分面值（元）","最后更新时间"	
				};
			
			private DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
			
			private PageResponse<Product> pager = new PageResponse<Product>(new PageRequest());
			
			private JTable table = new JTable(tableModel);
			
			public UndealPanel(){
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
				JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				
				searchPanel.add(new JLabel("搜索"));
				keyWordF = new JTextField(15);
				searchPanel.add(keyWordF);
				searchPanel.add(new JLabel("更新日期"));
				startF=new JTextField(15);
				searchPanel.add(startF);
				searchPanel.add(new JLabel("到"));
				endF=new JTextField(15);
				searchPanel.add(endF);
				
				searchBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						keyword = keyWordF.getText().trim();
						queryStart = startF.getText().trim();
						queryEnd = endF.getText().trim();
//						pager = memberIntegralService.queryByPage(new PageRequest(), keyword, queryStart, queryEnd);
//						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						
					}
				});
				searchPanel.add(searchBtn);
				
				detailBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int selectedRow = table.getSelectedRow();
						System.out.println("selected ROW -->" + selectedRow);
//						MemberIntegral memberIntegral = pager.getRecords().get(selectedRow);
//						new IntegralRecordDialog(memberIntegral.getMember(), ViewUtil.getRootFrame(IntegralMgrPanel.this));
					}
				});
				searchPanel.add(detailBtn);
				searchPanel.setAlignmentX(LEFT_ALIGNMENT);
				
				this.add(searchPanel);
				
				JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				delBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				deletePanel.add(delBtn);
				deletePanel.add(new JLabel("共 "));
				deletePanel.add(recordCountL);
				deletePanel.add(new JLabel(" 条记录"));
//				setDataModel(tableModel, pager.getRecords(), pager.getFirstResult()+1);
				this.add(new JScrollPane(table));
				
				initPagePanel();
				pagerPanel.setAlignmentX(LEFT_ALIGNMENT);
				this.add(pagerPanel);
			}
			
			private void initPagePanel(){
				for(int i=0;i< pageSizeCb.getItemCount();i++){
					if(pageSizeCb.getItemAt(i).intValue()==pager.getpageSize().intValue()){
						pageSizeCb.setSelectedIndex(i);
						System.out.println("Selected index = " + i);
						break;
					}
				}
				pagerPanel.add(pageSizeCb);
				refreshBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
						PageRequest request = new PageRequest(1,pageSize);
//						pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
//						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
					}
				});
				pagerPanel.add(refreshBtn);
				pagerPanel.add(new JLabel("共" +pager.getTotalCount()+" 条记录"));
				preBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(pager.getCurrentPage()>1){
							Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
							PageRequest request = new PageRequest(pager.getCurrentPage()-1,pageSize);
//							pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
//							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						}
					}
				});
				
				nextBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(pager.getCurrentPage() < pager.getTotalPage()){
							Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
							PageRequest request = new PageRequest(pager.getCurrentPage()+1,pageSize);
//							pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
//							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						}
					}
				});
				pagerPanel.add(preBtn);
				pagerPanel.add(nextBtn);
				pagerPanel.add(new JLabel("到第"));
				pagerPanel.add(pageF);
				pagerPanel.add(new JLabel("页"));
				goBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
						PageRequest request = new PageRequest(Integer.parseInt(pageF.getText().trim()),pageSize);
//						pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
//						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
					}
				});
				
				pagerPanel.add(goBtn);
			}
			
			private void setDataModel(DefaultTableModel model,List<Product> records,int firstResult){
				model.setRowCount(0);//清空原有数据
				int i=0;
				for(Product mi:records){
					Vector<Object> row = new Vector<Object>();
					row.add(firstResult+i);
					row.add(mi.getProductCd());
					row.add(mi.getProductNm());
					row.add(mi.getMember().getRealNm());
					row.add("");
					row.add(mi.getSafeguard().getCompanyId());
					row.add(mi.getAmountF());
					row.add(mi.getRate());
					row.add(mi.getRate());
					row.add(DateUtil.defaultFormat(mi.getUpdateTime()));
					model.addRow(row);
					i++;
				}
			}
		}
		
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


