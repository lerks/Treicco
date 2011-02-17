package treicco.client.api;

import java.util.Date;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface DirectoryView extends Editor<DirectoryProxy>, IsWidget {

	public interface EditorDriver extends RequestFactoryEditorDriver<DirectoryProxy, DirectoryView> {
	}

	void setPresenter(DirectoryPresenter presenter);

	void show(AcceptsOneWidget target);

	LeafValueEditor<String> codeName();

	LeafValueEditor<String> shortName();

	LeafValueEditor<String> fullName();

	LeafValueEditor<Date> startDate();

	LeafValueEditor<Date> endDate();

	LeafValueEditor<String> location();

	LeafValueEditor<String> website();
}
