package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class ContractMixin extends ASTWrapperPsiElement {

	public ContractMixin(ASTNode node) {
		super(node);
	}

	public String getFormattedName() {
		return this.getText().replaceAll("\\\\|-|/", "").replace("$", "Dollar").replace("%", "Ratio");
	}
}
