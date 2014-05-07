package org.jetbrains.jps.incremental.tara.compiler;

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
	public static final boolean DEFAULT_PLUGIN_GENERATION = true;
	public boolean pluginGeneration = DEFAULT_PLUGIN_GENERATION;
	public static final JpsElementChildRole<JpsTaraSettings> ROLE = JpsElementChildRoleBase.create("Tara Compiler Configuration");
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

	@Override
	public void applyChanges(@NotNull JpsTaraSettings modified) {
	}

	@NotNull
	@Override
	public JpsTaraSettings createCopy() {
		return new JpsTaraSettings(this);
	}

	public boolean isExcludedFromCompilation(File file) {
		return myExcludeFromStubGeneration != null && myExcludeFromStubGeneration.isExcluded(file);
	}

	void initExcludes() {
		myExcludeFromStubGeneration = new JpsCompilerExcludesImpl();
		JpsJavaCompilerConfigurationSerializer.readExcludes(excludes, myExcludeFromStubGeneration);
	}

}
