package tara.io.refactor;

import java.util.ArrayList;
import java.util.List;

public class Refactors extends ArrayList<Refactors.Refactor> {


	public List<Refactor> subListById(int id) {
		return id > this.size() ? this : this.subList(id, this.size());
	}

	public static class Refactor {

		public String anchor;
		public String oldQn;
		public String newQn;

		public Refactor() {
		}

		public Refactor(String anchor, String oldQn, String newQn) {
			this.anchor = anchor;
			this.oldQn = oldQn;
			this.newQn = newQn;
		}
	}
}
