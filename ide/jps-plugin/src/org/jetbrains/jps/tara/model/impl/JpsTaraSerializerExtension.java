package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;

import java.util.List;

import static java.util.Collections.singletonList;

public class JpsTaraSerializerExtension extends JpsModelSerializerExtension {
	private static final String TARA_MODULE_OPTION_NAME = "io.intino.tara.isTaraModule";
	private static final String CONFIGURATION_PROVIDER_OPTION_NAME = "org.siani.tara.configuration.provided";


	@NotNull
	@Override
	public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
		return singletonList(new TaraSettingsSerializer());
	}

	@Override
	public void loadModuleOptions(@NotNull JpsModule module, @NotNull Element rootElement) {
		if (Boolean.parseBoolean(rootElement.getAttributeValue(TARA_MODULE_OPTION_NAME)) && Boolean.parseBoolean(rootElement.getAttributeValue(CONFIGURATION_PROVIDER_OPTION_NAME)))
			JpsTaraExtensionService.instance().getOrCreateExtension(module);
	}

	private static class TaraSettingsSerializer extends JpsProjectExtensionSerializer {
		private TaraSettingsSerializer() {
			super(JpsTaraSettings.FILE, JpsTaraSettings.NAME);
		}

		@Override
		public void loadExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
			JpsTaraSettings settings = XmlSerializer.deserialize(componentTag, JpsTaraSettings.class);
			if (settings == null) settings = new JpsTaraSettings();
			TaraJpsCompilerSettings component = new TaraJpsCompilerSettings(settings);
			project.getContainer().setChild(TaraJpsCompilerSettings.ROLE, component);
		}

		@Override
		public void loadExtensionWithDefaultSettings(@NotNull JpsProject project) {
			TaraJpsCompilerSettings component = new TaraJpsCompilerSettings(new JpsTaraSettings());
			project.getContainer().setChild(TaraJpsCompilerSettings.ROLE, component);
		}

		@Override
		public void saveExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
		}
	}
}
