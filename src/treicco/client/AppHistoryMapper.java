package treicco.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class AppHistoryMapper implements PlaceHistoryMapper {

	public Place getPlace(String token) {
		if (token.equals("/"))
			return DirectoryPlace.ROOT;
		if (token.endsWith("/"))
			return new DirectoryPlace(token);
		else
			return new TaskPlace(token);
	}

	public String getToken(Place place) {
		if (place instanceof DirectoryPlace) {
			return ((DirectoryPlace) place).getId();
		} else {
			return ((TaskPlace) place).getId();
		}
	}
}
