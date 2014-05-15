package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.documentation.::projectProperName::DocumentationFormatter;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;

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

	public String getType() {
		MetaIdentifier type = getSignature().getType();
		return type != null ? type.getText() \: null;
	}

	\@Override
	public String getName() {
		Identifier identifierNode = (Identifier) getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() \: null;
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
		return ::projectProperName::DocumentationFormatter.doc2Html(this, text.toString());
	}

	public PsiElement getPsiElement() {
		return this;
	}

	\@Override
	public Icon getIcon(\@IconFlags int i) {
		if (this.isCase())
			return ::projectProperName::Icons.MORPH_13;
		if (this.isBase()) return ::projectProperName::Icons.POLYMORPHIC_13;
		return ::projectProperName::Icons.DEFINITION_13;
	}

	\@NotNull
	public PsiElement setName(String name) {
		return ::projectProperName::PsiImplUtil.setName(this.getSignature(), name);
	}

	public PsiElement getIdentifierNode() {
		return ::projectProperName::PsiImplUtil.getIdentifierNode((Definition) this);
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

	public boolean isIntention() {
		Annotations annotations = this.getAnnotations();
		if (annotations != null) {
			for (PsiElement element \: annotations.getAnnotations())
				if (element instanceof ::projectProperName::Intention)
					return true;
		}
		return false;
	}

	public Definition[] getCases() {
		ArrayList<Definition> cases = new ArrayList<>();
		if (isBase()) {
			Definition[] children = ::projectProperName::Util.getChildrenOf((Definition) this);
			for (Definition child \: children) {
				if (child.isCase()) cases.add(child);
			}
		}
		return cases.size() > 0 ? cases.toArray(new Definition[cases.size()]) \: null;
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
