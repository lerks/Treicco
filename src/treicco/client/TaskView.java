package treicco.client;

import java.util.List;

import treicco.shared.ImageProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.user.client.ui.IsWidget;

public interface TaskView extends IsWidget, Editor<TaskProxy> {

	public interface EditorDriver extends RequestFactoryEditorDriver<TaskProxy, TaskView> {
	}

	void setPresenter(TaskPresenter presenter);

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

//	LeafValueEditor<List<ExampleProxy>> examples();

	LeafValueEditor<String> notes();
}
