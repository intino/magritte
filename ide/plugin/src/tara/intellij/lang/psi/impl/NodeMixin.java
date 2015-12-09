package tara.intellij.lang.psi.impl;

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
import tara.Language;
import tara.Resolver;
import tara.dsl.Proteo;
import tara.intellij.documentation.TaraDocumentationFormatter;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Flags;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;
import static tara.intellij.codeinsight.languageinjection.helpers.Format.firstUpperCase;
import static tara.lang.model.Tag.*;

public class NodeMixin extends ASTWrapperPsiElement {

	private String fullType = simpleType();
	private String prevType = simpleType();
	private Set<Tag> inheritedFlags = new HashSet<>();
	private List<String> metaTypes = new ArrayList<>();

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

	public String simpleType() {
		MetaIdentifier type = getSignature().getType();
		if (type == null && this.isSub()) {
			Node baseNode = getBaseConcept();
			return baseNode != null ? baseNode.type() : "";
		} else
			return type == null || type.getText() == null ? "" : type.getText();
	}

	@NotNull
	public String type() {
		if (!prevType.equals(simpleType())) {
			fullType = simpleType();
			prevType = simpleType();
		}
		return fullType;
	}

	public void type(String fullType) {
		this.fullType = fullType;
	}

	public void setShortType(String type) {
		setType(type);
	}

	public Node resolve() {
		Language language = TaraUtil.getLanguage(this.getOriginalElement());
		if (language == null) return (Node) this;
		new Resolver(language).resolve((Node) this);
		return (Node) this;
	}

	public Node getBaseConcept() {
		return TaraPsiImplUtil.getParentOf((Node) this);
	}

	public List<Node> siblings() {
		NodeContainer container = container();
		if (container == null) return unmodifiableList(((TaraModel) this.getContainingFile()).components());
		return unmodifiableList(container.components());
	}

	public List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf((Node) this));
	}

	public CompositionRule ruleOf(Node component) {
		return null;
	}

	public List<Variable> variables() {
		return TaraPsiImplUtil.getVariablesInBody(this.getBody());
	}

	public List<Node> referenceComponents() {
		return unmodifiableList(TaraPsiImplUtil.getNodeReferencesOf((Node) this));
	}

	@Nullable
	public String parentName() {
		Signature signature = this.getSignature();
		return signature.getParentReference() != null ? signature.getParentReference().getText() : null;
	}

	public Node parent() {
		return TaraPsiImplUtil.getParentOf((Node) this);
	}

	public MetaIdentifier getMetaIdentifier() {
		MetaIdentifier[] childrenOfType = PsiTreeUtil.getChildrenOfType(this.getSignature(), MetaIdentifier.class);
		return childrenOfType == null ? null : childrenOfType[0];
	}

	@NotNull
	public String name() {
		Identifier identifierNode = getIdentifierNode();
		return identifierNode != null ? identifierNode.getText() : "";
	}

	public List<Parameter> parameters() {
		List<Parameter> parameterList = new ArrayList<>();
		final Parameters parameters = getSignature().getParameters();
		if (parameters != null) parameterList.addAll(parameters.getParameters());
		parameterList.addAll(getVarInits());
		return parameterList;
	}

	private List<Parameter> getVarInits() {
		if (this.getBody() == null) return EMPTY_LIST;
		return unmodifiableList(this.getBody().getVarInitList());
	}

	public String qualifiedName() {
		NodeContainer node = (Node) this;
		String name = "";
		while (node != null) {
			if (node instanceof Node && !(node instanceof TaraModel))
				name = getPathName((Node) node) + "." + name;
			node = node.container();
		}
		return name.substring(0, name.length() - 1);
	}

	public String qualifiedNameCleaned() {
		NodeContainer node = (Node) this;
		String name = "";
		while (node != null) {
			if (node instanceof Node && !(node instanceof TaraModel))
				name = getPathName((Node) node) + "." + name;
			node = node.container();
		}
		return name.substring(0, name.length() - 1);
	}

	private String getPathName(Node node) {
		final String name = node.name();
		return !name.isEmpty() ? name : node.simpleType();
	}


	public TaraModelImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraModelImpl) super.getContainingFile();
	}

	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.NODE;
	}

	public void name(String name) {
		setName(name);
	}

	private PsiElement setName(String name) {
		return TaraPsiImplUtil.setName(this.getSignature(), name);
	}

	private PsiElement setType(String type) {
		return TaraPsiImplUtil.setType(this.getSignature(), type);
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

	public boolean isComponent() {
		return is(Component);
	}

	public boolean isFacet() {
		return type().equals(Proteo.FACET) || metaTypes().contains(Proteo.METAFACET);
	}

	public List<String> metaTypes() {
		return metaTypes;
	}

	public void metaTypes(List<String> types) {
		this.metaTypes = types;
	}


	public boolean isAbstract() {
		return is(Abstract) || !subs().isEmpty();
	}

	public boolean isEnclosed() {
		return is(Enclosed);
	}

	public boolean isFeature() {
		return is(Feature);
	}

	public boolean isFinal() {
		return is(Named);
	}

	public boolean isTerminal() {
		return is(Terminal) || TaraUtil.getLevel(this) == 1;
	}

	public boolean isPrototype() {
		return is(Terminal);
	}

	public boolean intoComponent() {
		return into(Component);
	}

	public boolean isFacetInstance() {
		return inheritedFlags.contains(FacetInstance);
	}

	public boolean isInstance() {
		return inheritedFlags.contains(Instance);
	}

	private boolean is(Tag taraTags) {
		for (PsiElement annotation : getFlags())
			if (taraTags.name().equalsIgnoreCase(annotation.getText()))
				return true;
		Node parent = parentName() != null ? parent() : null;
		return hasFlag(taraTags) || (parent != null && ((NodeMixin) parent).is(taraTags));
	}

	private boolean into(Tag taraTags) {
		for (PsiElement annotation : getAnnotations())
			if (taraTags.name().equalsIgnoreCase(annotation.getText()))
				return true;
		Node parent = parentName() != null ? parent() : null;
		return parent != null && ((NodeMixin) parent).is(taraTags);
	}

	private boolean hasFlag(Tag tags) {
		for (Tag a : flags())
			if (a.equals(tags)) return true;
		return false;
	}

	public TaraAnchor getAnchor() {
		return getSignature().getAnchor();
	}

	public String anchor() {
		final TaraAnchor address = getSignature().getAnchor();
		return address != null ? address.getText() : null;
	}

	public List<Node> subs() {
		ArrayList<Node> subs = new ArrayList<>();
		List<Node> children = TaraPsiImplUtil.getInnerNodesInBody(this.getBody());
		children.stream().filter(Node::isSub).forEach(child -> {
			subs.add(child);
			subs.addAll(child.subs());
		});
		return unmodifiableList(subs);
	}

	public NodeContainer container() {
		if (isSub()) {
			Node rootOfSub = containerOfSub((Node) this);
			return rootOfSub == null ? null : rootOfSub;
		} else return TaraPsiImplUtil.getContainerOf(this);
	}

	private Node containerOfSub(Node container) {
		while (container != null && container.isSub())
			container = TaraPsiImplUtil.getContainerNodeOf((PsiElement) container);
		return TaraPsiImplUtil.getContainerNodeOf((PsiElement) container);
	}

	public List<Facet> facets() {
		if (getBody() != null) {
			final TaraBody body = ((TaraNode) this).getBody();
			return body != null ? unmodifiableList(body.getFacetApplyList()) : Collections.emptyList();
		}
		return EMPTY_LIST;
	}

	public List<FacetTarget> facetTargets() {
		return unmodifiableList(TaraPsiImplUtil.getFacetTargets((Node) this));
	}

	public List<String> types() {
		Set<String> types = new HashSet<>();
		types.add(type());
		types.addAll(secondaryTypes());
		return new ArrayList<>(types);
	}

	public List<String> secondaryTypes() {
		Set<String> types = facets().stream().map(tara.lang.model.Facet::type).collect(Collectors.toSet());
		if (parent() != null && !parent().equals(this)) types.addAll(parent().types());
		return new ArrayList<>(types);
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(Signature.class);
	}

	@NotNull
	private List<Annotation> getAnnotations() {
		Annotations annotations = this.getAnnotationsNode();
		return annotations == null ? EMPTY_LIST : unmodifiableList(annotations.getAnnotationList());
	}

	@NotNull
	private List<Flag> getFlags() {
		Flags flags = this.getFlagsElement();
		return flags == null ? EMPTY_LIST : flags.getFlagList();
	}


	public List<Tag> annotations() {
		return getAnnotations().stream().map(a -> Tag.valueOf(firstUpperCase().format(a.getText()).toString())).collect(Collectors.toList());
	}

	public List<Tag> flags() {
		final List<Tag> tags = getFlags().stream().
			map(f -> Tag.valueOf(firstUpperCase().format(f.getText()).toString())).collect(Collectors.toList());
		tags.addAll(inheritedFlags);
		return tags;
	}

	@Nullable
	public Annotations getAnnotationsNode() {
		return this.getSignature().getAnnotations();
	}

	@Nullable
	public Flags getFlagsNode() {
		return this.getSignature().getFlags();
	}

	@Nullable
	public Flags getFlagsElement() {
		return this.getSignature().getFlags();
	}

	public void addFlags(List<Tag> flags) {
		inheritedFlags.clear();
		inheritedFlags.addAll(flags);
	}

	public void addFlag(Tag flag) {
		inheritedFlags.add(flag);
	}

	public void addAnnotations(Tag... annotations) {

	}

	public void add(Variable... variables) {
		for (Variable variable : variables) {
			final TreeElement copy = ChangeUtil.copyToElement((PsiElement) variable);
			PsiElement psi = copy.getPsi();
			if (getBody() == null) TaraElementFactory.getInstance(this.getProject()); //TODO
			final PsiElement add = getBody().add((psi));
		}
	}

	public boolean contains(String type) {
		for (Node node : components())
			if (type.equals(node.type())) return true;
		return true;
	}

	public boolean isReference() {
		return false;
	}

	public Node destinyOfReference() {
		return null;
	}

	public String language() {
		return null;
	}

	public void language(String language) {
	}

	public void anchor(String anchor) {
		final TaraAnchor psiPlate = TaraElementFactory.getInstance(getProject()).createAnchor(anchor);
		final TreeElement copy = ChangeUtil.copyToElement(psiPlate);
		PsiElement psi = copy.getPsi();
		TaraSignature taraSignature = PsiTreeUtil.getChildrenOfType(this, TaraSignature.class)[0];
		taraSignature.getNode().addChild(ASTFactory.whitespace(" "));
		taraSignature.add(psi);
	}


	public List<Node> children() {
		return Collections.emptyList();
	}

	public boolean isAnonymous() {
		return name().isEmpty();
	}

	public Node component(String name) {
		for (Node node : components()) if (name.equals(node.name())) return node;
		return null;
	}

	public <T extends Node> boolean contains(T node) {
		return components().contains(node);
	}

	public String doc() {
		return buildDocText();
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public List<String> uses() {
		return Collections.emptyList();
	}


	public String buildDocText() {
		StringBuilder text = new StringBuilder();
		TaraDoc doc = ((TaraNode) this).getDoc();
		if (doc == null) return "";
		String comment = doc.getText();
		String trimmed = StringUtil.trimStart(comment, "!!");
		text.append(trimmed.trim()).append("\n");
		return TaraDocumentationFormatter.doc2Html(this, text.toString());
	}

	public void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
		final TaraElementFactory factory = TaraElementFactory.getInstance(this.getProject());
		Map<String, String> params = new HashMap();
		params.put(name, String.join(" ", toString(values)));
		final Parameters newParameters = factory.createExplicitParameters(params);
		if (getSignature().getParameters() == null)
			getSignature().addAfter(newParameters, getSignature().getMetaIdentifier());
		else {
			PsiElement anchor = calculateAnchor();
			final PsiElement separator = getSignature().getParameters().addAfter(factory.createParameterSeparator(), anchor);
			getSignature().getParameters().addAfter((PsiElement) newParameters.getParameters().get(0), separator);
		}
	}

	public List<String> toString(List<Object> values) {
		return values.stream().map(v -> {
			final String quote = mustBeQuoted(v);
			return quote + v.toString() + quote;
		}).collect(Collectors.toList());
	}

	private String mustBeQuoted(Object v) {
		if (v instanceof Primitive.Expression) return "'";
		else if (v instanceof String) return "\"";
		else return "";
	}

	public PsiElement calculateAnchor() {
		List<Parameter> parameters = getSignature().getParameters().getParameters();
		return (PsiElement) parameters.get(parameters.size() - 1);
	}


	public String toString() {
		return (isAnonymous() ? "unNamed" : name()) + "@" + type();
	}

}
