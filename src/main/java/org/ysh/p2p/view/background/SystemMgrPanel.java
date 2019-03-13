package org.ysh.p2p.view.background;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * 系统管理Panel
 * @author yanshuai
 *
 */
public class SystemMgrPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965962140612992589L;

	private JTree menuTree;
	private JSplitPane pane;
	
	private void initMenuTreePane(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
		DefaultMutableTreeNode integralNode = new DefaultMutableTreeNode("系统管理");
		root.add(integralNode);
		DefaultMutableTreeNode integralSetNode = new DefaultMutableTreeNode("费用设置");
		integralNode.add(integralSetNode);
		DefaultMutableTreeNode integralMgrNode = new DefaultMutableTreeNode("分润模板");
		integralNode.add(integralMgrNode);
		menuTree = new JTree(root);
		menuTree.setRootVisible(false);
		
		
		menuTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				if(null == path)
					return;
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
				if(selectedNode.isLeaf()){
					System.out.println(selectedNode.getUserObject());
					
					if("费用设置".equals(selectedNode.getUserObject())){
						pane.setRightComponent(new FeeItemPanel());
					}else if("分润模板".equals(selectedNode.getUserObject())){
						pane.setRightComponent(new IntegralMgrPanel());
					}
					
				}
				
			}
		});
	}
	
	public SystemMgrPanel(){
		initMenuTreePane();
		this.setLayout(new BorderLayout());
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuTree,new JScrollPane());
		this.add(pane,BorderLayout.CENTER);
	}
	
}
