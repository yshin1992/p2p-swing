package org.ysh.p2p.view.background;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.ysh.p2p.enums.CategoryAttrEnum;
import org.ysh.p2p.service.IntegralSetService;
import org.ysh.p2p.service.impl.IntegralSetServiceImpl;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.vo.IntegralSetDto;

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
