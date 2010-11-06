package treicco.shared;

import java.util.Date;

import treicco.server.Directory;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Directory.class)
public interface DirectoryProxy extends EntityProxy {

	public String getId();

	public String getName();

	public void setName(String name);

	public String getFullName();

	public void setFullName(String fullname);

	public Date getStartDate();

	public void setStartDate(Date startDate);

	public Date getEndDate();

	public void setEndDate(Date endDate);

	public String getLocation();

	public void setLocation(String location);

	public String getWebsite();

	public void setWebsite(String website);

}
