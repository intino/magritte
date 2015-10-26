package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.intellij.lang.psi.TaraTypes;

public class RuleMixin extends ASTWrapperPsiElement {

	public RuleMixin(ASTNode node) {
		super(node);
	}


	public boolean isLambda() {
		return this.getFirstChild().getNode().getElementType().equals(TaraTypes.LEFT_SQUARE);
	}

	public boolean accept(Object value) {
		return false;
	}

	@Override
	public String toString() {
		return "RuleMixin{}";
	}
}