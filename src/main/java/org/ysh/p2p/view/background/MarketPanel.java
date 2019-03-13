package org.ysh.p2p.view.background;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.ysh.p2p.enums.CategoryAttrEnum;
import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.service.IntegralSetService;
import org.ysh.p2p.service.MemberIntegralService;
import org.ysh.p2p.service.impl.IntegralSetServiceImpl;
import org.ysh.p2p.service.impl.MemberIntegralServiceImpl;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.util.ViewUtil;
import org.ysh.p2p.vo.IntegralSetDto;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

/**
 * 营销管理Panel
 * @author yanshuai
 *
 */
public class MarketPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5476189955120680244L;
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
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("营销管理");
		
		DefaultMutableTreeNode integralNode = new DefaultMutableTreeNode("积分管理");
		root.add(integralNode);
		
		DefaultMutableTreeNode integralSetNode = new DefaultMutableTreeNode("积分设置");
		integralNode.add(integralSetNode);
		DefaultMutableTreeNode integralMgrNode = new DefaultMutableTreeNode("积分管理");
		integralNode.add(integralMgrNode);
		
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
					
					if("积分设置".equals(selectedNode.getUserObject())){
						pane.setRightComponent(new IntegralSetPanel());
					}else if("积分管理".equals(selectedNode.getUserObject())){
						pane.setRightComponent(new IntegralMgrPanel());
					}
					
				}
				
			}
		});
	}
	
	public MarketPanel(){
		
		initMenuTreePane();
		this.setLayout(new BorderLayout());
		contentPane = new JScrollPane(new JLabel("Hello"));
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menutreePane,contentPane);
		
		this.add(pane,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.add(new MarketPanel());
		frame.setSize(1366,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class IntegralSetPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2379767075323982469L;
	
	private JButton saveBtn = new JButton("保存");
	
	private JCheckBox checkbox = new JCheckBox("启用/停用积分规则");
	
	private IntegralSetService integralSetService = new IntegralSetServiceImpl();
	
	//规则明细Panel
	private JPanel rulePanel = new JPanel();
	
	private JTextField regField = new JTextField(15);
	
	private JTextField loginField = new JTextField(15);
	
	private JTextField recommandField = new JTextField(15);
	
	private JTextField investRecommnandField = new JTextField(15);
	
	private JTextField investField = new JTextField(15);
	
	private JTextField investBigField = new JTextField(15);
	
	public IntegralSetPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(saveBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IntegralSetDto dto = new IntegralSetDto();
				dto.setRegisterGiveIntegral(Integer.parseInt(regField.getText().trim()));
				dto.setLoginGiveIntegral(Integer.parseInt(loginField.getText().trim()));
				dto.setIsUseIntegral(checkbox.isSelected() ? 1 : 0);
				dto.setRecommendFriendsGiveIntegral(Integer.parseInt(recommandField.getText().trim()));
				dto.setFriendsInvestGiveIntegral(Integer.parseInt(investRecommnandField.getText().trim()));
				dto.setInvestGiveIntegral(Integer.parseInt(investField.getText().trim()));
				dto.setMaxInvestGiveIntegral(Integer.parseInt(investBigField.getText().trim()));
				integralSetService.save(dto);
				JOptionPane.showConfirmDialog(ViewUtil.getRootFrame(IntegralSetPanel.this), "操作成功!","提示",JOptionPane.CLOSED_OPTION);
			}
		});
		
		checkbox.setAlignmentX(LEFT_ALIGNMENT);
		this.add(checkbox);
		boolean isUserIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd(), 0) == 0 ? false:true;
		checkbox.setSelected(isUserIntegral);
		checkbox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox.isSelected()){
					rulePanel.setVisible(true);
				}else{
					rulePanel.setVisible(false);
				}
				IntegralSetPanel.this.repaint();
			}
		});
		
		rulePanel.setLayout(new GridLayout(9,1));
		rulePanel.add(new JLabel("积分发放条件配置   不获得积分请填写0",JLabel.LEFT));
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(new JLabel("注册获得积分:",JLabel.LEFT));
		p2.add(regField);
		p2.add(new JLabel("积分"));
		rulePanel.add(p2);
		
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(new JLabel("登录获得积分:"));
		p3.add(loginField);
		p3.add(new JLabel("积分"));
		p3.add(new JLabel("注：每天只计算一次有效登录"));
		rulePanel.add(p3);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.add(new JLabel("推荐好友获得积分:"));
		p4.add(recommandField);
		p4.add(new JLabel("积分"));
		rulePanel.add(p4);
		
		rulePanel.add(new JLabel("好友投资获得积分"));
		
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p5.add(new JLabel("推荐人:"));
		p5.add(investRecommnandField);
		p5.add(new JLabel("积分/千元"));
		p5.add(new JLabel("注：不足1千元视为 0元"));
		rulePanel.add(p5);
		
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(new JLabel("投资获得积分:"));
		p6.add(investField);
		p6.add(new JLabel("积分/千元"));
		p6.add(new JLabel("注：不足1千元视为 0元"));
		rulePanel.add(p6);
		
		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p7.add(new JLabel("用户投资大于过往单次投资金额时:"));
		p7.add(investBigField);
		p7.add(new JLabel("积分/千元"));
		p7.add(new JLabel("注：不足1千元视为 0元"));
		rulePanel.add(p7);
		
		rulePanel.add(new JLabel("计算公式：本次投资金额 - 过往投资金额 = 计算积分的金额（取整）"));
		
		rulePanel.setAlignmentX(LEFT_ALIGNMENT);
		this.add(rulePanel);
		//根据是否启用规则，来确定是否显示规则面板
		rulePanel.setVisible(isUserIntegral);
		
		initFieldValue();
	}
	
	private void initFieldValue(){
		regField.setText(CacheUtil.getInt(
						CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd(), 0) +"");
		loginField.setText(CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_LOGINGIVEINTEGRAL.getAttrCd(), 0) + "");
		recommandField.setText(CacheUtil.getInt(
						CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd(), 0) + "");
		investRecommnandField.setText(CacheUtil.getInt(
						CategoryAttrEnum.INTEGRAL_FRIENDSINVESTGIVEINTEGRAL.getAttrCd(), 0) + "");
		investField.setText(CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_INVESTGIVEINTEGRAL.getAttrCd(), 0) +"");
		investBigField.setText(CacheUtil.getInt(
						CategoryAttrEnum.INTEGRAL_MAXINVESTGIVEINTEGRAL.getAttrCd(), 0) + "");
	}
	
}


class IntegralMgrPanel extends JPanel{

	private static final long serialVersionUID = 6724963712418085692L;
	
	private JTextField keyWordF,startF,endF;
	private JButton searchBtn,exportBtn,detailBtn;
	
	private JLabel totalL,usedL,unUsedL,usedAmountL;
	
	private JLabel recordCountL;
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
	
	private JTable table = new JTable(tableModel);
	
	private MemberIntegralService memberIntegralService  = new MemberIntegralServiceImpl();
	
	private PageResponse<MemberIntegral> pager = null;
	
	public IntegralMgrPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(new JLabel("会员积分管理"));
		
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
		
		searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				keyword = keyWordF.getText().trim();
				queryStart = startF.getText().trim();
				queryEnd = endF.getText().trim();
				MemberIntegralTitleDto titleDto = memberIntegralService.queryMemberIntegralTitleDto(keyword, queryStart, queryEnd);
				totalL.setText(titleDto.getTotalValue().toString());
				usedL.setText(titleDto.getUsedValue().toString());
				unUsedL.setText(titleDto.getEffectiveValue().toString());
				usedAmountL.setText(titleDto.getUsedAmountStr());
				pager = memberIntegralService.queryByPage(new PageRequest(), keyword, queryStart, queryEnd);
				setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
				
			}
		});
		searchPanel.add(searchBtn);
		exportBtn = new JButton("导出");
		exportBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		searchPanel.add(exportBtn);
		
		detailBtn=new JButton("查看详情");
		detailBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
				System.out.println("selected ROW -->" + selectedRow);
				if(selectedRow <0)
					return;
				MemberIntegral memberIntegral = pager.getRecords().get(selectedRow);
				new IntegralRecordDialog(memberIntegral.getMember(), ViewUtil.getRootFrame(IntegralMgrPanel.this));
			}
		});
		searchPanel.add(detailBtn);
		searchPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		this.add(searchPanel);
		
		MemberIntegralTitleDto titleDto = memberIntegralService.queryMemberIntegralTitleDto(null, null, null);
		
		JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
		descPanel.add(new JLabel("总积分数:"));
		totalL = new JLabel(titleDto.getTotalValue().toString());
		descPanel.add(totalL);
		
		descPanel.add(new JLabel("已使用总积分数:"));
		usedL = new JLabel(titleDto.getUsedValue().toString());
		descPanel.add(usedL);
		
		descPanel.add(new JLabel("未使用总积分数:"));
		unUsedL = new JLabel(titleDto.getEffectiveValue().toString());
		descPanel.add(unUsedL);
		
		descPanel.add(new JLabel("已使用积分总面值(元):"));
		usedAmountL = new JLabel(titleDto.getUsedAmountStr());
		descPanel.add(usedAmountL);
		
		descPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		this.add(descPanel);
		
		this.pager = memberIntegralService.queryByPage(new PageRequest(), null, null, null);
		
		recordCountL = new JLabel("共" +pager.getTotalCount()+" 条记录");
		this.add(recordCountL);
		setDataModel(tableModel, pager.getRecords(), pager.getFirstResult()+1);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
				pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
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
					pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
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
					pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
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
				pager = memberIntegralService.queryByPage(request, keyword, queryStart, queryEnd);
				setDataModel(tableModel,pager.getRecords(),pager.getFirstResult()+1);
			}
		});
		
		pagerPanel.add(goBtn);
	}
	
	private void setDataModel(DefaultTableModel model,List<MemberIntegral> records,int firstResult){
		model.setRowCount(0);//清空原有数据
		int i=0;
		for(MemberIntegral mi:records){
			Vector<Object> row = new Vector<Object>();
			row.add(firstResult+i);
			row.add(mi.getMember().getRealCd());
			row.add(mi.getMember().getNickName());
			row.add(mi.getMember().getRealNm());
			row.add(mi.getTotal());
			row.add(mi.getUsedValue());
			row.add(mi.getIntegralVal());
			row.add(mi.getUsedAmountStr());
			row.add(DateUtil.defaultFormat(mi.getUpdateTime()));
			model.addRow(row);
			i++;
		}
	}
}