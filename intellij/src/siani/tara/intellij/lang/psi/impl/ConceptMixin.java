package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.documentation.TaraDocumentationFormatter;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.lang.Annotation.*;

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

	public Collection<Concept> getConceptSiblings() {
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(this);
		if (contextOf == null) return ((TaraBoxFile) this.getContainingFile()).getConcepts();
		return contextOf.getInnerConcepts();
	}

	public Collection<Concept> getInnerConcepts() {
		return TaraUtil.getInnerConceptsOf((Concept) this);
	}

	public Collection<Variable> getVariables() {
		return TaraPsiImplUtil.getVariablesInBody(this.getBody());
	}

	public Collection<VarInit> getVarInits() {
		if (this.getBody() == null) return Collections.EMPTY_LIST;
		return (Collection<VarInit>) this.getBody().getVarInitList();
	}

	public TaraConceptReference[] getConceptLinks() {
		return TaraUtil.getLinksOf((Concept) this);
	}

	@Nullable
	public String getParentConceptName() {
		Signature signature = this.getSignature();
		return signature.getParentReference() != null ? signature.getParentReference().getText() : null;
	}

	public Concept getParentConcept() {
		return this.getSignature().getParentConcept();
	}

	public MetaIdentifier getMetaIdentifier() {
		return PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class)[0];
	}

	@Override
	public String getName() {
		Identifier identifierNode = getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() : null;
	}

	@NotNull
	public Parameter[] getParameters() {
		if (getSignature().getParameters() == null) return new Parameter[0];
		return getSignature().getParameters().getParameters();
	}

	public String getQualifiedName() {
		Identifier identifierNode = getIdentifierNode();
		String name = identifierNode != null ? identifierNode.getText() : "annonymous";
		Concept concept = (Concept) this;
		while ((concept = TaraPsiImplUtil.getConceptContainerOf(concept)) != null)
			name = concept.getName() + "." + name;
		return name;
	}

	public String getMetaQualifiedName() {
		return TaraUtil.getMetaQualifiedName((Concept) this);
	}

	public TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraBoxFileImpl) super.getContainingFile();
	}

	public void addAddress(TaraAddress address) {
		final TreeElement copy = ChangeUtil.copyToElement(address);
		PsiElement psi = copy.getPsi();
		TaraSignature taraSignature = PsiTreeUtil.getChildrenOfType(this, TaraSignature.class)[0];
		taraSignature.getNode().addChild(ASTFactory.whitespace(" "));
		taraSignature.add(psi);
	}

	@Nullable
	public String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		Doc[] docs = this.getDoc();
		String comment;
		for (Doc doc : docs) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
			text.append(trimmed.trim()).append("\n");
		}
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

	@Nullable
	public Body getBody() {
		return findChildByClass(Body.class);
	}

	public boolean isSub() {
		return this.getSignature().isSub();
	}

	public boolean isRoot() {
		return TaraPsiImplUtil.getConceptContainerOf(this) == null;
	}

	public boolean isIntention() {
		for (PsiElement annotation : getNormalAnnotations())
			if (INTENTION.getName().equals(annotation.getText()))
				return true;
		Concept parent = null;
		if (getParentConceptName() != null) parent = getParentConcept();
		return parent != null && parent.isIntention();
	}

	public boolean isFacet() {
		return is(FACET);
	}

	public boolean isAddressed() {
		return is(ADDRESSED);
	}

	public boolean isAggregated() {
		return is(AGGREGATED) || isMetaAggregated();
	}

	public boolean isAnnotatedAsAggregated() {
		for (PsiElement annotation : getNormalAnnotations())
			if (AGGREGATED.getName().equals(annotation.getText()))
				return true;
		return false;
	}

	public boolean isProperty() {
		return is(PROPERTY);
	}

	public boolean isComponent() {
		return is(COMPONENT) || isMetaComponent();
	}

	private boolean isMetaComponent() {
		Model metamodel = TaraLanguage.getMetaModel(this.getFile());
		if (metamodel == null) return false;
		Node node = metamodel.searchNode(this.getMetaQualifiedName());
		return (node != null && node.getObject().is(COMPONENT));
	}

	public boolean isMetaAggregated() {
		Model metamodel = TaraLanguage.getMetaModel(this.getFile());
		if (metamodel == null) return false;
		Node node = metamodel.searchNode(this.getMetaQualifiedName());
		return (node != null && node.getObject().is(AGGREGATED));
	}

	private boolean is(siani.tara.lang.Annotation taraAnnotation) {
		for (PsiElement annotation : getNormalAnnotations())
			if (taraAnnotation.getName().equals(annotation.getText()))
				return true;
		Concept parent = getParentConceptName() != null ? getParentConcept() : null;
		return parent != null && ((ConceptMixin) parent).is(taraAnnotation);
	}

	public TaraAddress getAddress() {
		return getSignature().getAddress();
	}

	public Collection<Concept> getSubConcepts() {
		ArrayList<Concept> subs = new ArrayList<>();
		List<Concept> children = TaraPsiImplUtil.getInnerConceptsInBody(this.getBody());
		for (Concept child : children)
			if (child.isSub()) {
				subs.add(child);
				subs.addAll(child.getSubConcepts());
			}
		return subs;
	}

	public TaraFacetApply[] getFacetApplies() {
		List<TaraFacetApply> facetApplies = new ArrayList<>();
		if (((TaraConcept) this).getAnnotationsAndFacets() != null && ((TaraConcept) this).getAnnotationsAndFacets().getFacetApply() != null)
			facetApplies.add(((TaraConcept) this).getAnnotationsAndFacets().getFacetApply());
		if (this.getBody() != null)
			facetApplies.addAll(getBody().getFacetApplies());
		return facetApplies.toArray(new TaraFacetApply[facetApplies.size()]);
	}

	public Collection<TaraFacetTarget> getFacetTargets() {
		return TaraPsiImplUtil.getFacetTargets((Concept) this);
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@NotNull
	public List<Annotation> getNormalAnnotations() {
		List<Annotation> list = new ArrayList<>();
		for (Annotation annotation : getAnnotations()) {
			if (!annotation.isMetaAnnotation()) list.add(annotation);
		}
		return list;
	}

	@NotNull
	public List<Annotation> getMetaAnnotations() {
		List<Annotation> list = new ArrayList<>();
		for (Annotation annotation : getAnnotations()) if (annotation.isMetaAnnotation()) list.add(annotation);
		return list;
	}

	public Annotation[] getAnnotations() {
		List<Annotation> list = new ArrayList<>();
		TaraAnnotationsAndFacets[] annotationsAndFacets = findChildrenByClass(TaraAnnotationsAndFacets.class);
		for (TaraAnnotationsAndFacets annotationsAndFacet : annotationsAndFacets) {
			if (annotationsAndFacet != null && annotationsAndFacet.getAnnotations() != null)
				list.addAll(annotationsAndFacet.getAnnotations().getAnnotationList());
			if (this.getBody() != null)
				for (TaraAnnotationsAndFacets inBody : getBody().getAnnotationsAndFacetsList()) {
					Annotations annotations = inBody.getAnnotations();
					if (annotations == null) continue;
					list.addAll(annotations.getAnnotationList());
				}
		}
		return list.toArray(new Annotation[list.size()]);
	}

	@NotNull
	public Doc[] getDoc() {
		return null;
	}

	@Override
	public String toString() {
		return getName() != null ? getName() : "unNamed";
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
