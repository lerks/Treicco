package treicco.client.ui.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;

public class Button extends com.google.gwt.user.client.ui.Button {

	private void styleButton() {
		setStylePrimaryName("treicco-Button");
	}

	public Button() {
		super();
		styleButton();
	}

	public Button(Element element) {
		super(element);
		styleButton();
	}

	public Button(String html, ClickHandler handler) {
		super(html, handler);
		styleButton();
	}

	public Button(String html) {
		super(html);
		styleButton();
	}

	public Button(SafeHtml html, ClickHandler handler) {
		super(html, handler);
		styleButton();
	}

	public Button(SafeHtml html) {
		super(html);
		styleButton();
	}
}
