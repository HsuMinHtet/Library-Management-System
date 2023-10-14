package business.usecase;

import business.exception.BookNotFoundException;
import business.exception.InvalidMemberException;
import business.exception.MemberNotFoundException;
import business.exception.BookCopyNotAvailableException;

public interface ICheckOutBook extends IPrintCheckOutRecord {
	public void checkOutBook(String memberId, String bookId)
            throws BookNotFoundException, MemberNotFoundException, BookCopyNotAvailableException, InvalidMemberException;
}
