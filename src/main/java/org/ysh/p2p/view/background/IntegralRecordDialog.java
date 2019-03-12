package org.ysh.p2p.view.background;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.ysh.p2p.enums.IntegralRecordEnum;
import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.service.IntegralRecordService;
import org.ysh.p2p.service.impl.IntegralRecordServiceImpl;
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public class IntegralRecordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4145197853760166857L;

	private final Member member;
	
	private JPanel contentPanel = new JPanel();
	
	private JComboBox<String> typeBox=new JComboBox<String>(new String[]{"全部","支出","收入"});
	private JTextField startF=new JTextField(15),endF=new JTextField(15);
	private JButton queryBtn=new JButton("查询"),exportBtn = new JButton("导出");
	
	private String[] tableHead = {"序号","积分数量","已使用积分面值（元）","积分来源","摘要","操作时间"};
	
	private DefaultTableModel tableModel=new DefaultTableModel(tableHead, 0);
	private JTable table = new JTable(tableModel);
	
	private IntegralRecordService recordService = new IntegralRecordServiceImpl();
	
	private JPanel pagerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JComboBox<Integer> pageSizeCb = new JComboBox<Integer>(new Integer[]{1,5,10,20,30,40});
	private JButton refreshBtn = new JButton("刷新");
	private JTextField pageF=new JTextField("1",3);
	private JButton goBtn = new JButton("Go");
	private JButton preBtn = new JButton("上一页");
	private JButton nextBtn = new JButton("下一页");
	private String queryStart="";
	private String queryEnd="";
	private String addFlag="";
	
	private PageResponse<IntegralRecord> pager = null;
	
	public void init(){
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(new JLabel("会员【"+member.getRealCd()+"】【"+member.getRealNm()+"】积分明细"));
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(new JLabel("积分类别:"));
		searchPanel.add(typeBox);
		searchPanel.add(new JLabel("更新日前:"));
		searchPanel.add(startF);
		searchPanel.add(new JLabel("到"));
		searchPanel.add(endF);
		
		queryBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				queryStart = startF.getText().trim();
				queryEnd = endF.getText().trim();
				addFlag = (String) typeBox.getSelectedItem();
				pager = recordService.queryByPage(new PageRequest(), member.getUuid(),addFlag, queryStart, queryEnd);
				setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
			}
		});
		
		exportBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		searchPanel.add(queryBtn);
		searchPanel.add(exportBtn);
		searchPanel.setAlignmentX(LEFT_ALIGNMENT);
		contentPanel.add(searchPanel);
		
		pager=recordService.queryByPage(new PageRequest(), member.getUuid(), addFlag,queryStart, queryEnd);
		setDataModel(tableModel, pager.getRecords(), pager.getFirstResult());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPanel.add(new JScrollPane(table));
		
		initPagePanel();
		pagerPanel.setAlignmentX(LEFT_ALIGNMENT);
		contentPanel.add(pagerPanel);
		
		this.add(contentPanel);
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
				pager = recordService.queryByPage(request, member.getUuid(), addFlag,queryStart, queryEnd);
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
					pager = recordService.queryByPage(request, member.getUuid(), addFlag,queryStart, queryEnd);
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
					pager = recordService.queryByPage(request, member.getUuid(), addFlag,queryStart, queryEnd);
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
				pager = recordService.queryByPage(request, member.getUuid(), addFlag,queryStart, queryEnd);
				setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
			}
		});
		
		pagerPanel.add(goBtn);
	}
	
	private void setDataModel(DefaultTableModel model,List<IntegralRecord> records,int firstResult){
		model.setRowCount(0);//清空原有数据
		int i=0;
		for(IntegralRecord mi:records){
			Vector<Object> row = new Vector<Object>();
			row.add(firstResult+1+i);
			row.add(mi.getIntegralVal());
			row.add(mi.getAmount());
			row.add(getObjTypeNm(mi.getObjType()+""));
			row.add(mi.getRemark());
			row.add(DateUtil.defaultFormat(mi.getCreateTime()));
			model.addRow(row);
			i++;
		}
	}
	
	private String getObjTypeNm(String objType){
		String resultStr = "";
		if (objType.equals(IntegralRecordEnum.OBJTYPE_REGISTER_0.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_REGISTER_0.getText();
		} else if (objType.equals(IntegralRecordEnum.OBJTYPE_LOGIN_1.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_LOGIN_1.getText();
		} else if (objType.equals(IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_REGISTRATION_2.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_REGISTRATION_2.getText();
		} else if (objType.equals(IntegralRecordEnum.OBJTYPE_INVEST_SUCCESS_3.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_INVEST_SUCCESS_3.getText();
		} else if (objType.equals(
				IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_INVEST_SUCCESS_4.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_INVEST_SUCCESS_4.getText();
		} else if (objType.equals(IntegralRecordEnum.OBJTYPE_INCREASE_IN_INVESTMENT_5.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_INCREASE_IN_INVESTMENT_5.getText();
		} else if (objType.equals(IntegralRecordEnum.OBJTYPE_INVESTMENT_CONSUMPTION_6.getKey())) {
			resultStr = IntegralRecordEnum.OBJTYPE_INVESTMENT_CONSUMPTION_6.getText();
		}
		return resultStr;
	}
	
	public IntegralRecordDialog(Member member,Frame owner){
		super(owner,true);
		this.member  = member;
		this.init();
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
