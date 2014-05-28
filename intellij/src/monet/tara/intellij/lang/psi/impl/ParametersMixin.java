package monet.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.intellij.lang.psi.Parameter;
import monet.tara.lang.Modifiable;
import org.jetbrains.annotations.NotNull;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(@NotNull ASTNode node) {
		super(node);
	}

	@Modifiable(tag = "ParameterMixin.getParameters")
	public Parameter[] getParameters() {
		return null;
	}
}
