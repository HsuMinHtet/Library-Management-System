package librarysystem;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.controller.ControllerType;
import business.usecase.ILogIn;
import business.controller.ControllerFactory;
import dataaccess.Auth;
import business.exception.LoginException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainLogin extends JFrame implements LibWindow{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private boolean isInitialized = false;
	
	ILogIn logInUseCase = ControllerFactory.getController(ControllerType.Login);
	
	public static final MainLogin INSTANCE = new MainLogin();

	/**
	 * Create the frame.
	 */
	public MainLogin() {}
	
	private void doLogIn() {
		String user = this.textField.getText();
		String pass = new String(passwordField.getText());
		try {
			Auth auth = logInUseCase.login(user, pass);
			UIController.INSTANCE.doAuthentication(auth);

			UIController.hideAllWindows();
			MainView.INSTANCE.init();
			Util.centerFrameOnDesktop(MainView.INSTANCE);
			MainView.INSTANCE.setVisible(true);
			MainView.INSTANCE.lblGreeting.setText("Welcome "+ user+ " !");
			MainView.INSTANCE.mnNewMenu.setText("Current User: "+user+" ");
		} catch (LoginException e) {
			//e.printStackTrace();	
			JOptionPane.showMessageDialog(this, "Login Failed");
		}
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 930, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("Button.border"));
		//panel.setBackground(new Color(81, 184, 196));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(450, 1, 930, 657);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Library Management System");
		lblNewLabel_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		lblNewLabel_1.setBounds(121, 148, 300, 41);
		lblNewLabel_1.setForeground(new Color(250, 250, 250));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(131, 199, 77, 19);
		lblNewLabel_2.setForeground(new Color(250, 250, 250));
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(218, 200, 130, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.HANGING_BASELINE, 12));
		lblNewLabel_2_1.setBounds(131, 249, 77, 19);
		lblNewLabel_2_1.setForeground(new Color(250, 250, 250));
		panel.add(lblNewLabel_2_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 249, 130, 20);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogIn();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(216, 299, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 472, 657);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(MainLogin.class.getResource("/librarysystem/library_image.jpg")).getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(lblNewLabel);

		isInitialized = true;
	}



	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}



	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
	}
}
