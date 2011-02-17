package treicco.client.ui;

import treicco.client.api.MainPresenter;
import treicco.client.api.MainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainViewImpl extends Composite implements MainView {

	interface MainViewUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

	MainPresenter presenter;

	@UiField
	SimplePanel navigationPanel;

	@UiField
	SimplePanel creationPanel;

	@UiField
	SimplePanel rootPanel;

	int scroll = 0;

	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		Window.addResizeHandler(new ResizeHandler() {
			public void onResize(ResizeEvent event) {
				if (rootPanel.getWidget() != null) {
					rootPanel.getWidget().getElement().getStyle().setPropertyPx("minHeight", event.getHeight() - 110);
				}
			}
		});

		Window.addWindowScrollHandler(new ScrollHandler() {
			public void onWindowScroll(ScrollEvent event) {
				if (navigationPanel.getWidget() != null) {
					if (event.getScrollTop() > 100 && scroll <= 100) {
						navigationPanel.getWidget().getElement().getStyle().setPosition(Position.FIXED);
					} else if (event.getScrollTop() <= 100 && scroll > 100) {
						navigationPanel.getWidget().getElement().getStyle().setPosition(Position.ABSOLUTE);
					}
				}
				scroll = event.getScrollTop();
			}
		});
	}

	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	public AcceptsOneWidget getNavigationPanel() {
		return new AcceptsOneWidget() {
			public void setWidget(IsWidget w) {
				if (w != null) {
					if (Window.getScrollTop() > 100) {
						w.asWidget().getElement().getStyle().setPosition(Position.FIXED);
					} else {
						w.asWidget().getElement().getStyle().setPosition(Position.ABSOLUTE);
					}
				}
				navigationPanel.setWidget(w);
			}
		};
	}

	public AcceptsOneWidget getCreationPanel() {
		return creationPanel;
	}

	public AcceptsOneWidget getRootPanel() {
		return new AcceptsOneWidget() {
			public void setWidget(IsWidget w) {
				if (w != null) {
					w.asWidget().getElement().getStyle().setPropertyPx("minHeight", Window.getClientHeight() - 110);
				}
				rootPanel.setWidget(w);
			}
		};
	}
}
