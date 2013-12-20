package monet.tara.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.tara.metamodel.file.TaraFileType;
import monet.tara.project.TaraTemplates;
import monet.tara.project.TaraTemplatesFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CreateTaraFileAction extends NewTaraActionBase implements DumbAware {

	public CreateTaraFileAction() {
		super("Metamodel Unit", "Creates a Metamodel file", TaraFileType.INSTANCE.getIcon());
	}

	@Override
	protected String getCommandName() {
		return "Create Metamodel Unit";
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName) {
		return null;
	}

	@Override
	protected String getDialogPrompt() {
		return "New Metamodel Unit";
	}

	@Override
	protected String getDialogTitle() {
		return "Create a Metamodel Unit";
	}

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		return super.isAvailable(dataContext);
	}

	@NotNull
	@Override
	protected PsiElement[] doCreate(String newName, PsiDirectory directory) throws Exception {
		PsiFile file = createTaraFromTemplate(directory, newName, TaraTemplates.METAMODEL_UNIT);
		PsiElement child = null;
		if (file.getChildren().length > 0)
			child = file.getLastChild();
		return child != null ? new PsiElement[]{ file, child } : new PsiElement[]{ file };
	}

	private static PsiFile createTaraFromTemplate(final PsiDirectory directory, String className, String templateName,
	                                              @NonNls String... parameters) throws IncorrectOperationException {
		return TaraTemplatesFactory.createFromTemplate(directory, className,
				className + "." + TaraFileType.INSTANCE.getDefaultExtension(), templateName, true, parameters);
	}
}