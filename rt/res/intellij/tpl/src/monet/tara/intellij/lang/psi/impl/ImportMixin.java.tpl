package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ImportMixin extends ASTWrapperPsiElement {
	public ImportMixin(\@NotNull ASTNode node) {
		super(node);
	}
}
