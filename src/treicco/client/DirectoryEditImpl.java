package treicco.client;

import java.util.Date;

import treicco.shared.DirectoryProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DirectoryEditImpl extends Composite implements Editor<DirectoryProxy>, DirectoryView {

	interface DirectoryEditUiBinder extends UiBinder<Widget, DirectoryEditImpl> {
	}

	private static DirectoryEditUiBinder uiBinder = GWT.create(DirectoryEditUiBinder.class);

	DirectoryPresenter presenter;

	@UiField
	FlowPanel values;

	@UiField
	ValueBoxEditorDecorator<String> shortName;

	@UiField
	ValueBoxEditorDecorator<String> fullName;

	@UiField
	ValueBoxEditorDecorator<String> location;

	@UiField
	ValueBoxEditorDecorator<String> website;
	
	DatePicker startDate = new DatePicker();

	DatePicker endDate = new DatePicker();

	public DirectoryEditImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		values.add(startDate);
		values.add(endDate);
	}

	public void setPresenter(DirectoryPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("commit")
	public void commitChanges(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("cancel")
	public void cancelChanges(ClickEvent e) {
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
