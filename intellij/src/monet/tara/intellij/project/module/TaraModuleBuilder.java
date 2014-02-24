package monet.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import monet.tara.intellij.metamodel.TaraIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaraModuleBuilder extends TaraModuleBuilderBase {

	private List<Pair<String, String>> mySourcePaths;

	public List<Pair<String, String>> getSourcePaths() {
		return mySourcePaths;
	}

	public void setSourcePaths(final List<Pair<String, String>> sourcePaths) {
		mySourcePaths = sourcePaths;
	}

	public void addSourcePath(final Pair<String, String> sourcePathInfo) {
		if (mySourcePaths == null) {
			mySourcePaths = new ArrayList<>();
		}
		mySourcePaths.add(sourcePathInfo);
	}

	@Override
	public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
		return getModuleType().createWizardSteps(wizardContext, this, modulesProvider);
	}

	@Override
	public Icon getNodeIcon() {
		return TaraIcons.ICON_13;
	}

}
