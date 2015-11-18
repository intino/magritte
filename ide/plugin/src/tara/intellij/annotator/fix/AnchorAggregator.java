package tara.intellij.annotator.fix;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.Node;
import tara.util.WordGenerator;

public class AnchorAggregator {
	private final Node[] nodes;
	private final Project project;
	private final TaraModelImpl file;

	public AnchorAggregator(Node... nodes) {
		this.nodes = nodes;
		project = ((TaraNode) nodes[0]).getProject();
		file = (TaraModelImpl) ((TaraNode) nodes[0]).getContainingFile();
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (Node node : nodes)
					if (node.anchor() == null) node.anchor(WordGenerator.generate());
			}
		};
		action.execute();
	}
}
