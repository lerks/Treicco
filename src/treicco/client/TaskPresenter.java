package treicco.client;

import com.google.gwt.requestfactory.shared.Receiver;

public interface TaskPresenter {

	public void startEdit();

	public void stopEdit();

	public void commitEdit();

	public TaskPlace getPlace();

	public void requestImageUploadUrl(Receiver<String> receiver);
}
