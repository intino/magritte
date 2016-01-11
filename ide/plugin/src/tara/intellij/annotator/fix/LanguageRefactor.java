package tara.intellij.annotator.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.NodeMixin;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.io.refactor.Refactors;
import tara.lang.model.Node;

import java.util.List;

public class LanguageRefactor {

	private final Refactors refactors;
	private final int refactorId;

	public LanguageRefactor(Refactors refactors, int refactorId) {
		this.refactors = refactors;
		this.refactorId = refactorId;
	}

	public void apply(Module module) {
		ApplicationManager.getApplication().invokeLater(() ->
			ApplicationManager.getApplication().runWriteAction(() -> {
				if (LanguageRefactor.this.refactors == null) return;
				final List<Refactors.Refactor> refactors = LanguageRefactor.this.refactors.subListById(refactorId);
				for (TaraModel taraModel : TaraUtil.getTaraFilesOfModule(module)) applyToFile(refactors, taraModel);
			})
		);
	}

	private void applyToFile(List<Refactors.Refactor> refactors, TaraModel taraModel) {
		for (Node node : TaraUtil.getAllNodesOfFile(taraModel)) apply(node, refactors);
	}

	private void apply(Node node, List<Refactors.Refactor> refactors) {
		refactors.stream().filter(refactor -> refactor.oldQn.equals(node.type())).forEach(refactor -> ((NodeMixin) node).setShortType(shortName(refactor.newQn)));
	}

	private String shortName(String newQn) {
		final String[] names = newQn.split("\\.");
		return names[names.length - 1];
	}
}
