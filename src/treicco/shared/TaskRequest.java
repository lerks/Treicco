package treicco.shared;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Task.class)
public interface TaskRequest extends RequestContext {

	Request<TaskProxy> findTask(String path);

	InstanceRequest<TaskProxy, Void> create(String id);

	InstanceRequest<TaskProxy, Void> update();

	InstanceRequest<TaskProxy, Void> delete();

}
