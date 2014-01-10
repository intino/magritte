// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.metamodel.psi.TaraPrimitiveType;
import monet.tara.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraPrimitiveTypeImpl extends ASTWrapperPsiElement implements TaraPrimitiveType {

	public TaraPrimitiveTypeImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitPrimitiveType(this);
		else super.accept(visitor);
	}

}
