package monet.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class WordMixin extends ASTWrapperPsiElement {
	public WordMixin(@NotNull ASTNode node) {
		super(node);
	}
}
