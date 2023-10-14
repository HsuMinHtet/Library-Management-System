package business.usecase;

import java.util.List;

import dataaccess.model.Book;

public interface ISearchBook {
	public Book searchBook(String isbn);

	public List<Book> getBookCollection();
}
