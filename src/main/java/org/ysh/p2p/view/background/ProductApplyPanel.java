package org.ysh.p2p.view.background;

import java.awt.CardLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.ysh.p2p.enums.ProductEnum;
import org.ysh.p2p.model.Product;
import org.ysh.p2p.service.ProductService;
import org.ysh.p2p.service.impl.ProductServiceImpl;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.util.ViewUtil;
import org.ysh.p2p.view.DateChooserJButton;
import org.ysh.p2p.vo.DropDownDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;
import org.ysh.p2p.vo.ProductDto;

/**
 * 项目申请Panel
 * @author yshin1992
 *
 */
public class ProductApplyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 423473145097297478L;

	private CardLayout card = new CardLayout();
	private JPanel cardPanel = new JPanel(card);
	
	private JButton createBtn = new JButton("新建项目");
	
	public ProductApplyPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		cardPanel.add("List",new ListPanel());
		cardPanel.add("Create",new ProductCreatePanel());
		this.add(cardPanel);
	}
	
	class ListPanel extends JPanel{

		private static final long serialVersionUID = -808736041217992349L;
		
		public ListPanel(){
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			createBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					card.show(cardPanel, "Create");
				}
			});
			this.add(createBtn);
			
			JTabbedPane tab = new JTabbedPane();
			tab.add("待处理项目",new JScrollPane(new UndealPanel()));
			tab.add("已处理项目",new JScrollPane(new DealedPanel()));
			tab.setAlignmentX(LEFT_ALIGNMENT);
			this.add(tab);
		}
		
		
		class UndealPanel extends JPanel{

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
			
			private String[] tableHead={
					"项目编号","标题","借款人","借款企业","担保机构","借款金额(元)","年利率(%)","借款期限","还款方式","标种名称","创建时间","状态"	
				};
			
			private DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
			
			private ProductService productService = new ProductServiceImpl();
			private ProductDto dto = new ProductDto();
			private PageResponse<Product> pager = productService.queryByPage(new PageRequest(), dto);
			
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
						dto.setKeywords(keyWordF.getText().trim());
						dto.setQueryStart(startF.getText().trim());
						dto.setQueryEnd(endF.getText().trim());
						dto.setDesc("1");
						pager = productService.queryByPage(new PageRequest(), dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						
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
				setDataModel(tableModel, pager.getRecords(), pager.getFirstResult()+1);
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
						pager = productService.queryByPage(request, dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
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
							pager = productService.queryByPage(request, dto);
							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						}
					}
				});
				
				nextBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(pager.getCurrentPage() < pager.getTotalPage()){
							Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
							PageRequest request = new PageRequest(pager.getCurrentPage()+1,pageSize);
							pager = productService.queryByPage(request, dto);
							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
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
						pager = productService.queryByPage(request, dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
					}
				});
				
				pagerPanel.add(goBtn);
			}
			
			private void setDataModel(DefaultTableModel model,List<Product> records,int firstResult){
				model.setRowCount(0);//清空原有数据
				for(Product mi:records){
					Vector<Object> row = new Vector<Object>();
					row.add(mi.getProductCd());
					row.add(mi.getProductNm());
					row.add(mi.getMember().getRealNm());
					row.add("");
					row.add("");
					row.add(mi.getAmountF());
					row.add(mi.getRate());
					row.add(mi.getPeriod());
					row.add(mi.getRepayMethod());
					row.add(mi.getTenderKindName());
					row.add(DateUtil.defaultFormat(mi.getCreateTime()));
					row.add(mi.getStatusName());
					model.addRow(row);
				}
			}
		}
		/**
		 * 产品已处理面板
		 * @author yshin1992
		 *
		 */
		class DealedPanel extends JPanel{

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
			private JButton delBtn = new JButton("撤回");
			
			private JPanel pagerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			private JComboBox<Integer> pageSizeCb = new JComboBox<Integer>(new Integer[]{1,5,10,20,30,40});
			private JButton refreshBtn = new JButton("刷新");
			private JTextField pageF=new JTextField("1",3);
			private JButton goBtn = new JButton("Go");
			private JButton preBtn = new JButton("上一页");
			private JButton nextBtn = new JButton("下一页");
			
			private String[] tableHead={
					"项目编号","标题","借款人","借款企业","担保机构","借款金额(元)","年利率(%)","借款期限","还款方式","标种名称","创建时间","状态"	
				};
			
			private DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
			
			private ProductService productService = new ProductServiceImpl();
			private ProductDto dto = new ProductDto();
			private PageResponse<Product> pager;
			
			private JTable table = new JTable(tableModel);
			
			public DealedPanel(){
				
				dto.setDesc("2");
				pager = productService.queryByPage(new PageRequest(), dto);
				
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
						dto.setKeywords(keyWordF.getText().trim());
						dto.setQueryStart(startF.getText().trim());
						dto.setQueryEnd(endF.getText().trim());
						dto.setDesc("1");
						pager = productService.queryByPage(new PageRequest(), dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						
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
				setDataModel(tableModel, pager.getRecords(), pager.getFirstResult()+1);
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
						pager = productService.queryByPage(request, dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
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
							pager = productService.queryByPage(request, dto);
							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
						}
					}
				});
				
				nextBtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(pager.getCurrentPage() < pager.getTotalPage()){
							Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
							PageRequest request = new PageRequest(pager.getCurrentPage()+1,pageSize);
							pager = productService.queryByPage(request, dto);
							setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
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
						pager = productService.queryByPage(request, dto);
						setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
					}
				});
				
				pagerPanel.add(goBtn);
			}
			
			private void setDataModel(DefaultTableModel model,List<Product> records,int firstResult){
				model.setRowCount(0);//清空原有数据
				for(Product mi:records){
					Vector<Object> row = new Vector<Object>();
					row.add(mi.getProductCd());
					row.add(mi.getProductNm());
					row.add(mi.getMember().getRealNm());
					row.add("");
					row.add("");
					row.add(mi.getAmountF());
					row.add(mi.getRate());
					row.add(mi.getPeriod());
					row.add(mi.getRepayMethod());
					row.add(mi.getTenderKindName());
					row.add(DateUtil.defaultFormat(mi.getCreateTime()));
					row.add(mi.getStatusName());
					model.addRow(row);
				}
			}
		}
		
	}
	
	/**
	 * 产品新建面板
	 * @author yshin1992
	 *
	 */
	class ProductCreatePanel extends JPanel{
		private static final long serialVersionUID = 4057204102567936921L;

		private JTabbedPane pane = new JTabbedPane();
		
		private JButton submitBtn=new JButton("提交审核");
		private JButton saveBtn = new JButton("保存");
		
		//基本信息 文本域
		private JTextField unitPriceF,quantityF,amountF,periodF,rateF,minTenderF,maxTenderF,minFullF,agreementNoF;
		private JComboBox<String> repayMethodF,periodTypeF,templateF,fundUseF,bussinessTypeF,kindF;
		private JTextField titleF,awardF;
		private JTextArea prdContentArea,fundUseArea,repaySourceArea;
		private DateChooserJButton groundBtn,investStartBtn,investEndBtn;
		private JRadioButton recommendYes,recommendNo,awardYes,awardNo;
		
		//费用信息文本域
		
		//借款方信息
		
		//保障措施
		
		//质押物信息
		
		//认证信息
		
		//图片资料
		
		public ProductCreatePanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			submitBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showConfirmDialog(ViewUtil.getRootFrame(ProductCreatePanel.this),  "提交审核成功!","提示",JOptionPane.CLOSED_OPTION);
					card.show(cardPanel, "List");
				}
			});
			topPanel.add(submitBtn);
			
			saveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showConfirmDialog(ViewUtil.getRootFrame(ProductCreatePanel.this),  "保存成功!","提示",JOptionPane.CLOSED_OPTION);
					card.show(cardPanel, "List");
				}
			});
			topPanel.add(saveBtn);
			topPanel.setAlignmentX(LEFT_ALIGNMENT);
			this.add(topPanel);
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
			
			
			pane.setAlignmentX(LEFT_ALIGNMENT);
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
