package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static tara.intellij.codeinsight.languageinjection.helpers.Format.firstUpperCase;


public class NodeReferenceMixin extends ASTWrapperPsiElement {
	private List<Tag> inheritedFlags = new ArrayList<>();

	public NodeReferenceMixin(ASTNode node) {
		super(node);
	}

	@Nullable
	public Annotations getAnnotationsNode() {
		TaraTags tags = ((TaraNodeReference) this).getTags();
		if (tags == null || tags.getAnnotations() == null) return null;
		return tags.getAnnotations();
	}

	@Nullable
	public TaraFlags getFlagsNode() {
		TaraTags tags = ((TaraNodeReference) this).getTags();
		if (tags == null || tags.getFlags() == null) return null;
		return tags.getFlags();
	}

	public List<String> secondaryTypes() {
		final Node destiny = destinyOfReference();
		if (destiny == null) return Collections.emptyList();
		return Collections.unmodifiableList(destiny.facets().stream().map(Facet::type).collect(Collectors.toList()));
	}

	public String simpleType() {
		final Node node = destinyOfReference();
		return node != null ? node.simpleType() : "";
	}

	public boolean isReference() {
		return true;
	}

	public boolean isSub() {
		return false;
	}

	public Node destinyOfReference() {
		return ReferenceManager.resolveToNode(((TaraNodeReference) this).getIdentifierReference());
	}

	public Node resolve() {
		final Node node = destinyOfReference();
		return node != null ? node.resolve() : null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public CompositionRule ruleOf(Node component) {
		return null;
	}

	public void addFlags(List<Tag> flags) {
		this.inheritedFlags.clear();
		this.inheritedFlags.addAll(flags);
	}

	public void addFlag(Tag flag) {
		this.inheritedFlags.add(flag);
	}

	public void addAnnotations(Tag... annotations) {
	}

	public boolean isFacet() {
		final Node node = destinyOfReference();
		return node != null && node.isFacet();
	}

	public boolean isAbstract() {
		return flags().contains(Tag.Abstract);
	}

	public boolean isTerminal() {
		return flags().contains(Tag.Terminal);
	}

	public boolean is(Tag tag) {
		return flags().contains(tag);
	}

	public boolean into(Tag tag) {
		return annotations().contains(tag);
	}

	public String anchor() {
		return "";
	}

	public List<Tag> annotations() {
		List<Tag> collect = destinyOfReference().annotations();
		if (getAnnotationsNode() != null)
			collect.addAll(getAnnotationsNode().getAnnotationList().stream().
				map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList()));
		return collect;
	}

	public List<Tag> flags() {
		List<Tag> collect = destinyOfReference().flags();
		collect.addAll(inheritedFlags);
		if (getFlagsNode() != null)
			collect.addAll(getFlagsNode().getFlagList().stream().
				map(a -> Tag.valueOf(firstUpperCase().format(a.getText()).toString())).collect(Collectors.toList()));
		return collect;
	}

	public String name() {
		return "";
	}

	public void name(String name) {
	}

	public List<Node> subs() {
		return Collections.emptyList();
	}

	public <T extends Node> boolean contains(T node) {
		return false;
	}

	public void anchor(String plate) {
	}

	public String qualifiedName() {
		return null;
	}

	public String qualifiedNameCleaned() {
		return null;
	}

	public String doc() {
		return "null";
	}

	public List<String> types() {
		final Node node = destinyOfReference();
		return node != null ? node.types() : Collections.emptyList();
	}

	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	public List<Node> components() {
		return Collections.emptyList();
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerOf(this);
	}

	public String type() {
		return destinyOfReference() == null ? "" : destinyOfReference().type();
	}

	public List<Node> component(String name) {
		return Collections.emptyList();
	}

	public List<Node> siblings() {
		NodeContainer contextOf = container();
		if (contextOf == null) return unmodifiableList(((TaraModel) this.getContainingFile()).components());
		return Collections.unmodifiableList(contextOf.components());
	}

	public List<Node> children() {
		return Collections.emptyList();
	}

	public List<Variable> variables() {
		return Collections.emptyList();
	}

	public List<String> allowedFacets() {
		return Collections.emptyList();
	}

	public Node parent() {
		return null;
	}

	public String parentName() {
		return null;
	}

	public boolean isAnonymous() {
		return false;
	}

	public void type(String type) {
	}

	public List<Node> referenceComponents() {
		return Collections.emptyList();
	}


	public List<Facet> facets() {
		return Collections.emptyList();
	}

	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	public List<String> uses() {
		return Collections.emptyList();
	}

	public String toString() {
		return "Reference -> " + ((TaraNodeReference) this).getIdentifierReference().getText() + "@" + type();
	}


}
