package treicco.client;

import java.util.Date;

import treicco.client.ui.widgets.TextBox;
import treicco.shared.DirectoryProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DirectoryEditImpl extends Composite implements Editor<DirectoryProxy>, DirectoryView {

	interface DirectoryEditUiBinder extends UiBinder<Widget, DirectoryEditImpl> {
	}

	private static DirectoryEditUiBinder uiBinder = GWT.create(DirectoryEditUiBinder.class);

	DirectoryPresenter presenter;

	@UiField
	TextBox shortName;

	@UiField
	TextBox fullName;

	@UiField
	TextBox location;

	@UiField
	TextBox website;

	DatePicker startDate = new DatePicker();

	DatePicker endDate = new DatePicker();

	public DirectoryEditImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		// values.add(startDate);
		// values.add(endDate);
	}

	public void setPresenter(DirectoryPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("commit_top")
	public void commitChangesTop(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("commit_bottom")
	public void commitChangesBottom(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("cancel_top")
	public void cancelChangesTop(ClickEvent e) {
		presenter.stopEdit();
	}

	@UiHandler("cancel_bottom")
	public void cancelChangesBottom(ClickEvent e) {
		presenter.stopEdit();
	}

	public LeafValueEditor<String> shortName() {
		return shortName.asEditor();
	}

	public LeafValueEditor<String> fullName() {
		return fullName.asEditor();
	}

	public LeafValueEditor<Date> startDate() {
		return startDate.asEditor();
	}

	public LeafValueEditor<Date> endDate() {
		return endDate.asEditor();
	}

	public LeafValueEditor<String> location() {
		return location.asEditor();
	}

	public LeafValueEditor<String> website() {
		return website.asEditor();
	}
}
