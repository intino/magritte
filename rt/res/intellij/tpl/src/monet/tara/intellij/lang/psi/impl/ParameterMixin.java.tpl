package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ParameterMixin extends ASTWrapperPsiElement {
	public ParameterMixin(\@NotNull ASTNode node) {
		super(node);
	}
}
