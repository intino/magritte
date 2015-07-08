package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getInnerNodesOf;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public String getTarget() {
		TaraIdentifierReference identifierReference = ((TaraFacetTarget) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	public List<String> getConstraints() {
		TaraConstraint with = ((TaraFacetTarget) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map(IdentifierReference::getText).collect(Collectors.toList());
	}

	@NotNull
	public List<Node> getIncludes() {
		return unmodifiableList(getInnerNodesOf((FacetTarget) this));
	}

	@NotNull
	public List<Variable> getVariables() {
		Body body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getVariableList());
	}

	public String getQualifiedName() {
		final String target = getTarget();
		return getContainer().getQualifiedName() + "." + getContainer().getName() + "_" + getTargetName(target);
	}

	@NotNull
	private String getTargetName(String target) {
		return target.contains(".") ? target.substring(0, target.lastIndexOf(".")) : target;
	}

	public Node getContainer() {
		return getContainerNodeOf(this);
	}


	@Override
	public String toString() {
		return getQualifiedName();
	}
}
