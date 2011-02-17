package treicco.client.api;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainView extends IsWidget {

	void setPresenter(MainPresenter presenter);

	AcceptsOneWidget getNavigationPanel();

	AcceptsOneWidget getCreationPanel();

	AcceptsOneWidget getRootPanel();
}
