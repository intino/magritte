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
	private String heapSize = DEFAULT_HEAP_SIZE;
	public static final boolean PLUGIN_GENERATION = true;
	private boolean pluginGeneration = PLUGIN_GENERATION;
	public static final JpsElementChildRole<JpsTaraSettings> ROLE = JpsElementChildRoleBase.create("Tara Compiler Configuration");
	private String version = "0.1";
	private String commentaries = "";

	@Tag("excludes")
	private Element excludes = new Element("aaa");

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
		//TODO
	}

	public boolean isExcludedFromStubGeneration(File file) {
		return myExcludeFromStubGeneration != null && myExcludeFromStubGeneration.isExcluded(file);
	}

	public String getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(String heapSize) {
		this.heapSize = heapSize;
	}

	public boolean isPluginGeneration() {
		return pluginGeneration;
	}

	public void setPluginGeneration(boolean pluginGeneration) {
		this.pluginGeneration = pluginGeneration;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCommentaries() {
		return commentaries;
	}

	public void setCommentaries(String commentaries) {
		this.commentaries = commentaries;
	}

	public Element getExcludes() {
		return excludes;
	}

	public void setExcludes(Element excludes) {
		this.excludes = excludes;
	}
}
