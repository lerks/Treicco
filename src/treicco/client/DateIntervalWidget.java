package treicco.client;

import java.util.Date;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DateIntervalWidget implements IsWidget {

	Date begin;

	Date end;

	Label widget = new Label();

	public LeafValueEditor<Date> getBegin() {
		return new LeafValueEditor<Date>() {
			public Date getValue() {
				return begin;
			}

			public void setValue(Date value) {
				begin = value;
				update();
			}
		};
	}

	public LeafValueEditor<Date> getEnd() {
		return new LeafValueEditor<Date>() {
			public Date getValue() {
				return end;
			}

			public void setValue(Date value) {
				end = value;
				update();
			}
		};
	}

	public void update() {
		if (begin == null && end == null) {
			widget.setText("NO DATES");
		} else if (begin == null) {
			widget.setText("→ " + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(end));
		} else if (end == null) {
			widget.setText(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(begin) + " →");
		} else {
			widget.setText(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(begin) + " - " + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(end));
		}
	}

	public Widget asWidget() {
		return widget;
	}
}
