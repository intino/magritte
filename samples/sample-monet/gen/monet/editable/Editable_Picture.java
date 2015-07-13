package monet.editable;

import siani.tara.magritte.wraps.Morph;

public class Editable_Picture extends Editable {

	public Format format() {
		return _components(Format.class).get(0);
	}

	public static class Format extends Morph {

		public String extension() {
			return _get("extension").asString();
		}

	}

}
