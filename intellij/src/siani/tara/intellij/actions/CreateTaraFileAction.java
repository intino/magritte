package siani.tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraBoxFileImpl> {

	public CreateTaraFileAction() {
		super(MessageProvider.message("new.model.menu.action.text"), MessageProvider.message("new.model.menu.action.description"),
			TaraIcons.getIcon(TaraIcons.MODEL), true);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle(MessageProvider.message("new.model.dlg.prompt"));
		String model = TaraTemplates.getTemplate("MODEL");
		builder.addKind("Model", TaraIcons.getIcon(TaraIcons.MODEL), model);
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return MessageProvider.message("new.model.menu.action.text");
	}

	@Nullable
	@Override
	protected PsiElement getNavigationElement(@NotNull TaraBoxFileImpl createdElement) {
		return createdElement;
	}

	@Nullable
	@Override
	protected TaraBoxFileImpl doCreate(PsiDirectory directory, String newName, String templateName) throws IncorrectOperationException {
		String fileName = newName + "." + TaraFileType.INSTANCE.getDefaultExtension();
		Module moduleOfDirectory = TaraUtil.getModuleOfDirectory(directory);
		String parentName = ModuleConfiguration.getInstance(moduleOfDirectory).getMetamodelName();
		PsiFile file;
		String[] list;
		list = parentName != null ? new String[]{"MODULE_NAME", moduleOfDirectory.getName(), "PARENT_MODULE_NAME", parentName, "TYPE", "Concept"}
			: new String[]{"MODULE_NAME", moduleOfDirectory.getName(), "TYPE", "Concept"};
		file = TaraTemplatesFactory.createFromTemplate(directory, newName, fileName, templateName, true, list);
		if (file instanceof TaraBoxFileImpl) return (TaraBoxFileImpl) file;
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(MessageProvider.message("tara.file.extension.is.not.mapped.to.tara.file.type", description));
	}
}