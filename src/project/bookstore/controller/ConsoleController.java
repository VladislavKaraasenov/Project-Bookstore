package project.bookstore.controller;

import java.util.*;
import project.bookstore.exceptions.*;
import project.bookstore.model.*;

public class ConsoleController {

	private Scanner sc;
	private Bookstore bookstore;

	private enum Menu {
		ADD_BOOK,
		SEARCH_BOOK,
		SHOW_ALL_BOOKS,
		SELL_BOOK, 
		REMOVE_BOOK,
		INVALID,
		EXIT,

	}

	public void start() {

		sc = new Scanner(System.in);
		bookstore = new Bookstore();
		boolean shouldContinue = true;

		do {
			Menu menu = displayMenu();
			switch (menu) {
			case ADD_BOOK:
				add();
				break;
			case SEARCH_BOOK:
				search();
				break;
			case SHOW_ALL_BOOKS:
				showAllBooks();
				break;
			case SELL_BOOK:
				sell();
				break;
			case REMOVE_BOOK:
				remove();
				break;
			case EXIT:
				System.out.println("Thank you for using our services.");
				shouldContinue = false;
				break;
			case INVALID:
				System.out.println("Invalid Option. Please try again.");
				break;
			}
		} while (shouldContinue);
	}

	private Menu displayMenu() {
		System.out.println("--MENU--");
		System.out.println("1. Add Book");
		System.out.println("2. Search Book");
		System.out.println("3. Show all Books");
		System.out.println("4. Sell Book");
		System.out.println("5. Delete Book");
		System.out.println("6. EXIT");
		System.out.println("Enter Option: ");

		int menu = sc.nextInt();
		sc.nextLine();

		switch (menu) {
		case 1:
			return Menu.ADD_BOOK;
		case 2:
			return Menu.SEARCH_BOOK;
		case 3:
			return Menu.SHOW_ALL_BOOKS;
		case 4:
			return Menu.SELL_BOOK;
		case 5:
			return Menu.REMOVE_BOOK;
		case 6:
			return Menu.EXIT;
		default:
			return Menu.INVALID;
		}
	}

	/**
	 * Creates a book from added data and adds the book in the bookstore.
	 */
	private void add() {
		System.out.println("Enter Book Title: ");
		String title = sc.nextLine();
		System.out.println("Enter Author: ");
		String author = sc.nextLine();
		System.out.println("Enter Name Publishing: ");
		String namePublisher = sc.nextLine();
		System.out.println("Enter Literature (foreign or local): ");
		String literature = sc.nextLine();
		System.out.println("Enter Price: ");
		String price = sc.nextLine();
		System.out.println("Enter Count: ");
		String count = sc.nextLine();
		
		try {
			Book book = new Book(title, author, namePublisher, literature, Double.parseDouble(price),
					Integer.parseInt(count));
			bookstore.addBook(book);
			System.out.println("Done");
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Error with entered data. Please try again.");
		} catch (PersistenceHelperSavingException e) {
			System.out.println("Error. Not Saved.");
		}
	}

	/**
	 * Search for existing book title containing key word.
	 */
	private void search() {
		System.out.println("Enter Book Title:");
		String title = sc.nextLine();
		List<Book> booksList = bookstore.searchBook(title);

		if (booksList.isEmpty()) {
			System.out.println("Book Not Found");
		} else {
			System.out.println("Title --- Price --- Count");
			for (Book book : booksList) {
				System.out.println(book.getTitle() + " --- " + book.getPrice() + " --- " + book.getCount());
			}
		}
	}

	/**
	 * Removes book from bookstore.
	 */
	private void remove() {
		try {
			System.out.println("Enter Book Title for Removal: ");
			String title = sc.nextLine();
			Book book = bookstore.removeBook(title);
			
			if (book == null) {
				System.out.println("Book Not Found");
			} else {
				System.out.println("Done");
			}
		} catch (PersistenceHelperSavingException e) {
			System.out.println("Error. Not Saved.");
		}
	}

	/**
	 * Makes a list of every book in the bookstore.
	 */
	private void showAllBooks() {
		List<Book> booksList = bookstore.getSortedBooks();
		
		if (booksList.isEmpty()) {
			System.out.println("The List is Empty.");
		} else {
			System.out.println("ID --- Title --- Author --- Name Publisher --- Literature --- Price --- Count");
			for (Book book : booksList) {
				System.out.println(book.getId() + " --- " + book.getTitle() + " --- " + book.getAuthor() + " --- "
						+ book.getNamePublisher() + " --- " + book.getLiterature() + " --- " + book.getPrice() + " --- "
						+ book.getCount());
			}
		}
	}

	/**
	 * Book supply decreases with one.
	 */
	private void sell() {
		System.out.println("Enter Book Title: ");
		String title = sc.nextLine();
		try {
			String book = bookstore.sellBook(title);
			
			if (book == null) {
				System.out.println("Book Not Found");
			} else {
				System.out.println("Done");
			}
		} catch (PersistenceHelperSavingException e) {
			System.out.println("Error. Not Saved.");
		}
	}
}
