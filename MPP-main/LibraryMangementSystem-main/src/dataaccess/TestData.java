package dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataaccess.model.*;

/**
 * This class loads data into the data repository and also sets up the dataaccess.storage
 * units that are used in the application. The main method in this class must be
 * run once (and only once) before the rest of the application can work
 * properly. It will create three serialized objects in the dataaccess.dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData {
	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		td.userData();

		td.authorData();

		td.checkOutRecordData();

		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
	}

	/// create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);

	}

	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}

	public void authorData() {
		DataAccessFacade.loadAuthorMap(allAuthors);
	}

	public void checkOutRecordData() {
		@SuppressWarnings("serial")
		LibraryMember libraryMember = new LibraryMember("1001", "Andrew", "Smiths", "444-333-2211", addresses.get(4));

		CheckOutRecordEntry entry = new CheckOutRecordEntry(LocalDate.now(), LocalDate.now(),
				allBooks.get(0).getCopy(0));
		CheckOutRecordEntry entry1 = new CheckOutRecordEntry(LocalDate.now(), LocalDate.now(),
				allBooks.get(0).getCopy(0));

		List<CheckOutRecordEntry> list = new ArrayList<CheckOutRecordEntry>();
		list.add(entry);
		list.add(entry1);
		@SuppressWarnings("serial")
		CheckOutRecord checkOutRecord = new CheckOutRecord(libraryMember, list);

		DataAccessFacade.loadCheckOutRecordMap(checkOutRecord);
	}

	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("0000", "John", "Smith", "111-222-3333", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("0001", "Henry", "Lewis", "123-456-7890", addresses.get(5));
		members.add(libraryMember);
		libraryMember = new LibraryMember("0002", "Tom", "Arthur", "123-456-7891", addresses.get(3));
		members.add(libraryMember);
		libraryMember = new LibraryMember("0003", "Miss", "Bradley", "123-456-7892", addresses.get(2));
		members.add(libraryMember);

		DataAccessFacade.loadMemberMap(members);
	}

	///////////// DATA //////////////
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")

	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("1000N 4th Street", "Fairfield", "IA", "52556"));
			add(new Address("1000N 4th Street", "MIU", "MI", "65434"));
			add(new Address("1000N 4th Street", "Palo Alto", "CA", "94301"));
			add(new Address("1000N 4th Street", "Seville", "Georgia", "41234"));
			add(new Address("1000N 4th Street", "Fairfield", "IA", "52556"));
			add(new Address("1000N 4th Street", "Los Angeles", "CA", "93736"));
			add(new Address("1000N 4th Street", "Ottumwa", "IA", "57789"));
		}
	};

	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("A001", "George", "Orwell", "999-999-9999", addresses.get(0), "Burmese Days"));
			add(new Author("A002", "Dan", "Brown", "999-999-9999", addresses.get(0), "Inferno"));
			add(new Author("A003", "Kim", "Ji Won", "999-999-9999", addresses.get(1), "Duty After School"));
			add(new Author("A004", "Haruki", "Murakami", "999-999-9999", addresses.get(2), "Norwegian Wood"));
			add(new Author("A005", "J.R.R", "Tolkien", "999-999-9999", addresses.get(3), "The Lord of the Rings"));
		//	add(new Author("A006", "P.Q.R", "Kim Jone", "999-999-9999", addresses.get(1), "The Sun set"));
		}
	};

	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11111", "Burmese Days", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12345", "Inferno", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22222", "Norwegian Wood", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-44444", "The Lord of the Rings", 7, Arrays.asList(allAuthors.get(1))));
		}
	};

	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("Librarian", "librarian", Auth.LIBRARIAN));
			add(new User("Admin", "admin", Auth.ADMIN));
			add(new User("Super", "super", Auth.BOTH));
		}
	};

}
