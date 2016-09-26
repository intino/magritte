package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.intellij.lang.psi.TaraConstraint;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.rules.CompositionRule;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public List<FacetTarget.Constraint> constraints() {
		TaraConstraint with = ((TaraFacetTargetImpl) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map((taraIdentifierReference) -> new FacetTarget.Constraint() {
			@Override
			public String name() {
				return taraIdentifierReference.getText();
			}

			@Override
			public Node node() {
				return null;
			}

			@Override
			public void node(Node node) {

			}

			@Override
			public boolean negated() {
				return false;
			}

			@Override
			public FacetTarget.Constraint clone() throws CloneNotSupportedException {
				return (FacetTarget.Constraint) super.clone();
			}
		}).collect(Collectors.toList());
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTargetImpl) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	public Node targetNode() {
		return (Node) ReferenceManager.resolveToNode(((TaraFacetTargetImpl) this).getIdentifierReference());
	}

	public CompositionRule ruleOf(Node component) {
		return null; //TODO
	}

	public <T extends Node> void targetNode(T destiny) {
	}

	public void target(String destiny) {
	}

	public void parent(Node destiny) {
	}


	public Node parent() {
		return null;//TODO
	}

	public List<Node> constraintNodes() {
		final TaraConstraint constraint = ((TaraFacetTargetImpl) this).getConstraint();
		return constraint != null ?
			constraint.getIdentifierReferenceList().stream().map(i -> ReferenceManager.resolveToNode(i)).collect(Collectors.toList()) :
			Collections.emptyList();
	}

	public boolean inherited() {
		return owner().isSub();
	}

	public void inherited(boolean inherited) {
	}


	public void constraints(List<String> constraints) {
	}

	public void constraintNodes(List<Node> constraints) {
	}

	public Node owner() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public String toString() {
		return "on " + target();
	}
}