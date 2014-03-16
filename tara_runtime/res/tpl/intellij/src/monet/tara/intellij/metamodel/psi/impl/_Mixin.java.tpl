package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DefinitionMixin extends ASTWrapperPsiElement {

	public DefinitionMixin(\@NotNull ASTNode node) {
		super(node);
	}

	\@NotNull
	public SearchScope getUseScope() {
		return GlobalSearchScope.allScope(getProject());
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

	\@Override
	public String getName() {
		return ::projectProperName::PsiImplUtil.getIdentifier((Definition) this);
	}

	public ::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException {
		return (::projectProperName::FileImpl) super.getContainingFile();
	}

	\@Nullable
	public String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		Doc doc = ((Definition) this).getDoc();
		String comment;
		if (doc != null) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "\#"), "!");
			text.insert(0, trimmed.trim());
			if (text.length() == 0) return null;
		} else
			text.append(this.getText());
		return text.toString();
	}

	public PsiElement getPsiElement() {
		return this;
	}

	\@Override
	public Icon getIcon(\@IconFlags int i) {
		if (this.isMorph())
		return ::projectProperName::Icons.MORPH_13;
		if (this.isPolymorphic()) return ::projectProperName::Icons.POLYMORPHIC_13;
		return ::projectProperName::Icons.CONCEPT_13;
	}

	\@NotNull
	public PsiElement setName(String newName) {
		return ::projectProperName::PsiImplUtil.setName(((Definition) this).getSignature(), newName);
	}

	public PsiElement getIdentifierNode() {
		return ::projectProperName::PsiImplUtil.getIdentifierNode((Definition) this);
	}


	public Body getBody() {
		return findChildByClass(Body.class);
	}

	public boolean isPolymorphic(){
		return ((Definition) this).getSignature().getPolymorphic() != null;
	}

	public boolean isMorph() {
		return ((Definition) this).getSignature().getMorph() != null;
	}

	\@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	\@Nullable
	public Annotations getAnnotations() {
		return findChildByClass(Annotations.class);
	}
}
