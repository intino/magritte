package monet.tara.intellij.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.project.TaraTemplates;
import monet.tara.intellij.project.TaraTemplatesFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CreateTaraFileAction extends NewTaraActionBase implements DumbAware {

	public CreateTaraFileAction() {
		super("Concept", "Creates Concept file", TaraFileType.INSTANCE.getIcon());
	}

	@Override
	protected String getCommandName() {
		return "Create Concept";
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName) {
		return null;
	}

	@Override
	protected String getDialogPrompt() {
		return "New Concept";
	}

	@Override
	protected String getDialogTitle() {
		return "Create a Concept";
	}

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		return super.isAvailable(dataContext);
	}

	@NotNull
	@Override
	protected PsiElement[] doCreate(String newName, PsiDirectory directory) throws Exception {
		PsiFile file = createTaraFromTemplate(directory, newName, TaraTemplates.CONCEPT);
		PsiElement child = null;
		if (file.getChildren().length > 0)
			child = file.getLastChild();
		return child != null ? new PsiElement[]{ file, child } : new PsiElement[]{ file };
	}

	private static PsiFile createTaraFromTemplate(final PsiDirectory directory, String className, String templateName,
	                                              @NonNls String... parameters) throws IncorrectOperationException {
		return TaraTemplatesFactory.createFromTemplate(directory,
			className + "." + TaraFileType.INSTANCE.getDefaultExtension(), templateName, true);
	}
}