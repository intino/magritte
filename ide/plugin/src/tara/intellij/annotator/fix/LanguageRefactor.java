package tara.intellij.annotator.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.NodeMixin;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.io.refactor.Refactors;
import tara.lang.model.Node;

import java.util.List;

import static java.util.Collections.emptyList;

public class LanguageRefactor {

	private List<Refactors.Refactor> engineRefactors;
	private List<Refactors.Refactor> domainRefactors;

	public LanguageRefactor(Refactors[] refactors, int engineRefactorId, int domainRefactorId) {
		this.engineRefactors = refactors[0] != null ? refactors[0].subListById(engineRefactorId) : emptyList();
		this.domainRefactors = refactors[1] != null ? refactors[1].subListById(domainRefactorId) : emptyList();
	}

	public void apply(Module module) {
		applyRefactors(module, domainRefactors);
		applyRefactors(module, engineRefactors);
	}

	public void applyRefactors(final Module module, List<Refactors.Refactor> refactors) {
		final Object[] files = new Object[1];
		ApplicationManager.getApplication().runReadAction(() -> {
			files[0] = TaraUtil.getTaraFilesOfModule(module);
		});
		new WriteCommandAction.Simple(module.getProject(), ((List<TaraModel>) files[0]).toArray(new TaraModel[((List) files[0]).size()])) {
			@Override
			protected void run() throws Throwable {
				for (TaraModel taraModel : ((List<TaraModel>) files[0])) applyToFile(taraModel, refactors);
			}
		}.execute();
	}

	private void applyToFile(TaraModel taraModel, List<Refactors.Refactor> refactors) {
		TaraUtil.getAllNodesOfFile(taraModel).forEach((node) -> apply(node, refactors));
	}

	private void apply(Node node, List<Refactors.Refactor> refactors) {
		refactors.stream().filter(refactor -> refactor.oldQn.equals(node.type())).
			forEach(refactor -> ((NodeMixin) node).setShortType(shortName(refactor.newQn)));
	}

	private String shortName(String newQn) {
		final String[] names = newQn.split("\\.");
		return names[names.length - 1];
	}
}
