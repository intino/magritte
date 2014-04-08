package monet.tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import monet.tara.intellij.metamodel.file.TaraFileType;

public class CreateFileAction extends CreateFileFromTemplateAction implements DumbAware {

	public CreateFileAction() {
		super("Concept", "Creates Concept file", TaraFileType.INSTANCE.getIcon());
		try {
			FileTemplateManager.getInstance().getInternalTemplate("Concept");
		} catch (Exception e) {
			FileTemplateManager.getInstance().addTemplate("Concept", "m2").setText(getConceptTemplate());
		}
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle("New Tara file").addKind("Tara Concept", TaraFileType.INSTANCE.getIcon(), "Concept");
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return "Create Tara concept " + newName;
	}


	public String getConceptTemplate() {
		return "' ${DATE}. Documentation of the concept\n" +
			"Concept as ${NAME}\n";
	}

}