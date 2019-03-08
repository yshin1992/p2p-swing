package org.ysh.p2p.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.ysh.p2p.util.StringUtil;

public class ForgetPasswordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3959922094101254422L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;

	private String verifyCode;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new ForgetPasswordDialog(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForgetPasswordDialog(JFrame owner) {
		super(owner,true);
		setBounds(100, 100, 344, 294);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 212, 328, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						ForgetPasswordDialog.this.dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel label = new JLabel("手机号码");
		label.setBounds(10, 11, 82, 21);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("新密码");
		label_1.setBounds(10, 57, 54, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("确认密码");
		label_2.setBounds(10, 89, 54, 30);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("验证码");
		label_3.setBounds(10, 121, 54, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("短信验证码");
		label_4.setBounds(10, 159, 82, 15);
		getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setBounds(114, 11, 150, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(114, 54, 150, 21);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(114, 86, 150, 21);
		getContentPane().add(passwordField_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 118, 66, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		verifyCode = StringUtil.generateVerifyCode(4);
		final JButton lblNewLabel = new JButton(verifyCode);
		lblNewLabel.setToolTipText("看不清?点击更换");
		lblNewLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyCode = StringUtil.generateVerifyCode(4);
				lblNewLabel.setText(verifyCode);
			}
		});
		lblNewLabel.setBounds(197, 117, 67, 22);
		getContentPane().add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 156, 66, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
