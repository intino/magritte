package tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;
import org.jetbrains.jps.model.module.JpsModuleSourceRootType;
import tara.intellij.MessageProvider;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;

import java.util.Map;
import java.util.Set;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraModelImpl> {

	private Set<? extends JpsModuleSourceRootType<?>> mySourceRootTypes = JavaModuleSourceRootTypes.SOURCES;

	public CreateTaraFileAction() {
		super(MessageProvider.message("new.model.menu.action.text"), MessageProvider.message("new.model.menu.action.description"), TaraIcons.MODEL, true);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle(MessageProvider.message("new.model.dlg.prompt"));
		String model = TaraTemplates.getTemplate("MODEL");
		builder.addKind("Model", TaraIcons.MODEL, model);
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return MessageProvider.message("new.model.menu.action.text");
	}

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		PsiElement data = CommonDataKeys.PSI_ELEMENT.getData(dataContext);
		if (!(data instanceof PsiDirectory)) return false;
		Module module = ModuleProvider.getModuleOf(data);
		return super.isAvailable(dataContext) && TaraFacet.isOfType(module) && isInModelDirectory((PsiDirectory) data, module);
	}

	private boolean isInModelDirectory(PsiDirectory dir, Module module) {
		return isIn(getModelSourceRoot(module), dir);
	}

	private boolean isIn(VirtualFile modelSourceRoot, PsiDirectory dir) {
		if (modelSourceRoot == null) return false;
		PsiDirectory parent = dir;
		while (parent != null && !modelSourceRoot.equals(parent.getVirtualFile())) {
			parent = parent.getParent();
		}
		return parent != null && parent.getVirtualFile().equals(modelSourceRoot);
	}

	private VirtualFile getModelSourceRoot(Module module) {
		for (VirtualFile mySourceRootType : ModuleRootManager.getInstance(module).getSourceRoots())
			if (mySourceRootType.getName().equals("model")) return mySourceRootType;
		return null;
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
		Module moduleOfDirectory = ModuleProvider.getModuleOf(directory);
		TaraFacet facet = TaraFacet.getTaraFacetByModule(moduleOfDirectory);
		if (facet == null) throw new IncorrectOperationException(MessageProvider.message("tara.file.error"));
		String dsl = facet.getConfiguration().getDsl();
		PsiFile file;
		String[] list;
		list = dsl != null ? new String[]{"MODULE_NAME", moduleOfDirectory.getName(), "PARENT_MODULE_NAME", dsl}
			: new String[]{"MODULE_NAME", moduleOfDirectory.getName()};
		file = TaraTemplatesFactory.createFromTemplate(directory, newName, fileName, templateName, true, list);
		if (file instanceof TaraModelImpl) return (TaraModelImpl) file;
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(MessageProvider.message("tara.file.extension.is.not.mapped.to.tara.file.type", description));
	}

	@Override
	protected void postProcess(TaraModelImpl createdElement, String templateName, Map<String, String> customProperties) {
		super.postProcess(createdElement, templateName, customProperties);
		setCaret(createdElement);
		createdElement.navigate(true);
	}

	public void setCaret(PsiFile file) {
		final PsiDocumentManager instance = PsiDocumentManager.getInstance(file.getProject());
		Document doc = instance.getDocument(file);
		if (doc == null) return;
		instance.commitDocument(doc);
		final int lineEndOffset = doc.getLineEndOffset(2);
		Editor editor = EditorFactory.getInstance().createEditor(doc, file.getProject(), file.getFileType(), false);
		editor.getCaretModel().moveToVisualPosition(editor.offsetToVisualPosition(lineEndOffset));
	}


}