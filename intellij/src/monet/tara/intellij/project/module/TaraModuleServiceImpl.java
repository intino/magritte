package monet.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.platform.DirectoryProjectGenerator;

/**
 * Created by oroncal on 07/03/14.
 */
public class TaraModuleServiceImpl extends TaraModuleService {
	@Override
	public ModuleBuilder createTaraModuleBuilder(DirectoryProjectGenerator generator) {
		return new TaraModuleBuilderBase(generator);
	}
}
