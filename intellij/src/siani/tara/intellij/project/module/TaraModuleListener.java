package siani.tara.intellij.project.module;

import com.intellij.ProjectTopics;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.ModuleAdapter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.Function;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaraModuleListener implements ProjectComponent {

	private final Project myProject;
	private final MessageBus myMessageBus;
	private MessageBusConnection myConnection;

	public TaraModuleListener(final Project project, final MessageBus messageBus) {
		myProject = project;
		myMessageBus = messageBus;
	}

	@Override
	public void projectOpened() {
		if (ApplicationManager.getApplication().isUnitTestMode()) return;

		final StartupManager manager = StartupManager.getInstance(myProject);
		manager.registerPostStartupActivity(new Runnable() {
			@Override
			public void run() {
				if (myMessageBus != null) {
					myConnection = myMessageBus.connect();
					final MyModuleListener listener = new MyModuleListener(myProject);
					myConnection.subscribe(ProjectTopics.MODULES, listener);
				}
			}
		});
	}

	@Override
	public void projectClosed() {
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void disposeComponent() {
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "ModuleChangedDetector";
	}

	private class MyModuleListener extends ModuleAdapter {

		private final Map<Module, String> myModulesNames = new HashMap<>();

		public MyModuleListener(Project project) {
			Disposer.register(project, new Disposable() {
				@Override
				public void dispose() {
					myModulesNames.clear();
				}
			});
		}

		@Override
		public void moduleAdded(Project project, Module module) {
			myModulesNames.put(module, module.getName());
		}

		@Override
		public void beforeModuleRemoved(Project project, Module module) {

		}

		@Override
		public void moduleRemoved(Project project, Module module) {
			myModulesNames.remove(module);
		}

		@Override
		public void modulesRenamed(Project project, List<Module> modules, Function<Module, String> oldNameProvider) {
			for (Module module : modules) {
				String newName = module.getName();
				String oldName = myModulesNames.put(module, newName);
				if (!newName.equals(oldName)) {
					changeDependentModules(modules, module, oldName);
				}

			}
		}

		private void changeDependentModules(List<Module> modules, Module rootModule, String oldName) {
			for (Module module : modules) {
				if (ModuleConfiguration.getInstance(module).getMetamodelName().equals(oldName)) {
					ModuleConfiguration.getInstance(module).setMetamodelFilePath(rootModule.getModuleFilePath());
					ModuleConfiguration.getInstance(module).setMetamodelName(rootModule.getName());
				}
				refreshFiles(TaraUtil.getTaraFilesOfModule(module));
			}

		}

		private void refreshFiles(List<TaraBoxFileImpl> files) {
			for (TaraBoxFileImpl file : files) file.updateDSL();
		}
	}
}
