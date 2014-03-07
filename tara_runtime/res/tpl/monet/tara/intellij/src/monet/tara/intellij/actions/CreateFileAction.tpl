package monet.::projectName::.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;

public class Create::projectProperName::FileAction extends CreateFileFromTemplateAction implements DumbAware {

	public Create::projectProperName::FileAction() {
		super("Definition", "Creates Definition file", ::projectProperName::FileType.INSTANCE.getIcon());
		if (FileTemplateManager.getInstance().getInternalTemplate("Definition") != null)
			FileTemplateManager.getInstance().removeTemplate(FileTemplateManager.getInstance().getInternalTemplate("Definition"));
		FileTemplateManager.getInstance().addTemplate("Definition", "m2").setText(getDefinitionTemplate());
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle("New ::projectProperName:: file").addKind("::projectProperName:: Definition", ::projectProperName::FileType.INSTANCE.getIcon(), "Definition");
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return "Create ::projectProperName:: definition " + newName;
	}


	public String getDefinitionTemplate() {
		return "' ${DATE}. Documentation of the definition\n" +
			"Definition as ${NAME}\n";
	}

}