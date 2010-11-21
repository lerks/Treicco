package treicco.shared;

public class CompetitionSyntax {
	public static boolean isDirectory(String id) {
		if (id.endsWith("/"))
			return true;
		else
			return false;
	}

	public static boolean isTask(String id) {
		return !isDirectory(id);
	}

	public static String extractCodeName(String id) {
		if (isDirectory(id))
			return id.substring(id.lastIndexOf("/", id.length() - 2) + 1, id.length() - 1);
		else
			return id.substring(id.lastIndexOf("/", id.length() - 1) + 1, id.length());
	}

	public static String extractParent(String id) {
		if (isDirectory(id))
			return id.substring(0, id.lastIndexOf("/", id.length() - 2) + 1);
		else
			return id.substring(0, id.lastIndexOf("/", id.length() - 1) + 1);
	}

	public static String extractDirectory(String id) {
		return id.substring(0, id.lastIndexOf("/", id.length() - 1) + 1);
	}

	public static String composeDirectoryId(String parent, String codename) {
		return parent + codename + "/";
	}

	public static String composeTaskId(String parent, String codename) {
		return parent + codename;
	}
}
