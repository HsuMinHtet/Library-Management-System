package librarysystem;

import javax.swing.JFrame;

import dataaccess.Auth;

import java.awt.*;

public class UIController extends JFrame implements LibWindow {

	public final static UIController INSTANCE = new UIController();

	private boolean isInitialized = false;

	private static LibWindow[] allWindows = { 
			UIController.INSTANCE,
			AddMemberWindow.INSTANCE, 
			BookWindow.INSTANCE, 
			BookCopyWindow.INSTANCE,
			CheckoutBookWindow.INSTANCE,
			PrintCheckOutRecordWindow.INSTANCE,
			CheckOverdueWindow.INSTANCE,
			AddAuthorWindow.INSTANCE,
			EditMemberWindow.INSTANCE,
			MainView.INSTANCE,
			MainLogin.INSTANCE};

	public static void hideAllWindows() {

		for (LibWindow frame : allWindows) {
			frame.setVisible(false);
		}
	}

	private UIController() {
	}

	public void init() {
		UIController.hideAllWindows();
		MainLogin.INSTANCE.init();
		Util.centerFrameOnDesktop(MainLogin.INSTANCE);
		MainLogin.INSTANCE.setVisible(true);
	}


	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}
	
	public void doAuthentication(Auth auth) {
		if (auth == Auth.ADMIN) {
			MainView.doAdminAuthentication();
		} else if (auth == Auth.LIBRARIAN) {
			MainView.doLibrarianAuthentication();
		} else if (auth == Auth.BOTH) {
			MainView.permitAll();
		}
	}
	
	

}
