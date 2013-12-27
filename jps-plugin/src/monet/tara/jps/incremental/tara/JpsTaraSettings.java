package monet.tara.jps.incremental.tara;

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
	static final JpsElementChildRole<JpsTaraSettings> ROLE = JpsElementChildRoleBase.create("Tara Compiler Configuration");
	public static final String DEFAULT_HEAP_SIZE = "400";
	public static final boolean DEFAULT_INVOKE_DYNAMIC = false;
	public static final boolean DEFAULT_TRANSFORMS_OK = false;

	public String heapSize = DEFAULT_HEAP_SIZE;
	public boolean invokeDynamic = DEFAULT_INVOKE_DYNAMIC;

	@Tag("excludes")
	public Element excludes = new Element("aaa");

	public boolean transformsOk = DEFAULT_TRANSFORMS_OK;
	private JpsCompilerExcludes myExcludeFromStubGeneration;

	public JpsTaraSettings() {
	}

	public JpsTaraSettings(JpsTaraSettings original) {
		heapSize = original.heapSize;
		invokeDynamic = original.invokeDynamic;
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

	@NotNull
	public static JpsTaraSettings getSettings(@NotNull JpsProject project) {
		JpsTaraSettings settings = project.getContainer().getChild(ROLE);
		return settings == null ? new JpsTaraSettings() : settings;
	}

	public boolean isExcludedFromStubGeneration(File file) {
		return myExcludeFromStubGeneration != null && myExcludeFromStubGeneration.isExcluded(file);
	}
}
