package org.jetbrains.jps.tara.compiler;

import com.intellij.util.xmlb.annotations.Tag;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.java.compiler.JpsCompilerExcludes;
import org.jetbrains.jps.model.java.impl.compiler.JpsCompilerExcludesImpl;
import org.jetbrains.jps.model.serialization.java.compiler.JpsJavaCompilerConfigurationSerializer;

import java.io.File;

public class JpsTaraSettings extends JpsElementBase<JpsTaraSettings> {
	public static final String DEFAULT_HEAP_SIZE = "400";
	public String heapSize = DEFAULT_HEAP_SIZE;
	public static final boolean PLUGIN_GENERATION = true;
	public boolean pluginGeneration = PLUGIN_GENERATION;
	static final JpsElementChildRole<JpsTaraSettings> ROLE = JpsElementChildRoleBase.create("Tara Compiler Configuration");
	public String version = "0.1";
	public String commentaries = "";

	@Tag("excludes")
	public Element excludes = new Element("aaa");

	private JpsCompilerExcludes myExcludeFromStubGeneration;

	public JpsTaraSettings() {
	}

	public JpsTaraSettings(JpsTaraSettings original) {
		heapSize = original.heapSize;
		pluginGeneration = original.pluginGeneration;
	}

	@NotNull
	public static JpsTaraSettings getSettings(@NotNull JpsProject project) {
		JpsTaraSettings settings = project.getContainer().getChild(ROLE);
		return settings == null ? new JpsTaraSettings() : settings;
	}

	void initExcludes() {
		myExcludeFromStubGeneration = new JpsCompilerExcludesImpl();
		JpsJavaCompilerConfigurationSerializer.readExcludes(excludes, myExcludeFromStubGeneration);
	}

	@NotNull
	@Override
	public JpsTaraSettings createCopy() {
		return new JpsTaraSettings(this);
	}

	@Override
	public void applyChanges(@NotNull JpsTaraSettings modified) {
	}

	public boolean isExcludedFromStubGeneration(File file) {
		return myExcludeFromStubGeneration != null && myExcludeFromStubGeneration.isExcluded(file);
	}
}
