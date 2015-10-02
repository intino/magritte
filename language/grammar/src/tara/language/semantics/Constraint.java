package tara.language.semantics;

import tara.language.model.Element;
import tara.language.model.Tag;

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

			interface None extends Include {
			}
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

			String type();

			boolean multiple();

			Object defaultValue();

			String[] allowedValues();

			int position();

			String metric();

			String[] annotations();
		}
	}

	interface Reject extends Constraint {
	}
}
