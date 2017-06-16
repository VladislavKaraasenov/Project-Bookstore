package project.bookstore.helpers;

import java.io.*;
import java.util.*;
import project.bookstore.model.*;
import project.bookstore.exceptions.*;

public class PersistenceHelper {

	private static final String SEPARATOR = ",";
	private static final String FILE_INPUT = "Bookstore/Bookstore.csv";
	private static final String FILE_OUTPUT = "Bookstore/Bookstore.csv";

	/**
	 * Reads file and adds data in collection of books.
	 * 
	 * @return collection of books
	 * @throws PersistenceHelperLoadException
	 *             if file you are trying to open does not exist
	 */
	public static Collection<Book> load() throws PersistenceHelperLoadingException {
		List<Book> booksList = new ArrayList<>();
		
		try (Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(FILE_INPUT)))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Book book = createBook(line);
				booksList.add(book);
			}
		} catch (FileNotFoundException e) {
			throw new PersistenceHelperLoadingException();
		}
		return booksList;
	}

	/**
	 * Saves collection of books in file.
	 * 
	 * @param collection of books
	 * @throws PersistenceHelperSaveException
	 *             if the file in which you are trying to save books information does not exist          
	 */
	public static void save(Collection<Book> books) throws PersistenceHelperSavingException {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_OUTPUT)))) {
			
			for (Book book : books) {
				String newString = createString(book);
				writer.println(newString);
			}
		} catch (IOException e) {
			throw new PersistenceHelperSavingException();
		}
	}

	/**
	 * Creates book from string.
	 * 
	 * @param line from file
	 * @return book
	 */
	private static final Book createBook(String line) {
		String[] elements = line.split(SEPARATOR);

		return new Book(elements[1], elements[2], elements[3], elements[4], Double.parseDouble(elements[5]),
				Integer.parseInt(elements[6]));
	}

	/**
	 * Creates string from book.
	 * 
	 * @param book
	 * @return String
	 */
	private static final String createString(Book book) {
		StringBuilder builder = new StringBuilder();
		builder.append(book.getId());
		builder.append(SEPARATOR);
		builder.append(book.getTitle());
		builder.append(SEPARATOR);
		builder.append(book.getAuthor());
		builder.append(SEPARATOR);
		builder.append(book.getNamePublisher());
		builder.append(SEPARATOR);
		builder.append(book.getLiterature());
		builder.append(SEPARATOR);
		builder.append(book.getPrice());
		builder.append(SEPARATOR);
		builder.append(book.getCount());

		return builder.toString();
	}
}
