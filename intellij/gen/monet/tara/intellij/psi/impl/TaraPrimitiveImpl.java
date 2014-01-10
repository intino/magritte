// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.psi.TaraPrimitive;
import monet.tara.intellij.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraPrimitiveImpl extends ASTWrapperPsiElement implements TaraPrimitive {

	public TaraPrimitiveImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitPrimitive(this);
		else super.accept(visitor);
	}

}
