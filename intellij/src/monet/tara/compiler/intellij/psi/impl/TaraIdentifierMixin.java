package monet.tara.compiler.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.compiler.intellij.psi.TaraIdentifier;
import org.jetbrains.annotations.NotNull;

public class TaraIdentifierMixin extends ASTWrapperPsiElement {
	public TaraIdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier((TaraIdentifier) this);
	}
}
