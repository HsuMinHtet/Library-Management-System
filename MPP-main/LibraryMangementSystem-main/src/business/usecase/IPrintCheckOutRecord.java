package business.usecase;

import java.util.HashMap;

import dataaccess.model.CheckOutRecord;

public interface IPrintCheckOutRecord {
	public CheckOutRecord getCheckOutRecord(String memberId);
	public  HashMap<String, CheckOutRecord> getCheckOutOverdue();
}
