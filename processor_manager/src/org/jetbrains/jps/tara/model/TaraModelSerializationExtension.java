package org.jetbrains.jps.tara.model;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.module.JpsDependencyElement;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;

public class TaraModelSerializationExtension extends JpsModelSerializerExtension {

	private static final String PRODUCTION_ON_TEST_ATTRIBUTE = "production-on-test";
	private static final String TARA_MODULE_ATTRIBUTE = "org.jetbrains.idea.tara.project.TaraProjectsManager.isTaraModule";

	@Override
	public void loadModuleOptions(@NotNull JpsModule module, @NotNull Element rootElement) {
		if (Boolean.parseBoolean(rootElement.getAttributeValue(TARA_MODULE_ATTRIBUTE))) {
			JpsTaraExtensionService.getInstance().getOrCreateExtension(module);
		}
	}

	@Override
	public void saveModuleOptions(@NotNull JpsModule module, @NotNull Element rootElement) {
		if (JpsTaraExtensionService.getInstance().getExtension(module) != null) {
			rootElement.setAttribute(TARA_MODULE_ATTRIBUTE, "true");
		}
	}

	@Override
	public void loadModuleDependencyProperties(JpsDependencyElement dependency, Element orderEntry) {
		if (orderEntry.getAttributeValue(PRODUCTION_ON_TEST_ATTRIBUTE) != null) {
			JpsTaraExtensionService.getInstance().setProductionOnTestDependency(dependency, true);
		}
	}

	@Override
	public void saveModuleDependencyProperties(JpsDependencyElement dependency, Element orderEntry) {
		if (JpsTaraExtensionService.getInstance().isProductionOnTestDependency(dependency)) {
			orderEntry.setAttribute(PRODUCTION_ON_TEST_ATTRIBUTE, "");
		}
	}
}