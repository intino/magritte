package siani.tara.intellij.codegeneration;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

public class NameInstanceGenerator {
	private final Node[] nodes;
	private final Project project;
	private final TaraModelImpl file;

	public NameInstanceGenerator(Node... nodes) {
		this.nodes = nodes;
		project = nodes[0].getProject();
		file = nodes[0].getFile();
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (Node concept : nodes)
					if (concept.getAddress() == null)
						concept.addAddress(TaraElementFactory.getInstance(concept.getProject()).createAddress(WordGenerator.generate()));
			}
		};
		action.execute();
	}
}
