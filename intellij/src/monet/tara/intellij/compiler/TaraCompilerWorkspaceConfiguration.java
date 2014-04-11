package monet.tara.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.incremental.tara.compiler.JpsTaraSettings;

@State(name = "TaraCompilerConfiguration", storages = @Storage(file = StoragePathMacros.WORKSPACE_FILE))
public class TaraCompilerWorkspaceConfiguration implements PersistentStateComponent<JpsTaraSettings>, Disposable {
	final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();
	String myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
	boolean pluginGeneration = JpsTaraSettings.DEFAULT_PLUGIN_GENERATION;
	private String version = "0.1";
	private String commentaries = "";

	public JpsTaraSettings getState() {
		final JpsTaraSettings jpsSettings = new JpsTaraSettings();
		jpsSettings.heapSize = myHeapSize;
		jpsSettings.pluginGeneration = pluginGeneration;
		jpsSettings.version = version;
		jpsSettings.commentaries = commentaries;
		myExcludeFromStubGeneration.writeExternal(jpsSettings.excludes);
		return jpsSettings;
	}

	public void loadState(JpsTaraSettings jpsSettings) {
		myHeapSize = jpsSettings.heapSize;
		pluginGeneration = jpsSettings.pluginGeneration;
		commentaries = jpsSettings.commentaries;
		version = jpsSettings.version;
		myExcludeFromStubGeneration.readExternal(jpsSettings.excludes);
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}
}
