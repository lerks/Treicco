package treicco.client.api;

import java.util.List;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Task.class)
public interface TaskRequest extends RequestContext {

	Request<TaskProxy> findTask(String id);

	Request<List<TaskProxy>> findAllTasks(List<String> ids);

	Request<List<TaskProxy>> listTasks(String parent);

	Request<List<List<TaskProxy>>> listAllTasks(List<String> parents);

	InstanceRequest<TaskProxy, Void> create(String parent, String codeName);

	InstanceRequest<TaskProxy, Void> update();

	InstanceRequest<TaskProxy, Void> delete();

}
