package tara.compiler.refactor;

import java.util.ArrayList;

public class Refactors extends ArrayList<Refactors.Refactor> {

	public static class Refactor {
		int id;
		String anchor;
		String oldQn;
		String newQn;
	}
}
