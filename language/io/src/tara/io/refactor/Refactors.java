package tara.io.refactor;

import java.util.ArrayList;
import java.util.List;

public class Refactors extends ArrayList<Refactors.Refactor> {


	public List<Refactor> subListById(long id) {
		for (Refactor refactor : this)
			if (refactor.id >= id)
				return this.subList(this.indexOf(refactor), this.size());
		return this;
	}

	public static class Refactor {

		public int id;
		public String anchor;
		public String oldQn;
		public String newQn;

		public Refactor() {
		}

		public Refactor(int id, String anchor, String oldQn, String newQn) {
			this.id = id;
			this.anchor = anchor;
			this.oldQn = oldQn;
			this.newQn = newQn;
		}
	}
}
