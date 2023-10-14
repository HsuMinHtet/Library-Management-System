package librarysystem;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.controller.ControllerType;
import business.exception.InvalidMemberException;
import business.usecase.ICheckOutBook;
import business.controller.ControllerFactory;
import dataaccess.model.BookCopy;
import dataaccess.model.CheckOutRecord;
import dataaccess.model.CheckOutRecordEntry;
import business.exception.BookCopyNotAvailableException;
import business.exception.BookNotFoundException;
import business.exception.MemberNotFoundException;

public class CheckoutBookWindow extends JFrame implements LibWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();

	private CheckoutBookWindow() {
	}

	ICheckOutBook checkOutBookUseCase = ControllerFactory.getController(ControllerType.CheckOutBook);
	private boolean isInitialized = false;

	JTextField txtISBN, txtMemberID;
	JComboBox<BookCopy> cmbCopies;
	JTable jt;

    public void checkOutBook() {
    	if(txtISBN != null) {
    		if(jt != null) {
    			DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
    			model2.setRowCount(0);
    		}
    		return;
    	}
        // TODO Auto-generated method stub
    	setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBackground(new Color(64, 64, 64));
		setBounds(1, 1, 930, 657);
		
        JPanel panelCheckoutFields = new JPanel();
        panelCheckoutFields.setLayout(null);
        panelCheckoutFields.setSize(500,500);

		this.setMinimumSize(new Dimension(930, 657));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Checkout Book");
		Util.adjustLabelFont(title, Color.WHITE, true);
		title.setLayout(new FlowLayout(0));
		titlePanel.add(title);
		
		titlePanel.setBackground(new Color(0, 0, 0));
		titlePanel.setBounds(0, 0, 930, 30);

		panelCheckoutFields.add(titlePanel);

		JLabel lblISBN = new JLabel("<html><font color='white'>ISBN</font><font color='red'> *</font></html>");
		lblISBN.setBounds(20, 40, 150, 20);
		txtISBN = new JTextField(10);
		txtISBN.setBounds(110, 40, 150, 20);
		JLabel lblMemberID = new JLabel("<html><font color='white'>Member ID</font><font color='red'> *</font></html>");
		lblMemberID.setBounds(20, 80, 150, 20);
		txtMemberID = new JTextField(10);
		txtMemberID.setBounds(110, 80, 150, 20);

		JButton btnCheckout = new JButton("CHECKOUT");
		btnCheckout.setBounds(110, 120, 150, 20);
		addCheckoutListener(btnCheckout);

        JButton btnBackToMain = new JButton("HOME");
		btnBackToMain.setBounds(20, 170, 100, 20);
        addBackButtonListener(btnBackToMain);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Member Id");
		model.addColumn("Member Name");
		model.addColumn("ISBN");
		model.addColumn("Book Name");
		model.addColumn("Checkout Date");
		model.addColumn("Due Date");

		jt = new JTable(model);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 220, 880, 380);
		panelCheckoutFields.add(sp);

		panelCheckoutFields.add(lblMemberID);
		panelCheckoutFields.add(txtISBN);

		panelCheckoutFields.add(lblISBN);
		panelCheckoutFields.add(txtMemberID);

		panelCheckoutFields.add(btnCheckout);
		panelCheckoutFields.add(btnBackToMain);
                
        panelCheckoutFields.setBackground(new Color(64, 64, 64));
        panelCheckoutFields.setBounds(40, 49, 850, 600);

		this.add(panelCheckoutFields);

	}

	private void addCheckoutListener(JButton butn) {
		butn.addActionListener(evt -> {
	
			String bkISBN = txtISBN.getText().trim();
			String memberID = txtMemberID.getText().trim();
			
			if (bkISBN.length() == 0 || memberID.length() == 0) {
				JOptionPane.showMessageDialog(this, "ISBN and member ID required", "Checkout Failed",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					checkOutBookUseCase.checkOutBook(memberID, bkISBN);
					JOptionPane.showMessageDialog(this, "Checkout successful", "Thank you", JOptionPane.PLAIN_MESSAGE);
					displayCheckoutInfo();
				} catch (BookNotFoundException | MemberNotFoundException | BookCopyNotAvailableException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Check out book", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidMemberException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
        	txtISBN.setText("");
        	txtMemberID.setText("");
            UIController.hideAllWindows();
            MainView.INSTANCE.setVisible(true);
        });
    }

	private void displayCheckoutInfo() {
		CheckOutRecord cr = checkOutBookUseCase.getCheckOutRecord(txtMemberID.getText());
		if (cr == null)
			return;

		DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
		model2.setRowCount(0);

		for (CheckOutRecordEntry entry : cr.getCheckOutRecordEntries()) {
			model2.addRow(new Object[] { cr.getMember().getMemberId(), cr.getMember().getFullName(),
					entry.getBookCopy().getBook().getIsbn(), entry.getBookCopy().getBook().getTitle(),
					entry.getCheckOutDate().toString(), entry.getDueDate().toString() });

		}

	}

	public boolean isInitialized() {
		return this.isInitialized;
	}

	public void isInitialized(boolean val) {
		this.isInitialized = val;
	}

	@Override
	public void init() {
		checkOutBook();
	}

}