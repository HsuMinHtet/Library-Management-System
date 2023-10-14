package business.usecase;

import business.exception.InvalidMemberException;
import dataaccess.model.LibraryMember;

public interface IUpdateLibraryMember {

	public LibraryMember searchMember(String memberId);

	public void updateLibraryMember(LibraryMember member) throws InvalidMemberException;

}
