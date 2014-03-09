package monet.::projectName::.intellij.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.jps.::projectName::.compiler.Jps::projectProperName::Settings;

@State(
	name = "::projectProperName::CompilerProjectConfiguration",
	storages = {
		@Storage(file = StoragePathMacros.PROJECT_FILE),
		@Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/::projectName::c.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)

public class ::projectProperName::CompilerConfiguration implements PersistentStateComponent<Jps::projectProperName::Settings>, Disposable {
	private String myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
	private boolean myInvokeDynamic = Jps::projectProperName::Settings.DEFAULT_INVOKE_DYNAMIC;
	public boolean transformsOk = Jps::projectProperName::Settings.DEFAULT_TRANSFORMS_OK;
	private final ExcludedEntriesConfiguration myExcludeFromStubGeneration = new ExcludedEntriesConfiguration();

	public ::projectProperName::CompilerConfiguration(Project project) {
		::projectProperName::CompilerWorkspaceConfiguration workspaceConfiguration = ServiceManager.getService(project, ::projectProperName::CompilerWorkspaceConfiguration.class);
		loadState(workspaceConfiguration.getState());
		workspaceConfiguration.myHeapSize = Jps::projectProperName::Settings.DEFAULT_HEAP_SIZE;
		workspaceConfiguration.transformsOk = Jps::projectProperName::Settings.DEFAULT_TRANSFORMS_OK;
		workspaceConfiguration.myInvokeDynamic = Jps::projectProperName::Settings.DEFAULT_INVOKE_DYNAMIC;
		workspaceConfiguration.myExcludeFromStubGeneration.removeAllExcludeEntryDescriptions();
	}

	public Jps::projectProperName::Settings getState() {
		final Jps::projectProperName::Settings bean = new Jps::projectProperName::Settings();
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

	public void loadState(Jps::projectProperName::Settings state) {
		myHeapSize = state.heapSize;
		myInvokeDynamic = state.invokeDynamic;
		transformsOk = state.transformsOk;
		myExcludeFromStubGeneration.readExternal(state.excludes);
	}

	public static ::projectProperName::CompilerConfiguration getInstance(Project project) {
		return ServiceManager.getService(project, ::projectProperName::CompilerConfiguration.class);
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
