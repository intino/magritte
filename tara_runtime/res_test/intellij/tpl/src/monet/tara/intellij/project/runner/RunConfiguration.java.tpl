package monet.::projectName::.intellij.project.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.ExternalizablePath;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizer;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringElementAdapter;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.hash.LinkedHashMap;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RunConfiguration extends ModuleBasedConfiguration<RunConfigurationModule> implements RefactoringListenerProvider {
	private final Map<String, String> envs = new LinkedHashMap<>();
	private String workDir;
	private boolean isDebugEnabled;
	\@Nullable
	private String deployParams;
	private String deployURL;

	public RunConfiguration(final String name, final Project project, final ConfigurationFactory factory) {
		super(name, new RunConfigurationModule(project), factory);
		workDir = PathUtil.getLocalPath(project.getBaseDir());
	}

	public String getWorkDir() {
		return workDir;
	}

	\@Nullable
	public Module getModule() {
		return getConfigurationModule().getModule();
	}

	public Collection<Module> getValidModules() {
		Module[] modules = ModuleManager.getInstance(getProject()).getModules();
		final Runner scriptRunner = findConfiguration();
		if (scriptRunner == null)
			return Arrays.asList(modules);
		List res = new ArrayList<>();
		for (Module module \: modules)
			if (scriptRunner.isValidModule(module))
				res.add(module);
		return res;
	}

	\@Nullable
	private Runner findConfiguration() {
		return new DefaultRunner();
	}

	public void readExternal(Element element) throws InvalidDataException {
		PathMacroManager.getInstance(getProject()).expandPaths(element);
		super.readExternal(element);
		readModule(element);
		deployParams = JDOMExternalizer.readString(element, "deployParams");
		JDOMExternalizer.write(element, "url", deployURL);
		final String wrk = JDOMExternalizer.readString(element, "workDir");
		if (!".".equals(wrk))
			workDir = ExternalizablePath.localPathValue(wrk);
		isDebugEnabled = Boolean.parseBoolean(JDOMExternalizer.readString(element, "debug"));
		envs.clear();
		JDOMExternalizer.readMap(element, envs, null, "env");
	}

	public void writeExternal(Element element) throws WriteExternalException {
		super.writeExternal(element);
		writeModule(element);
		JDOMExternalizer.write(element, "params", deployParams);
		JDOMExternalizer.write(element, "url", deployURL);
		JDOMExternalizer.write(element, "debug", isDebugEnabled);
		JDOMExternalizer.writeMap(element, envs, null, "env");
		PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
	}

	public RunProfileState getState(\@NotNull Executor executor, \@NotNull ExecutionEnvironment environment) throws ExecutionException {
		return null;
	}


	\@Override
	public RefactoringElementListener getRefactoringElementListener(PsiElement element) {
		final PsiClass classToRun = RunnerUtil.getRunningClass(element);

		if (element instanceof ::projectProperName::FileImpl) {
			return new RefactoringElementAdapter() {
				\@Override
				protected void elementRenamedOrMoved(\@NotNull PsiElement newElement) {
					//TODO
				}

				\@Override
				public void undoElementMovedOrRenamed(\@NotNull PsiElement newElement, \@NotNull String oldQualifiedName) {
					elementRenamedOrMoved(newElement);
				}
			};
		} else if (element instanceof PsiClass && element.getManager().areElementsEquivalent(element, classToRun)) {
			return new RefactoringElementAdapter() {
				\@Override
				protected void elementRenamedOrMoved(\@NotNull PsiElement newElement) {
					setName(((PsiClass) newElement).getName());
				}

				\@Override
				public void undoElementMovedOrRenamed(\@NotNull PsiElement newElement, \@NotNull String oldQualifiedName) {
					elementRenamedOrMoved(newElement);
				}
			};
		}
		return null;
	}


	\@NotNull
	public SettingsEditor<? extends com.intellij.execution.configurations.RunConfiguration> getConfigurationEditor() {
		return new RunConfigurationEditor();
	}


	public boolean isDebugEnabled() {
		return isDebugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		isDebugEnabled = debugEnabled;
	}

	public String getDeployURL() {
		return deployURL;
	}

	public void setDeployURL(String deployURL) {
		this.deployURL = deployURL;
	}
}
