package siani.tara.intellij.project;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.project.facet.TaraFacet;

import java.io.File;

public class DslListener implements com.intellij.openapi.module.ModuleComponent {

	private final Project project;

	public DslListener(Project project) {
		this.project = project;
	}

	@Override
	public void projectOpened() {
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
			if (facet == null) continue;
			facet.getConfiguration().setDslsDirectory(project.getBasePath() + File.separator + "dsl" + File.separator);
		}
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

	}

	@NotNull
	@Override
	public String getComponentName() {
		return "dslListener";
	}
}
