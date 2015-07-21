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
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraElementFactoryImpl;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Facet;
import tara.language.model.Node;

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
		for (Node node : body.getNodeList()) {
			final TaraBody nodeBody = ((TaraNode) node).getBody();
			if (nodeBody != null) propagateIndents(replaced, factory, nodeBody);
		}
		for (TaraFacetTarget facetTarget : body.getFacetTargetList())
			if (facetTarget.getBody() != null) propagateIndents(replaced, factory, facetTarget.getBody());
		for (Facet apply : body.getFacetApplyList()) {
			final TaraBody facetBody = ((TaraFacetApply) apply).getBody();
			if (facetBody != null) propagateIndents(replaced, factory, facetBody);
		}

	}

	private PsiElement getTargetElement(PsiElement element) {
		PsiElement previous = element.getPrevSibling() != null ? element.getPrevSibling() : element.getParent().getPrevSibling();
		if (is(previous, NEW_LINE_INDENT))
			return previous;
		if (is(previous, TaraTypes.NEWLINE) && element.getParent().getParent() instanceof Body)
			return element.getParent().getParent().getFirstChild();
		if (previous == null)
			previous = ((PsiElement) TaraPsiImplUtil.getContainerOf(element)).getPrevSibling();
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
		return "To inline statement";
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
			PsiElement contextOf = (PsiElement) TaraPsiImplUtil.getContainerOf(element);
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
