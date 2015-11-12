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

	public boolean isMain() {
		return flags().contains(Tag.MAIN);
	}

	public boolean isFacet() {
		return flags().contains(Tag.FACET);
	}

	public boolean isAbstract() {
		return flags().contains(Tag.ABSTRACT);
	}

	public boolean isNamed() {
		return flags().contains(Tag.NAMED);
	}

	public boolean isFeature() {
		return flags().contains(Tag.FEATURE);
	}

	public boolean isFeatureInstance() {
		return flags().contains(Tag.FEATURE_INSTANCE);
	}

	public boolean isFinal() {
		return flags().contains(Tag.FINAL);
	}

	public boolean isEnclosed() {
		return flags().contains(Tag.ENCLOSED);
	}

	public boolean isTerminal() {
		return flags().contains(Tag.TERMINAL);
	}

	public boolean isPrototype() {
		return flags().contains(Tag.PROTOTYPE);
	}

	public boolean isTerminalInstance() {
		return flags().contains(Tag.TERMINAL_INSTANCE);
	}

	public boolean intoMain() {
		return annotations().contains(Tag.MAIN);
	}

	public String plate() {
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
				map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList()));
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

	public void plate(String plate) {
	}

	public String qualifiedName() {
		return null;
	}

	public String qualifiedNameCleaned() {
		return null;
	}

	public String doc() {
		return null;
	}

	public List<String> types() {
		return destinyOfReference().types();
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

	public Node component(String name) {
		return null;
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

}
