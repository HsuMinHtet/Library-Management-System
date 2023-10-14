package librarysystem;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.controller.ControllerType;
import business.controller.ControllerFactory;
import dataaccess.model.Author;
import dataaccess.model.Book;
import business.exception.BookNotFoundException;
import business.usecase.IBookCopy;

public class BookCopyWindow extends JFrame implements LibWindow {
    public BookCopyWindow() {
    }

    private static final long serialVersionUID = 1L;
    public static final BookCopyWindow INSTANCE = new BookCopyWindow();
    private boolean isInitialized = false;

    IBookCopy addBookCopyUseCase = ControllerFactory.getController(ControllerType.Book);

    private JTextField txtISBN, txtCopyNumber;

    public void initComponent() {

        setResizable(false);
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1, 1, 490, 257);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(64, 64, 64));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelCheckoutFields = new JPanel();
        panelCheckoutFields.setLayout(null);
        panelCheckoutFields.setSize(500, 500);

        this.setMinimumSize(new Dimension(930, 657));

        JLabel lblISBN = new JLabel("<html><font color='white'>ISBN</font><font color='red'> *</font></html>");
        lblISBN.setBounds(20, 20, 100, 20);

        txtISBN = new JTextField(10);
        txtISBN.setBounds(116, 20, 100, 20);

        JLabel lblCopyNumber = new JLabel("<html><font color='white'>Copy Number</font><font color='red'> *</font></html>");
        lblCopyNumber.setBounds(20, 50, 100, 20);

        txtCopyNumber = new JTextField(10);
        txtCopyNumber.setBounds(116, 50, 100, 20);

        JPanel pnlButtonSave = new JPanel();
        
        pnlButtonSave.setBackground(new Color(128, 128, 128));

        JButton btnSave = new JButton("SAVE");
        addCreateCopyButtonListener(btnSave);

        JButton btnBacktoMain = new JButton("HOME");
        addBackButtonListener(btnBacktoMain);

        pnlButtonSave.add(btnBacktoMain);
        pnlButtonSave.add(btnSave);
        pnlButtonSave.setBounds(100, 100, 244, 40);

        panelCheckoutFields.add(lblISBN);
        panelCheckoutFields.add(txtISBN);

        panelCheckoutFields.add(lblCopyNumber);
        panelCheckoutFields.add(txtCopyNumber);

        panelCheckoutFields.add(pnlButtonSave, BorderLayout.CENTER);
        
        panelCheckoutFields.setBackground(new Color(128, 128, 128));
        panelCheckoutFields.setBounds(250, 150, 490, 250);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Add Book Copy");
        Util.adjustLabelFont(title, Color.WHITE, true);
        title.setLayout(new FlowLayout(0));
        titlePanel.add(title);  
        titlePanel.setBackground(new Color(0, 0, 0));
        
        titlePanel.setBounds(0, 0, 930, 30);
        getContentPane().add(titlePanel);
        getContentPane().add(panelCheckoutFields);
    }

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            UIController.hideAllWindows();
            MainView.INSTANCE.setVisible(true);
        });
    }

    private void addCreateCopyButtonListener(JButton butn) {
        butn.addActionListener(evt -> {

            int noOfCopies = 0;

            try {
                noOfCopies = Integer.parseInt(txtCopyNumber.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid Input", "Save Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtISBN.getText().equals("") || txtCopyNumber.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Required Fields can not be left empty", "Save Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Book book = new Book(txtISBN.getText(), null, 0, new ArrayList<Author>());

                book = addBookCopyUseCase.addBookCopy(book, noOfCopies);

                txtISBN.setText("");
                txtCopyNumber.setText("");

                JOptionPane.showMessageDialog(this, "Book copy added successfully, Book " + book.getIsbn() + " has " + book.getNumCopies() + " copies");

            } catch (BookNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Save Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        this.isInitialized = true;
    }

    @Override
    public void init() {
        initComponent();
    }

}