package treicco.shared;

import java.util.List;

import treicco.server.Directory;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Directory.class)
public interface DirectoryRequest extends RequestContext {

	Request<DirectoryProxy> findDirectory(String parent, String codeName);

	Request<List<DirectoryProxy>> listDirectories(String parent);

	InstanceRequest<DirectoryProxy, Void> create(String parent, String codeName);

	InstanceRequest<DirectoryProxy, Void> update();

	InstanceRequest<DirectoryProxy, Void> delete();

}
