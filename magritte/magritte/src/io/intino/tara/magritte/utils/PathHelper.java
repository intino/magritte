package io.intino.tara.magritte.utils;

public class PathHelper {

	private static final String STASH_EXT = ".stash";

	public static String pathOf(String id) {
		return id.contains("#") ? id.substring(0, id.indexOf("#")) : "";
	}

	public static String pathWithExtension(String id) {
		if(id.endsWith(STASH_EXT)) return id.contains("#") ? pathOf(id) : id;
        return canonicalPath(id.contains("#") ? pathOf(id) + STASH_EXT : id + STASH_EXT);
    }

	public static String canonicalPath(String path){
	    return path.replace("\\", "/");
    }

}
