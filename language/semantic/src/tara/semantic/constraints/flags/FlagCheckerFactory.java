package tara.semantic.constraints.flags;

import java.util.HashMap;

public class FlagCheckerFactory {

	public static HashMap<String, Class<? extends AnnotationChecker>> checkers = new HashMap<String, Class<? extends AnnotationChecker>>() {{
		put("facet", FacetChecker.class);
		put("facetInstance", FacetInstanceChecker.class);
		put("terminal", TerminalChecker.class);
	}};


	public static Class<? extends AnnotationChecker> get(Object key) {
		return checkers.get(key.toString());
	}
}
