package tara.intellij.annotator.fix;

import tara.intellij.lang.psi.TaraNode;
import tara.lang.model.Node;
import tara.lang.refactor.Refactors;

import java.util.List;

public class LanguageRefactor {

	private final Refactors refactors;
	private final long refactorId;

	public LanguageRefactor(Refactors refactors, long refactorId) {
		this.refactors = refactors;
		this.refactorId = refactorId;
	}

	public void apply(Node node) {
		final List<Refactors.Refactor> refactors = this.refactors.subListById(refactorId);
		for (Refactors.Refactor refactor : refactors) {
			if (refactor.oldQn.equals(node.type())) ((TaraNode) node).setShortType(shortName(refactor.newQn));
		}
	}

	private String shortName(String newQn) {
		final String[] names = newQn.split("\\.");
		return names[names.length - 1];
	}
}
