package treicco.client.ui.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FileBox extends Composite implements HasName {

	interface FileBoxUiBinder extends UiBinder<Widget, FileBox> {
	}

	private static FileBoxUiBinder uiBinder = GWT.create(FileBoxUiBinder.class);

	@UiField
	Label label;

	@UiField
	FileUpload input;

	public FileBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setName(String name) {
		input.setName(name);
	}

	public String getName() {
		return input.getName();
	}

	public String getFilename() {
		return input.getFilename();
	}

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String text) {
		label.setText(text);
	}
}
