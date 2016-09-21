package tara.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.JavaCreateTemplateInPackageAction;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
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
import tara.intellij.actions.utils.TaraTemplates;
import tara.intellij.actions.utils.TaraTemplatesFactory;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.configuration.Configuration;
import tara.intellij.project.module.ModuleProvider;

import java.util.List;
import java.util.Map;

import static tara.intellij.lang.TaraIcons.ICON_16;
import static tara.intellij.messages.MessageProvider.message;

public class CreateTaraFileAction extends JavaCreateTemplateInPackageAction<TaraModelImpl> {

	public CreateTaraFileAction() {
		super(message("new.file.menu.action.text"), message("new.file.menu.action.description"), ICON_16, true);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
		builder.setTitle(message("new.model.dlg.prompt"));
		final Module module = ModuleProvider.moduleOf(directory);
		if (!TaraModuleType.isTara(module)) throw new IncorrectOperationException(message("tara.file.error"));
		final Configuration conf = TaraUtil.configurationOf(module);
		if (!conf.platformDsl().isEmpty()) builder.addKind(conf.platformDsl(), ICON_16, conf.platformDsl());
		if (!conf.applicationDsl().isEmpty()) builder.addKind(conf.applicationDsl(), ICON_16, conf.applicationDsl());
		if (!conf.systemDsl().isEmpty()) builder.addKind(conf.systemDsl(), ICON_16, conf.systemDsl());
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName) {
		return message("new.file.menu.action.text");
	}

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		PsiElement data = CommonDataKeys.PSI_ELEMENT.getData(dataContext);
		if (!(data instanceof PsiFile || data instanceof PsiDirectory)) return false;
		Module module = ModuleProvider.moduleOf(data);
		return super.isAvailable(dataContext) && TaraModuleType.isTara(module);
	}

	@Nullable
	@Override
	protected PsiElement getNavigationElement(@NotNull TaraModelImpl createdElement) {
		return createdElement;
	}

	@Nullable
	@Override
	protected TaraModelImpl doCreate(PsiDirectory directory, String newName, String dsl) throws IncorrectOperationException {
		String template = TaraTemplates.getTemplate("FILE");
		String fileName = newName + "." + TaraFileType.instance().getDefaultExtension();
		PsiFile file = TaraTemplatesFactory.createFromTemplate(directory, newName, fileName, template, true, "DSL", dsl);
		final Module module = ModuleProvider.moduleOf(directory);
		if (isTest(directory, module) && dsl.equals(TaraUtil.configurationOf(module).systemDsl()))
			TestClassCreator.creteTestClass(module, dsl, newName);
		return file instanceof TaraModelImpl ? (TaraModelImpl) file : error(file);
	}

	private boolean isTest(PsiElement dir, Module module) {
		final List<VirtualFile> roots = testContentRoot(module);
		for (VirtualFile root : roots) if (isIn(root, dir)) return true;
		return false;
	}

	private boolean isIn(VirtualFile modelSourceRoot, PsiElement dir) {
		if (modelSourceRoot == null) return false;
		PsiElement parent = dir;
		while (parent != null && !modelSourceRoot.equals(virtualFileOf(parent)))
			parent = parent.getParent();
		return parent != null && virtualFileOf(parent).equals(modelSourceRoot);
	}

	private VirtualFile virtualFileOf(PsiElement element) {
		return element instanceof PsiDirectory ? ((PsiDirectory) element).getVirtualFile() : ((PsiFile) element).getVirtualFile();
	}

	private List<VirtualFile> testContentRoot(Module module) {
		return ModuleRootManager.getInstance(module).getSourceRoots(JavaModuleSourceRootTypes.TESTS);
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

	private void setCaret(PsiFile file) {
		final PsiDocumentManager instance = PsiDocumentManager.getInstance(file.getProject());
		Document doc = instance.getDocument(file);
		if (doc == null) return;
		instance.commitDocument(doc);
	}
}