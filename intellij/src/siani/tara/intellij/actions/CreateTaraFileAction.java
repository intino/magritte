package siani.tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraModelImpl> {

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
	protected PsiElement getNavigationElement(@NotNull TaraModelImpl createdElement) {
		return createdElement;
	}

	@Nullable
	@Override
	protected TaraModelImpl doCreate(PsiDirectory directory, String newName, String templateName) throws IncorrectOperationException {
		String fileName = newName + "." + TaraFileType.INSTANCE.getDefaultExtension();
		Module moduleOfDirectory = TaraUtil.getModuleOfDirectory(directory);
		String parentName = ModuleConfiguration.getInstance(moduleOfDirectory).getMetamodelName();
		PsiFile file;
		String[] list;
		list = parentName != null ? new String[]{"MODULE_NAME", moduleOfDirectory.getName(), "PARENT_MODULE_NAME", parentName}
			: new String[]{"MODULE_NAME", moduleOfDirectory.getName()};
		file = TaraTemplatesFactory.createFromTemplate(directory, newName, fileName, templateName, true, list);
		if (file instanceof TaraModelImpl) {
			setCaret(file);
			return (TaraModelImpl) file;
		}
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(MessageProvider.message("tara.file.extension.is.not.mapped.to.tara.file.type", description));
	}

	public void setCaret(PsiFile file) {
		PsiDocumentManager dm = PsiDocumentManager.getInstance(file.getProject());
		Document doc = dm.getDocument(file);
		if (doc == null) return;
		Editor editor = EditorFactory.getInstance().createEditor(doc, file.getProject(), file.getFileType(), false);
		editor.getCaretModel().moveToOffset(file.getText().length() - 1);
	}
}