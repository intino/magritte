package tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.actions.utils.TaraTemplates;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tara.intellij.messages.MessageProvider.message;
import static tara.intellij.actions.utils.TaraTemplatesFactory.createFromTemplate;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraModelImpl> {

	public CreateTaraFileAction() {
		super(message("new.model.menu.action.text"), message("new.model.menu.action.description"), TaraIcons.ICON_16, true);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle(message("new.model.dlg.prompt"));
		String model = TaraTemplates.getTemplate("MODEL");
		builder.addKind("Model", TaraIcons.ICON_16, model);
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return message("new.model.menu.action.text");
	}

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		PsiElement data = CommonDataKeys.PSI_ELEMENT.getData(dataContext);
		Module module = ModuleProvider.getModuleOf(data);
		return super.isAvailable(dataContext) && TaraFacet.isOfType(module) && isInModelDirectory(data, module);
	}

	private boolean isInModelDirectory(PsiElement dir, Module module) {
		return isIn(getModelSourceRoot(module), dir);
	}

	private boolean isIn(VirtualFile modelSourceRoot, PsiElement dir) {
		if (modelSourceRoot == null) return false;
		PsiElement parent = dir;
		while (parent != null && !modelSourceRoot.equals(getVirtualFile(parent)))
			parent = parent.getParent();
		return parent != null && getVirtualFile(parent).equals(modelSourceRoot);
	}

	private VirtualFile getVirtualFile(PsiElement element) {
		return element instanceof PsiDirectory ? ((PsiDirectory) element).getVirtualFile() : ((PsiFile) element).getVirtualFile();
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
		Module module = ModuleProvider.getModuleOf(directory);
		TaraFacet facet = TaraFacet.of(module);
		if (facet == null) throw new IncorrectOperationException(message("tara.file.error"));
		String dsl = facet.getConfiguration().getDsl();
		String[] parameters = dsl != null ?
			new String[]{"MODULE_NAME", module.getName(), "PARENT_MODULE_NAME", dsl} : new String[]{"MODULE_NAME", module.getName()};
		PsiFile file = createFromTemplate(directory, newName, fileName, templateName, true, parameters);
		if (facet.getConfiguration().isTest()) creteTestClass(module, facet.getConfiguration(), newName);
		return file instanceof TaraModelImpl ? (TaraModelImpl) file : error(file);
	}

	private void creteTestClass(Module module, TaraFacetConfiguration conf, String newName) {
		final PsiDirectory psiDirectory = testDirectory(module);
		if (psiDirectory == null) return;
		final PsiClass aClass = JavaDirectoryService.getInstance().createClass(psiDirectory, newName + "Test", "TaraTest", false, getTestTemplateParameters(module, conf, newName));
		assert aClass != null;
		VfsUtil.markDirtyAndRefresh(true, true, true, psiDirectory.getVirtualFile());
	}

	private Map<String, String> getTestTemplateParameters(Module module, TaraFacetConfiguration conf, String newName) {
		Map<String, String> map = new HashMap();
		map.put("NAME", newName);
		map.put("DOMAIN", conf.getDsl());
		if (LanguageManager.getLanguage(module) != null) map.put("ENGINE", LanguageManager.getLanguage(module).metaLanguage());
		return map;
	}

	private PsiDirectory testDirectory(Module module) {
		final List<VirtualFile> sourceRoots = TaraUtil.getSourceRoots(module);
		for (VirtualFile sourceRoot : sourceRoots)
			if (sourceRoot.isDirectory() && "test".equals(sourceRoot.getName()))
				return PsiManager.getInstance(module.getProject()).findDirectory(sourceRoot);
		return null;
	}

	private TaraModelImpl error(PsiFile file) {
		final String description = file.getFileType().getDescription();
		throw new IncorrectOperationException(message("tara.file.extension.is.not.mapped.to.tara.file.type", description));
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
		if (!editor.isDisposed()) ((EditorImpl) editor).release();
		editor.getCaretModel().moveToVisualPosition(editor.offsetToVisualPosition(lineEndOffset));
	}
}