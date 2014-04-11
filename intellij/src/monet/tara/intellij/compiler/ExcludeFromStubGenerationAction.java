package monet.tara.intellij.compiler;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.compiler.options.ExcludeEntryDescription;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import monet.tara.intellij.metamodel.file.TaraFileType;

public class ExcludeFromStubGenerationAction extends AnAction implements DumbAware {
	public static void doExcludeFromStubGeneration(PsiFile file) {
		final VirtualFile virtualFile = file.getVirtualFile();
		assert virtualFile != null;
		final Project project = file.getProject();

		final TaraCompilerConfigurable configurable = new TaraCompilerConfigurable(project);
		ShowSettingsUtil.getInstance().editConfigurable(project, configurable, new Runnable() {
			public void run() {
				configurable.getExcludes().addEntry(new ExcludeEntryDescription(virtualFile, false, true, project));
			}
		});
	}

	private static boolean isEnabled(AnActionEvent e) {
		PsiFile file = e.getData(DataKeys.PSI_FILE);
		if (file == null || file.getLanguage() != TaraFileType.INSTANCE.getLanguage()) {
			return false;
		}

		final VirtualFile virtualFile = file.getVirtualFile();
		return virtualFile != null && !TaraCompilerConfiguration.getExcludeConfiguration(file.getProject()).isExcluded(virtualFile);
	}

	public void actionPerformed(final AnActionEvent e) {
		final PsiFile file = e.getData(DataKeys.PSI_FILE);

		assert file != null && file.getLanguage() == TaraFileType.INSTANCE.getLanguage();

		doExcludeFromStubGeneration(file);
	}

	public void update(AnActionEvent e) {
		Presentation presentation = e.getPresentation();

		boolean enabled = isEnabled(e);
		presentation.setEnabled(enabled);
		presentation.setVisible(enabled);
	}

}