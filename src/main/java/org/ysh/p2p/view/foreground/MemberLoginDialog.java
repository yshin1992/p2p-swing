package org.ysh.p2p.view.foreground;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.service.MemberService;
import org.ysh.p2p.service.impl.MemberServiceImpl;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.ResponseMsg;

public class MemberLoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7736745225833085466L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	private MemberService memberService = new MemberServiceImpl();
	
	private Preferences pref = Preferences.userRoot().node("/org/ysh/p2p");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new MemberLoginDialog(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MemberLoginDialog(final JFrame owner) {
		super(owner,true);
		setBounds(100, 100, 365, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("没有账户？");
		label.setBounds(165, 10, 70, 15);
		contentPanel.add(label);
		
		JButton button = new JButton("免费注册");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PersonalRegisterDialog(owner);
			}
		});
		button.setBounds(245, 6, 93, 23);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("手机号");
		label_1.setBounds(26, 47, 54, 15);
		contentPanel.add(label_1);
		
		textField = new JTextField();
		if(pref.get("phone",null) != null){
			textField.setText(pref.get("phone", null));
		}
		textField.setBounds(117, 44, 221, 33);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setBounds(26, 112, 54, 15);
		contentPanel.add(label_2);
		
		passwordField = new JPasswordField();
		if(pref.get("password",null) != null){
			passwordField.setText(pref.get("password", null));
		}
		passwordField.setBounds(117, 106, 221, 33);
		contentPanel.add(passwordField);
		
		final JCheckBox checkBox = new JCheckBox("记住密码");
		checkBox.setSelected(pref.getBoolean("remember", false));
		checkBox.setBounds(26, 161, 83, 23);
		contentPanel.add(checkBox);
		
		JLabel label_3 = new JLabel("忘记密码？");
		label_3.setBounds(165, 165, 70, 15);
		contentPanel.add(label_3);
		
		JButton button_1 = new JButton("点击找回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgetPasswordDialog(owner);
			}
		});
		button_1.setBounds(245, 161, 93, 23);
		contentPanel.add(button_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("登录");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//登录验证
						String phone = textField.getText().trim();
						String password=new String(passwordField.getPassword());
						if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(password)){
							JOptionPane.showConfirmDialog(MemberLoginDialog.this, "用户名或密码不能为空!","提示",JOptionPane.CLOSED_OPTION);
							return;
						}
						
						Member member = new Member();
						member.setPhone(phone);
						member.setPassword(password);
						
						try {
							ResponseMsg<Member> msg = memberService.login(member);
							if(msg.getCode() == ResponseMsg.FAILURE_CODE){
								JOptionPane.showConfirmDialog(MemberLoginDialog.this, msg.getExtras(),"提示",JOptionPane.CLOSED_OPTION);
								return;
							}else{
								//判断用户是否选择记住密码
								if(checkBox.isSelected()){
									//如果用户选择了记住密码，则从首选项(注册表)中记录信息
									pref.put("phone", phone);
									pref.put("password", password);
									pref.putBoolean("remember", true);
									pref.flush();
								}else{
									pref.remove("phone");
									pref.remove("password");
									pref.remove("remember");
									pref.flush();
								}
								
								JOptionPane.showConfirmDialog(MemberLoginDialog.this, "登录成功!","提示",JOptionPane.CLOSED_OPTION);
								MemberLoginDialog.this.dispose();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MemberLoginDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}
}
