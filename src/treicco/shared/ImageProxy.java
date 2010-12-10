package treicco.shared;

import treicco.server.Image;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Image.class)
public interface ImageProxy extends EntityProxy {

	public String getName();

	public String getUrl();
}
