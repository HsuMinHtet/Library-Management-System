package dataaccess.repository.child;

import business.exception.InvalidMemberException;
import dataaccess.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.repository.BaseRepository;

import java.util.HashMap;

public class LibraryMemberRepository extends BaseRepository {

    public LibraryMemberRepository(DataAccess da) {
        this.dataAccess = da;
    }

    public boolean checkMember(String memberId) throws InvalidMemberException{
        if (getMember(memberId) != null)
            return true;

        return false;

    }

    public LibraryMember getMember(String memberId) {
        HashMap<String, LibraryMember> map = dataAccess.readMemberMap();
        return map.get(memberId);
    }

    public void addLibraryMember(LibraryMember member) throws InvalidMemberException {


        if (member == null) {
            throw new InvalidMemberException("Member is null");
        }

        if (member.getMemberId().isEmpty()) {
            throw new InvalidMemberException("Invalid Member Id");
        }

        if (member.getFirstName().isEmpty()) {
            throw new InvalidMemberException("Invalid First Name");
        }

        if (member.getLastName().isEmpty()) {
            throw new InvalidMemberException("Invalid Last Name");
        }

        if (checkMember(member.getMemberId())) {
            throw new InvalidMemberException("Member ID already exist");
        }

        dataAccess.saveNewMember(member);

    }

    public void updateLibraryMember(LibraryMember member) throws InvalidMemberException {


        if (member == null) {
            throw new InvalidMemberException("Member is null");
        }

        if (member.getMemberId().isEmpty()) {
            throw new InvalidMemberException("Invalid Member Id");
        }

        if (member.getFirstName().isEmpty()) {
            throw new InvalidMemberException("Invalid First Name");
        }

        if (member.getLastName().isEmpty()) {
            throw new InvalidMemberException("Invalid Last Name");
        }

        dataAccess.updateMember(member);

    }


    public void editLibraryMember(LibraryMember member) throws InvalidMemberException {

        //Implement Code Here
    }

}
