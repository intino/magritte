package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.TaraConstraint;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public List<String> constraints() {
		TaraConstraint with = ((TaraFacetTargetImpl) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map(IdentifierReference::getText).collect(Collectors.toList());
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTargetImpl) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	public Node targetNode() {
		return ReferenceManager.resolveToNode(((TaraFacetTargetImpl) this).getIdentifierReference());
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

	public boolean inherited() {
		return owner().isSub();
	}

	public void inherited(boolean inherited) {
	}


	public void constraints(List<String> constraints) {
	}

	public List<Node> constraintNodes() {
		final TaraConstraint constraint = ((TaraFacetTargetImpl) this).getConstraint();
		return constraint != null ?
			constraint.getIdentifierReferenceList().stream().map(ReferenceManager::resolveToNode).collect(Collectors.toList()) :
			Collections.emptyList();
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
