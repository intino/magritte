package magritte.primitives;

import magritte.Reference;
import magritte.Source;

public class Resource {

	private final Source source;

	public Resource(Source source) {
		this.source = source;
	}

	public static Resource resource(Source source) {
		return new Resource(source);
	}

	public Reference uid() {
		return source.uid();
	}

	public String format() {
		return extension(source.uid().value());
	}

	public byte[] data() {
		return source.data();
	}

	public int size() {
		return data().length;
	}

	private String extension(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}
}
