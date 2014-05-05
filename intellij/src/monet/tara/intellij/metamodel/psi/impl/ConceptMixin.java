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
import monet.tara.intellij.documentation.TaraDocumentationFormatter;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;

public class ConceptMixin extends ASTWrapperPsiElement {


	public ConceptMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
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

	@Override
	public String getName() {
		return TaraPsiImplUtil.getIdentifier((Concept) this);
	}

	public TaraFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraFileImpl) super.getContainingFile();
	}

	@Nullable
	public String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		Doc doc = ((Concept) this).getDoc();
		String comment;
		if (doc != null) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
			text.insert(0, trimmed.trim());
			if (text.length() == 0) return null;
		} else
			text.append(this.getText());
		return TaraDocumentationFormatter.doc2Html(this, text.toString());
	}

	public PsiElement getPsiElement() {
		return this;
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		if (this.isCase())
			return TaraIcons.MORPH_13;
		if (this.isBase()) return TaraIcons.POLYMORPHIC_13;
		return TaraIcons.CONCEPT_13;
	}

	@NotNull
	public PsiElement setName(String newName) {
		return TaraPsiImplUtil.setName(this.getSignature(), newName);
	}

	public PsiElement getIdentifierNode() {
		return TaraPsiImplUtil.getIdentifierNode((Concept) this);
	}

	public Body getBody() {
		return findChildByClass(Body.class);
	}

	public boolean isBase() {
		return this.getSignature().isBase();
	}

	public boolean isCase() {
		return this.getSignature().isCase();
	}

	public boolean isExtensible() {
		Annotations annotations = this.getAnnotations();
		if (annotations != null) {
			PsiElement[] taraAnnotations = annotations.getAnnotations();
			for (PsiElement element : taraAnnotations)
				if (element instanceof TaraExtensible)
					return true;
		}
		return false;
	}

	public boolean isExtension() {
		Annotations annotations = this.getAnnotations();
		if (annotations != null) {
			PsiElement[] taraAnnotations = annotations.getAnnotations();
			for (PsiElement element : taraAnnotations)
				if (element instanceof TaraExtension)
					return true;
		}
		return false;
	}

	public Concept[] getCases() {
		ArrayList<Concept> cases = new ArrayList<>();
		if (isBase()) {
			Concept[] children = TaraUtil.getChildrenOf((Concept) this);
			for (Concept child : children) {
				if (child.isCase()) cases.add(child);
			}
		}
		return cases.size() > 0 ? cases.toArray(new Concept[cases.size()]) : null;
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@Nullable
	public Annotations getAnnotations() {
		return findChildByClass(Annotations.class);
	}
}
