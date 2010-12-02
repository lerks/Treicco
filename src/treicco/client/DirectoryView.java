package treicco.client;

import java.util.Date;

import treicco.shared.DirectoryProxy;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.user.client.ui.IsWidget;

public interface DirectoryView extends IsWidget, Editor<DirectoryProxy> {

	public interface EditorDriver extends RequestFactoryEditorDriver<DirectoryProxy, DirectoryView> {
	}

	void setPresenter(DirectoryPresenter presenter);

	LeafValueEditor<String> shortName();

	LeafValueEditor<String> fullName();

	LeafValueEditor<Date> startDate();

	LeafValueEditor<Date> endDate();

	LeafValueEditor<String> location();

	LeafValueEditor<String> website();
}
