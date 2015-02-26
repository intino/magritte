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
		return TaraPsiImplUtil.getParentOf((Concept) this);
	}

	public MetaIdentifier getMetaIdentifier() {
		return PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class)[0];
	}

	@Override
	public String getName() {
		Identifier identifierNode = getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() : null;
	}

	public boolean isAnonymous() {
		return getName() == null;
	}

	@NotNull
	public Parameter[] getParameterList() {
		if (getSignature().getParameters() == null) return new Parameter[0];
		return getSignature().getParameters().getParameters();
	}

	@Nullable
	public Parameters getParameters() {
		if (getSignature().getParameters() == null) return null;
		return getSignature().getParameters();
	}

	public String getQualifiedName() {
		Concept concept = (Concept) this;
		String name = getPathName(concept);
		while (concept != null) {
			Concept parent = TaraPsiImplUtil.getConceptContainerOf(concept);
			if (parent != null)
				if (parent.isSub() && !concept.isSub() || !parent.isSub() && !concept.isSub())
					name = parent.getName() + "." + name;
			concept = parent;
		}
		return name;
	}

	private String getPathName(Concept concept) {
		return concept.getIdentifierNode() != null ? concept.getIdentifierNode().getText() : (getType() + "@anonymous");
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
		if (this.getFile() == null) return false;
		Node node = TaraUtil.findNode((Concept) this, TaraLanguage.getMetaModel(this.getFile()));
		return node != null && node.getObject().is(COMPONENT);
	}

	public boolean isMetaAggregated() {
		if (this.getFile() == null) return false;
		Node node = TaraUtil.findNode((Concept) this, TaraLanguage.getMetaModel(this.getFile()));
		return node != null && node.isAggregated();
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

	public Concept getContainer() {
		return TaraPsiImplUtil.getConceptContainerOf(this);
	}

	public FacetApply[] getFacetApplies() {
		if (getBody() != null) {
			List<FacetApply> facetApplies = (List<FacetApply>) getBody().getFacetApplyList();
			return facetApplies.toArray(new FacetApply[facetApplies.size()]);
		}
		return new TaraFacetApply[0];
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
		Annotations annotations = ((Concept) this).getAnnotations();
		return annotations == null ? Collections.EMPTY_LIST : (List<Annotation>) annotations.getNormalAnnotations();
	}

	@NotNull
	public List<Annotation> getMetaAnnotations() {
		Annotations annotations = ((Concept) this).getAnnotations();
		return annotations == null ? Collections.EMPTY_LIST : (List<Annotation>) annotations.getMetaAnnotations();
	}

	public boolean contains(String type) {
		for (Concept concept : getInnerConcepts())
			if (type.equals(concept.getType())) return true;
		return true;
	}

	@NotNull
	public Doc[] getDoc() {
		return new Doc[0];
	}

	@Override
	public String toString() {
		return getName() != null ? getName() : "unNamed";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Concept && ((Concept) obj).getQualifiedName().equals(this.getQualifiedName());
	}

	@Override
	public int hashCode() {
		return this.getQualifiedName().hashCode();
	}
}
