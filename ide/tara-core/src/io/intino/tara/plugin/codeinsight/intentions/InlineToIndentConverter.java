package io.intino.tara.plugin.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.impl.TaraElementFactoryImpl;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraTypes;

import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;

public class InlineToIndentConverter extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		PsiElement toReplace = getReplacingElement(element);
		if (toReplace == null) return;
		replace(new TaraElementFactoryImpl(project), toReplace);
	}

	private void replace(TaraElementFactoryImpl factory, PsiElement toReplace) {
		PsiTreeUtil.getChildrenOfTypeAsList(TaraPsiImplUtil.getBodyContextOf(toReplace), LeafPsiElement.class).stream().
				filter(leaf -> is(leaf, TaraTypes.NEWLINE) && ";".equals(leaf.getText())).forEach(leaf -> {
			if (is(leaf.getNextSibling(), WHITE_SPACE)) leaf.getNextSibling().delete();
			if (is(leaf.getPrevSibling(), WHITE_SPACE)) leaf.getPrevSibling().delete();
			leaf.replace(factory.createBodyNewLine(TaraPsiImplUtil.getIndentation(toReplace)));
		});
		if (is(toReplace.getNextSibling(), WHITE_SPACE))
			toReplace.getNextSibling().delete();
		toReplace.replace(factory.createNewLineIndent(TaraPsiImplUtil.getIndentation(toReplace)));
	}


	private PsiElement getReplacingElement(PsiElement element) {
		if (is(element, NEW_LINE_INDENT)) return element;
		PsiElement previous = element.getPrevSibling() != null ? element.getPrevSibling() : element.getParent().getPrevSibling();
		if (is(previous, NEW_LINE_INDENT)) return previous;
		return null;
	}

	@NotNull
	public String getText() {
		return "To indented statement";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.isWritable() && isAvailable(element);
	}

	private boolean isAvailable(@NotNull PsiElement element) {
		PsiElement previous;
		if (element.getPrevSibling() != null) previous = element.getPrevSibling();
		else {
			if (element.getParent() == null) return false;
			previous = element.getParent().getPrevSibling();
			if (previous == null) return false;
		}
		PsiElement toReplace = getReplacingElement(element);
		return TaraPsiImplUtil.getIndentation(toReplace) != 0 && (isAfterInline(element, element.getText()) || isAfterInline(previous, previous.getText()));
	}

	private boolean isAfterInline(@NotNull PsiElement element, String text) {
		return ">".equals(text) && is(element, NEW_LINE_INDENT);
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
