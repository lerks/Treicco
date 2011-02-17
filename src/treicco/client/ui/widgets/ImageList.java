package treicco.client.ui.widgets;

import java.util.ArrayList;
import java.util.List;

import treicco.client.api.ImageProxy;
import treicco.client.api.TaskPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ImageList extends Composite implements LeafValueEditor<List<ImageProxy>> {

	interface ImageListUiBinder extends UiBinder<Widget, ImageList> {
	}

	private static ImageListUiBinder uiBinder = GWT.create(ImageListUiBinder.class);

	TaskPresenter presenter;

	@UiField
	FlowPanel listPanel;

	@UiField
	DeckPanel deckPanel;

	@UiField
	FormPanel formPanel;

	@UiField
	FlowPanel imagePanel;

	Anchor focused;

	Anchor selected;

	Anchor add;

	@UiField
	TextBox name;

	@UiField
	FileBox file;

	@UiField
	Hidden parent;

	@UiField
	Button submit;

	@UiField
	Image image;

	List<String> urls = new ArrayList<String>();

	public ImageList() {
		initWidget(uiBinder.createAndBindUi(this));

		add = styleAnchor(new Anchor("Add..."));

		add.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setupForm();
				deckPanel.showWidget(0);
			}
		});

		listPanel.add(add);

		selected = add;

		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
				urls.add(event.getResults().split(" ")[0]);
				addAnchorForImage(event.getResults().split(" ")[1]);
			}
		});
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;
	}

	private Anchor styleAnchor(Anchor a) {
		a.setStylePrimaryName("treicco-ImageList_ListItem");

		a.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (focused == selected) {
					selected.removeStyleDependentName("active");
					deckPanel.removeStyleDependentName("active");
					if (listPanel.getWidget(0) == selected)
						listPanel.removeStyleDependentName("top-active");
					if (add == selected)
						listPanel.removeStyleDependentName("bottom-active");
				}
				selected.removeStyleDependentName("select");
				deckPanel.removeStyleDependentName("select");
				if (listPanel.getWidget(0) == selected)
					listPanel.removeStyleDependentName("top-select");
				if (add == selected)
					listPanel.removeStyleDependentName("bottom-select");
				selected = (Anchor) event.getSource();
				selected.addStyleDependentName("select");
				deckPanel.addStyleDependentName("select");
				if (listPanel.getWidget(0) == selected)
					listPanel.addStyleDependentName("top-select");
				if (add == selected)
					listPanel.addStyleDependentName("bottom-select");
				if (focused == selected) {
					selected.addStyleDependentName("active");
					deckPanel.addStyleDependentName("active");
					if (listPanel.getWidget(0) == selected)
						listPanel.addStyleDependentName("top-active");
					if (add == selected)
						listPanel.addStyleDependentName("bottom-active");
				}
			}
		});

		a.addFocusHandler(new FocusHandler() {
			public void onFocus(FocusEvent event) {
				focused = (Anchor) event.getSource();
				focused.addStyleDependentName("focus");
				deckPanel.addStyleDependentName("focus");
				if (selected == focused) {
					focused.addStyleDependentName("active");
					deckPanel.addStyleDependentName("active");
					if (listPanel.getWidget(0) == focused)
						listPanel.addStyleDependentName("top-active");
					if (add == focused)
						listPanel.addStyleDependentName("bottom-active");
				}
			}
		});

		a.addBlurHandler(new BlurHandler() {
			public void onBlur(BlurEvent event) {
				if (focused == selected) {
					focused.removeStyleDependentName("active");
					deckPanel.removeStyleDependentName("active");
					if (listPanel.getWidget(0) == focused)
						listPanel.removeStyleDependentName("top-active");
					if (add == focused)
						listPanel.removeStyleDependentName("bottom-active");
				}
				focused.removeStyleDependentName("focus");
				deckPanel.removeStyleDependentName("focus");
				focused = null;
			}
		});

		a.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				((Anchor) event.getSource()).addStyleDependentName("hover");
				deckPanel.addStyleDependentName("hover");
			}
		});

		a.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				((Anchor) event.getSource()).removeStyleDependentName("hover");
				deckPanel.removeStyleDependentName("hover");
			}
		});

		return a;
	}

	private void addAnchor(Anchor a) {
		listPanel.insert(styleAnchor(a), listPanel.getWidgetIndex(add));
	}

	private void addAnchorForImage(String name) {
		Anchor a = new Anchor(name);

		a.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				image.setUrl(urls.get(listPanel.getWidgetIndex((Widget) event.getSource())));
				deckPanel.showWidget(1);
			}
		});

		addAnchor(a);
	}

	private void clearAnchors() {
		listPanel.clear();
		listPanel.add(add);
	}

	private void setupForm() {
		name.setText("");
		parent.setValue(presenter.getPlace().getId());

		presenter.requestImageUploadUrl(new Receiver<String>() {
			@Override
			public void onSuccess(String response) {
				formPanel.setAction(response);
			}
		});
	}

	public List<ImageProxy> getValue() {
		return null;
	}

	public void setValue(List<ImageProxy> value) {
		urls.clear();

		clearAnchors();

		for (ImageProxy i : value) {
			urls.add(i.getUrl());
			addAnchorForImage(i.getName());
		}

		NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
		DomEvent.fireNativeEvent(event, listPanel.getWidget(0));
	}

	@UiHandler("submit")
	void okClick(ClickEvent e) {
		if (name.getValue() == null || !name.getValue().matches("[a-zA-Z0-9_]{3,}")) {
			GWT.log("Error with \"name\" while submitting image form");
			return;
		}
		if (parent.getValue() == null || !parent.getValue().matches("(/[a-zA-Z0-9_]{3,})+")) {
			GWT.log("Error with \"parent\" while submitting image form");
			return;
		}
		if (file.getFilename() == null || file.getFilename().equals("")) {
			GWT.log("Error with \"file\" while submitting image form");
			return;
		}
		formPanel.submit();
	}
}
