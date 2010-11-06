package treicco.shared;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Task.class)
public interface TaskProxy extends EntityProxy {

	public String getId();

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

}
