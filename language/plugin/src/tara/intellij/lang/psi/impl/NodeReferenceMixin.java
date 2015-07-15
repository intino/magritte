package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.Tag;

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
	public Flags getFlagsNode() {
		TaraTags tags = ((TaraNodeReference) this).getTags();
		if (tags == null || tags.getFlags() == null) return null;
		return tags.getFlags();
	}

	public List<String> secondaryTypes() {
		final Node destiny = destinyOfReference();
		if (destiny == null) return Collections.emptyList();
		return Collections.unmodifiableList(destiny.facets().stream().map(FacetApply::type).collect(Collectors.toList()));
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

	public tara.language.model.Node resolve() {
		final tara.intellij.lang.psi.Node node = destinyOfReference();
		return node != null ? node.resolve() : null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}


	public void addFlags(Tag... flags) {
		Collections.addAll(inheritedFlags, flags);
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

	public boolean isRequired() {
		return flags().contains(Tag.REQUIRED);
	}

	public boolean isSingle() {
		return flags().contains(Tag.SINGLE);
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

	public boolean isTerminal() {
		return flags().contains(Tag.TERMINAL);
	}

	public boolean isTerminalInstance() {
		return flags().contains(Tag.TERMINAL_INSTANCE);
	}

	public boolean intoSingle() {
		return annotations().contains(Tag.SINGLE);
	}

	public boolean intoRequired() {
		return annotations().contains(Tag.REQUIRED);
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
		setName(name);
	}

	@NotNull
	private PsiElement setName(String name) {
		return null;
// TODO		return TaraPsiImplUtil.setName(this.getSignature(), name);
	}

	public List<Node> subs() {
		return Collections.emptyList();
	}

	public <T extends tara.language.model.Node> boolean contains(T node) {
		return false;
	}

	public void plate(String plate) {
	}

	public String qualifiedName() {
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
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String type() {
		return destinyOfReference().type();
	}

	public tara.language.model.Node component(String name) {
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

	public List<? extends Variable> variables() {
		return Collections.emptyList();
	}

	public List<String> allowedFacets() {
		return null;
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


	public List<FacetApply> facets() {
		return Collections.emptyList();
	}

	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

}
