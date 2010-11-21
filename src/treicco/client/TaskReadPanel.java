package treicco.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class TaskReadPanel extends ResizeComposite {
	Label l = new Label("Task");

	public TaskReadPanel() {
		LayoutPanel lp = new LayoutPanel();

		lp.addStyleName("TaskReadPanel");
		lp.add(l);

		initWidget(lp);
	}

	public void setPath(String path) {
		l.setText(path);
	}
}
