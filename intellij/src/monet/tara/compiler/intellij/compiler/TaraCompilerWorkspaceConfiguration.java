package monet.tara.compiler.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.util.Disposer;
import monet.tara.jps.incremental.JpsTaraSettings;

@State(name = "TaraCompilerConfiguration", storages = @Storage(file = StoragePathMacros.WORKSPACE_FILE))
public class TaraCompilerWorkspaceConfiguration implements PersistentStateComponent<JpsTaraSettings>, Disposable {
	String myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
	boolean myInvokeDynamic = JpsTaraSettings.DEFAULT_INVOKE_DYNAMIC;
	boolean transformsOk = JpsTaraSettings.DEFAULT_TRANSFORMS_OK;
	final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();

	public JpsTaraSettings getState() {
		final JpsTaraSettings bean = new JpsTaraSettings();
		bean.heapSize = myHeapSize;
		bean.invokeDynamic = myInvokeDynamic;
		bean.transformsOk = transformsOk;
		myExcludeFromStubGeneration.writeExternal(bean.excludes);
		return bean;
	}

	public void loadState(JpsTaraSettings state) {
		myHeapSize = state.heapSize;
		myInvokeDynamic = state.invokeDynamic;
		transformsOk = state.transformsOk;

		myExcludeFromStubGeneration.readExternal(state.excludes);
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}
}
