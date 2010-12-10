package treicco.client.ui.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TextBox extends Composite implements IsEditor<ValueBoxEditor<String>>, HasValue<String>, HasName, HasText {

	interface TextBoxUiBinder extends UiBinder<Widget, TextBox> {
	}

	private static TextBoxUiBinder uiBinder = GWT.create(TextBoxUiBinder.class);

	@UiField
	Label label;

	@UiField
	com.google.gwt.user.client.ui.TextBox input;

	public TextBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ValueBoxEditor<String> asEditor() {
		return input.asEditor();
	}

	public void setName(String name) {
		input.setName(name);
	}

	public String getName() {
		return input.getName();
	}

	public String getValue() {
		return input.getValue();
	}

	public void setValue(String value) {
		input.setValue(value);
	}

	public void setValue(String value, boolean fireEvents) {
		input.setValue(value, fireEvents);
	}

	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return input.addValueChangeHandler(handler);
	}

	public String getText() {
		return input.getText();
	}

	public void setText(String text) {
		input.setText(text);
	}

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String text) {
		label.setText(text);
	}
}
