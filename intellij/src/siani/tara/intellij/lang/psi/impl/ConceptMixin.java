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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.documentation.TaraDocumentationFormatter;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		if (type == null && this.isSub()) {
			Concept baseConcept = getBaseConcept();
			return baseConcept != null ? baseConcept.getType() : null;
		} else
			return type != null ? type.getText() : null;
	}

	public Concept getBaseConcept() {
		return TaraPsiImplUtil.getParentOf((Concept) this);
	}

	public Concept[] getConceptSiblings() {
		Concept contextOf = TaraPsiImplUtil.getContextOf(this);
		if (contextOf == null) return ((TaraBoxFile) this.getContainingFile()).getConcepts();
		return contextOf.getConceptChildren();
	}

	public Concept[] getConceptChildren() {
		return TaraUtil.getChildrenOf((Concept) this);
	}

	public TaraConceptReference[] getConceptLinks() {
		return TaraUtil.getLinksOf((Concept) this);
	}

	@Nullable
	public String getParentConcept() {
		Signature signature = this.getSignature();
		return signature.getIdentifierReference() != null ? signature.getIdentifierReference().getText() : null;
	}

	public MetaIdentifier getMetaIdentifier() {
		return PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class)[0];
	}

	@Override
	public String getName() {
		Identifier identifierNode = getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() : null;
	}

	public String getQualifiedName() {
		Identifier identifierNode = getIdentifierNode();
		String name = identifierNode != null ? identifierNode.getText() : "annonymous";
		String packageName = ((TaraBoxFile) this.getContainingFile()).getBoxReference().getHeaderReference().getText();
		Concept concept = (Concept) this;
		while ((concept = TaraPsiImplUtil.getContextOf(concept)) != null) {
			name = concept.getName() + "." + name;
		}
		return packageName + "." + name;
	}

	public TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraBoxFileImpl) super.getContainingFile();
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
		if (this.isSub())
			return TaraIcons.getIcon(TaraIcons.CASE_13);
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@NotNull
	public PsiElement setName(String name) {
		return TaraPsiImplUtil.setName(this.getSignature(), name);
	}

	public Identifier getIdentifierNode() {
		return TaraPsiImplUtil.getIdentifierNode((Concept) this);
	}

	public Body getBody() {
		return findChildByClass(Body.class);
	}


	public boolean isSub() {
		return this.getSignature().isSub();
	}

	public boolean isIntention() {
		for (PsiElement annotation : getAnnotations())
			if (siani.tara.intellij.lang.parser.TaraAnnotations.INTENTION.equals(annotation.getText()))
				return true;
		return false;
	}

	public Concept[] getSubConcepts() {
		ArrayList<Concept> cases = new ArrayList<>();
		Concept[] children = TaraUtil.getChildrenOf((Concept) this);
		for (Concept child : children) {
			if (child.isSub()) cases.add(child);
		}
		return cases.size() > 0 ? cases.toArray(new Concept[cases.size()]) : new Concept[0];
	}

	public TaraFacetApply[] getFacetApplies() {
		if (this.getBody() == null) return new TaraFacetApply[0];
		List<TaraFacetApply> facetApplies = getBody().getFacetApplies();
		return facetApplies.toArray(new TaraFacetApply[facetApplies.size()]);
	}

	public TaraFacetTarget[] getFacetTargets() {
		if (this.getBody() == null) return new TaraFacetTarget[0];
		List<TaraFacetTarget> facetTargets = getBody().getFacetTargets();
		return facetTargets.toArray(new TaraFacetTarget[facetTargets.size()]);
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@NotNull
	public PsiElement[] getAnnotations() {
		List<PsiElement> list = new ArrayList<>();
		TaraAnnotationsAndFacets annotationsAndFacets = findChildByClass(TaraAnnotationsAndFacets.class);
		if (annotationsAndFacets != null)
			for (Annotations taraAnnotations : annotationsAndFacets.getAnnotationsList())
				Collections.addAll(list, taraAnnotations.getAnnotations());
		if (this.getBody() != null && getBody().getAnnotationsAndFacetsList() != null)
			for (TaraAnnotationsAndFacets inBody : getBody().getAnnotationsAndFacetsList())
				for (TaraAnnotations taraAnnotations : inBody.getAnnotationsList()) {
					Collections.addAll(list, taraAnnotations.getAnnotations());
				}
		return list.toArray(new PsiElement[list.size()]);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Concept && ((Concept) obj).getQualifiedName().equals(this.getQualifiedName()));
	}

	@Override
	public int hashCode() {
		return this.getQualifiedName().hashCode();
	}
}
