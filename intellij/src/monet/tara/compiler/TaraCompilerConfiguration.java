package monet.tara.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import monet.tara.jps.incremental.JpsTaraSettings;

@State(
	name = "TaraCompilerProjectConfiguration",
	storages = {
		@Storage(file = StoragePathMacros.PROJECT_FILE),
		@Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/tarac.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)

public class TaraCompilerConfiguration implements PersistentStateComponent<JpsTaraSettings>, Disposable {
	private String myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
	private boolean myInvokeDynamic = JpsTaraSettings.DEFAULT_INVOKE_DYNAMIC;
	public boolean transformsOk = JpsTaraSettings.DEFAULT_TRANSFORMS_OK;
	private final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();

	public TaraCompilerConfiguration(Project project) {
		TaraCompilerWorkspaceConfiguration workspaceConfiguration = ServiceManager.getService(project, TaraCompilerWorkspaceConfiguration.class);
		loadState(workspaceConfiguration.getState());
		workspaceConfiguration.myHeapSize = JpsTaraSettings.DEFAULT_HEAP_SIZE;
		workspaceConfiguration.transformsOk = JpsTaraSettings.DEFAULT_TRANSFORMS_OK;
		workspaceConfiguration.myInvokeDynamic = JpsTaraSettings.DEFAULT_INVOKE_DYNAMIC;
		workspaceConfiguration.myExcludeFromStubGeneration.removeAllExcludeEntryDescriptions();
	}

	public JpsTaraSettings getState() {
		final JpsTaraSettings bean = new JpsTaraSettings();
		bean.heapSize = myHeapSize;
		bean.invokeDynamic = myInvokeDynamic;
		bean.transformsOk = transformsOk;
		myExcludeFromStubGeneration.writeExternal(bean.excludes);
		return bean;
	}

	public static ExcludedEntriesConfiguration getExcludeConfiguration(Project project) {
		return getInstance(project).myExcludeFromStubGeneration;
	}

	public ExcludedEntriesConfiguration getExcludeFromStubGeneration() {
		return myExcludeFromStubGeneration;
	}

	public void loadState(JpsTaraSettings state) {
		myHeapSize = state.heapSize;
		myInvokeDynamic = state.invokeDynamic;
		transformsOk = state.transformsOk;
		myExcludeFromStubGeneration.readExternal(state.excludes);
	}

	public static TaraCompilerConfiguration getInstance(Project project) {
		return ServiceManager.getService(project, TaraCompilerConfiguration.class);
	}

	public String getHeapSize() {
		return myHeapSize;
	}

	public boolean isInvokeDynamic() {
		return myInvokeDynamic;
	}

	public void setHeapSize(String heapSize) {
		myHeapSize = heapSize;
	}

	public void setInvokeDynamic(boolean invokeDynamic) {
		myInvokeDynamic = invokeDynamic;
	}

	public void dispose() {
		Disposer.dispose(myExcludeFromStubGeneration);
	}

}
