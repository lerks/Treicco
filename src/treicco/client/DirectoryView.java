package treicco.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface DirectoryView extends IsWidget {
	void setPresenter (DirectoryPresenter presenter);
	
	void setShortName (String shortName);
	void setFullName (String fullName);
	void setDate (String date);
	void setLocation (String location);
	void setWebsite (String website);
}
