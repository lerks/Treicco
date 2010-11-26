package treicco.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface TaskView extends IsWidget {
	void setPresenter (TaskPresenter presenter);
	
	void setShortName (String shortName);
	void setFullName (String fullName);	
}
