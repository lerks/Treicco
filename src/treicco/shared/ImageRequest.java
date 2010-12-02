package treicco.shared;

import treicco.server.Image;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Image.class)
public interface ImageRequest extends RequestContext {

	Request<String> createUploadURL();

	InstanceRequest<ImageProxy, Void> delete();
}
