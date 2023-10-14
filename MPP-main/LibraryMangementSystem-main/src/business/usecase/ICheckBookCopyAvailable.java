package business.usecase;

import dataaccess.model.Book;

public interface ICheckBookCopyAvailable {
	public Book checkBookAvailableCopy(String bookId);
}
