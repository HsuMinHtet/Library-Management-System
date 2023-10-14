package librarysystem;

import business.controller.ControllerFactory;
import business.controller.ControllerType;
import business.exception.InvalidAuthorException;
import business.usecase.IAuthor;
import dataaccess.model.Address;
import dataaccess.model.Author;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAuthorWindow extends JFrame implements LibWindow {
    private static final long serialVersionUID = 1L;
    public static final AddAuthorWindow INSTANCE = new AddAuthorWindow();
    IAuthor authorUsecase = ControllerFactory.getController(ControllerType.Author);

    private boolean isInitialized = false;

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel outerMiddel = new JPanel();
    private JPanel lowerPanel;

    private JTextField txtAuthorId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtPhoneNumber;

    private JTextField txtBio;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtZipCode;
    private JTextField txtState;

    private JLabel lblAuthorId;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblPhoneNumber;

    private JLabel lblBio;

    private JLabel lblStreet;
    private JLabel lblCity;
    private JLabel lblZipCode;
    private JLabel lblState;

    private AddAuthorWindow() {
    }

    @Override
    public void init() {
        this.initializeComponent();
        setBounds(1, 1, 930, 657);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("Library Management System");
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(64, 64, 64));
        
        this.setMinimumSize(new Dimension(930, 657));

        mainPanel  = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.defineTopPanel();
        this.defineOuterMiddle();
        this.defineLowerPanel();

        this.mainPanel.add(this.topPanel, BorderLayout.NORTH);
        this.mainPanel.add(this.outerMiddel, BorderLayout.CENTER);
        //this.mainPanel.add(this.lowerPanel, BorderLayout.SOUTH);
        
        this.mainPanel.setBackground(new Color(64, 64, 64)); 
        isInitialized = true;


        this.mainPanel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
        this.mainPanel.setBounds(1, 1, 930, 657);
        getContentPane().add(this.mainPanel);


    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    private void initializeComponent() {
        txtAuthorId = new JTextField(20);
        txtFirstName = new JTextField(20);
        txtLastName = new JTextField(20);
        txtPhoneNumber = new JTextField(15);
        txtBio = new JTextField(20);
        txtStreet = new JTextField(20);
        txtCity = new JTextField(20);
        txtZipCode = new JTextField(20);
        txtState = new JTextField(20);

        lblAuthorId = new JLabel("<html><font color='white'>Author ID</font><font color='red'> *</font></html>");
        lblFirstName = new JLabel("<html><font color='white'>First Name</font><font color='red'> *</font></html>");
        lblLastName = new JLabel("<html><font color='white'>Last Name</font><font color='red'> *</font></html>");
        lblPhoneNumber = new JLabel("<html><font color='white'>Phone Number</font><font color='red'> *</font></html>");
        lblBio = new JLabel("<html><font color='white'>Bio/Book</font></html>");
        lblStreet = new JLabel("<html><font color='white'>Street</font></html>");
        lblCity = new JLabel("<html><font color='white'>City</font></html>");
        lblZipCode = new JLabel("<html><font color='white'>Zip Code</font></html>");
        lblState = new JLabel("<html><font color='white'>State</font></html>");
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void defineTopPanel() {
        this.topPanel = new JPanel();
       
        topPanel.setBackground(new Color(0, 0, 0));
        JLabel addMemberLabel = new JLabel("Add Author");
        addMemberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Util.adjustLabelFont(addMemberLabel, Color.WHITE, true);
        this.topPanel.setLayout(new FlowLayout(0));
        this.topPanel.add(addMemberLabel);
        
    }

    public void defineOuterMiddle() {
        this.outerMiddel = new JPanel();
        this.outerMiddel.setLayout(new BorderLayout());

        JPanel middlePanel = new JPanel();
        //middlePanel.setBackground(new Color(174, 242, 250));
        middlePanel.setBackground(new Color(64, 64, 64));
        FlowLayout fl = new FlowLayout(1, 25, 25);

        middlePanel.setLayout(fl);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setBackground(new Color(64, 64, 64));
        rightPanel.setBackground(new Color(64, 64, 64));
        
        leftPanel.setLayout(new BoxLayout(leftPanel, 1));
        rightPanel.setLayout(new BoxLayout(rightPanel, 1));

        leftPanel.add(lblAuthorId);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblFirstName);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblLastName);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblPhoneNumber);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblBio);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblStreet);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblCity);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblZipCode);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        leftPanel.add(lblState);

        rightPanel.add(txtAuthorId);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        rightPanel.add(txtFirstName);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        rightPanel.add(txtLastName);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        rightPanel.add(txtPhoneNumber);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        rightPanel.add(txtBio);
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
        backToMainButn.addActionListener(new BackToMainListener());
        JPanel buttonPanel = new JPanel();
  
        buttonPanel.setBackground(new Color(64, 64, 64));
        buttonPanel.add(backToMainButn);

        JButton btnAddMember = new JButton("ADD");
        attachAddMemberButtonListener(btnAddMember);
        buttonPanel.add(btnAddMember);

        JButton btnClear = new JButton("CANCEL");
        btnClear.addActionListener(e -> {
            clearInput();
        });
        buttonPanel.add(btnClear);

        this.outerMiddel.add(middlePanel, BorderLayout.NORTH);
        this.outerMiddel.add(buttonPanel, BorderLayout.CENTER);


    }

    private void attachAddMemberButtonListener(JButton btn) {
        btn.addActionListener((evt) -> {
            if (validateInput()) {
                try {
                    authorUsecase.addAuthor(bindObject());
                    JOptionPane.showMessageDialog(this, "Author added successfully.");
                    clearInput();
                } catch (InvalidAuthorException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private Author bindObject() {
        Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZipCode.getText());
        Author author = new Author(txtAuthorId.getText(), txtFirstName.getText(), txtLastName.getText(),
                txtPhoneNumber.getText(), address, txtBio.getText());
        return author;
    }

    private void clearInput() {
        txtStreet.setText("");
        txtCity.setText("");
        txtState.setText("");
        txtZipCode.setText("");
        txtAuthorId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhoneNumber.setText("");
        txtBio.setText("");
    }

    private boolean validateInput() {
        if (txtAuthorId.getText().isEmpty()) {
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

        return true;
    }

    public void defineLowerPanel() {
        JButton backToMainButn = new JButton("HOME");
        backToMainButn.addActionListener(new BackToMainListener());
        lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lowerPanel.add(backToMainButn);

        JButton btnAddMember = new JButton("ADD");
        attachAddMemberButtonListener(btnAddMember);
        lowerPanel.add(btnAddMember);

        JButton btnClear = new JButton("CANCEL");
        btnClear.addActionListener(e -> {
            clearInput();
        });
        lowerPanel.add(btnClear);

//		JPanel addBookButtonPanel = new JPanel();
//		addBookButtonPanel.setLayout(new FlowLayout(1));
//		addBookButtonPanel.add(btnAddMember);
        // this.outerMiddel.add(addBookButtonPanel, BorderLayout.CENTER);

    }

    class BackToMainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            UIController.hideAllWindows();
            MainView.INSTANCE.setVisible(true);
        }
    }

}
