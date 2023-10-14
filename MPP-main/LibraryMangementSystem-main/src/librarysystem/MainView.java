package librarysystem;

import dataaccess.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainView extends JFrame implements LibWindow {

	private JPanel contentPane;

	public final static MainView INSTANCE = new MainView();

	JLabel lblGreeting;

	JMenu mnNewMenu_1, mnNewMenu_2;
	JMenu mnNewMenu;

	JMenuItem logout, addLibraryMember, checkOutBook, addBookCopy, addBook, printCheckOutRecord, checkOverdue,
			editMember, addAuthor;

	private static LibWindow[] allWindows = { UIController.INSTANCE, AddMemberWindow.INSTANCE, BookWindow.INSTANCE,
			BookCopyWindow.INSTANCE, EditMemberWindow.INSTANCE, AddAuthorWindow.INSTANCE, CheckoutBookWindow.INSTANCE,
			PrintCheckOutRecordWindow.INSTANCE, CheckOverdueWindow.INSTANCE, MainView.INSTANCE, MainLogin.INSTANCE };

	/**
	 * Create the frame.
	 */
	public MainView() {
		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 930, 657);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		// panel.setBackground(new Color(91, 211, 226));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(450, 30, 930, 657);
		contentPane.add(panel);
		panel.setLayout(null);

		lblGreeting = new JLabel("Dear User");
		lblGreeting.setForeground(new Color(245, 245, 220));

		lblGreeting.setFont(new Font("Segoe UI", Font.ITALIC, 29));
		lblGreeting.setBounds(146, 229, 370, 58);
		panel.add(lblGreeting);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 30, 472, 657);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(MainLogin.class.getResource("/librarysystem/library.jpeg"))
				.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 11, 930, 22);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Auth");
		menuBar.add(mnNewMenu);

		JMenuItem logout_1 = new JMenuItem("LOGOUT");
		logout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIController.hideAllWindows();
				MainLogin.INSTANCE.init();
				Util.centerFrameOnDesktop(MainLogin.INSTANCE);
				MainLogin.INSTANCE.setVisible(true);
				User.updateCurrentUser(null);
			}
		});
		mnNewMenu.add(logout_1);

		mnNewMenu_1 = new JMenu("Book Checkout");
		menuBar.add(mnNewMenu_1);

		checkOutBook = new JMenuItem("Checkout Book");
		checkOutBook.addActionListener(new CheckOutBookListener());
		mnNewMenu_1.add(checkOutBook);

		printCheckOutRecord = new JMenuItem("Print Checkout Record");
		printCheckOutRecord.addActionListener(new PrintCheckOutRecordListener());
		mnNewMenu_1.add(printCheckOutRecord);

		checkOverdue = new JMenuItem("Check Overdue");
		checkOverdue.addActionListener(new CheckOverdueListener());
		mnNewMenu_1.add(checkOverdue);

		mnNewMenu_2 = new JMenu("Add");
		menuBar.add(mnNewMenu_2);

		addLibraryMember = new JMenuItem("Add Member");
		addLibraryMember.addActionListener(new AddLibraryMemberListener());
		mnNewMenu_2.add(addLibraryMember);

		editMember = new JMenuItem("Edit Member");
		editMember.addActionListener(new EditMemberListener());
		mnNewMenu_2.add(editMember);

		addBook = new JMenuItem("Add Book");
		addBook.addActionListener(new AddBookListener());
		mnNewMenu_2.add(addBook);

		addBookCopy = new JMenuItem("Add Book Copy");
		addBookCopy.addActionListener(new AddBookCopyListener());
		mnNewMenu_2.add(addBookCopy);

		addAuthor = new JMenuItem("Add Author");
		addAuthor.addActionListener(new AddAuthorListener());
		mnNewMenu_2.add(addAuthor);
	}

	public static void hideAllWindows() {

		for (LibWindow frame : allWindows) {
			frame.setVisible(false);

		}
	}

	static void doLibrarianAuthentication() {
		MainView.INSTANCE.mnNewMenu_1.setVisible(true);
		MainView.INSTANCE.checkOutBook.setVisible(true);
		MainView.INSTANCE.printCheckOutRecord.setVisible(true);
		MainView.INSTANCE.checkOverdue.setVisible(false);

		MainView.INSTANCE.mnNewMenu_2.setVisible(false);
		MainView.INSTANCE.addLibraryMember.setVisible(false);
		MainView.INSTANCE.addBookCopy.setVisible(false);
		MainView.INSTANCE.addBook.setVisible(false);
	}

	static void doAdminAuthentication() {
		MainView.INSTANCE.mnNewMenu_1.setVisible(false);
		MainView.INSTANCE.checkOutBook.setVisible(false);
		MainView.INSTANCE.printCheckOutRecord.setVisible(false);
		MainView.INSTANCE.checkOverdue.setVisible(false);

		MainView.INSTANCE.mnNewMenu_2.setVisible(true);
		MainView.INSTANCE.addLibraryMember.setVisible(true);
		MainView.INSTANCE.addBookCopy.setVisible(true);
		MainView.INSTANCE.addBook.setVisible(true);
	}

	static void permitAll() {
		MainView.INSTANCE.mnNewMenu_1.setVisible(true);
		MainView.INSTANCE.checkOutBook.setVisible(true);
		MainView.INSTANCE.printCheckOutRecord.setVisible(true);
		MainView.INSTANCE.checkOverdue.setVisible(true);

		MainView.INSTANCE.mnNewMenu_2.setVisible(true);
		MainView.INSTANCE.addLibraryMember.setVisible(true);
		MainView.INSTANCE.addBookCopy.setVisible(true);
		MainView.INSTANCE.addBook.setVisible(true);
	}

	static void denyAll() {
		MainView.INSTANCE.mnNewMenu_1.setVisible(false);
		MainView.INSTANCE.checkOutBook.setVisible(false);
		MainView.INSTANCE.printCheckOutRecord.setVisible(false);
		MainView.INSTANCE.checkOverdue.setVisible(false);

		MainView.INSTANCE.mnNewMenu_2.setVisible(false);
		MainView.INSTANCE.addLibraryMember.setVisible(false);
		MainView.INSTANCE.addBookCopy.setVisible(false);
		MainView.INSTANCE.addBook.setVisible(false);
	}

	class AddLibraryMemberListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			AddMemberWindow.INSTANCE.init();
			AddMemberWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
			AddMemberWindow.INSTANCE.setVisible(true);
		}

	}

	class EditMemberListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			EditMemberWindow.INSTANCE.init();
			EditMemberWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(EditMemberWindow.INSTANCE);
			EditMemberWindow.INSTANCE.setVisible(true);
		}

	}

	class CheckOutBookListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			CheckoutBookWindow.INSTANCE.init();
			CheckoutBookWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(CheckoutBookWindow.INSTANCE);
			CheckoutBookWindow.INSTANCE.setVisible(true);

		}

	}

	class AddBookCopyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			BookCopyWindow.INSTANCE.init();
			BookCopyWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(BookCopyWindow.INSTANCE);
			BookCopyWindow.INSTANCE.setVisible(true);

		}

	}

	class AddBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			BookWindow.INSTANCE.init();
			BookWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(BookWindow.INSTANCE);
			BookWindow.INSTANCE.setVisible(true);
		}

	}

	class AddAuthorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			AddAuthorWindow.INSTANCE.init();
			AddAuthorWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(AddAuthorWindow.INSTANCE);
			AddAuthorWindow.INSTANCE.setVisible(true);
		}

	}

	class PrintCheckOutRecordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			PrintCheckOutRecordWindow.INSTANCE.init();
			PrintCheckOutRecordWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(PrintCheckOutRecordWindow.INSTANCE);
			PrintCheckOutRecordWindow.INSTANCE.setVisible(true);

		}

	}

	class CheckOverdueListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			UIController.hideAllWindows();
			CheckOverdueWindow.INSTANCE.init();
			CheckOverdueWindow.INSTANCE.pack();
			Util.centerFrameOnDesktop(CheckOverdueWindow.INSTANCE);
			CheckOverdueWindow.INSTANCE.setVisible(true);

		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub

	}
}
