package project.bookstore.model;

import java.util.*;
import project.bookstore.exceptions.*;
import project.bookstore.helpers.*;

public class Bookstore {

	private Map<String, Book> bookstore;

	public Bookstore() {
		loading();
	}

	private void loading() {
		try {
			Collection<Book> loadedBooks = PersistenceHelper.load();
			bookstore = new HashMap<>();
			for (Book book : loadedBooks) {
				bookstore.put(book.getTitle(), book);
			}
		} catch (PersistenceHelperLoadingException e) {
			bookstore = new HashMap<>();
		}
	}

	/**
	 * Adds book in bookstore.
	 * 
	 * @param book to add
	 * @throws PersistenceHelperSavingException
	 *             if the file in which you are trying to save book information does not exist
	 */
	public void addBook(Book book) throws PersistenceHelperSavingException {
		this.bookstore.put(book.getTitle(), book);
		save();
	}

	/**
	 * Search for existing book title containing key word.
	 * 
	 * @param word
	 * @return list of books which contain key word in the title
	 */
	public List<Book> searchBook(String word) {
		List<Book> booksList = new ArrayList<>();
		for (Book book : bookstore.values()) {
			if (book.getTitle().contains(word) || book.getTitle().equalsIgnoreCase(word)) {
				booksList.add(book);
			}
		}
		Collections.sort(booksList);
		return booksList;
	}

	/**
	 * Arranging books in the bookstore in alphabetical order.
	 * 
	 * @return list of books in bookstore arranging in alphabetical order
	 */
	public List<Book> getSortedBooks() {
		List<Book> sortedBooks = new ArrayList<>();
		for (Book book : bookstore.values()) {
			sortedBooks.add(book);
		}
		Collections.sort(sortedBooks);
		return sortedBooks;
	}

	/**
	 * Book supply decreases with one.
	 * 
	 * @param book title
	 * @return String
	 * @throws PersistenceHelperSavingException
	 *             if the file in which you are trying to save book information does not exist
	 */
	public String sellBook(String title) throws PersistenceHelperSavingException {
		for (Book book : bookstore.values()) {
			if (book.getTitle().equals(title)) {
				int count = book.getCount();
				if (count <= 0) {
					return null;
				} else {
					count--;
					book.setCount(count);
					save();
				}
			}
		}
		return toString();
	}

	/**
	 * Removes book from bookstore.
	 * 
	 * @param book title
	 * @return book
	 * @throws PersistenceHelperSavingException
	 *             if the file in which you are trying to save book information does not exist
	 */
	public Book removeBook(String title) throws PersistenceHelperSavingException {
		Book book = bookstore.remove(title);
		save();
		return book;
	}

	/**
	 * Saves book information in file.
	 * 
	 * @throws PersistenceHelperSavingException
	 *             if the file in which you are trying to save book information does not exist
	 */
	private void save() throws PersistenceHelperSavingException {
		PersistenceHelper.save(bookstore.values());
	}

}
