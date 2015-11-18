package tara.compiler.refactor;

import java.util.ArrayList;

public class Refactors extends ArrayList<Refactors.Refactor> {

	public static class Refactor {

		public Refactor() {
		}

		public Refactor(int id, String anchor, String oldQn, String newQn) {
			this.id = id;
			this.anchor = anchor;
			this.oldQn = oldQn;
			this.newQn = newQn;
		}

		int id;
		String anchor;
		String oldQn;
		String newQn;
	}
}
