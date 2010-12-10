package treicco.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class Image {

	// @Parent
	// @NotNull
	// @Pattern(regexp = "(/[a-zA-Z0-9_]{3,})+")
	private String parent;

	@Id
	// @NotNull
	private String id;

	// @NotNull
	// @Pattern(regexp = "[a-zA-Z0-9_]{3,}")
	private String name;

	static {
		ObjectifyService.register(Image.class);
	}

	private static final Logger log = Logger.getLogger(Image.class.getName());

	// Needed by RequestFactory
	public Image() {

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

	public String getName() {
		return name;
	}

	public String getUrl() {
		return "/filestore?id=" + id;
	}

	public static Image findImage(String id) {
		log.info("Requested Image " + id);

		Objectify o = ObjectifyService.begin();

		Image i = o.get(Image.class, id);

		return i;
	}

	public static List<Image> findAllImages(List<String> ids) {
		List<Image> l = new ArrayList<Image>();

		for (String id : ids) {
			l.add(findImage(id));
		}

		return l;
	}

	public static List<Image> listImages(String parent) {
		log.severe("Requested Images in " + parent);

		Objectify o = ObjectifyService.begin();

		List<Image> l = o.query(Image.class).filter("parent", parent).order("name").list();

		return l;
	}

	public static List<List<Image>> listAllImages(List<String> parents) {
		List<List<Image>> l = new ArrayList<List<Image>>();

		for (String parent : parents) {
			l.add(listImages(parent));
		}

		return l;
	}

	public static String createUploadURL() {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		return blobstoreService.createUploadUrl("/filestore");
	}

	public void create(String parent, String name, String id) {
		log.severe("Requested creation of Image " + name + " in " + parent);

		if (!name.matches("[a-zA-Z0-9\\_]{3,}")) {
			throw new IllegalArgumentException("Codename is malformed: " + name);
		}

		Objectify o = ObjectifyService.begin();

		this.parent = parent;
		this.name = name;
		this.id = id;

		o.put(this);
	}

	public void delete() {
		log.info("Requested deletion of Image " + name + " in " + parent);

		Objectify o = ObjectifyService.begin();

		o.delete(this);
	}
}
