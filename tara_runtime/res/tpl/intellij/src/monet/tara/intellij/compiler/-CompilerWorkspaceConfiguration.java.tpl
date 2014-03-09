package monet.::projectName::.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.::projectName::.compiler.Jps::projectProperName::Settings;

@State(name = "::projectProperName::CompilerConfiguration", storages = @Storage(file = StoragePathMacros.WORKSPACE_FILE))
public class ::projectProperName::CompilerWorkspaceConfiguration implements PersistentStateComponent<Jps::projectProperName::Settings>, Disposable {
	String myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
	boolean myInvokeDynamic = Jps::projectProperName::Settings.DEFAULT_INVOKE_DYNAMIC;
	boolean transformsOk = Jps::projectProperName::Settings.DEFAULT_TRANSFORMS_OK;
	final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();

	public Jps::projectProperName::Settings getState() {
		final Jps::projectProperName::Settings bean = new Jps::projectProperName::Settings();
		bean.heapSize = myHeapSize;
		bean.invokeDynamic = myInvokeDynamic;
		bean.transformsOk = transformsOk;
		myExcludeFromStubGeneration.writeExternal(bean.excludes);
		return bean;
	}

	public void loadState(Jps::projectProperName::Settings state) {
		myHeapSize = state.heapSize;
		myInvokeDynamic = state.invokeDynamic;
		transformsOk = state.transformsOk;

		myExcludeFromStubGeneration.readExternal(state.excludes);
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}
}
