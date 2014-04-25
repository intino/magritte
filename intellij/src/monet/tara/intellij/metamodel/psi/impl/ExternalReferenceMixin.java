package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ExternalReferenceMixin extends ASTWrapperPsiElement {
	public ExternalReferenceMixin(@NotNull ASTNode node) {
		super(node);
	}
}
