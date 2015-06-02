package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.Collections;
import java.util.List;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTarget) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	@NotNull
	public List<Node> includes() {
		Body body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getNodeList());
	}

	@NotNull
	public List<Variable> getVariables() {
		Body body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getVariableList());
	}

	@Override
	public String toString() {
		return "on " + target();
	}
}
