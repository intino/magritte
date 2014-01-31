package monet.tara.compiler.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.compiler.intellij.psi.TaraNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class TaraNamedElementImpl extends ASTWrapperPsiElement implements TaraNamedElement {
	public TaraNamedElementImpl(@NotNull ASTNode node) {
		super(node);
	}
}