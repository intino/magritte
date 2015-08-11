package tara.intellij.project;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import tara.intellij.highlighting.TaraSyntaxHighlighter;

public class DslListener implements com.intellij.openapi.module.ModuleComponent {

	private final Project project;

	public DslListener(Project project) {
		this.project = project;
	}

	@Override
	public void projectOpened() {
		TaraSyntaxHighlighter.setProject(this.project);
	}

	@Override
	public void projectClosed() {

	}

	@Override
	public void moduleAdded() {

	}

	@Override
	public void initComponent() {
	}

	@Override
	public void disposeComponent() {
		TaraSyntaxHighlighter.setProject(null);
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "dslListener";
	}
}
