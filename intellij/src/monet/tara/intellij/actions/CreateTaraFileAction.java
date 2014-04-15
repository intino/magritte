package monet.tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraFileImpl> {

	public CreateTaraFileAction() {
		super(TaraBundle.message("newconcept.menu.action.text"), TaraBundle.message("newconcept.menu.action.description"),
			TaraIcons.ICON_13, true);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder
			.setTitle(TaraBundle.message("newconcept.dlg.prompt"))
			.addKind("Tara Concept", TaraFileType.INSTANCE.getIcon(), TaraTemplates.TARA_CONCEPT);
		//gen %definitions%

		//end
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return TaraBundle.message("newconcept.menu.action.text");
	}

	@Nullable
	@Override
	protected PsiElement getNavigationElement(@NotNull TaraFileImpl createdElement) {
		return createdElement;
	}

	@Nullable
	@Override
	protected TaraFileImpl doCreate(PsiDirectory directory, String newName, String templateName) throws IncorrectOperationException {
		String fileName = newName + ".m2";
		PsiFile file = TaraTemplatesFactory.createFromTemplate(directory, newName, fileName, templateName, true);
		if (file instanceof TaraFileImpl) return (TaraFileImpl) file;
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(TaraBundle.message("tara.file.extension.is.not.mapped.to.tara.file.type", description));
	}
}