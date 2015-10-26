package tara.lang.semantics;

import tara.lang.model.Element;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Tag;

import java.util.List;

public interface Constraint {
	void check(Element element) throws SemanticException;

	interface Require extends Constraint {

		interface Name extends Require {
		}

		interface TerminalVariableRedefinition extends Require {

		}

		interface Include extends Require {
			String type();

			Tag[] annotations();
		}

		interface Multiple extends Include {
		}

		interface Single extends Include {
		}

		interface OneOf extends Include {
			Require[] requires();
		}

		interface Plate extends Require {
		}

		interface Parameter extends Require {

			String name();

			Primitive type();

			boolean multiple();

			Object defaultValue();

			int position();

			Rule rule();

			List<String> annotations();
		}
	}

	interface Reject extends Constraint {
	}
}
