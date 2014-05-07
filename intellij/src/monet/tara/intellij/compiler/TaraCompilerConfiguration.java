package monet.tara.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.incremental.tara.compiler.JpsTaraSettings;

@State(
	name = "TaraCompilerProjectConfiguration",
	storages = {
		@Storage(file = StoragePathMacros.PROJECT_FILE),
		@Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/tarac.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)

public class TaraCompilerConfiguration implements PersistentStateComponent<JpsTaraSettings>, Disposable {
	private final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();
	private String myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
	private Boolean pluginGeneration = JpsTaraSettings.DEFAULT_PLUGIN_GENERATION;
	private String version = "0.1";
	private String commentaries = "";
	private String vendor="";

	public TaraCompilerConfiguration(Project project) {
		TaraCompilerWorkspaceConfiguration workspaceConfiguration = ServiceManager.getService(project, TaraCompilerWorkspaceConfiguration.class);
		loadState(workspaceConfiguration.getState());
		workspaceConfiguration.myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
		workspaceConfiguration.pluginGeneration = JpsTaraSettings.DEFAULT_PLUGIN_GENERATION;
		workspaceConfiguration.myExcludeFromStubGeneration.removeAllExcludeEntryDescriptions();
	}

	public static ExcludedEntriesConfiguration getExcludeConfiguration(Project project) {
		return getInstance(project).myExcludeFromStubGeneration;
	}

	public static TaraCompilerConfiguration getInstance(Project project) {
		return ServiceManager.getService(project, TaraCompilerConfiguration.class);
	}

	public JpsTaraSettings getState() {
		final JpsTaraSettings settings = new JpsTaraSettings();
		settings.heapSize = myHeapSize;
		settings.pluginGeneration = pluginGeneration;
		settings.version = version;
		settings.commentaries = commentaries;
		myExcludeFromStubGeneration.writeExternal(settings.excludes);
		return settings;
	}

	public ExcludedEntriesConfiguration getExcludeFromStubGeneration() {
		return myExcludeFromStubGeneration;
	}

	public void loadState(JpsTaraSettings state) {
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

	public boolean isPluginGeneration() {
		return pluginGeneration;
	}

	public void setPluginGeneration(boolean generation) {
		pluginGeneration = generation;
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

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
