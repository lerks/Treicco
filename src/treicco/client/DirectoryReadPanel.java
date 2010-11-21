package treicco.client;

import treicco.shared.CompetitionSyntax;
import treicco.shared.DirectoryProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryReadPanel extends Composite {

	private static DirectoryReadPanelUiBinder uiBinder = GWT.create(DirectoryReadPanelUiBinder.class);

	interface DirectoryReadPanelUiBinder extends UiBinder<Widget, DirectoryReadPanel> {
	}

	@UiField
	Label title;

	@UiField
	Label date;

	@UiField
	Label location;

	@UiField
	Label website;

	public DirectoryReadPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		setPath(History.getToken());
	}

	public void setPath(String targetPath) {
		if (targetPath.equals("/"))
			return;

		Treicco.requestFactory.directoryRequest().findDirectory(CompetitionSyntax.extractParent(targetPath), CompetitionSyntax.extractCodeName(targetPath)).fire(new Receiver<DirectoryProxy>() {
			public void onSuccess(DirectoryProxy response) {
				title.setText(response.getFullName());

				if (response.getStartDate() != null && response.getEndDate() != null) {
					date.setText(response.getStartDate().toString() + " to " + response.getEndDate().toString());
				}

				if (response.getLocation() != null && !response.getLocation().equals("")) {
					location.setText(response.getLocation());
				}

				if (response.getWebsite() != null) {
					location.setText(response.getWebsite());
				}
			}
		});
	}
}
