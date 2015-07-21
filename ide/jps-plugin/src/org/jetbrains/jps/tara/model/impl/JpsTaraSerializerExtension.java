package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.facet.JpsFacetConfigurationSerializer;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.util.Collections;
import java.util.List;

public class JpsTaraSerializerExtension extends JpsModelSerializerExtension {

	@NotNull
	@Override
	public List<? extends JpsFacetConfigurationSerializer<?>> getFacetConfigurationSerializers() {
		return Collections.singletonList(new JpsTaraModuleExtensionSerializer());
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
}
