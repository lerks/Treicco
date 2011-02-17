package treicco.client.api;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface TaskView extends Editor<TaskProxy>, IsWidget {

	public interface EditorDriver extends RequestFactoryEditorDriver<TaskProxy, TaskView> {
	}

	void setPresenter(TaskPresenter presenter);

	void show(AcceptsOneWidget target);

	LeafValueEditor<String> codeName();

	LeafValueEditor<String> shortName();

	LeafValueEditor<String> fullName();

	LeafValueEditor<String> author();

	LeafValueEditor<String> timeLimit();

	LeafValueEditor<String> memoryLimit();

	LeafValueEditor<String> difficulty();

	LeafValueEditor<String> description();

	LeafValueEditor<List<ImageProxy>> images();

	LeafValueEditor<String> input();

	LeafValueEditor<String> output();

	LeafValueEditor<String> constraints();

	// LeafValueEditor<List<ExampleProxy>> examples();

	LeafValueEditor<String> notes();
}
