package org.jetbrains.jps.incremental.tara.model;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;

import java.util.Arrays;
import java.util.List;

public class TaraModelSerializerExtension extends JpsModelSerializerExtension {

	private static final Logger LOG = Logger.getInstance(TaraModelSerializerExtension.class.getName());

	@NotNull
	@Override
	public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
		return Arrays.asList(new JpsTaraConfigurationSerializer());
	}
}