package treicco.shared;

import java.util.List;

import treicco.server.Directory;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Directory.class)
public interface DirectoryRequest extends RequestContext {

	Request<DirectoryProxy> findDirectory(String id);
	
	Request<List<DirectoryProxy>> findAllDirectories(List<String> ids);

	Request<List<DirectoryProxy>> listDirectories(String parent);

	Request<List<List<DirectoryProxy>>> listAllDirectories(List<String> parents);

	InstanceRequest<DirectoryProxy, Void> create(String parent, String codeName);

	InstanceRequest<DirectoryProxy, Void> update();

	InstanceRequest<DirectoryProxy, Void> delete();

}
