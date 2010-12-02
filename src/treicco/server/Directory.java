package treicco.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.appengine.api.datastore.Link;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Entity
public class Directory {
	@Id
	// @NotNull
	// @Pattern(regexp = "(/[a-zA-Z0-9_]{3,})+/")
	private String id;

	// @NotNull
	// @Pattern(regexp = "(/[a-zA-Z0-9_]{3,})+/")
	private String parent;

	// @NotNull
	// @Pattern(regexp = "[a-zA-Z0-9\\_]{3,}")
	private String codeName;

	@NotNull
	@Size(min = 3)
	private String shortName;

	@NotNull
	@Size(min = 3)
	private String fullName;

	private Date startDate;

	private Date endDate;

	private String location;

	private Link website;

	static {
		ObjectifyService.register(Directory.class);
	}

	private static final Logger log = Logger.getLogger(Directory.class.getName());

	// Needed by RequestFactory
	public Directory() {

	}

	// Needed by RequestFactory
	// TODO: return an actual version of the entity
	public Integer getVersion() {
		return new Integer(1);
	}

	// Needed by RequestFactory
	public String getId() {
		return id;
	}

	public String getParent() {
		return parent;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWebsite() {
		if (website == null)
			return null;
		return website.getValue();
	}

	public void setWebsite(String website) {
		this.website = new Link(website);
	}

	public static Directory findDirectory(String id) {
		log.info("Requested Directory " + id);

		Objectify o = ObjectifyService.begin();

		Directory d = o.get(Directory.class, id);

		return d;
	}

	public static List<Directory> findAllDirectories(List<String> ids) {
		List<Directory> l = new ArrayList<Directory>();

		for (String id : ids) {
			l.add(findDirectory(id));
		}

		return l;
	}

	public static List<Directory> listDirectories(String parent) {
		log.info("Requested Directories in " + parent);

		Objectify o = ObjectifyService.begin();

		List<Directory> l = o.query(Directory.class).filter("parent", parent).order("codeName").list();

		return l;
	}

	public static List<List<Directory>> listAllDirectories(List<String> parents) {
		List<List<Directory>> l = new ArrayList<List<Directory>>();

		for (String parent : parents) {
			l.add(listDirectories(parent));
		}

		return l;
	}

	public void create(String parent, String codeName) {
		log.info("Requested creation of Directory " + codeName + " in " + parent);

		if (!codeName.matches("[a-zA-Z0-9\\_]{3,}")) {
			throw new IllegalArgumentException("Codename is malformed: " + codeName);
		}

		Objectify o = ObjectifyService.begin();

		this.parent = parent;
		this.codeName = codeName;
		this.id = parent + codeName + "/";

		o.put(this);
	}

	public void update() {
		log.info("Requested update of Directory " + codeName + " in " + parent);

		Objectify o = ObjectifyService.begin();

		o.put(this);
	}

	public void delete() {
		log.info("Requested deletion of Directory " + codeName + " in " + parent);

		Objectify o = ObjectifyService.begin();

		o.delete(this);
	}
}
