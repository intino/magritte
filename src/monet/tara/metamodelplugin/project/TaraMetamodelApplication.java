package monet.tara.metamodelplugin.project;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;


public class TaraMetamodelApplication implements ApplicationComponent {
	public TaraMetamodelApplication() {
	}

	public void initComponent() {
		// TODO: insert component initialization logic here
	}

	public void disposeComponent() {
		// TODO: insert component disposal logic here
	}

	@NotNull
	public String getComponentName() {
		return "Tara Metamodel Application";
	}
}
