package org.ysh.p2p.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.ysh.p2p.util.StringUtil;

/**
 * 个人注册对话框
 * @author yshin1992
 *
 */
public class PersonalRegisterDialog extends JFrame {
	
	private static final long serialVersionUID = -4163092179676398742L;

	private JTextField nickNameField,phoneField,emailField,verifyField,vfCodeField,invField;
	
	private JPasswordField pswdField,confirmField;
	
	private JButton vfBtn,codeBtn,registBtn,cancelBtn;
	
	public PersonalRegisterDialog(){
		
		JPanel panel = new JPanel(null);
		
		JLabel jLabel = new JLabel("用户名",JLabel.RIGHT);
		jLabel.setBounds(0,10,100,35);
		panel.add(jLabel);
		
		nickNameField = new JTextField(30);
		nickNameField.setBounds(110,10,200,35);
		panel.add(nickNameField);
		
		JLabel jLabel2 = new JLabel("手机号码",JLabel.RIGHT);
		jLabel2.setBounds(0,55,100,35);
		panel.add(jLabel2);
		phoneField = new JTextField(30);
		phoneField.setBounds(110,55,200,35);
		panel.add(phoneField);
		
		JLabel jLabel3 = new JLabel("邮箱",JLabel.RIGHT);
		jLabel3.setBounds(0,100,100,35);
		panel.add(jLabel3);
		emailField= new JTextField(30);
		emailField.setBounds(110,100,200,35);
		panel.add(emailField);
		
		JLabel jLabel4 = new JLabel("密码",JLabel.RIGHT);
		jLabel4.setBounds(0,145,100,35);
		panel.add(jLabel4);
		pswdField = new JPasswordField(30);
		pswdField.setBounds(110,145,200,35);
		panel.add(pswdField);
		
		JLabel jLabel5 = new JLabel("确认密码",JLabel.RIGHT);
		jLabel5.setBounds(0,190,100,35);
		panel.add(jLabel5);
		confirmField = new JPasswordField(30);
		confirmField.setBounds(110,190,200,35);
		panel.add(confirmField);
		
		JLabel jLabel6 = new JLabel("验证码",JLabel.RIGHT);
		jLabel6.setBounds(0,235,100,35);
		panel.add(jLabel6);
		
		JPanel tmp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		verifyField = new JTextField(5);
		tmp1.add(verifyField);
		vfBtn = new JButton(StringUtil.generateVerifyCode(4));
		tmp1.add(vfBtn);
		tmp1.setBounds(110,235,240,35);
		panel.add(tmp1);
		
		JLabel jLabel7 = new JLabel("短信验证码",JLabel.RIGHT);
		jLabel7.setBounds(0,280,100,35);
		panel.add(jLabel7);
		JPanel tmp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		vfCodeField= new JTextField(5);
		tmp2.add(vfCodeField);
		
		codeBtn = new JButton("获取短信验证码");
		tmp2.add(codeBtn);
		tmp2.setBounds(110,280,240,35);
		panel.add(tmp2);
		
		JLabel jLabel8 = new JLabel("推荐人",JLabel.RIGHT);
		jLabel8.setBounds(0,325,100,35);
		panel.add(jLabel8);
		invField = new JTextField(30);
		invField.setBounds(110,325,200,35);
		panel.add(invField);
		
		registBtn = new JButton("注册");
		registBtn.setBounds(50,380,100,35);
		panel.add(registBtn);
		
		cancelBtn = new JButton("取消");
		cancelBtn.setBounds(200,380,100,35);
		panel.add(cancelBtn);
		
		this.add(panel);
		this.setSize(350,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PersonalRegisterDialog();
	}

}
