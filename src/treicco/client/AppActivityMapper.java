package treicco.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	public Activity getActivity(Place place) {
		if (place instanceof DirectoryPlace)
			return new DirectoryActivity((DirectoryPlace) place, clientFactory);
		else
			return new TaskActivity((TaskPlace) place, clientFactory);
	}
}
