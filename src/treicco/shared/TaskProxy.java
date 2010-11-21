package treicco.shared;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Task.class)
public interface TaskProxy extends EntityProxy {

	public String getParent();

	public String getCodeName();

	public String getShortName();

	public void setShortName(String shortName);

	public String getFullName();

	public void setFullName(String fullName);

	public String getDescription();

	public void setDescription(String description);

}
