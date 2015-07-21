package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public String toString() {
		return this.qualifiedName();
	}

	public List<String> constraints() {
		TaraConstraint with = ((TaraFacetTargetImpl) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map(IdentifierReference::getText).collect(Collectors.toList());
	}

	@NotNull
	public List<Variable> variables() {
		Body body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getVariableList());
	}

	public String qualifiedName() {
		final String target = target();
		return container().qualifiedName() + "." + ((Node) container()).name() + "_" + (target.contains(".") ? target.substring(0, target.lastIndexOf(".")) : target);
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTargetImpl) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	public Node targetNode() {
		return ReferenceManager.resolveToNode(((TaraFacetTargetImpl) this).getIdentifierReference());
	}

	public <T extends tara.language.model.Node> void targetNode(T destiny) {
	}

	public void target(String destiny) {
	}

	public void constraints(List<String> constraints) {
	}


	public String type() {
		return target();
	}

	public List<Node> components() {
		return TaraPsiImplUtil.getComponentsOf((FacetTarget) this);
	}

	public Node component(String name) {
		for (Node node : components()) if (name.equals(node.name())) return node;
		return null;
	}

	public <T extends Node> boolean contains(T node) {
		return components().contains(node);
	}

	public List<Node> siblings() {
		return container().components();
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String doc() {
		return null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}


}
