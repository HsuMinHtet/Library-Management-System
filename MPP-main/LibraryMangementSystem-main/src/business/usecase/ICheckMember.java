package business.usecase;

import business.exception.InvalidMemberException;
import dataaccess.model.LibraryMember;

public interface ICheckMember {
	public boolean checkMember(String memberId) throws InvalidMemberException;

	public LibraryMember getMember(String memberId) throws InvalidMemberException;
}
