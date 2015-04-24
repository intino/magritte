package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraBody;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifierReference;

import java.util.Collection;
import java.util.Collections;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public Collection<Node> includes() {
		TaraBody body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : body.getNodeList();
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTarget) this).getIdentifierReference();

		return identifierReference == null ? "" : identifierReference.getText();
	}
}
