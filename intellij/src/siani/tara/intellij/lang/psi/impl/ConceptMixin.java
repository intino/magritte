package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import siani.tara.intellij.documentation.TaraDocumentationFormatter;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;
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

	public String getType() {
		MetaIdentifier type = getSignature().getType();
		return type != null ? type.getText() : null;
	}

	public MetaIdentifier getMetaIdentifier() {
		return PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class)[0];
	}

	@Override
	public String getName() {
		Identifier identifierNode = (Identifier) getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() : null;
	}

	public String getQualifiedName() {
		Identifier identifierNode = (Identifier) getIdentifierNode();
		String name = identifierNode != null ? identifierNode.getText() : "annonymous";
		String packageName = ((TaraFile) this.getContainingFile()).getPackage().getHeaderReference().getText();
		Concept concept = (Concept) this;
		while ((concept = TaraPsiImplUtil.getContextOf(concept)) != null) {
			name = concept.getName() + "." + name;
		}
		return packageName + "." + name;
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
			return TaraIcons.getIcon(TaraIcons.CASE_13);
		if (this.isBase()) return TaraIcons.getIcon(TaraIcons.BASE_13);
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@NotNull
	public PsiElement setName(String name) {
		return TaraPsiImplUtil.setName(this.getSignature(), name);
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

	public boolean isIntention() {
		Annotations annotations = this.getAnnotations();
		if (annotations != null) {
			for (PsiElement element : annotations.getAnnotations())
				if (element instanceof TaraIntention)
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
