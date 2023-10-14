package librarysystem;

import business.controller.ControllerFactory;
import business.controller.ControllerType;
import business.usecase.IAddBook;
import business.usecase.IAuthor;
import business.usecase.ISearchBook;
import dataaccess.model.Author;
import dataaccess.model.Book;
import dataaccess.model.BookCopy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookWindow extends JFrame implements LibWindow {
	public static final BookWindow INSTANCE = new BookWindow();
	private static final long serialVersionUID = 1L;
	private boolean isInitialized = false;

	private IAddBook addBookUseCase = ControllerFactory.getController(ControllerType.Book);
	private ISearchBook searchBookUseCase = ControllerFactory.getController(ControllerType.Book);

	private JPanel mainPanel = new JPanel();
	private JPanel topPanel;
	private JPanel outerMiddle;

	private JTextField txtISBN, txtTitle, txtNoOfCopy;

	private List<JCheckBox> jCheckBoxs = new ArrayList<>();

	private JButton btnOtherAuthor;
	private JComboBox<Integer> cmbMaxCheckOutLength;

	private JScrollPane jScrollPane;
	JTable jt;
	DefaultTableModel jtmodel = new DefaultTableModel();
	private List<Author> m_authors;
	private JPanel contentPane;

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
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		mainPanel.add(this.topPanel, BorderLayout.NORTH);
		mainPanel.add(this.outerMiddle, BorderLayout.CENTER);
		
		mainPanel.setBackground(new Color(64, 64, 64)); 
		
		mainPanel.setBounds(0, 0, 930, 720);

		getContentPane().add(mainPanel);
         
		isInitialized(true);
		m_authors = getAllAuthors();

		attachAddAuthorListener(btnOtherAuthor);
	}

	private JScrollPane initializeTable() {
		// Clear rows and columns
		jtmodel.setRowCount(0);
		jtmodel.setColumnCount(0);

		// jTable
		jtmodel.addColumn("ISBN");
		jtmodel.addColumn("Copy Number");
		jtmodel.addColumn("Book Title");
		jtmodel.addColumn("Availability");
		

		jt = new JTable(jtmodel);

		jt.getColumnModel().getColumn(0).setPreferredWidth(20);
		jt.getColumnModel().getColumn(1).setPreferredWidth(20);
		jt.getColumnModel().getColumn(3).setPreferredWidth(80);
		jt.getColumnModel().getColumn(3).setPreferredWidth(20);
		
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 375, 800, 150);

		// load books
		List<Book> data = searchBookUseCase.getBookCollection();

		for (Book lm : data) {
			String isbn = lm.getIsbn();
			String title = lm.getTitle();

			for (BookCopy bc : lm.getCopies()) {
				jtmodel.addRow(new Object[] { isbn, bc.getCopyNum(), title, bc.isAvailable() });
			}

		}
		return sp;

	}

	// Constructor
	private BookWindow() {
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public void clearData() {
		this.txtISBN.setText("");
		this.txtTitle.setText("");
		this.txtNoOfCopy.setText("");

		jCheckBoxs.forEach(s -> s.setSelected(false));
	}

	public List<Author> getAllAuthors() {
		IAuthor ac = ControllerFactory.getController(ControllerType.Author);
		List<Author> authors = ac.getAllAuthors();
		return authors;
	}

	public void defineTopPanel() {
		this.topPanel = new JPanel();
		
		topPanel.setBackground(new Color(0, 0, 0));
		JLabel AddBookLabel = new JLabel("Add New Book");
		Util.adjustLabelFont(AddBookLabel, Color.WHITE, true);
		this.topPanel.setLayout(new FlowLayout(0));
		this.topPanel.add(AddBookLabel);
	}

	public void defineOuterMiddle() {
		this.outerMiddle = new JPanel();
		this.outerMiddle.setLayout(new BorderLayout());
		
		JPanel middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(1, 25, 25);
		middlePanel.setLayout(fl);
		
		middlePanel.setBackground(new Color(64, 64, 64));
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftPanel.setBackground(new Color(64, 64, 64));
        rightPanel.setBackground(new Color(64, 64, 64));
		
		
		leftPanel.setLayout(new BoxLayout(leftPanel, 1));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));

		// Labels
		JLabel lblISBN = new JLabel("<html><font color='white'>ISBN</font><font color='red'> *</font></html>");
		JLabel lblTitle = new JLabel("<html><font color='white'>Title</font><font color='red'> *</font></html>");
		JLabel lblAuthors = new JLabel("<html><font color='white'>Authors</font><font color='red'> *</font></html>");
		JLabel lblAuthors1 = new JLabel("<html><font color='white'>Authors</font></html>");
		JLabel lblMaxCheckOutLength = new JLabel("<html><font color='white'>Maximum Checkout Length</font><font color='red'> *</font></html>");
		JLabel lblNumberOfCopies = new JLabel("<html><font color='white'>Book Copies</font><font color='red'> *</font></html>");

		this.txtISBN = new JTextField(20);
		this.txtTitle = new JTextField(10);
		this.txtNoOfCopy = new JTextField(10);

		this.cmbMaxCheckOutLength = new JComboBox<Integer>();
		cmbMaxCheckOutLength.addItem(21);
		cmbMaxCheckOutLength.addItem(7);

		leftPanel.add(lblISBN);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(lblTitle);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		leftPanel.add(lblAuthors);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(lblMaxCheckOutLength);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		leftPanel.add(lblNumberOfCopies);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));

		// TextField, JList, JCombo
		rightPanel.add(this.txtISBN);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		rightPanel.add(this.txtTitle);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		// load authors
		List<Author> authors = getAllAuthors();
		JPanel p = new JPanel();
		
		//p.setBackground(new Color(64, 64, 64));
		p.setLayout(new GridLayout(3, 5));

		btnOtherAuthor = new JButton("ADD AUTHOR");

		authors.forEach(author -> {
		//	JCheckBox box = new JCheckBox("<html><font color='white'>"+ author.getFullName() +"</font></html>");
			JCheckBox box = new JCheckBox(author.getFullName());
			jCheckBoxs.add(box);
			p.add(box);
		});
		p.add(btnOtherAuthor);
		rightPanel.add(p);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		rightPanel.add(this.cmbMaxCheckOutLength);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 6)));
		rightPanel.add(this.txtNoOfCopy);

		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		this.outerMiddle.add(middlePanel, "North");

		// Buttons
		JButton btnBackToMain = new JButton("HOME");
		addBackButtonListener(btnBackToMain);

		JButton btnAddBook = new JButton("ADD");
		attachAddBookButtonListener(btnAddBook);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(1));
		buttonPanel.add(btnBackToMain);
		buttonPanel.add(btnAddBook);

		buttonPanel.setBackground(new Color(64, 64, 64));
		rightPanel.add(buttonPanel);
		
		jScrollPane = initializeTable();

		this.outerMiddle.add(jScrollPane, "South");


	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			UIController.hideAllWindows();
			MainView.INSTANCE.setVisible(true);
		});
	}

	private void attachAddBookButtonListener(JButton butn) {
		butn.addActionListener((evt) -> {

			try {
				String isbn = txtISBN.getText().trim();
				List<Book> data2 = searchBookUseCase.getBookCollection();
				boolean isExist=false;
				for (Book bk1 : data2) {
					if (isbn.equals(bk1.getIsbn())) {
						JOptionPane.showMessageDialog(this, "Existing ISBN");
						isExist=true;
					}
				}
				if (isExist==false) {
					String title = txtTitle.getText().trim();
					int maxCheckoutPeriod = (int) cmbMaxCheckOutLength.getSelectedItem();

					List<Author> selectedAuthors = new ArrayList<Author>();

					int noOfCopy = Integer.valueOf(txtNoOfCopy.getText());

					// Save data to dataaccess.storage
					jCheckBoxs.forEach(box -> {
						if (box.isSelected()) {
							for (Author author : m_authors) {
								if (box.getText().equals(author.getFullName())) {
									selectedAuthors.add(author);
								}
							}
						}
					});

					for (Author model : selectedAuthors) {
						System.out.println(model.getFullName());
					}

					Book book = new Book(isbn, title, maxCheckoutPeriod, selectedAuthors);
					System.out.println("Book : " + book.toString());
					for (int i = 1; i <= noOfCopy; i++) {
						book.addCopy();
					}

					addBookUseCase.addBook(book);
					JOptionPane.showMessageDialog(this,
							"The book " + txtTitle.getText().trim() + " has been added " + "to the collection!");
				}

				DefaultTableModel model = (DefaultTableModel) jt.getModel();

				model.setRowCount(0); // clear data

				// load books
				List<Book> data = searchBookUseCase.getBookCollection();

				System.out.println("List of book: " + data.toString());

				if (data != null || data.size() > 0) {
					model.setRowCount(0);
				}

				for (Book bk : data) {
					String strISBN = bk.getIsbn();
					String strTitle = bk.getTitle();

					for (BookCopy bc : bk.getCopies()) {
						model.addRow(new Object[] { strISBN, bc.getCopyNum(), strTitle, bc.isAvailable() });
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Required Fields can not be left empty", "Save Failed", JOptionPane.ERROR_MESSAGE);
			}

			clearData();

			// load books
			List<Book> dataa = searchBookUseCase.getBookCollection();
			jtmodel = new DefaultTableModel();
			for (Book lm : dataa) {
				String isbn = lm.getIsbn();
				String title = lm.getTitle();
				for (BookCopy bc : lm.getCopies()) {
					jtmodel.addRow(new Object[] { isbn, bc.getCopyNum(), title, bc.isAvailable() });
				}

			}
		});
	}

	public void attachAddAuthorListener(JButton btn){
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UIController.hideAllWindows();
				AddAuthorWindow.INSTANCE.init();
				AddAuthorWindow.INSTANCE.pack();
				Util.centerFrameOnDesktop(AddAuthorWindow.INSTANCE);
				AddAuthorWindow.INSTANCE.setVisible(true);
			}
		});
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

}
