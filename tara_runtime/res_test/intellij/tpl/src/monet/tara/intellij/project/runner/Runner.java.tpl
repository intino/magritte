package monet.::projectName::.intellij.project.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Runner {
	public abstract boolean isValidModule(\@NotNull Module module);

	public abstract boolean ensureRunnerConfigured(\@Nullable Module module, RunProfile profile, Executor executor, final Project project) throws ExecutionException;


	public boolean shouldRefreshAfterFinish() {
		return false;
	}


}
