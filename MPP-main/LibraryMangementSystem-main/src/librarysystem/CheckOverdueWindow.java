package librarysystem;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.usecase.*;
import business.controller.*;
import dataaccess.model.*;


public class CheckOverdueWindow extends JFrame implements LibWindow {

	private static final long serialVersionUID = 1L;
	public static final CheckOverdueWindow INSTANCE = new CheckOverdueWindow();
	private JPanel contentPane;

	private CheckOverdueWindow() {
	}

	IPrintCheckOutRecord printCheckOutBookUseCase = ControllerFactory.getController(ControllerType.CheckOutBook);
	private boolean isInitialized = false;

	JTextField txtIsbn;
	JTable jt;

	public void checkOutOverdue() {
		if(txtIsbn != null) {

			if(jt != null) {
				DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
				model2.setRowCount(0);
			}
			return;
		}

		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 930, 657);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(64, 64, 64));
		
		JPanel panelCheckoutFields = new JPanel();
		panelCheckoutFields.setLayout(null);
		panelCheckoutFields.setSize(930, 657);

		this.setMinimumSize(new Dimension(930, 657));

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Check Overdue");
		Util.adjustLabelFont(title, Color.WHITE, true);
		title.setLayout(new FlowLayout(0));
		titlePanel.add(title);
		titlePanel.setBackground(new Color(0, 0, 0));
		titlePanel.setBounds(0, 0, 930, 30);

		panelCheckoutFields.add(titlePanel);

		JLabel lbIsbn = new JLabel("<html><font color='white'>ISBN</font><font color='red'> *</font></html>");
		lbIsbn.setBounds(25, 65, 150, 20);
		txtIsbn = new JTextField(10);
		txtIsbn.setBounds(120, 65, 150, 20);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(120, 95, 150, 20);
		addCheckIDListener(btnSearch);

		JButton btnBackToMain = new JButton("HOME");

		addBackButtonListener(btnBackToMain);

		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnBackToMain);
		pnlButtons.add(btnSearch);
		pnlButtons.setBackground(new Color(64, 64, 64));
		pnlButtons.setBounds(20, 60, 890, 35);


		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ISBN");
		model.addColumn("Title");
		model.addColumn("No of Copies");
		model.addColumn("Member ID");
		model.addColumn("Member Name");
		model.addColumn("Overdue");

		jt = new JTable(model);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 120, 890, 430);
		panelCheckoutFields.add(sp);

		// Print CheckoutRecord

		panelCheckoutFields.add(lbIsbn);
		panelCheckoutFields.add(txtIsbn);

		panelCheckoutFields.add(pnlButtons, BorderLayout.CENTER);

		panelCheckoutFields.setBackground(new Color(64, 64, 64));
		panelCheckoutFields.setBounds(0, 1, 930, 657);
		getContentPane().add(panelCheckoutFields);

	}

	private void addCheckIDListener(JButton butn) {
		butn.addActionListener(evt -> {
			String isbn = txtIsbn.getText().trim();

			if (isbn.length() == 0) {
				JOptionPane.showMessageDialog(this, "ISBN required", "Search Failed", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					displayCheckoutInfo(isbn);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Search Failed!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			txtIsbn.setText("");
			UIController.hideAllWindows();
			MainView.INSTANCE.setVisible(true);
		});
	}


	private void displayCheckoutInfo(String isbn) {
		 HashMap<String, CheckOutRecord> cr = printCheckOutBookUseCase.getCheckOutOverdue();
		if (cr == null) {
			JOptionPane.showMessageDialog(this, "This Book's Overdue Record is not found!", "Check Overdue", JOptionPane.ERROR_MESSAGE);
			return;
		}	

		DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
		model2.setRowCount(0);
		
		Iterator<Map.Entry<String, CheckOutRecord>> iterator = cr.entrySet().iterator();
		
		while (iterator.hasNext()) {
            Map.Entry<String, CheckOutRecord> entryOverdue = iterator.next();

            String key = entryOverdue.getKey();
            CheckOutRecord checkOutRecord = entryOverdue.getValue();
            
            for(CheckOutRecordEntry e : checkOutRecord.getCheckOutRecordEntries()) {
            	String dueNoDue = "";
            	if(e.getDueDate().isBefore(LocalDate.now())){
            		dueNoDue = "Yes";
            	}else {
            		dueNoDue = "No";
            	}
            	 if(e.getBookCopy().getBook().getIsbn().equals(isbn)) {
                 	model2.addRow(new Object[] { e.getBookCopy().getBook().getIsbn(), e.getBookCopy().getBook().getTitle(),
         					e.getBookCopy().getCopyNum(),key, checkOutRecord.getMember().getFullName(), dueNoDue });
                 }
            }
                       
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
		checkOutOverdue();
	}

}
