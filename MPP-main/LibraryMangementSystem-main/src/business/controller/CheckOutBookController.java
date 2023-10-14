package business.controller;

import business.exception.InvalidMemberException;
import business.usecase.ICheckOutBook;
import dataaccess.DataAccessFacade;
import dataaccess.model.Book;
import dataaccess.model.CheckOutRecord;
import dataaccess.model.LibraryMember;

import java.time.LocalDate;
import java.util.HashMap;

import business.exception.BookCopyNotAvailableException;
import business.exception.BookNotFoundException;
import business.exception.MemberNotFoundException;
import dataaccess.repository.child.BookRepository;
import dataaccess.repository.child.CheckOutRepository;
import dataaccess.repository.child.LibraryMemberRepository;

public class CheckOutBookController extends BaseController implements ICheckOutBook {


	@Override
	public void checkOutBook(String memberId, String bookId)
			throws BookNotFoundException, MemberNotFoundException, BookCopyNotAvailableException, InvalidMemberException {

		BookRepository bookRepo = new BookRepository(new DataAccessFacade());
		LibraryMemberRepository libmemRepo = new LibraryMemberRepository(new DataAccessFacade());

		Book book = bookRepo.searchBook(bookId);

		if (book == null) {
			throw new BookNotFoundException("Book not found");
		}


		if (!libmemRepo.checkMember(memberId)) {
			throw new MemberNotFoundException("Member not found");
		}

		LibraryMember member = libmemRepo.getMember(memberId);

		bookRepo.checkOutBook(book,member);


	}

	@Override
	public CheckOutRecord getCheckOutRecord(String memberId) {
		CheckOutRepository checkoutRepo = new CheckOutRepository(new DataAccessFacade());
		return checkoutRepo.getCheckOutRecord(memberId);
	}

	@Override
	public  HashMap<String, CheckOutRecord> getCheckOutOverdue() {
		
		CheckOutRepository checkoutRepo = new CheckOutRepository(new DataAccessFacade());
		return checkoutRepo.getCheckOutOverdue();
	}

	
}
