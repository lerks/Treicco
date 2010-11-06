package treicco.shared;

import java.util.List;

import treicco.server.Directory;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Directory.class)
public interface DirectoryRequest extends RequestContext {

	Request<DirectoryProxy> findDirectory(String path);

	Request<List<DirectoryProxy>> listChildren(String parent);

	Request<List<TaskProxy>> listTasks(String parent);

	Request<Void> init();

	InstanceRequest<DirectoryProxy, Void> create(String id);

	InstanceRequest<DirectoryProxy, Void> update();

	InstanceRequest<DirectoryProxy, Void> delete();

}
