package org.jetbrains.jps.incremental.tara.compiler;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.tara.compiler.JpsTaraSettings;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;

import java.util.Arrays;
import java.util.List;

public class TaraModelSerializerExtension extends JpsModelSerializerExtension {

	@NotNull
	@Override
	public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
		return Arrays.asList(new JpsProjectExtensionSerializer("tara.xml", "TaraCompilerProjectConfiguration") {
			@Override
			public void loadExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
				JpsTaraSettings configuration = XmlSerializer.deserialize(componentTag, JpsTaraSettings.class);
				if (configuration == null) {
					configuration = new JpsTaraSettings();
				}
				configuration.initExcludes();
				project.getContainer().setChild(JpsTaraSettings.ROLE, configuration);
			}

			@Override
			public void saveExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
			}
		});
	}
}