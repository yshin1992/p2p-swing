package org.ysh.p2p.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

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
		frame.add(new ProductPanel());
		frame.setSize(1366,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
