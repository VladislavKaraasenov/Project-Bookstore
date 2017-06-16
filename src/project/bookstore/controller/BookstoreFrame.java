package project.bookstore.controller;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import project.bookstore.exceptions.*;
import project.bookstore.helpers.*;
import project.bookstore.model.*;

@SuppressWarnings("serial")
public class BookstoreFrame extends JFrame {

	private JScrollPane scrollPane;
	private JTable table;

	private JTextField searchTextField;
	private JTextField sellTextField;
	private JTextField removeTextField;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextField namePublisherTextField;
	private JTextField literatureTextField;
	private JTextField priceTextField;
	private JTextField countTextField;
	private JPasswordField passwordField;

	private JLabel passwordLabel;

	private JButton searchButton;
	private JButton sellButton;
	private JButton removeButton;
	private JButton addButton;
	private JButton editButton;
	private JButton saveButton;
	private JButton loginButton;

	private Bookstore bookstore;
	private BookstoreTableModel tableModel;

	private final String ADMIN_USER_PASSWORD = "1234";
	private final String NORMAL_USER_PASSWORD = "4321";

	public BookstoreFrame() {
		super();
		bookstore = new Bookstore();
		createView();
	}

	public void createView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(50, 30, 1250, 650);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 900, 570);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		tableModel = new BookstoreTableModel();
		tableModel.setBooksList(bookstore.getSortedBooks());
		table.setModel(tableModel);
		table.setVisible(false);

		JLabel searchTitleLabel = new JLabel("Enter Book Title:");
		searchTitleLabel.setBounds(930, 20, 220, 20);
		add(searchTitleLabel);

		searchTextField = new JTextField();
		searchTextField.setBounds(930, 40, 220, 20);
		searchTextField.setEnabled(false);
		add(searchTextField);

		searchButton = new JButton("Search");
		searchButton.setBounds(1000, 65, 90, 20);
		searchButton.addActionListener(search -> search());
		searchButton.setEnabled(false);
		add(searchButton);

		JLabel sellTitleLabel = new JLabel("Enter Book Title:");
		sellTitleLabel.setBounds(930, 85, 220, 20);
		add(sellTitleLabel);

		sellTextField = new JTextField();
		sellTextField.setBounds(930, 105, 220, 20);
		sellTextField.setEnabled(false);
		add(sellTextField);

		sellButton = new JButton("Sell");
		sellButton.setBounds(1000, 130, 90, 20);
		sellButton.addActionListener(sell -> sell());
		sellButton.setEnabled(false);
		add(sellButton);

		JLabel removeTitleLabel = new JLabel("Enter Book Title:");
		removeTitleLabel.setBounds(930, 150, 220, 20);
		add(removeTitleLabel);

		removeTextField = new JTextField();
		removeTextField.setBounds(930, 170, 220, 20);
		removeTextField.setEnabled(false);
		add(removeTextField);

		removeButton = new JButton("Delete");
		removeButton.setBounds(1000, 195, 90, 20);
		removeButton.addActionListener(remove -> remove());
		removeButton.setEnabled(false);
		add(removeButton);

		titleTextField = new JTextField();
		titleTextField.setBounds(930, 230, 150, 20);
		titleTextField.setEnabled(false);
		add(titleTextField);

		JLabel titleLabel = new JLabel("Book Title");
		titleLabel.setBounds(1090, 230, 90, 20);
		add(titleLabel);

		authorTextField = new JTextField();
		authorTextField.setBounds(930, 260, 150, 20);
		authorTextField.setEnabled(false);
		add(authorTextField);

		JLabel authorLabel = new JLabel("Author");
		authorLabel.setBounds(1090, 260, 90, 20);
		add(authorLabel);

		namePublisherTextField = new JTextField();
		namePublisherTextField.setBounds(930, 290, 150, 20);
		namePublisherTextField.setEnabled(false);
		add(namePublisherTextField);

		JLabel namePublisherLabel = new JLabel("Name Publisher");
		namePublisherLabel.setBounds(1090, 290, 100, 20);
		add(namePublisherLabel);

		literatureTextField = new JTextField();
		literatureTextField.setBounds(930, 320, 150, 20);
		literatureTextField.setEnabled(false);
		add(literatureTextField);

		JLabel literatureLabel = new JLabel("Literature");
		literatureLabel.setBounds(1090, 320, 90, 20);
		add(literatureLabel);

		priceTextField = new JTextField();
		priceTextField.setBounds(930, 350, 150, 20);
		priceTextField.setEnabled(false);
		add(priceTextField);

		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(1090, 350, 90, 20);
		add(priceLabel);

		countTextField = new JTextField();
		countTextField.setBounds(930, 380, 150, 20);
		countTextField.setEnabled(false);
		add(countTextField);

		JLabel countLabel = new JLabel("Count");
		countLabel.setBounds(1090, 380, 90, 20);
		add(countLabel);

		addButton = new JButton("Add Book");
		addButton.setBounds(930, 410, 90, 20);
		addButton.addActionListener(add -> add());
		addButton.setEnabled(false);
		add(addButton);

		editButton = new JButton("Edit");
		editButton.setBounds(930, 440, 90, 20);
		editButton.addActionListener(edit -> edit());
		editButton.setEnabled(false);
		add(editButton);

		saveButton = new JButton("Save");
		saveButton.setBounds(930, 470, 90, 20);
		saveButton.addActionListener(save -> save());
		saveButton.setEnabled(false);
		add(saveButton);

		passwordLabel = new JLabel("Please Enter Password:");
		passwordLabel.setBounds(930, 500, 150, 20);
		add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(930, 520, 150, 20);
		passwordField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
		});
		add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(930, 550, 90, 20);
		loginButton.addActionListener(login -> login());
		loginButton.setEnabled(false);
		add(loginButton);
	}

	/**
	 * Checks if there is any text typed in the space for password if there is
	 * allows pressing the login button.
	 */
	private void changed() {
		String passwordText = new String(passwordField.getPassword());
		if (passwordText.equals("")) {
			loginButton.setEnabled(false);
		} else {
			loginButton.setEnabled(true);
		}
	}

	/**
	 * Creates a book from added data and adds the book in the bookstore.
	 */
	private void add() {
		String title = titleTextField.getText();
		String author = authorTextField.getText();
		String namePublisher = namePublisherTextField.getText();
		String literature = literatureTextField.getText();
		String price = priceTextField.getText();
		String count = countTextField.getText();
		
		try {
			Book book = new Book(title, author, namePublisher, literature, Double.parseDouble(price),
					Integer.parseInt(count));
			bookstore.addBook(book);
			JOptionPane.showMessageDialog(this, "Book Added Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
			tableModel.setBooksList(bookstore.getSortedBooks());
		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Error with entered data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (PersistenceHelperSavingException e) {
			JOptionPane.showMessageDialog(this, "Not Saved.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Removes book from bookstore.
	 */
	private void remove() {
		try {
			String title = removeTextField.getText();
			Book book = bookstore.removeBook(title);
			if (book != null) {
				JOptionPane.showMessageDialog(this, "Book Deleted Successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
				tableModel.setBooksList(bookstore.getSortedBooks());
			} else {
				JOptionPane.showMessageDialog(this, "Book Not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (PersistenceHelperSavingException e) {
			JOptionPane.showMessageDialog(this, "Not Saved.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Search for existing book title containing key word.
	 */
	private void search() {
		String word = searchTextField.getText();
		List<Book> books = bookstore.searchBook(word);
		
		if (books.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Book Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			for (Book book : books) {
				JOptionPane.showMessageDialog(this, "Title: " + book.getTitle() + " - " + "Price: " + book.getPrice()
						+"$" + " - " + "Count: " + book.getCount(), "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Book supply decreases with one.
	 */
	private void sell() {
		try {
			String title = sellTextField.getText();
			String bookTitle = bookstore.sellBook(title);
			
			if (bookTitle != null) {
				JOptionPane.showMessageDialog(this, "Book Sold Successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
				tableModel.setBooksList(bookstore.getSortedBooks());
			} else {
				JOptionPane.showMessageDialog(this, "Book Not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (PersistenceHelperSavingException e) {
			JOptionPane.showMessageDialog(this, "Not Saved.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Allows book editing in the bookstore.
	 */
	private void edit() {
		tableModel.setCellEditable(true);
	}

	/**
	 * Saves changes after editing.
	 */
	private void save() {
		List<Book> books = bookstore.getSortedBooks();
		try {
			PersistenceHelper.save(books);
			tableModel.setCellEditable(false);
			tableModel.setBooksList(books);
			JOptionPane.showMessageDialog(this, "The List is Saved Successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
		} catch (PersistenceHelperSavingException e) {
			JOptionPane.showMessageDialog(this, "Not Saved.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Shows how the program continues to work after the entered password.
	 */
	private void login() {
		String passwordText = new String(passwordField.getPassword());

		if (passwordText.equals(ADMIN_USER_PASSWORD)) {
			table.setVisible(true);
			searchTextField.setEnabled(true);
			searchButton.setEnabled(true);
			sellTextField.setEnabled(true);
			sellButton.setEnabled(true);
			removeTextField.setEnabled(true);
			removeButton.setEnabled(true);
			titleTextField.setEnabled(true);
			authorTextField.setEnabled(true);
			namePublisherTextField.setEnabled(true);
			literatureTextField.setEnabled(true);
			priceTextField.setEnabled(true);
			countTextField.setEnabled(true);
			addButton.setEnabled(true);
			editButton.setEnabled(true);
			saveButton.setEnabled(true);
			passwordLabel.setVisible(false);
			passwordField.setVisible(false);
			loginButton.setVisible(false);
		} else if (passwordText.equals(NORMAL_USER_PASSWORD)) {
			table.setVisible(true);
			searchTextField.setEnabled(true);
			searchButton.setEnabled(true);
			sellTextField.setEnabled(true);
			sellButton.setEnabled(true);
			passwordLabel.setVisible(false);
			passwordField.setVisible(false);
			loginButton.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
