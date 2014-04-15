package monet.::projectName::.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;

public class CreateFileAction extends CreateFileFromTemplateAction implements DumbAware {

	public CreateFileAction() {
		super("Definition", "Creates Definition file", ::projectProperName::FileType.INSTANCE.getIcon());
		try {
			FileTemplateManager.getInstance().getInternalTemplate("Definition");
		} catch (Exception e) {
			FileTemplateManager.getInstance().addTemplate("Definition", "m1").setText(getDefinitionTemplate());
		}
	}

	\@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle("New ::projectProperName:: file").addKind("::projectProperName:: Definition", ::projectProperName::FileType.INSTANCE.getIcon(), "Definition");
	}

	\@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return "Create ::projectProperName:: definition " + newName;
	}


	public String getDefinitionTemplate() {
		return "' ${DATE}. Documentation of the definition\\n" +
			"Definition as ${NAME}\\n";
	}

}