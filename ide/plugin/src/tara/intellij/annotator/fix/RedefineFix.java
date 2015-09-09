package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.ex.IdeDocumentHistory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.livetemplates.TaraTemplateContext;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;

public class RedefineFix implements IntentionAction {
	private final Node node;
	private final String[] parameters;

	public RedefineFix(PsiElement element, String... parameters) {
		this.node = element instanceof Node ? (Node) element : TaraPsiImplUtil.getContainerNodeOf(element);
		this.parameters = parameters;
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Redefine Variable";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		IdeDocumentHistory.getInstance(project).includeCurrentPlaceAsChangePlace();
		ApplicationManager.getApplication().runWriteAction(() -> {
			final PsiElement anchor = addLineSeparator(((TaraNode) node));
			final Editor bodyEditor = positionCursor(project, file, anchor);
			TemplateManager.getInstance(project).startTemplate(bodyEditor, createTemplate(file));
		});
		PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private PsiElement addLineSeparator(TaraNode node) {
		final PsiElement newLineIndent = TaraElementFactory.getInstance(node.getProject()).createBodyNewLine(TaraPsiImplUtil.getIndentation(node) + 1);
		return node.add(newLineIndent);
	}

	public Template createTemplate(PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", "var $TYPE$ $NAME$");
		template.addVariable("TYPE", "", '"' + parameters[1] + '"', true);
		template.addVariable("NAME", "", '"' + parameters[0] + '"', false);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	private static <T extends TemplateContextType> T contextType(Class<T> clazz) {
		return ContainerUtil.findInstance(TemplateContextType.EP_NAME.getExtensions(), clazz);
	}


	@Nullable("null means unable to open the editor")
	protected static Editor positionCursor(@NotNull Project project, @NotNull PsiFile targetFile, @NotNull PsiElement element) {
		TextRange range = element.getTextRange();
		int textOffset = range.getEndOffset();
		VirtualFile file = targetFile.getVirtualFile();
		if (file == null) {
			file = PsiUtilCore.getVirtualFile(element);
			if (file == null) return null;
		}
		OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file, textOffset);
		return FileEditorManager.getInstance(project).openTextEditor(descriptor, true);
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
