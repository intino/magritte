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
import siani.tara.Language;
import siani.tara.Resolver;
import siani.tara.intellij.documentation.TaraDocumentationFormatter;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.semantic.LanguageNode;

import javax.swing.*;
import java.util.*;

import static siani.tara.intellij.lang.lexer.Annotation.*;

public class NodeMixin extends ASTWrapperPsiElement {

	private String fullType = getType();
	private String prevType = getType();
	private Set<String> inheritedAnnotations = new HashSet<>();

	public NodeMixin(@NotNull ASTNode node) {
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
			Node baseNode = getBaseConcept();
			return baseNode != null ? baseNode.getType() : "";
		} else
			return type == null || type.getText() == null ? "" : type.getText();
	}

	@NotNull
	public String getFullType() {
		if (!prevType.equals(getType())) {
			fullType = getType();
			prevType = getType();
		}
		return fullType;
	}

	public void setFullType(String fullType) {
		this.fullType = fullType;
	}

	@NotNull
	public Node resolve() {
		Language language = TaraUtil.getLanguage(this.getOriginalElement());
		if (language == null) return (Node) this;
		LanguageNode node = new LanguageNode((Node) this);
		new Resolver(language).resolve(node);
		return (Node) this;
	}

	public Node getBaseConcept() {
		return TaraPsiImplUtil.getParentOf((Node) this);
	}

	public Collection<Node> getNodeSiblings() {
		Node contextOf = TaraPsiImplUtil.getContainerNodeOf(this);
		if (contextOf == null) return ((TaraModel) this.getContainingFile()).getNodes();
		return contextOf.getInnerNodes();
	}

	public Collection<Node> getInnerNodes() {
		return TaraUtil.getInnerNodesOf((Node) this);
	}

	public Collection<Variable> getVariables() {
		return TaraPsiImplUtil.getVariablesInBody(this.getBody());
	}

	public Collection<VarInit> getVarInits() {
		if (this.getBody() == null) return Collections.EMPTY_LIST;
		return (Collection<VarInit>) this.getBody().getVarInitList();
	}

	public Collection<NodeReference> getInnerNodeReferences() {
		return TaraUtil.getLinksOf((Node) this);
	}

	@Nullable
	public String getParentName() {
		Signature signature = this.getSignature();
		return signature.getParentReference() != null ? signature.getParentReference().getText() : null;
	}

	public Node getParentNode() {
		return TaraPsiImplUtil.getParentOf((Node) this);
	}

	public MetaIdentifier getMetaIdentifier() {
		MetaIdentifier[] childrenOfType = PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class);
		return childrenOfType == null ? null : childrenOfType[0];
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
	public Collection<Parameter> getParameterList() {
		if (getSignature().getParameters() == null) return Collections.EMPTY_LIST;
		return getSignature().getParameters().getParameters();
	}

	@Nullable
	public Parameters getParameters() {
		if (getSignature().getParameters() == null) return null;
		return getSignature().getParameters();
	}

	public String getQualifiedName() {
		Node node = (Node) this;
		String name = getPathName(node);
		while (node != null) {
			Node parent = TaraPsiImplUtil.getContainerNodeOf(node);
			if (parent != null && (parent.isSub() && !node.isSub() || !parent.isSub() && !node.isSub()))
				name = parent.getName() + "." + name;
			node = parent;
		}
		return name;
	}

	private String getPathName(Node node) {
		return node.getIdentifierNode() != null ? node.getIdentifierNode().getText() : (getType() + "@anonymous");
	}

	public String getMetaQualifiedName() {
		return TaraUtil.getMetaQualifiedName((Node) this);
	}

	public TaraModelImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraModelImpl) super.getContainingFile();
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
		Collection<Doc> docs = this.getDoc();
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
		return TaraPsiImplUtil.getIdentifierNode((Node) this);
	}

	@Nullable
	public Body getBody() {
		return findChildByClass(Body.class);
	}

	public boolean isSub() {
		return this.getSignature().isSub();
	}

	public boolean isRoot() {
		return TaraPsiImplUtil.getContainerNodeOf(this) == null;
	}

	public boolean isIntention() {
		for (PsiElement annotation : getAnnotations())
			if (INTENTION.getName().equals(annotation.getText()))
				return true;
		Node parent = null;
		if (getParentName() != null) parent = getParentNode();
		return parent != null && parent.isIntention();
	}

	public boolean isFacet() {
		return is(FACET);
	}

	public boolean isAddressed() {
		return is(ADDRESSED);
	}

	public boolean isAbstract() {
		return is(ABSTRACT) || !getSubNodes().isEmpty();
	}


	public boolean isAggregated() {
		return is(AGGREGATED);
	}

	public boolean isAnnotatedAsAggregated() {
		for (PsiElement annotation : getAnnotations())
			if (AGGREGATED.getName().equals(annotation.getText()))
				return true;
		return false;
	}

	public boolean isProperty() {
		return is(PROPERTY);
	}

	public boolean isComponent() {
		return is(COMPONENT);
	}

	private boolean is(siani.tara.intellij.lang.lexer.Annotation taraAnnotation) {
		for (PsiElement annotation : getAnnotations())
			if (taraAnnotation.getName().equals(annotation.getText()))
				return true;
		Node parent = getParentName() != null ? getParentNode() : null;
		return parent != null && ((NodeMixin) parent).is(taraAnnotation);
	}

	public TaraAddress getAddress() {
		return getSignature().getAddress();
	}

	public Collection<Node> getSubNodes() {
		ArrayList<Node> subs = new ArrayList<>();
		List<Node> children = TaraPsiImplUtil.getInnerNodesInBody(this.getBody());
		for (Node child : children)
			if (child.isSub()) {
				subs.add(child);
				subs.addAll(child.getSubNodes());
			}
		return subs;
	}

	public Node getContainer() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public Collection<FacetApply> getFacetApplies() {
		if (getBody() != null) return (List<FacetApply>) getBody().getFacetApplyList();
		return Collections.EMPTY_LIST;
	}

	public Collection<TaraFacetTarget> getFacetTargets() {
		return TaraPsiImplUtil.getFacetTargets((Node) this);
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@NotNull
	public List<Annotation> getAnnotations() {
		Annotations annotations = ((Node) this).getAnnotationsNode();
		return annotations == null ? Collections.EMPTY_LIST : annotations.getAnnotationList();
	}

	@Nullable
	public Annotations getAnnotationsNode() {
		return this.getSignature().getAnnotations();
	}

	public void addInheritedAnnotations(String... annotations) {
		Collections.addAll(inheritedAnnotations, annotations);
	}

	public Collection<String> getAssumedAnnotations() {
		return inheritedAnnotations;
	}

	public boolean contains(String type) {
		for (Node node : getInnerNodes())
			if (type.equals(node.getType())) return true;
		return true;
	}

	@NotNull
	public Collection<Doc> getDoc() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public String toString() {
		return getName() != null ? getName() : "unNamed";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Node && ((Node) obj).getQualifiedName().equals(this.getQualifiedName());
	}

	@Override
	public int hashCode() {
		return this.getQualifiedName().hashCode();
	}
}
