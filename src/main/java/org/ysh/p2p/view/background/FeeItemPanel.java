package org.ysh.p2p.view.background;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.ysh.p2p.model.ItemType;
import org.ysh.p2p.service.ItemTypeService;
import org.ysh.p2p.service.impl.ItemTypeServiceImpl;
import org.ysh.p2p.session.Session;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.DecimalUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.DropDownDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public class FeeItemPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7018603315948893657L;

	private JButton addBtn = new JButton("新增收费项");
	
	private CardLayout cardLayout = new CardLayout();
	
	public FeeItemPanel(){
		this.setLayout(cardLayout);
		this.add("List",new ListPanel());
		this.add("Edit",new EditPanel());
	}
	
	class ListPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		private JTextField keyWordF = new JTextField(25);
		private JButton searchBtn = new JButton("查询");
		private JButton editBtn = new JButton("编辑");
		private JLabel recordCountL = new JLabel();
		private JButton delBtn = new JButton("删除");
		
		private JPanel pagerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		private JComboBox<Integer> pageSizeCb = new JComboBox<Integer>(new Integer[]{1,5,10,20,30,40});
		private JButton refreshBtn = new JButton("刷新");
		private JTextField pageF=new JTextField("1",3);
		private JButton goBtn = new JButton("Go");
		private JButton preBtn = new JButton("上一页");
		private JButton nextBtn = new JButton("下一页");
		
		private String keyword=null;
		
		private String[] tableHead={
				"费用项目名称","付款方","收款方","费用节点","费率参考","付费费率(%)","是否关联期数或天数","上限(元)","下限(元)"	
			};
		
		private DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
		
		private ItemTypeService typeService = new ItemTypeServiceImpl();
		private PageResponse<ItemType> pager = typeService.queryByPage(new PageRequest(), null);
		
		private JTable table = new JTable(tableModel);
		
		public ListPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			addBtn.setAlignmentX(LEFT_ALIGNMENT);
			addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(FeeItemPanel.this,"Edit");	
				}
			});
			this.add(addBtn);
			
			JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			searchPanel.add(new JLabel("搜索"));
			keyWordF = new JTextField(15);
			searchPanel.add(keyWordF);
			searchBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					keyword = keyWordF.getText().trim();
					pager = typeService.queryByPage(new PageRequest(), keyword);
					setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
					
				}
			});
			searchPanel.add(searchBtn);
			editBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int selectedRow = table.getSelectedRow();
					System.out.println("selected ROW -->" + selectedRow);
//					MemberIntegral memberIntegral = pager.getRecords().get(selectedRow);
//					new IntegralRecordDialog(memberIntegral.getMember(), ViewUtil.getRootFrame(IntegralMgrPanel.this));
				}
			});
			searchPanel.add(editBtn);
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
					break;
				}
			}
			pagerPanel.add(pageSizeCb);
			refreshBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Integer pageSize = (Integer) pageSizeCb.getSelectedItem();
					PageRequest request = new PageRequest(1,pageSize);
					pager = typeService.queryByPage(request, keyword);
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
						pager = typeService.queryByPage(request, keyword);
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
						pager = typeService.queryByPage(request, keyword);
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
					pager = typeService.queryByPage(request, keyword);
					setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
				}
			});
			
			pagerPanel.add(goBtn);
		}
		
		private void setDataModel(DefaultTableModel model,List<ItemType> records,int firstResult){
			model.setRowCount(0);//清空原有数据
			for(ItemType mi:records){
				Vector<Object> row = new Vector<Object>();
				row.add(mi.getItemTypeNm());
				row.add(mi.getBillerName());
				row.add(mi.getChargerName());
				row.add(mi.getNodeName());
				row.add(mi.getRateReferenedNm());
				row.add(mi.getRateP());
				row.add(mi.getPeriodOrDay() == 0 ?"否":"是");
				if(StringUtil.isEmpty(mi.getMaxAmount()) || mi.getMaxAmount().compareTo(BigDecimal.ZERO)<=0){
					row.add("不限");
				}else{
					row.add(new DecimalFormat("#.##").format(mi.getMaxAmount().doubleValue()));
				}
				if(StringUtil.isEmpty(mi.getMinAmount())|| mi.getMinAmount().compareTo(BigDecimal.ZERO)<=0){
					row.add("不限");
				}else{
					row.add(new DecimalFormat("#.##").format(mi.getMinAmount().doubleValue()));
				}
				model.addRow(row);
			}
		}
	}
	
	class EditPanel extends JPanel{

		private static final long serialVersionUID = 2867950987516517638L;
		
		private List<DropDownDto> feeTypeList = CacheUtil.getConfigList("feeTypeType");
		private List<DropDownDto> feeNodeList = CacheUtil.getConfigList("feesetting.feenode");
		private List<DropDownDto> referenceList = CacheUtil.getConfigList("feesetting.feereference");
		
		private JTextField nameF,rateF,minF,maxF;
		private JComboBox<String> feeTypeBox,feeNodeBox,referenceBox;
		private JCheckBox periodC,onlineCalC;
		
		private JComboBox<String> billerBox,chargerBox;
		
		private JRadioButton fixR,relR;
		
		private String[] memberType={"融资人","投资人","平台","担保方 ","转让人","受让人","三方支付公司","渠道商"};
		private Integer[] memberType_Int={1,2,11,12,3,4,13,15};
 		
		private JButton saveBtn,retBtn;
		
		private ItemTypeService typeService = new ItemTypeServiceImpl();
		
		private void init(){
			feeTypeBox = new JComboBox<String>();
			for(DropDownDto dto: feeTypeList){
				feeTypeBox.addItem(dto.getAttrNm());
			}
			
			feeNodeBox = new JComboBox<String>();
			for(DropDownDto dto: feeNodeList){
				feeNodeBox.addItem(dto.getAttrNm());
			}
			
			referenceBox = new JComboBox<String>();
			for(DropDownDto dto: referenceList){
				referenceBox.addItem(dto.getAttrNm());
			}
			
			nameF=new JTextField(15);
			rateF=new JTextField(15);
			minF=new JTextField(15);
			maxF=new JTextField(15);
			
			periodC=new JCheckBox("期数/天数");
			onlineCalC=new JCheckBox("是否参与线上运算");
			
			billerBox=new JComboBox<String>(memberType);
			chargerBox=new JComboBox<String>(memberType);
			
			fixR= new JRadioButton("固定");
			relR= new JRadioButton("按项目");
			
			saveBtn = new JButton("保存");
			retBtn = new JButton("返回");
		}
		
		public EditPanel(){
			init();
			
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel top=new JPanel(new FlowLayout(FlowLayout.LEFT));
			saveBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ItemType itemType = new ItemType();
					itemType.setCreateBy(Session.getInstance().getLoginUser().getUserCd());
					itemType.setCreateTime(new Date());
					itemType.setItemTypeId(StringUtil.generateUuid());
					itemType.setItemTypeNm(nameF.getText().trim());
					itemType.setBiller(memberType_Int[billerBox.getSelectedIndex()]);
					itemType.setCharger(memberType_Int[chargerBox.getSelectedIndex()]);
					itemType.setFeeType(feeTypeList.get(feeTypeBox.getSelectedIndex()).getAttrCd());
					itemType.setNode(Integer.parseInt(feeNodeList.get(feeNodeBox.getSelectedIndex()).getAttrCd()));
					itemType.setEdited(relR.isSelected()? 1:0);
					itemType.setRateReferened(Integer.parseInt(referenceList.get(referenceBox.getSelectedIndex()).getAttrCd()));
					itemType.setRate(StringUtil.isEmpty(rateF.getText().trim())? null : DecimalUtil.fromPercent(new BigDecimal(rateF.getText().trim())));
					itemType.setPeriodOrDay(periodC.isSelected() ? 1 : null);
					itemType.setMaxAmount(StringUtil.isEmpty(maxF.getText().trim())?null:new BigDecimal(maxF.getText().trim()));
					itemType.setMinAmount(StringUtil.isEmpty(minF.getText().trim())?null:new BigDecimal(minF.getText().trim()));
					itemType.setCalOnlineFlag(onlineCalC.isSelected()?1:0);
					
					typeService.add(itemType);
					cardLayout.show(FeeItemPanel.this, "List");
				}
			});
			top.add(saveBtn);
			retBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					cardLayout.show(FeeItemPanel.this, "List");
				}
			});
			top.add(retBtn);
			top.setAlignmentX(LEFT_ALIGNMENT);
			this.add(top);
			
			JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p1.add(new JLabel("*费用名称"));
			p1.add(nameF);
			p1.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p1);
			
			JPanel p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p2.add(new JLabel("*付款方"));
			p2.add(billerBox);
			p2.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p2);
			
			JPanel p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p3.add(new JLabel("*收款方"));
			p3.add(chargerBox);
			p3.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p3);
			
			JPanel p4=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p4.add(new JLabel("*费用分类"));
			p4.add(feeTypeBox);
			p4.add(new JLabel("*费用节点"));
			p4.add(feeNodeBox);
			p4.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p4);
			
			JPanel p5=new JPanel(new FlowLayout(FlowLayout.LEFT));
			ButtonGroup group = new ButtonGroup();
			group.add(fixR);
			group.add(relR);
			p5.add(new JLabel("*扣费方式"));
			p5.add(fixR);
			p5.add(relR);
			p5.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p5);
			
			this.add(new JLabel("选择“期数/天数”，可根据借款期数（天标按天）来扣费；“逾期还款”节点，天数代表逾期天数；上限不填则上不设限 "));
			
			JPanel p6=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p6.add(new JLabel("费率参考"));
			p6.add(referenceBox);
			p6.add(new JLabel("x"));
			p6.add(new JLabel("费率"));
			p6.add(rateF);
			p6.add(new JLabel("x"));
			p6.add(periodC);
			p6.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p6);
			
			JPanel p7=new JPanel(new FlowLayout(FlowLayout.LEFT));
			p7.add(new JLabel("上限"));
			p7.add(maxF);
			p7.add(new JLabel("元"));
			p7.add(new JLabel("下限"));
			p7.add(minF);
			p7.add(new JLabel("元"));
			p7.add(onlineCalC);
			p7.setAlignmentX(LEFT_ALIGNMENT);
			this.add(p7);
		}
		
	}
}
