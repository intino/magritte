package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class DefinitionInjectionMixin extends ASTWrapperPsiElement {
	public DefinitionInjectionMixin(\@NotNull ASTNode node) {
		super(node);
	}
}
