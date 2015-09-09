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
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Primitives;
import tara.language.semantics.Constraint;

import java.util.List;
import java.util.stream.Collectors;

public class AddRequiredParameterFix implements IntentionAction {

	private final Node node;

	public AddRequiredParameterFix(PsiElement element) {
		this.node = element instanceof Node ? (Node) element : TaraPsiImplUtil.getContainerNodeOf(element);
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Add required parameters";
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
		List<Constraint.Require.Parameter> requires = TaraUtil.getConstraintsOf(node).stream().
			filter(constraint -> constraint instanceof Constraint.Require.Parameter).
			map(constraint -> (Constraint.Require.Parameter) constraint).collect(Collectors.toList());
		filterPresentParameters(requires);
		createLiveTemplateFor(requires, file, editor);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private void createLiveTemplateFor(List<Constraint.Require.Parameter> requires, PsiFile file, Editor editor) {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		IdeDocumentHistory.getInstance(file.getProject()).includeCurrentPlaceAsChangePlace();
		ApplicationManager.getApplication().runWriteAction(() -> {
			PsiElement anchor = findAnchor();
			PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
			final Editor parameterEditor = positionCursor(file.getProject(), file, anchor);
			TemplateManager.getInstance(file.getProject()).startTemplate(parameterEditor, createTemplate(requires, file));
			PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(parameterEditor.getDocument());
		});
	}

	private PsiElement findAnchor() {
		TaraNode taraNode = (TaraNode) node;
		if (taraNode.getSignature().getParameters() == null) {
			final PsiElement emptyParameters = TaraElementFactory.getInstance(taraNode.getProject()).createEmptyParameters();
			return taraNode.getSignature().addAfter(emptyParameters, taraNode.getSignature().getMetaIdentifier()).getFirstChild();

		} else {
			final List<Parameter> parameters = taraNode.getSignature().getParameters().getParameters();
			return (PsiElement) parameters.get(parameters.size() - 1);
		}

	}

	public Template createTemplate(List<Constraint.Require.Parameter> requires, PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", createTemplateText(requires));
		addVariables(template, requires);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	public void addVariables(Template template, List<Constraint.Require.Parameter> requires) {
		for (int i = 0; i < requires.size(); i++)
			template.addVariable("VALUE" + i, "", '"' + (mustBeQuoted(requires.get(i)) ? "\\\"\\\"" : "") + '"', true);
	}

	private boolean mustBeQuoted(Constraint.Require.Parameter parameter) {
		return Primitives.DATE.equals(parameter.type()) || Primitives.STRING.equals(parameter.type()) || Primitives.TIME.equals(parameter.type());
	}


	private static <T extends TemplateContextType> T contextType(Class<T> clazz) {
		return ContainerUtil.findInstance(TemplateContextType.EP_NAME.getExtensions(), clazz);
	}

	public String createTemplateText(List<Constraint.Require.Parameter> requires) {
		String text = "";
		for (int i = 0; i < requires.size(); i++) {
			text += ", " + requires.get(i).name() + " = " + "$VALUE" + i + "$";
		}
		return text.substring(2);
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

	private void filterPresentParameters(List<Constraint.Require.Parameter> requires) {
		for (Parameter parameter : node.parameters()) {
			Constraint.Require.Parameter require = findInRequires(requires, parameter.name());
			if (require != null) requires.remove(require);
		}
	}

	private Constraint.Require.Parameter findInRequires(List<Constraint.Require.Parameter> requires, String name) {
		for (Constraint.Require.Parameter require : requires) if (require.name().equals(name)) return require;
		return null;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
