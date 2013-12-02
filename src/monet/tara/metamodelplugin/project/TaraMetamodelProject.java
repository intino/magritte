package monet.tara.metamodelplugin.project;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class TaraMetamodelProject implements ProjectComponent {
	public TaraMetamodelProject(Project project) {
	}

	public void initComponent() {
		// TODO: insert component initialization logic here
	}

	public void disposeComponent() {
		// TODO: insert component disposal logic here
	}

	@NotNull
	public String getComponentName() {
		return "Tara Metamodel Project";
	}

	public void projectOpened() {
		// called when project is opened

	}


	public void projectClosed() {
		// called when project is being closed
	}
}