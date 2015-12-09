package tara.lang.semantics.constraints.flags;

import java.util.HashMap;
import java.util.Map;

public class FlagCheckerFactory {

	public static final Map<String, Class<? extends AnnotationChecker>> checkers = new HashMap<>();

	static {
	}

	private FlagCheckerFactory() {
	}


	public static Class<? extends AnnotationChecker> get(Object key) {
		return checkers.get(key.toString());
	}

}
