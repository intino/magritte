package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraComponent;
import monet.tara.intellij.metamodel.psi.TaraDoc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TaraComponentMixin extends ASTWrapperPsiElement {

	public TaraComponentMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public SearchScope getUseScope() {
		// concept ref can occur in any file
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

	@Override
	public String getName() {
		return TaraPsiImplUtil.getIdentifier((TaraComponent) this);
	}

	public TaraFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraFileImpl) super.getContainingFile();
	}

	@Nullable
	public String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		TaraDoc doc = ((TaraComponent) this).getDoc();
		String comment;

		if (doc != null) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
			text.insert(0, trimmed.trim());
			if (text.length() == 0) return null;
		} else
			text.append(this.getText());
		return text.toString();
	}

	public PsiElement getPsiElement() {
		return this;
	}

	@NotNull
	public PsiElement setName(String newName) {
		return TaraPsiImplUtil.setName(((TaraComponent) this).getConceptSignature(), newName);
	}

	public PsiElement getIdentifierNode() {
		return TaraPsiImplUtil.getIdentifierNode((IConcept) this);
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.CONCEPT_13;
	}
}
