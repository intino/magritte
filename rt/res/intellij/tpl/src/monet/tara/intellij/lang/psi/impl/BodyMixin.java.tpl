package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.lang.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BodyMixin extends ASTWrapperPsiElement {

	public BodyMixin(\@NotNull ASTNode node) {
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

	public ::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException {
		return (::projectProperName::FileImpl) super.getContainingFile();
	}

	public PsiElement getPsiElement() {
		return this;
	}

	\@NotNull
	public List<? extends Attribute> getAttributeList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, Attribute.class);
	}

	\@NotNull
	public List<? extends Definition> getDefinitionList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, Definition.class);
	}

	\@NotNull
	public List<? extends ReferenceStatement> getReferenceStatementList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, ReferenceStatement.class);
	}

	\@NotNull
	public List<? extends Word> getWordList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, Word.class);
	}

}
