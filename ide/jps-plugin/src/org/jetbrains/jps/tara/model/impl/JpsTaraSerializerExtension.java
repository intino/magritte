package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;
import org.jetbrains.jps.model.serialization.facet.JpsFacetConfigurationSerializer;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.util.List;

import static java.util.Collections.singletonList;

public class JpsTaraSerializerExtension extends JpsModelSerializerExtension {

	@NotNull
	@Override
	public List<? extends JpsFacetConfigurationSerializer<?>> getFacetConfigurationSerializers() {
		return singletonList(new JpsTaraModuleExtensionSerializer());
	}

	@NotNull
	@Override
	public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
		return singletonList(new TaraSettingsSerializer());
	}

	private static class JpsTaraModuleExtensionSerializer extends JpsFacetConfigurationSerializer<JpsTaraModuleExtension> {
		public JpsTaraModuleExtensionSerializer() {
			super(JpsTaraModuleExtensionImpl.ROLE, "Tara", "Tara");
		}

		@Override
		protected JpsTaraModuleExtension loadExtension(@NotNull Element facetConfigurationElement,
													   String name,
													   JpsElement parent,
													   JpsModule module) {
			TaraModuleExtensionProperties properties = XmlSerializer.deserialize(facetConfigurationElement, TaraModuleExtensionProperties.class);
			return new JpsTaraModuleExtensionImpl(properties != null ? properties : new TaraModuleExtensionProperties());
		}

		@Override
		protected void saveExtension(JpsTaraModuleExtension extension, Element facetConfigurationTag, JpsModule module) {
			XmlSerializer.serializeInto(((JpsTaraModuleExtensionImpl) extension).getProperties(), facetConfigurationTag);
		}
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
