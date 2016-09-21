package legio.plugin.project;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.TaraModel;

public class LegioModuleCreator {
	private final Module module;

	public LegioModuleCreator(Module module) {
		this.module = module;
	}

	public TaraModel create() {
		return null;
	}
}
