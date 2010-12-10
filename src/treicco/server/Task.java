package treicco.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	private String author;

	private String timeLimit;

	private String memoryLimit;

	private String difficulty;

	private String description;

	private String input;

	private String output;

	private String constraints;

	private String notes;

	static {
		ObjectifyService.register(Task.class);
	}

	private static final Logger log = Logger.getLogger(Task.class.getName());

	// Needed by RequestFactory
	public Task() {

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(String memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Image> getImages() {
		List<Image> l = Image.listImages(id);
		;
		log.severe("" + l.size());
		return l;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	// public List<Example> getExamples() {
	// return examples;
	// }
	//
	// public void setExamples(List<Example> examples) {
	// this.examples = examples;
	// }

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public static Task findTask(String id) {
		log.info("Requested Task " + id);

		Objectify o = ObjectifyService.begin();

		Task t = o.get(Task.class, id);

		return t;
	}

	public static List<Task> findAllTasks(List<String> ids) {
		List<Task> l = new ArrayList<Task>();

		for (String id : ids) {
			l.add(findTask(id));
		}

		return l;
	}

	public static List<Task> listTasks(String parent) {
		log.info("Requested Tasks in " + parent);

		Objectify o = ObjectifyService.begin();

		List<Task> l = o.query(Task.class).filter("parent", parent).order("codeName").list();

		return l;
	}

	public static List<List<Task>> listAllTasks(List<String> parents) {
		List<List<Task>> l = new ArrayList<List<Task>>();

		for (String parent : parents) {
			l.add(listTasks(parent));
		}

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
