package project.bookstore.model;

import java.util.UUID;

/**
 * There is information about a book in the bookstore.
 *
 * @author Vladislav Karaasenov
 */

public class Book implements Comparable<Book> {

	private String title;
	private String author;
	private String namePublisher;
	private String literature;
	private double price;
	private int count;
	private String id;

	public Book(String title, String author, String namePublisher, String literature, double price, int count) {
		this.title = title;
		this.author = author;
		this.namePublisher = namePublisher;
		this.literature = literature;
		this.price = price;
		this.count = count;
		this.id = UUID.randomUUID().toString();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNamePublisher() {
		return this.namePublisher;
	}

	public void setNamePublisher(String namePublisher) {
		this.namePublisher = namePublisher;
	}

	public String getLiterature() {
		return this.literature;
	}

	public void setLiterature(String literature) {
		this.literature = literature;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id + ", " + this.title + ", " + this.author + ", " + this.namePublisher + ", " + this.literature
				+ ", " + this.price + ", " + this.count + ".";
	}

	@Override
	public int compareTo(Book o) {
		return this.title.compareTo(o.title);
	}

}
