package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.controller.ControllerType;
import business.usecase.IAddLibraryMember;
import business.controller.ControllerFactory;
import dataaccess.model.Address;
import dataaccess.model.LibraryMember;
import business.exception.InvalidMemberException;

public class AddMemberWindow extends JFrame implements LibWindow {
	private static final long serialVersionUID = 1L;
	public static final AddMemberWindow INSTANCE = new AddMemberWindow();
	IAddLibraryMember addLibraryMemberUseCase = ControllerFactory.getController(ControllerType.LibraryMember);

	private boolean isInitialized = false;

	private JPanel mainPanel = new JPanel();
	private JPanel topPanel;
	private JPanel outerMiddel;
	private JPanel lowerPanel;

	private JTextField txtMember;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtZipCode;
	private JTextField txtState;

	private JLabel lblMemberId;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblPhoneNumber;
	private JLabel lblStreet;
	private JLabel lblCity;
	private JLabel lblZipCode;
	private JLabel lblState;

	private JPanel contentPane;

	private AddMemberWindow() {
	}

	@Override
	public void init() {

		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 930, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(64, 64, 64));
		this.setMinimumSize(new Dimension(930, 657));

		mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.defineTopPanel();
		this.defineOuterMiddle();
		this.defineLowerPanel();

		this.mainPanel.add(this.topPanel, BorderLayout.NORTH);
		this.mainPanel.add(this.outerMiddel, BorderLayout.CENTER);

		this.mainPanel.setBackground(new Color(64, 64, 64));
		mainPanel.setBounds(0, 0, 930, 720);

		getContentPane().add(this.mainPanel);

		isInitialized(true);

	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private void initializeComponent() {

		txtMember = new JTextField(20);
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtPhoneNumber = new JTextField(15);
		txtStreet = new JTextField(20);
		txtCity = new JTextField(20);
		txtZipCode = new JTextField(20);
		txtState = new JTextField(20);

		lblMemberId = new JLabel("<html><font color ='white'>Member ID</font><font color='red'> *</font></html>");
		lblFirstName = new JLabel("<html><font color ='white'>First Name</font><font color='red'> *</font></html>");
		lblLastName = new JLabel("<html><font color ='white'>Last Name</font><font color='red'> *</font></html>");
		lblPhoneNumber = new JLabel("<html><font color ='white'>Phone Number</font><font color='red'> *</font></html>");
		lblStreet = new JLabel("<html><font color ='white'>Street</font>");
		lblCity = new JLabel("<html><font color ='white'>City</font>");
		lblZipCode = new JLabel("<html><font color ='white'>Zip Code</font>");
		lblState = new JLabel("<html><font color ='white'>State</font>");

	}

	public void defineTopPanel() {
		this.topPanel = new JPanel();
		
		topPanel.setBackground(new Color(0, 0, 0));
		JLabel addMemberLabel = new JLabel("Add Library Member");
		Util.adjustLabelFont(addMemberLabel, Color.WHITE, true);
		this.topPanel.setLayout(new FlowLayout(0));
		this.topPanel.add(addMemberLabel);
	}

	public void defineOuterMiddle() {

		this.outerMiddel = new JPanel();
		this.outerMiddel.setLayout(new BorderLayout());

		JPanel middlePanel = new JPanel();
		
		middlePanel.setBackground(new Color(64, 64, 64));
		FlowLayout fl = new FlowLayout(1, 25, 25);

		middlePanel.setLayout(fl);

		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

        leftPanel.setBackground(new Color(64, 64, 64));
        rightPanel.setBackground(new Color(64, 64, 64));
		
		initializeComponent();

		clearInput();
		leftPanel.setLayout(new BoxLayout(leftPanel, 1));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));

		leftPanel.add(lblMemberId);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblFirstName);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblLastName);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblPhoneNumber);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblStreet);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblCity);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblZipCode);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		leftPanel.add(lblState);

		rightPanel.add(txtMember);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtFirstName);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtLastName);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtPhoneNumber);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtStreet);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtCity);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtZipCode);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rightPanel.add(txtState);

		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);

		JButton backToMainButn = new JButton("HOME");
		addBackButtonListener(backToMainButn);
		JPanel buttonPanel = new JPanel();
		//buttonPanel.setBackground(new Color(174, 242, 250));
		buttonPanel.setBackground(new Color(64, 64, 64));
		buttonPanel.add(backToMainButn);

		JButton btnAddMember = new JButton("ADD");
		attachAddMemberButtonListener(btnAddMember);
		buttonPanel.add(btnAddMember);

		JButton btnClear = new JButton("CANCEL");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(e -> {
			clearInput();
		});

		this.outerMiddel.add(middlePanel, BorderLayout.NORTH);
		this.outerMiddel.add(buttonPanel, BorderLayout.CENTER);

	}

	private void attachAddMemberButtonListener(JButton btn) {

		btn.addActionListener((evt) -> {
			if (validateInput()) {
				try {
					addLibraryMemberUseCase.addLibraryMember(bindObject());
					JOptionPane.showMessageDialog(this, "Member added successfully.");
					clearInput();
				} catch (InvalidMemberException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	private LibraryMember bindObject() {
		Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZipCode.getText());
		LibraryMember member = new LibraryMember(txtMember.getText(), txtFirstName.getText(), txtLastName.getText(),
				txtPhoneNumber.getText(), address);
		return member;
	}

	private void clearInput() {

		this.txtStreet.setText("");
		this.txtCity.setText("");
		this.txtState.setText("");
		this.txtZipCode.setText("");
		this.txtMember.setText("");
		this.txtFirstName.setText("");
		this.txtLastName.setText("");
		this.txtPhoneNumber.setText("");
	}

	private boolean validateInput() {

		if (txtMember.getText().isEmpty()) {

			JOptionPane.showMessageDialog(this, "Invalid Member Id");
			return false;
		}

		if (txtFirstName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid First Name");
			return false;
		}

		if (txtLastName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid Last Name");
			return false;
		}

		if (!txtZipCode.getText().isEmpty()) {
			String val = txtZipCode.getText().trim();
			Boolean b = true;
			try {
				Integer.parseInt(val);
				// val is numeric
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Zipcode must be numeric");
				b = false;
				return false;

			}
			if (val.length() != 5 && b) {
				JOptionPane.showMessageDialog(this, "Zipcode must have 5 digits");
				return false;
			}
		}

		if (txtPhoneNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid Phone Number");
			return false;
		}

		return true;
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			UIController.hideAllWindows();
			MainView.INSTANCE.setVisible(true);
		});

	}

	public void defineLowerPanel() {

		JButton backToMainButn = new JButton("HOME");
		addBackButtonListener(backToMainButn);
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		lowerPanel.add(backToMainButn);

	}

	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			UIController.hideAllWindows();
			MainView.INSTANCE.setVisible(true);
		}
	}

}
