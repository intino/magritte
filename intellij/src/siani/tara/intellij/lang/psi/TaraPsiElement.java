package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public interface TaraPsiElement extends PsiElement {

	@NotNull
	public com.intellij.lang.ASTNode getNode();

	void accept(@NotNull PsiElementVisitor visitor);

}
