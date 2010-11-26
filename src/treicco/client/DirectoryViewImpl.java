package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryViewImpl extends Composite implements DirectoryView {
	private static DirectoryViewUiBinder uiBinder = GWT.create(DirectoryViewUiBinder.class);

	interface DirectoryViewUiBinder extends UiBinder<Widget, DirectoryViewImpl> {
	}

	DirectoryPresenter presenter;

	@UiField
	Label fullName;

	@UiField
	Label date;

	@UiField
	Label location;

	@UiField
	Label website;

	public DirectoryViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(DirectoryPresenter presenter) {
		this.presenter = presenter;
	}

	public void setShortName(String shortName) {
		// Nothing...
	}

	public void setFullName(String fullName) {
		this.fullName.setText(fullName);
	}

	public void setDate(String date) {
		this.date.setText(date);
	}

	public void setLocation(String location) {
		this.location.setText(location);
	}

	public void setWebsite(String website) {
		this.website.setText(website);
	}
}
