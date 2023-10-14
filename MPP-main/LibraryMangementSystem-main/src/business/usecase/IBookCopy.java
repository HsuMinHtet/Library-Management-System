package business.usecase;

import dataaccess.model.Book;
import business.exception.BookNotFoundException;

public interface IBookCopy {
	public Book addBookCopy(Book book, int noOfCopies) throws BookNotFoundException;
}
