package treicco.client.ui;

import java.util.Date;

import treicco.client.DateIntervalWidget;
import treicco.client.api.DirectoryPresenter;
import treicco.client.api.DirectoryView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryDisplayImpl extends Composite implements DirectoryView {

	interface DirectoryViewUiBinder extends UiBinder<Widget, DirectoryDisplayImpl> {
	}

	private static DirectoryViewUiBinder uiBinder = GWT.create(DirectoryViewUiBinder.class);

	DirectoryPresenter presenter;

	@UiField
	Label fullName;

	// @UiField
	// Label date;

	@UiField
	Label location;

	@UiField
	Label website;

	DateIntervalWidget interval = new DateIntervalWidget();

	public DirectoryDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		// values.add(interval);
	}

	public void setPresenter(DirectoryPresenter presenter) {
		this.presenter = presenter;
	}

	public void show(AcceptsOneWidget target) {
		target.setWidget(this);
	}

	public LeafValueEditor<String> codeName() {
		return null;
	}

	public LeafValueEditor<String> shortName() {
		return null;
	}

	public LeafValueEditor<String> fullName() {
		return fullName.asEditor();
	}

	public LeafValueEditor<Date> startDate() {
		return interval.getBegin();
	}

	public LeafValueEditor<Date> endDate() {
		return interval.getEnd();
	}

	public LeafValueEditor<String> location() {
		return location.asEditor();
	}

	public LeafValueEditor<String> website() {
		return website.asEditor();
	}

	@UiHandler("edit")
	void cancelClick(ClickEvent e) {
		presenter.toUpdate();
	}
}
