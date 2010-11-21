package treicco.server;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Entity
public class Task {
	@Id
	// @NotNull
	// @Pattern(regexp = "(/[a-zA-Z0-9_]{3,})+")
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

	private Text description;

	static {
		ObjectifyService.register(Task.class);
	}

	private static final Logger log = Logger.getLogger(Task.class.getName());

	// Needed by RequestFactory
	public Task() {

	}

	// Needed by RequestFactory
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

	public String getDescription() {
		return description.getValue();
	}

	public void setDescription(String description) {
		this.description = new Text(description);
	}

	public static Task findTask(String parent, String codeName) {
		log.info("Requested Task " + codeName + " in " + parent);

		Objectify o = ObjectifyService.begin();

		Task t = o.get(Task.class, parent + codeName);

		return t;
	}

	public static List<Task> listTasks(String parent) {
		log.info("Requested Tasks in " + parent);

		Objectify o = ObjectifyService.begin();

		List<Task> l = o.query(Task.class).filter("parent", parent).order("codeName").list();

		return l;
	}

	public void create(String parent, String codeName) {
		log.info("Requested creation of Task " + codeName + " in " + parent);

		if (!codeName.matches("[a-zA-Z0-9\\_]{3,}")) {
			throw new IllegalArgumentException("Codename is malformed: " + codeName);
		}

		Objectify o = ObjectifyService.begin();

		this.parent = parent;
		this.codeName = codeName;
		this.id = parent + codeName;

		o.put(this);
	}

	public void update() {
		log.info("Requested update of Task " + codeName + " in " + parent);

		Objectify o = ObjectifyService.begin();

		o.put(this);
	}

	public void delete() {
		log.info("Requested deletion of Task " + codeName + " in " + parent);

		Objectify o = ObjectifyService.begin();

		o.delete(this);
	}
}
