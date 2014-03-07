package monet.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.platform.DirectoryProjectGenerator;

public abstract class TaraModuleService {

	public static TaraModuleService getInstance() {
		return ServiceManager.getService(TaraModuleService.class);
	}

	/**
	 * Creates a ModuleBuilder that creates a Tara module and runs the specified DirectoryProjectGenerator to perform
	 * further initialization. The showGenerationSettings() method on the generator is not called, and the generateProject() method
	 * receives null as the 'settings' parameter.
	 *
	 * @param generator the generator to run for configuring the project
	 * @return the created module builder instance
	 */
	public abstract ModuleBuilder createTaraModuleBuilder(DirectoryProjectGenerator generator);
}
