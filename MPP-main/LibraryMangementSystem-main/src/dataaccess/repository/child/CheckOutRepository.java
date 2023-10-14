package dataaccess.repository.child;

import dataaccess.model.BookCopy;
import dataaccess.model.CheckOutRecord;
import dataaccess.model.CheckOutRecordEntry;
import dataaccess.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.repository.BaseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CheckOutRepository extends BaseRepository {

	public CheckOutRepository(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public void checkOutBook(BookCopy bookCopy, LibraryMember member) {

		LocalDate dueDate = LocalDate.now().plusDays(bookCopy.getBook().getMaxCheckoutLength());
		LocalDate checkOutDate = LocalDate.now();

		CheckOutRecordEntry checkOutEntry = new CheckOutRecordEntry(dueDate, checkOutDate, bookCopy);

		HashMap<String, CheckOutRecord> hmCheckOutRecord = dataAccess.readCheckOutRecordsMap();

		CheckOutRecord checkOutRecord;

		if (hmCheckOutRecord.get(member.getMemberId()) != null) {
			checkOutRecord = hmCheckOutRecord.get(member.getMemberId());

			checkOutRecord.addCheckOutRecordEntry(checkOutEntry);
		} else {

			ArrayList<CheckOutRecordEntry> list = new ArrayList<>();
			list.add(checkOutEntry);
			checkOutRecord = new CheckOutRecord(member, list);
		}

		hmCheckOutRecord.put(member.getMemberId(), checkOutRecord);
		dataAccess.saveCheckOutRecord(hmCheckOutRecord);
	}

	public CheckOutRecord getCheckOutRecord(String memberId) {

		HashMap<String, CheckOutRecord> hmCheckOutRecord = dataAccess.readCheckOutRecordsMap();

		return hmCheckOutRecord.get(memberId);

	}

	public HashMap<String, CheckOutRecord> getCheckOutOverdue() {
		HashMap<String, CheckOutRecord> hmCheckOutOverdue = new HashMap<String, CheckOutRecord>();
		HashMap<String, CheckOutRecord> hmCheckOutRecord = dataAccess.readCheckOutRecordsMap();

		Iterator<Map.Entry<String, CheckOutRecord>> iterator = hmCheckOutRecord.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, CheckOutRecord> entryOverdue = iterator.next();

			String key = entryOverdue.getKey();
			CheckOutRecord checkOutRecord = entryOverdue.getValue();

			for (CheckOutRecordEntry e : checkOutRecord.getCheckOutRecordEntries()) {
				if (e.getBookCopy() != null) {					
						hmCheckOutOverdue.put(key, checkOutRecord);				
				}
			}

		}

		return hmCheckOutOverdue;
	}
}
