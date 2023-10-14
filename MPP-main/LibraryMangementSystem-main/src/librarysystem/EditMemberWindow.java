package librarysystem;

import business.controller.ControllerFactory;
import business.controller.ControllerType;
import business.exception.InvalidMemberException;
import business.usecase.ICheckMember;
import business.usecase.IUpdateLibraryMember;
import dataaccess.model.Address;
import dataaccess.model.LibraryMember;
import business.usecase.IAddLibraryMember;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMemberWindow extends JFrame implements LibWindow {
	private static final long serialVersionUID = 1L;
	public static final EditMemberWindow INSTANCE = new EditMemberWindow();
	IUpdateLibraryMember addLibraryMemberUseCase = ControllerFactory.getController(ControllerType.LibraryMember);

	private boolean isInitialized = false;

	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel outerMiddel = new JPanel();
	private JPanel lowerPanel;

	private JTextField txtSearchMemberId;
	private JButton btnSearch;

	private JTextField txtMemberId;
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

	private EditMemberWindow() {
	}

	@Override
	public void init() {
		setResizable(false);
		setTitle("Library System");
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
		this.mainPanel.setBounds(1, 1, 930, 657);
		getContentPane().add(this.mainPanel);
		isInitialized(true);

	}

	private void initializeComponent() {
		txtMemberId = new JTextField(20);
		txtMemberId.setEditable(false);
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtPhoneNumber = new JTextField(15);
		txtStreet = new JTextField(20);
		txtCity = new JTextField(20);
		txtZipCode = new JTextField(20);
		txtState = new JTextField(20);

		lblMemberId = new JLabel("<html><font color='white'>Member ID</font><font color='red'> *</font></html>");
		lblFirstName = new JLabel("<html><font color='white'>First Name</font><font color='red'> *</font></html>");
		lblLastName = new JLabel("<html><font color='white'>Last Name</font><font color='red'> *</font></html>");
		lblPhoneNumber = new JLabel("<html><font color='white'>Phone Number</font><font color='red'> *</font></html>");
		
		lblStreet = new JLabel("<html><font color='white'>Street</font></html>");
		lblCity = new JLabel("<html><font color='white'>City</font></html>");
		lblZipCode = new JLabel("<html><font color='white'>Zip Code</font></html>");
		lblState = new JLabel("<html><font color='white'>State</font></html>");

		txtSearchMemberId = new JTextField(7);
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSearchMemberId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(EditMemberWindow.this, "Please Enter Member ID.");
				} else {
					if (addLibraryMemberUseCase.searchMember(txtSearchMemberId.getText()) != null) {
						LibraryMember member = addLibraryMemberUseCase.searchMember(txtSearchMemberId.getText());
						populateOldData(member);
					}
					else {
						JOptionPane.showMessageDialog(EditMemberWindow.this, "Can't find out this Member ID.");
						clearInputNotFound();
					}
				}
			}
		});
	}

	public void defineTopPanel() {
		this.topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 0));
		JLabel addMemberLabel = new JLabel("Edit Library Member");
		Util.adjustLabelFont(addMemberLabel, Color.WHITE, true);
		this.topPanel.setLayout(new FlowLayout(0));
		this.topPanel.add(addMemberLabel);
	}

	public void defineOuterMiddle() {
		this.outerMiddel = new JPanel();
		this.outerMiddel.setLayout(new BorderLayout());

		initializeComponent();
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBackground(new Color(64, 64, 64));

		searchPanel.add(txtSearchMemberId, BorderLayout.NORTH);
		searchPanel.add(btnSearch, BorderLayout.CENTER);

		JPanel middlePanel = new JPanel();
		middlePanel.setBackground(new Color(64, 64, 64));
		FlowLayout fl = new FlowLayout(1, 25, 25);

		middlePanel.setLayout(fl);

		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setBackground(new Color(64, 64, 64));
		rightPanel.setBackground(new Color(64, 64, 64));

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

		rightPanel.add(txtMemberId);
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

		middlePanel.add(searchPanel);
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);

		JButton backToMainButn = new JButton("HOME");
		backToMainButn.addActionListener(new BackToMainListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(64, 64, 64));
		buttonPanel.add(backToMainButn);

		JButton btnEditMember = new JButton("UPDATE");
		attachUpdateMemberButtonListener(btnEditMember);
		buttonPanel.add(btnEditMember);

		JButton btnClear = new JButton("CANCEL");
		btnClear.addActionListener(e -> {
			clearInput();
		});
		buttonPanel.add(btnClear);

		this.outerMiddel.add(middlePanel, BorderLayout.NORTH);
		this.outerMiddel.add(buttonPanel, BorderLayout.CENTER);

	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private void attachUpdateMemberButtonListener(JButton btn) {
		btn.addActionListener((evt) -> {
			if (validateInput()) {
				try {
					addLibraryMemberUseCase.updateLibraryMember(bindObject());
					JOptionPane.showMessageDialog(this, "Member updated successfully.");
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
		LibraryMember member = new LibraryMember(txtMemberId.getText(), txtFirstName.getText(), txtLastName.getText(),
				txtPhoneNumber.getText(), address);
		return member;
	}

	private void populateOldData(LibraryMember member) {
		txtMemberId.setText(member.getMemberId());
		txtFirstName.setText(member.getFirstName());
		txtLastName.setText(member.getLastName());
		txtPhoneNumber.setText(member.getTelephone());
		txtStreet.setText(member.getAddress().getStreet());
		txtCity.setText(member.getAddress().getCity());
		txtState.setText(member.getAddress().getState());
		txtZipCode.setText(member.getAddress().getZip());
	}

	private void clearInput() {
		txtSearchMemberId.setText("");
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZipCode.setText("");
		txtMemberId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhoneNumber.setText("");
	}
	
	private void clearInputNotFound() {
		
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZipCode.setText("");
		txtMemberId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhoneNumber.setText("");
	}

	private boolean validateInput() {
		if (txtMemberId.getText().isEmpty()) {
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

		if (txtPhoneNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid Phone Number");
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

		return true;
	}

	public void defineLowerPanel() {
		JButton backToMainButn = new JButton("HOME");
		backToMainButn.addActionListener(new BackToMainListener());
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		lowerPanel.add(backToMainButn);

		JButton btnUpdateMember = new JButton("UPDATE");
		attachUpdateMemberButtonListener(btnUpdateMember);
		lowerPanel.add(btnUpdateMember);

		JButton btnClear = new JButton("CANCEL");
		btnClear.addActionListener(e -> {
			clearInput();
		});
		lowerPanel.add(btnClear);

	}

	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			UIController.hideAllWindows();
			MainView.INSTANCE.setVisible(true);
			// clearInput();
		}
	}

}
