package business.controller;

import business.usecase.IAddLibraryMember;
import business.usecase.ICheckMember;
import business.usecase.IUpdateLibraryMember;
import dataaccess.DataAccessFacade;
import dataaccess.model.LibraryMember;
import business.exception.InvalidMemberException;
import dataaccess.repository.child.LibraryMemberRepository;

public class LibraryMemberController extends BaseController implements IAddLibraryMember, IUpdateLibraryMember, ICheckMember {
	LibraryMemberController() {
	}

	@Override
	public void addLibraryMember(LibraryMember member) throws InvalidMemberException {
		LibraryMemberRepository libMemRepo = new LibraryMemberRepository(new DataAccessFacade());
		libMemRepo.addLibraryMember(member);
	}

	@Override
	public boolean checkMember(String memberId) throws InvalidMemberException {

		LibraryMemberRepository libMemRepo = new LibraryMemberRepository(new DataAccessFacade());

		return libMemRepo.checkMember(memberId);


	}

	@Override
	public LibraryMember getMember(String memberId) throws InvalidMemberException{
		LibraryMemberRepository libMemRepo = new LibraryMemberRepository(new DataAccessFacade());
		return libMemRepo.getMember(memberId);

	}

	@Override
	public LibraryMember searchMember(String memberId) {
		LibraryMemberRepository libMemRepo = new LibraryMemberRepository(new DataAccessFacade());
		return libMemRepo.getMember(memberId);
	}

	@Override
	public void updateLibraryMember(LibraryMember member) throws InvalidMemberException {
		LibraryMemberRepository libMemRepo = new LibraryMemberRepository(new DataAccessFacade());
		libMemRepo.updateLibraryMember(member);
	}

}
