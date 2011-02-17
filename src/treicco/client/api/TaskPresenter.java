package treicco.client.api;

import treicco.client.place.TaskPlace;

import com.google.gwt.requestfactory.shared.Receiver;

public interface TaskPresenter {

	public void commit();

	public void toDisplay();

	public void toUpdate();

	// TODO: move to ImagePresenter
	public TaskPlace getPlace();

	// TODO: move to ImagePresenter
	public void requestImageUploadUrl(Receiver<String> receiver);
}
