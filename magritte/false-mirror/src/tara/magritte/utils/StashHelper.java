package tara.magritte.utils;

public class StashHelper {

	private static final String STASH_EXT = ".stash";

	public static String stashName(String id) {
		return id.substring(0, id.indexOf("#"));
	}

	public static String stashWithExtension(String id) {
		if(id.endsWith(STASH_EXT)) return id.contains("#") ? stashName(id) : id;
		return id.contains("#") ? stashName(id) + STASH_EXT : id + STASH_EXT;
	}

}
