package monet.tara.intellij.project.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultRunner extends Runner {

	@Override
	public boolean isValidModule(@NotNull Module module) {
		return true;
	}

	@Override
	public boolean ensureRunnerConfigured(@Nullable Module module, RunProfile profile, Executor executor, final Project project) throws ExecutionException {
		if (module == null)
			throw new ExecutionException("Module is not specified");
//		if (LibrariesUtil.getGroovyHomePath(module) == null) {
//			ExecutionUtil.handleExecutionError(project, executor.getToolWindowId(), profile, new ExecutionException("Tara is not configured"));
//			ModulesConfigurator.showDialog(module.getProject(), module.getName(), ClasspathEditor.NAME);
//			return false;
//		}
		return true;
	}
}

