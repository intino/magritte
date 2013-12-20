package monet.tara.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import monet.tara.metamodel.file.TaraFileType;

public class CreateTaraFileAction extends CreateFileFromTemplateAction implements DumbAware {

	public CreateTaraFileAction() {
		super("Metamodel File", "Creates a Metamodel file from the specified template", TaraFileType.INSTANCE.getIcon());
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle("New Metamodel unit");
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return "Create Metamodel " + newName;
	}
}
