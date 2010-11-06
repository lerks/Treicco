package treicco.shared;

import com.google.gwt.requestfactory.shared.RequestFactory;

public interface CompetitionRequestFactory extends RequestFactory {

	DirectoryRequest directoryRequest();

	TaskRequest taskRequest();

}