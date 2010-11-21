package treicco.shared;

import java.util.List;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Task.class)
public interface TaskRequest extends RequestContext {

	Request<TaskProxy> findTask(String parent, String codeName);

	Request<List<TaskProxy>> listTasks(String parent);

	InstanceRequest<TaskProxy, Void> create(String parent, String codeName);

	InstanceRequest<TaskProxy, Void> update();

	InstanceRequest<TaskProxy, Void> delete();

}
