package siani.tara.intellij.codeinsight.intentions;

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
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraElementFactoryImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.psi.TokenType.NEW_LINE_INDENT;

public class IndentToInlineConverter extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		PsiElement toReplace = getTargetElement(element);
		List<PsiElement> replaced = new ArrayList<>();
		if (toReplace == null) return;
		TaraElementFactoryImpl factory = new TaraElementFactoryImpl(project);
		Body body = TaraPsiImplUtil.getBodyContextOf(element);
		propagateIndents(replaced, factory, body);
		addSpaces(factory, replaced);
	}

	private void propagateIndents(List<PsiElement> replaced, TaraElementFactoryImpl factory, Body body) {
		for (LeafPsiElement leaf : PsiTreeUtil.getChildrenOfTypeAsList(body, LeafPsiElement.class)) {
			if (is(leaf, TaraTypes.NEWLINE))
				replaced.add(leaf.replace(factory.createInlineNewLine()));
			if (is(leaf, TaraTypes.NEW_LINE_INDENT))
				replaced.add(leaf.replace(factory.createInlineNewLineIndent()));
		}
		for (Concept concept : body.getConceptList())
			if (concept.getBody() != null) propagateIndents(replaced, factory, concept.getBody());
		for (TaraFacetTarget facetTarget : body.getFacetTargetList())
			if (facetTarget.getBody() != null) propagateIndents(replaced, factory, facetTarget.getBody());
		for (FacetApply apply : body.getFacetApplyList())
			if (apply.getBody() != null) propagateIndents(replaced, factory, apply.getBody());

	}

	private PsiElement getTargetElement(PsiElement element) {
		PsiElement previous = element.getPrevSibling() != null ? element.getPrevSibling() : element.getParent().getPrevSibling();
		if (is(previous, NEW_LINE_INDENT))
			return previous;
		if (is(previous, TaraTypes.NEWLINE) && element.getParent().getParent() instanceof Body)
			return element.getParent().getParent().getFirstChild();
		if (previous == null)
			previous = TaraPsiImplUtil.getContextOf(element).getPrevSibling();
		return previous;
	}

	private void addSpaces(TaraElementFactoryImpl factory, List<PsiElement> replaced) {
		for (PsiElement replace : replaced) {
			replace.getParent().addAfter(factory.createWhiteSpace(), replace);
			replace.getParent().addBefore(factory.createWhiteSpace(), replace);
		}
	}

	@NotNull
	public String getText() {
		return "To Inline Statement";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		if (!element.isWritable()) return false;
		PsiElement toCheck = getReplacingElement(element);
		return toCheck != null && !toCheck.getText().contains(">");
	}

	private PsiElement getReplacingElement(PsiElement element) {
		if (is(element, NEW_LINE_INDENT) || (is(element, TaraTypes.NEWLINE) && element.getParent().getParent() instanceof Body))
			return element;
		PsiElement previous = element.getPrevSibling() != null ? element.getPrevSibling() : element.getParent().getPrevSibling();
		if (previous == null) {
			PsiElement contextOf = TaraPsiImplUtil.getContextOf(element);
			if (contextOf != null) previous = contextOf.getPrevSibling();
		}
		if (is(previous, NEW_LINE_INDENT) || (is(previous, TaraTypes.NEWLINE) && element.getParent().getParent() instanceof Body))
			return previous;
		return null;
	}

	private boolean is(PsiElement element, IElementType type) {
		if (element == null || !element.getLanguage().is(TaraLanguage.INSTANCE)) return false;
		return element.getNode().getElementType().equals(type);
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
