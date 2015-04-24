package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class NativeNameMixin extends ASTWrapperPsiElement {

	public NativeNameMixin(ASTNode node) {
		super(node);
	}

	public String getFormattedName() {
		return this.getText().replaceAll("\\\\|-|/", "").replace("$", "Dollar").replace("%", "Ratio");
	}
}
