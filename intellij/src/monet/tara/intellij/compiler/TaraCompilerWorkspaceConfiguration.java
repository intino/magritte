package monet.tara.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.tara.compiler.JpsTaraSettings;

@State(name = "TaraCompilerConfiguration", storages = @Storage(file = StoragePathMacros.WORKSPACE_FILE))
public class TaraCompilerWorkspaceConfiguration implements PersistentStateComponent<JpsTaraSettings>, Disposable {
	final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();
	String myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
	boolean pluginGeneration = JpsTaraSettings.PLUGIN_GENERATION;
	private String version = "0.1";
	private String commentaries = "";

	public JpsTaraSettings getState() {
		final JpsTaraSettings jpsSettings = new JpsTaraSettings();
		jpsSettings.setHeapSize(myHeapSize);
		jpsSettings.setPluginGeneration(pluginGeneration);
		jpsSettings.setVersion(version);
		jpsSettings.setCommentaries(commentaries);
		myExcludeFromStubGeneration.writeExternal(jpsSettings.getExcludes());
		return jpsSettings;
	}

	public void loadState(JpsTaraSettings jpsSettings) {
		myHeapSize = jpsSettings.getHeapSize();
		pluginGeneration = jpsSettings.isPluginGeneration();
		commentaries = jpsSettings.getCommentaries();
		version = jpsSettings.getVersion();
		myExcludeFromStubGeneration.readExternal(jpsSettings.getExcludes());
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}
}
