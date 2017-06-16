package project.bookstore.model;

import java.util.*;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class BookstoreTableModel extends AbstractTableModel {

	private List<Book> booksList;
	private boolean hasCellEditable;

	public List<Book> getBooksList() {
		return this.booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
		fireTableDataChanged();
	}

	public boolean getCellEditable() {
		return this.hasCellEditable;
	}

	public void setCellEditable(boolean hasCellEditable) {
		this.hasCellEditable = hasCellEditable;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return this.hasCellEditable;
	}

	@Override
	public int getRowCount() {
		return booksList == null ? 0 : booksList.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = booksList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return book.getId();
		case 1:
			return book.getTitle();
		case 2:
			return book.getAuthor();
		case 3:
			return book.getNamePublisher();
		case 4:
			return book.getLiterature();
		case 5:
			return book.getPrice();
		case 6:
			return book.getCount();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private final Class[] columnClass = new Class[] { String.class, String.class, String.class, String.class,
			String.class, Double.class, Integer.class };

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Book book = booksList.get(rowIndex);
		if (columnIndex == 1) {
			book.setTitle((String) aValue);
		} else if (columnIndex == 2) {
			book.setAuthor((String) aValue);
		} else if (columnIndex == 3) {
			book.setNamePublisher((String) aValue);
		} else if (columnIndex == 4) {
			book.setLiterature((String) aValue);
		} else if (columnIndex == 5) {
			book.setPrice((Double) aValue);
		} else if (columnIndex == 6) {
			book.setCount((Integer) aValue);
		}
	}

	@Override
	public String getColumnName(int index) {
		final String[] columns = { "ID", "Title", "Author", "Name Publisher", "Literature", "Price", "Count" };
		return columns[index];
	}

}
