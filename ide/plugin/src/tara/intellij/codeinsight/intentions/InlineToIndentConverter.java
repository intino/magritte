package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraTypes;
import tara.intellij.lang.psi.impl.TaraElementFactoryImpl;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;

public class InlineToIndentConverter extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		PsiElement toReplace = getReplacingElement(element);
		if (toReplace == null) return;
		TaraElementFactoryImpl factory = new TaraElementFactoryImpl(project);
		replace(factory, toReplace);
	}

	private void replace(TaraElementFactoryImpl factory, PsiElement toReplace) {
		for (LeafPsiElement leaf : PsiTreeUtil.getChildrenOfTypeAsList(TaraPsiImplUtil.getBodyContextOf(toReplace), LeafPsiElement.class))
			if (is(leaf, TaraTypes.NEWLINE) && ";".equals(leaf.getText())) {
				if (is(leaf.getNextSibling(), WHITE_SPACE)) leaf.getNextSibling().delete();
				if (is(leaf.getPrevSibling(), WHITE_SPACE)) leaf.getPrevSibling().delete();
				leaf.replace(factory.createBodyNewLine(getIndentation(toReplace) + 1));
			}
		if (is(toReplace.getNextSibling(), WHITE_SPACE))
			toReplace.getNextSibling().delete();
		toReplace.replace(factory.createNewLineIndent(getIndentation(toReplace) + 1));
	}

	private int getIndentation(PsiElement element) {
		PsiElement concept = (PsiElement) TaraPsiImplUtil.getContainerOf(element);
		if (concept == null) return 0;
		if (is(concept.getPrevSibling(), TaraTypes.NEWLINE) || is(concept.getPrevSibling(), TaraTypes.NEW_LINE_INDENT))
			return countTabs(concept.getPrevSibling().getText());
		return 0;
	}

	private int countTabs(String text) {
		int i = text.length() - text.replace("\t", "").length();
		return i + (text.length() - text.replace(" ", "").length()) / 4;
	}

	private PsiElement getReplacingElement(PsiElement element) {
		if (is(element, NEW_LINE_INDENT)) return element;
		PsiElement previous = element.getPrevSibling() != null ? element.getPrevSibling() : element.getParent().getPrevSibling();
		if (is(previous, NEW_LINE_INDENT)) return previous;
		return null;
	}

	@NotNull
	public String getText() {
		return "To Indented Statement";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		if (!element.isWritable()) return false;
		PsiElement previous;
		if (element.getPrevSibling() != null) previous = element.getPrevSibling();
		else {
			if (element.getParent() == null) return false;
			previous = element.getParent().getPrevSibling();
			if (previous == null) return false;
		}
		return ">".equals(element.getText()) && is(element, NEW_LINE_INDENT) || ">".equals(previous.getText()) && is(previous, NEW_LINE_INDENT);
	}

	private boolean is(PsiElement element, IElementType type) {
		return element != null && element.getNode().getElementType().equals(type);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}
}
