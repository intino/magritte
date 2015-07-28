package org.jetbrains.jps.tara.compiler;

import com.intellij.util.xmlb.annotations.Tag;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

public class JpsTaraSettings extends JpsElementBase<JpsTaraSettings> {
	public static final String DEFAULT_HEAP_SIZE = "400";
	public static final boolean DEFAULT_PLUGIN_GENERATION = true;
	public static final JpsElementChildRole<JpsTaraSettings> ROLE = JpsElementChildRoleBase.create("Tara Compiler Configuration");
	public String heapSize = DEFAULT_HEAP_SIZE;
	public boolean pluginGeneration = DEFAULT_PLUGIN_GENERATION;
	public String version = "0.1";
	public String commentaries = "";
	@Tag("excludes")
	public Element excludes = new Element("aaa");

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

}