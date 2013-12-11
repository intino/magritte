package monet.tara.plugin.project;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import org.jetbrains.annotations.NotNull;

public class ProjectWizardStep extends ProjectTemplatesFactory implements ApplicationComponent {
	@NotNull
	@Override
	public String[] getGroups() {
		return new String[0];
	}

	@NotNull
	@Override
	public ProjectTemplate[] createTemplates(String group, WizardContext context) {

		return new ProjectTemplate[0];
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
		return null;
	}
}
