package business.usecase;

import java.util.List;

import dataaccess.model.Book;

public interface IGetBook {
	public List<Book> getBookCollection();
}
