package monet.::projectName::.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.::projectName::.compiler.Jps::projectProperName::Settings;

\@State(name = "::projectProperName::CompilerConfiguration", storages = \@Storage(file = StoragePathMacros.WORKSPACE_FILE))
public class ::projectProperName::CompilerWorkspaceConfiguration implements PersistentStateComponent<Jps::projectProperName::Settings>, Disposable {
	String myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
	boolean pluginGeneration = Jps::projectProperName::Settings.PLUGIN_GENERATION;
	private String version = "0.1";
	private String commentaries = "";
	final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();

	public Jps::projectProperName::Settings getState() {
		final Jps::projectProperName::Settings jpsSettings = new Jps::projectProperName::Settings();
		jpsSettings.heapSize = myHeapSize;
		jpsSettings.pluginGeneration = pluginGeneration;
		jpsSettings.version= version;
		jpsSettings.commentaries= commentaries;
		myExcludeFromStubGeneration.writeExternal(jpsSettings.excludes);
		return jpsSettings;
	}

	public void loadState(Jps::projectProperName::Settings jpsSettings) {
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
