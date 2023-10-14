package business.controller;

import java.util.List;

import business.exception.BookNotFoundException;
import business.usecase.*;
import dataaccess.*;
import dataaccess.model.*;
import dataaccess.repository.child.BookRepository;

public class BookController extends BaseController implements ISearchBook, IAddBook, IBookCopy {
	BookController() {
	}

	@Override
	public Book searchBook(String isbn) {
		BookRepository repo = new BookRepository(new DataAccessFacade());
		return repo.searchBook(isbn);
	}

	@Override
	public void addBook(Book book) {
		BookRepository repo = new BookRepository(new DataAccessFacade());
		repo.addBook(book);
	}

	@Override
	public List<Book> getBookCollection() {
		BookRepository repo = new BookRepository(new DataAccessFacade());
		return repo.getBookCollection();
	}


	@Override
	public Book addBookCopy(Book book, int noOfCopies) throws BookNotFoundException {

		BookRepository repo = new BookRepository(new DataAccessFacade());
		return repo.addBookCopy(book,noOfCopies);

	}
}
