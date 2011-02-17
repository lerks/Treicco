package treicco.client;

import treicco.client.place.DirectoryDisplayPlace;
import treicco.client.place.DirectoryPlace;
import treicco.client.place.TaskDisplayPlace;
import treicco.client.place.TaskPlace;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class AppHistoryMapper implements PlaceHistoryMapper {

	public Place getPlace(String token) {
		if (token.equals("/"))
			return DirectoryPlace.ROOT;
		if (token.endsWith("/"))
			return new DirectoryDisplayPlace(token);
		else
			return new TaskDisplayPlace(token);
	}

	public String getToken(Place place) {
		if (place instanceof DirectoryPlace) {
			return ((DirectoryPlace) place).getId();
		} else {
			return ((TaskPlace) place).getId();
		}
	}
}
