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
import siani.tara.semantic.Assumption;
import siani.tara.semantic.model.Tag;

import javax.swing.*;
import java.util.*;

import static java.util.Collections.*;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getInnerNodesOf;
import static siani.tara.semantic.Assumption.FacetInstance;
import static siani.tara.semantic.model.Tag.*;

public class NodeMixin extends ASTWrapperPsiElement {

	private String fullType = getType();
	private String prevType = getType();
	private Set<String> inheritedFlags = new HashSet<>();

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

	public List<Node> getNodeSiblings() {
		Node contextOf = TaraPsiImplUtil.getContainerNodeOf(this);
		if (contextOf == null) return unmodifiableList(((TaraModel) this.getContainingFile()).getRootNodes());
		return unmodifiableList(contextOf.getIncludes());
	}

	public List<Node> getIncludes() {
		return unmodifiableList(getInnerNodesOf((Node) this));
	}

	public List<Variable> getVariables() {
		return TaraPsiImplUtil.getVariablesInBody(this.getBody());
	}

	public List<VarInit> getVarInits() {
		if (this.getBody() == null) return EMPTY_LIST;
		return unmodifiableList(this.getBody().getVarInitList());
	}

	public List<NodeReference> getInnerNodeReferences() {
		return unmodifiableList(TaraUtil.getLinksOf((Node) this));
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

	@NotNull
	public List<Parameter> getParameters() {
		if (getSignature().getParameters() == null) return EMPTY_LIST;
		return unmodifiableList(getSignature().getParameters().getParameters());
	}

	public String getQualifiedName() {
		Node node = (Node) this;
		String name = "";
		while (node != null) {
			name = getPathName(node) + "." + name;
			node = node.container();
		}
		return name.substring(0, name.length() - 1);
	}

	private String getPathName(Node node) {
		return node.getIdentifierNode() != null ? node.getIdentifierNode().getText() : (node.getType() + "@anonymous");
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

	@Override
	public Icon getIcon(@IconFlags int i) {
		if (this.isSub())
			return TaraIcons.SUB_13;
		return TaraIcons.CONCEPT;
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

	public boolean isMain() {
		return is(MAIN);
	}

	public boolean isFacet() {
		return is(FACET);
	}


	public boolean isAbstract() {
		return is(ABSTRACT) || !getSubNodes().isEmpty();
	}

	public boolean isEnclosed() {
		return is(ENCLOSED);
	}

	public boolean isFeature() {
		return is(FEATURE);
	}

	public boolean isProperty() {
		return is(IMPLICIT);
	}

	public boolean isFacetInstance() {
		return is(FacetInstance.class);
	}

	public boolean isPropertyInstance() {
		return is(FacetInstance.class);
	}

	public boolean FeatureInstance() {
		return is(Assumption.Featureinstance.class);
	}

	public boolean isAnnotatedAsRoot() {
		for (PsiElement annotation : getAnnotations())
			if (MAIN.name().equalsIgnoreCase(annotation.getText()))
				return true;
		return false;
	}

	public boolean is(Class<? extends Assumption> assumptionType) {
		Collection<Assumption> assumptions = TaraUtil.getAssumptionsOf((Node) this);
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumptionType.getClass().isInstance(assumption)) return true;
		return false;
	}

	private boolean is(Tag taraTags) {
		for (PsiElement annotation : getAnnotations())
			if (taraTags.name().equalsIgnoreCase(annotation.getText()))
				return true;
		Node parent = getParentName() != null ? getParentNode() : null;
		return hasInheritedAnnotation(taraTags) || (parent != null && ((NodeMixin) parent).is(taraTags));
	}

	private boolean hasInheritedAnnotation(Tag tags) {
		for (String a : inheritedFlags)
			if (a.equals(tags.name())) return true;
		return false;
	}

	public TaraAddress getAddress() {
		return getSignature().getAddress();
	}

	public List<Node> getSubNodes() {
		ArrayList<Node> subs = new ArrayList<>();
		List<Node> children = TaraPsiImplUtil.getInnerNodesInBody(this.getBody());
		for (Node child : children)
			if (child.isSub()) {
				subs.add(child);
				subs.addAll(child.getSubNodes());
			}
		return unmodifiableList(subs);
	}

	public Node container() {
		if (isAnnotatedAsRoot()) return null;//TODO
		if (isSub()) {
			Node rootOfSub = containerOfSub((Node) this);
			return rootOfSub == null ? null : rootOfSub;
		} else return getContainerNodeOf(this);
	}

	private Node containerOfSub(Node container) {
		while (container != null && container.isSub())
			container = getContainerNodeOf(container);
		return getContainerNodeOf(container);
	}

	public List<FacetApply> getFacetApplies() {
		if (getBody() != null) return unmodifiableList(getBody().getFacetApplyList());
		return EMPTY_LIST;
	}

	public List<FacetTarget> getFacetTargets() {
		return unmodifiableList(TaraPsiImplUtil.getFacetTargets((Node) this));
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@NotNull
	public List<Annotation> getAnnotations() {
		Annotations annotations = this.getAnnotationsNode();
		return annotations == null ? EMPTY_LIST : unmodifiableList(annotations.getAnnotationList());
	}

	@NotNull
	public List<Flag> getFlags() {
		Flags flags = this.getFlagsNode();
		return flags == null ? EMPTY_LIST : flags.getFlagList();
	}

	@Nullable
	public Annotations getAnnotationsNode() {
		return this.getSignature().getAnnotations();
	}

	@Nullable
	public Flags getFlagsNode() {
		return this.getSignature().getFlags();
	}

	public void addInheritedFlags(String... flags) {
		Collections.addAll(inheritedFlags, flags);
	}

	public Collection<String> getAssumedFlags() {
		return unmodifiableSet(inheritedFlags);
	}

	public boolean contains(String type) {
		for (Node node : getIncludes())
			if (type.equals(node.getType())) return true;
		return true;
	}

	@NotNull
	public Collection<Doc> getDoc() {
		return EMPTY_LIST;
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
