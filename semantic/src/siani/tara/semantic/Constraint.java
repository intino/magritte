package siani.tara.semantic;

import siani.tara.semantic.model.Element;
import siani.tara.semantic.model.Tag;

public interface Constraint {
	void check(Element element) throws SemanticException;

	interface Require extends Constraint {

		interface Name extends Require {
		}

		interface Include extends Require {
			String type();

			Relation relation();

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

		interface Parameter extends Require {

			String name();

			String type();

			boolean multiple();

			String[] allowedValues();

			int position();

			String metric();

			String[] annotations();

			interface None extends Require {
			}
		}

		interface Address extends Require {
		}
	}

	interface Reject extends Constraint {
	}
}
