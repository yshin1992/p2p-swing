package org.ysh.p2p.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.service.SysUserService;
import org.ysh.p2p.service.impl.SysUserServiceImpl;
import org.ysh.p2p.session.Session;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = -6740706912387186082L;
	
	private SysUserService sysUserService = new SysUserServiceImpl();
	
	private JLabel nameLabel,paswdLabel;
	
	private JTextField nameField;
	
	private JPasswordField pswdField;
	
	private JButton loginBtn,exitBtn;
	
	public LoginDialog(JFrame owner){
		super(owner,true);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints(1, 0, 2, 1, 50d,50d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10);
		nameLabel = new JLabel("用户名");
		add(nameLabel,gbc);
		
		nameField = new JTextField();
		add(nameField,new GridBagConstraints(4, 0, 4, 1, 100d, 100d, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10,10,10,10), 10, 10));
		
		paswdLabel = new JLabel("密码");
		add(paswdLabel,new GridBagConstraints(1, 2, 1, 1, 50d,50d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		
		pswdField = new JPasswordField();
		add(pswdField,new GridBagConstraints(4, 2, 4, 1, 100d, 100d, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10,10,10,10), 10, 10));
		
		add(new JLabel(""),new GridBagConstraints(0, 4, 1, 1, 0d,0d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		loginBtn = new JButton("登录");
		add(loginBtn,new GridBagConstraints(1, 4, 2, 1, 50d,50d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		add(new JLabel(""),new GridBagConstraints(4, 4, 1, 1, 50d,50d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		exitBtn = new JButton("退出");
		add(exitBtn,new GridBagConstraints(5, 4, 2, 1, 50d,50d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		add(new JLabel(""),new GridBagConstraints(8, 4, 1, 1, 0d,0d, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 1), 10,10));
		
		loginBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String userCd = nameField.getText().trim();
				String passwd = new String(pswdField.getPassword());
				SysUser user = sysUserService.validateUser(userCd, passwd);
				
				if(null != user){
					Session.getInstance().setLoginUser(user);
					LoginDialog.this.dispose();
				}else{
					JOptionPane.showConfirmDialog(LoginDialog.this, "用户名或密码错误!","提示",JOptionPane.CLOSED_OPTION);
				}
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.setSize(320,180);
		this.setVisible(true);
		this.setTitle("登录");
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}
	
}
