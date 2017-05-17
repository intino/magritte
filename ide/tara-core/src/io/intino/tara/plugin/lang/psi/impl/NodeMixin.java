package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
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
import io.intino.tara.Language;
import io.intino.tara.Resolver;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.Rule;
import io.intino.tara.plugin.documentation.TaraDocumentationFormatter;
import io.intino.tara.plugin.lang.TaraIcons;
import io.intino.tara.plugin.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Node.ANONYMOUS;
import static io.intino.tara.lang.model.Tag.Abstract;
import static io.intino.tara.lang.model.Tag.Terminal;
import static io.intino.tara.plugin.codeinsight.languageinjection.helpers.Format.firstUpperCase;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;

public class NodeMixin extends ASTWrapperPsiElement {

	private String fullType;
	private String prevType;
	private Set<Tag> inheritedFlags = new HashSet<>();
	private List<String> metaTypes = new ArrayList<>();

	NodeMixin(@NotNull ASTNode node) {
		super(node);
	}

	@Override
	public String getName() {
		return qualifiedName();
	}

	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return getName();
			}

			public String getLocationString() {
				return "";
			}

			public Icon getIcon(final boolean open) {
				return TaraIcons.NODE;
			}
		};
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

	@NotNull
	public String type() {
		if (prevType == null) prevType = shortType();
		if (fullType == null) fullType = shortType();
		if (!prevType.equals(shortType())) {
			fullType = shortType();
			prevType = shortType();
		}
		return fullType;
	}

	private String shortType() {
		MetaIdentifier type = getSignature().getType();
		if (type == null && this.isSub()) {
			Node parent = parent();
			return parent != null ? parent.type() : "";
		} else return type == null || type.getText() == null ? "" : type.getText() + targetType(facetTarget());
	}

	private String targetType(FacetTarget target) {
		if (target == null) return "";
		final Node node = target.targetNode();
		if (node == null) return "";
		final String type = node.type();
		return ":" + (type.contains(":") ? type.substring(0, type.indexOf(":")) : type);
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

	public List<Node> siblings() {
		NodeContainer container = container();
		if (container == null) return unmodifiableList(((TaraModel) this.getContainingFile()).components());
		return unmodifiableList(container.components());
	}

	public List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf((Node) this));
	}

	public List<Rule> rulesOf(Node component) {
		return Collections.emptyList();
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
		Identifier identifierNode = TaraPsiImplUtil.getIdentifierNode((Node) this);
		return identifierNode != null ? identifierNode.getText() : "";
	}

	public List<Parameter> parameters() {
		List<Parameter> parameterList = new ArrayList<>();
		final Parameters parameters = getSignature().getParameters();
		if (parameters != null) parameterList.addAll(parameters.getParameters());
		parameterList.addAll(getVarInits());
		for (Facet facet : facets()) {
			final TaraParameters p = ((TaraFacetApply) facet).getParameters();
			if (p != null) parameterList.addAll(p.getParameterList());
		}
		return parameterList;
	}

	private List<Parameter> getVarInits() {
		if (this.getBody() == null) return EMPTY_LIST;
		return unmodifiableList(this.getBody().getVarInitList());
	}

	public String qualifiedName() {
		if (container() == null) return name();
		String container = container().qualifiedName();
		return new StringBuilder().append(container.isEmpty() ? "" : container + ".").
			append(name().isEmpty() ?
				"[" + ANONYMOUS + shortType() + "]" :
				name() + (facetTarget() != null ? facetTarget().target().replace(".", ":") : "")).toString();
	}

	public String cleanQn() {
		if (container() == null) return firstUpperCase().format(name()).toString();
		String container = container().qualifiedName();
		return new StringBuilder().append(container.isEmpty() ? "" : container + "$").
			append(name().isEmpty() ?
				"[" + ANONYMOUS + shortType() + "]" :
				firstUpperCase().format(name()).toString() + (facetTarget() != null ? facetTarget().target().replace(".", ":") : "")).toString();
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

	@Nullable
	public Body getBody() {
		return findChildByClass(Body.class);
	}

	public boolean isSub() {
		return this.getSignature().isSub();
	}

	public boolean isFacet() {
		return ProteoConstants.FACET.equals(type()) || (metaTypes() != null && metaTypes().contains(ProteoConstants.METAFACET));
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

	public boolean isTerminal() {
		final Configuration.Level type = TaraUtil.level(this);
		return is(Terminal) || (type != null && type.equals(Configuration.Level.Product));
	}

	public boolean is(Tag tag) {
		Node parent = parentName() != null ? parent() : null;
		return hasFlag(tag) || (parent != null && parent.is(tag));
	}

	public boolean into(Tag tag) {
		Node parent = parentName() != null ? parent() : null;
		return hasAnnotation(tag) || parent != null && parent.is(tag);
	}

	private boolean hasFlag(Tag tags) {
		for (Tag a : flags())
			if (a.equals(tags)) return true;
		return false;
	}

	private boolean hasAnnotation(Tag tag) {
		for (Tag a : annotations())
			if (a.equals(tag)) return true;
		return false;
	}

	public List<Node> subs() {
		ArrayList<Node> subs = new ArrayList<>();
		List<Node> children = TaraPsiImplUtil.getBodyComponents(this.getBody());
		children.stream().filter(Node::isSub).forEach(child -> {
			subs.add(child);
			subs.addAll(child.subs());
		});
		return unmodifiableList(subs);
	}

	public Node container() {
		return isSub() ? containerOfSub((Node) this) : TaraPsiImplUtil.getContainerNodeOf(this);
	}

	private Node containerOfSub(Node node) {
		Node container = node;
		while (container != null && container.isSub())
			container = TaraPsiImplUtil.getContainerNodeOf((PsiElement) container);
		return container != null ? container.container() : null;
	}

	public List<Facet> facets() {
		return unmodifiableList(((TaraNode) this).getSignature().facets());
	}

	public FacetTarget facetTarget() {
		FacetTarget facetTarget = this.getSignature().getFacetTarget();
		if (facetTarget == null && parent() != null) facetTarget = parent().facetTarget();
		return facetTarget;
	}

	public List<String> types() {
		Set<String> types = new HashSet<>();
		types.add(type());
		types.addAll(secondaryTypes());
		return new ArrayList<>(types);
	}

	public List<String> secondaryTypes() {
		Set<String> types = facets().stream().map(f -> f.type() + ":" + this.type()).collect(Collectors.toSet());
		if (parent() != null && !parent().equals(this)) types.addAll(parent().types());
		return new ArrayList<>(types);
	}

	@NotNull
	public Signature getSignature() {
		return findNotNullChildByClass(TaraSignature.class);
	}

	@NotNull
	private List<Annotation> getAnnotations() {
		Annotations annotations = this.getAnnotationsNode();
		return annotations == null ? EMPTY_LIST : unmodifiableList(annotations.getAnnotationList());
	}

	@NotNull
	private List<Flag> getFlags() {
		io.intino.tara.plugin.lang.psi.Flags flags = this.getFlagsElement();
		return flags == null ? EMPTY_LIST : flags.getFlagList();
	}


	public List<Tag> annotations() {
		return getAnnotations().stream().map(a -> Tag.valueOf(firstUpperCase().format(a.getText()).toString())).collect(Collectors.toList());
	}

	public List<Tag> flags() {
		final List<Tag> tags = new ArrayList<>();
		tags.addAll(getFlags().stream().
			map(f -> Tag.valueOf(firstUpperCase().format(f.getText()).toString())).collect(Collectors.toList()));
		tags.addAll(inheritedFlags);
		return tags;
	}

	@Nullable
	public Annotations getAnnotationsNode() {
		return this.getSignature().getAnnotations();
	}

	@Nullable
	public io.intino.tara.plugin.lang.psi.Flags getFlagsNode() {
		return this.getSignature().getFlags();
	}

	@Nullable
	public io.intino.tara.plugin.lang.psi.Flags getFlagsElement() {
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

	public String languageName() {
		return null;
	}

	public void languageName(String language) {
	}

	public List<Node> children() {
		return Collections.emptyList();
	}

	public boolean isAnonymous() {
		return name().isEmpty();
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

	public void addParameter(String name, String facet, int position, String extension, int line, int column, List<Object> values) {
		final TaraElementFactory factory = TaraElementFactory.getInstance(this.getProject());
		Map<String, String> params = new HashMap();
		params.put(name, String.join(" ", toString(values)));
		final Parameters newParameters = factory.createExplicitParameters(params);
		final Parameters parameters = parametersAnchor(facet);
		if (parameters == null)
			getSignature().addAfter(newParameters, metaidentifier(facet));
		else {
			PsiElement anchor = calculateAnchor();
			final PsiElement separator = parameters.addAfter(factory.createParameterSeparator(), anchor);
			parameters.addAfter((PsiElement) newParameters.getParameters().get(0), separator);
		}
	}

	private Parameters parametersAnchor(String facet) {
		PsiElement element = metaidentifier(facet);
		while (element != null && !(element instanceof Parameters)) element = element.getPrevSibling();
		return (Parameters) element;
	}

	private TaraMetaIdentifier metaidentifier(String facet) {
		return facet.isEmpty() ? getSignature().getMetaIdentifier() : findFacet(facet);
	}

	private TaraMetaIdentifier findFacet(String facet) {
		for (Facet f : getSignature().facets()) if (f.type().equals(facet)) return ((TaraFacetApply) f).getMetaIdentifier();
		return null;
	}

	public List<String> toString(List<Object> values) {
		return values.stream().map(v -> {
			final String quote = mustBeQuoted(v);
			return quote + (v instanceof Node ? ((Node) v).qualifiedName() : v.toString()) + quote;
		}).collect(Collectors.toList());
	}

	private String mustBeQuoted(Object v) {
		if (v instanceof Primitive.Expression) return "'";
		else if (v instanceof String && !((String) v).startsWith("\"")) return "\"";
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
