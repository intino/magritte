package monet.::projectName::.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Create::projectProperName::FileAction extends JavaCreateTemplateInPackageAction<::projectProperName::FileImpl> {

	public Create::projectProperName::FileAction() {
		super(::projectProperName::Bundle.message("newdefinition.menu.action.text"), ::projectProperName::Bundle.message("newdefinition.menu.action.description"),
			::projectProperName::Icons.ICON_13, true);
	}

	\@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder
			.setTitle(::projectProperName::Bundle.message("newdefinition.dlg.prompt"))
			.addKind("::projectProperName:: Definition", ::projectProperName::FileType.INSTANCE.getIcon(), ::projectProperName::Templates.::projectUpperName::_DEFINITION);
		::empty| ::
	}

	\@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return ::projectProperName::Bundle.message("newdefinition.menu.action.text");
	}

	\@Nullable
	\@Override
	protected PsiElement getNavigationElement(\@NotNull ::projectProperName::FileImpl createdElement) {
		return createdElement;
	}

	\@Nullable
	\@Override
	protected ::projectProperName::FileImpl doCreate(PsiDirectory directory, String newName, String templateName) throws IncorrectOperationException {
		String fileName = newName + ".m1";
		PsiFile file = ::projectProperName::TemplatesFactory.createFromTemplate(directory, newName, fileName, templateName, true);
		if (file instanceof ::projectProperName::FileImpl) return (::projectProperName::FileImpl) file;
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(::projectProperName::Bundle.message("::projectName::.file.extension.is.not.mapped.to.::projectName::.file.type", description));
	}
}