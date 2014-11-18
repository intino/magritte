package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.lang.Annotations.Annotation.*;

public class Annotations {

	public static final Annotation[] SUB_ANNOTATIONS = new Annotation[]{PROPERTY, NAMED, TERMINAL, ADDRESSED, AGGREGATED};
	public static final Annotation[] COMPONENT_ANNOTATIONS = new Annotation[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, NAMED, INTENTION, ADDRESSED, AGGREGATED};
	public static final Annotation[] VARIABLE_ANNOTATIONS = new Annotation[]{ABSTRACT, TERMINAL, PROPERTY, SINGLE, PROPERTY, UNIVERSAL};
	public static final Annotation[] PRIME_ANNOTATIONS = new Annotation[]{ABSTRACT, COMPONENT, SINGLE, NAMED, TERMINAL,
		PROPERTY, REQUIRED, INTENTION, FACET, ADDRESSED, AGGREGATED};

	private Annotations() {
	}

	public static String[] getAnnotations() {
		List<String> list = new ArrayList();
		for (Annotation annotation : values()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}

	public enum Annotation {
		NAMED("named"), COMPONENT("component"), TERMINAL("terminal"), SINGLE("single"), REQUIRED("required"),
		ABSTRACT("abstract"), PROPERTY("property"), FACET("facet"), INTENTION("intention"), UNIVERSAL("universal"),
		ADDRESSED("addressed"), AGGREGATED("aggregated");

		private String name;

		private Annotation(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}
