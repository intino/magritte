package monet.tara.compiler.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.DirectoryProjectGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraModuleBuilderBase extends ModuleBuilder {

	private final DirectoryProjectGenerator myGenerator;


	public TaraModuleBuilderBase(DirectoryProjectGenerator generator) {
		myGenerator = generator;
	}

	public TaraModuleBuilderBase() {
		myGenerator = null;
	}

	@Override
	public String getGroupName() {
		return "Tara";
	}

	public ModuleType getModuleType() {
		return TaraModuleTypeBase.getInstance();
	}


	@Override
	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		final CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
		compilerModuleExtension.setExcludeOutput(true);
		if (myJdk != null)
			rootModel.setSdk(myJdk);
		else
			rootModel.inheritSdk();

		doAddContentEntry(rootModel);
	}


	@Nullable
	@Override
	public Module commitModule(@NotNull Project project, @Nullable ModifiableModuleModel model) {
		Module module = super.commitModule(project, model);
		if (module != null && myGenerator != null) {
			ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
			VirtualFile[] contentRoots = moduleRootManager.getContentRoots();
			VirtualFile dir = module.getProject().getBaseDir();
			if (contentRoots.length > 0 && contentRoots[0] != null) {
				dir = contentRoots[0];
			}
			myGenerator.generateProject(project, dir, null, module);
		}
		return module;
	}
}
