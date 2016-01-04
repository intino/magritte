package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraBody;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BodyMixin extends ASTWrapperPsiElement {

	public BodyMixin(@NotNull ASTNode node) {
		super(node);
	}

	public void delete() throws IncorrectOperationException {
		final ASTNode parentNode = getParent().getNode();
		assert parentNode != null;
		ASTNode node = getNode();
		ASTNode prev = node.getTreePrev();
		ASTNode next = node.getTreeNext();
		parentNode.removeChild(node);
		if ((prev == null || prev.getElementType() == TokenType.WHITE_SPACE) && next != null &&
			next.getElementType() == TokenType.WHITE_SPACE) {
			parentNode.removeChild(next);
		}
	}

	public TaraModelImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraModelImpl) super.getContainingFile();
	}

	public PsiElement getPsiElement() {
		return this;
	}

	public List<Node> getNodeLinks() {
		Node[] references = PsiTreeUtil.getChildrenOfType(this, TaraNodeReference.class);
		return references == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(Arrays.asList(references));
	}

	public List<PsiElement> getStatements() {
		List<PsiElement> statements = new ArrayList<>();
		statements.addAll(((TaraBody) this).getVariableList());
		statements.addAll(((TaraBody) this).getVarInitList());
		statements.addAll(((TaraBody) this).getNodeList());
		statements.addAll(((TaraBody) this).getNodeReferenceList());
		statements.addAll(((TaraBody) this).getFacetApplyList());
		return statements;
	}

}
