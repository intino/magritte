package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Flags;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.*;
import tara.language.model.FacetTarget;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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

	public boolean isFacet() {
		return false;
	}

	public boolean isAbstract() {
		return false;
	}

	public boolean isRequired() {
		return false;
	}

	public boolean isSingle() {
		return false;
	}

	public boolean isNamed() {
		return false;
	}

	public boolean isFeature() {
		return false;
	}

	public boolean isFeatureInstance() {
		return false;
	}

	public boolean isFinal() {
		return false;
	}

	public boolean isTerminal() {
		return false;
	}

	public boolean isTerminalInstance() {
		return false;
	}

	public boolean intoSingle() {
		return false;
	}

	public boolean intoRequired() {
		return false;
	}

	public void plate(String plate) {
	}

	public String plate() {
		return null;
	}

	public List<Tag> annotations() {
		return destinyOfReference().getAnnotationsNode().getAnnotationList().stream().
			map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList());
	}

	public List<Tag> flags() {
		return destinyOfReference().getFlagsNode().getFlagList().stream().
			map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList());
	}

	public List<? extends tara.language.model.Node> components() {
		return null;
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String fullType() {
		return destinyOfReference().fullType();
	}

	public String type() {
		return destinyOfReference().type();
	}

	public tara.language.model.Node component(String name) {
		return null;
	}

	public List<? extends tara.language.model.Node> siblings() {
		return null;
	}

	public List<? extends tara.language.model.Node> children() {
		return null;
	}

	public List<? extends Variable> variables() {
		return null;
	}

	public List<String> allowedFacets() {
		return null;
	}

	public tara.language.model.Node parent() {
		return null;
	}

	public String parentName() {
		return null;
	}

	public boolean isAnonymous() {
		return false;
	}

	public void fullType(String type) {
	}

	public List<? extends tara.language.model.Node> referenceComponents() {
		return Collections.emptyList();
	}

	public int line() {
		return 0;
	}

	public void line(int line) {

	}

	public List<? extends Facet> facets() {
		return Collections.emptyList();
	}

	public List<? extends FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

}
