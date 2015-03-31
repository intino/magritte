package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class MeasureTypeMixin extends ASTWrapperPsiElement {

	public MeasureTypeMixin(ASTNode node) {
		super(node);
	}

	public String getFormmatedName() {
		return this.getText().replaceAll("\\\\|-|/", "").replace("$", "Dollar").replace("%", "Ratio");
	}
}
