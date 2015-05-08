package siani.tara.semantic.constraints.flags;

import java.util.HashMap;

public class FlagCheckerFactory {

	public static HashMap<String, Class<? extends AnnotationChecker>> checkers = new HashMap<String, Class<? extends AnnotationChecker>>() {{
		put("aggregated", AggregatedChecker.class);
		put("property", PropertyChecker.class);
		put("feature", FeatureChecker.class);
		put("facet", FacetChecker.class);
		put("facetInstance", FacetInstanceChecker.class);
		put("component", ComponentChecker.class);
	}};


	public static Class<? extends AnnotationChecker> get(Object key) {
		return checkers.get(key.toString());
	}
}
