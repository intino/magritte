package monet.::projectName::.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.::projectName::.compiler.Jps::projectProperName::Settings;

\@State(
	name = "::projectProperName::CompilerProjectConfiguration",
	storages = {
		\@Storage(file = StoragePathMacros.PROJECT_FILE),
		\@Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/::projectName::c.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)

public class ::projectProperName::CompilerConfiguration implements PersistentStateComponent<Jps::projectProperName::Settings>, Disposable {
	private final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();
	private String myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
	private boolean pluginGeneration = Jps::projectProperName::Settings.PLUGIN_GENERATION;
	private String version = "0.1";
	private String commentaries = "";

	public ::projectProperName::CompilerConfiguration(Project project) {
		::projectProperName::CompilerWorkspaceConfiguration workspaceConfiguration = ServiceManager.getService(project, ::projectProperName::CompilerWorkspaceConfiguration.class);
		loadState(workspaceConfiguration.getState());
		workspaceConfiguration.myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
		workspaceConfiguration.pluginGeneration = Jps::projectProperName::Settings.PLUGIN_GENERATION;
		workspaceConfiguration.myExcludeFromStubGeneration.removeAllExcludeEntryDescriptions();
	}

	public static ExcludedEntriesConfiguration getExcludeConfiguration(Project project) {
		return getInstance(project).myExcludeFromStubGeneration;
	}

	public static ::projectProperName::CompilerConfiguration getInstance(Project project) {
		return ServiceManager.getService(project, ::projectProperName::CompilerConfiguration.class);
	}

	public Jps::projectProperName::Settings getState() {
		final Jps::projectProperName::Settings jpsSettings = new Jps::projectProperName::Settings();
		jpsSettings.heapSize = myHeapSize;
		jpsSettings.pluginGeneration = pluginGeneration;
		jpsSettings.version = version;
		jpsSettings.commentaries = commentaries;
		myExcludeFromStubGeneration.writeExternal(jpsSettings.excludes);
		return jpsSettings;
	}

	public ExcludedEntriesConfiguration getExcludeFromStubGeneration() {
		return myExcludeFromStubGeneration;
	}

	public void loadState(Jps::projectProperName::Settings state) {
		myHeapSize = state.heapSize;
		pluginGeneration = state.pluginGeneration;
		commentaries = state.commentaries;
		version = state.version;
		myExcludeFromStubGeneration.readExternal(state.excludes);
	}

	public String getHeapSize() {
		return myHeapSize;
	}

	public void setHeapSize(String heapSize) {
		myHeapSize = heapSize;
	}

	public boolean IsPluginGeneration() {
		return pluginGeneration;
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

	public void setPluginGeneration(boolean generation) {
		pluginGeneration = generation;
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}

}
