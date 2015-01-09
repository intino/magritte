package siani.tara.intellij.codegeneration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

public class MetricClassCreator {

	private final Project project;
	private final String className;
	private final PsiDirectory destiny;

	public MetricClassCreator(Project project, PsiDirectory destiny, String className) {
		this.project = project;
		this.className = className;
		this.destiny = destiny;
	}

	public void createClass() {
		if (destiny.findFile(className + ".java") != null) return;
		JavaDirectoryService.getInstance().createClass(destiny, className, "MetricClass", false);
	}
}
